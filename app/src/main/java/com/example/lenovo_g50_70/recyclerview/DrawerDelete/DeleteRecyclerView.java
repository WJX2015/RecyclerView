package com.example.lenovo_g50_70.recyclerview.DrawerDelete;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.Scroller;

import com.example.lenovo_g50_70.recyclerview.R;

/**
 * Created by lenovo-G50-70 on 2017/4/19.
 */
public class DeleteRecyclerView extends RecyclerView {

    private Context mContext;
    private VelocityTracker velocityTracker;
    private Scroller scroller;
    private View itemView;
    private int position;
    private ImageView imageView;

    //4种状态,分别是关闭、正在关闭、正在打开、打开
    private int status =CLOSE;
    public static final int CLOSE=0;
    public static final int CLOSING=1;
    public static final int OPENING=2;
    public static final int OPEN=3;
    private OnItemClickListener listener;

    //滑动速度临界值
    public static final int VELOCITY=100;
    //默认的滑动时间
    public static final int DEFAULT_TIME=200;
    //删除图片的宽度
    private int maxLength;
    //onTouch点的位置
    private int LastX;
    private int LastY;
    //是否水平滑动
    private boolean isHorMoving;
    //是否垂直滑动
    private boolean isVerMoving;
    //是否开始滑动
    private boolean isStartScroll;

    public DeleteRecyclerView(Context context) {
        this(context,null);
    }

    public DeleteRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext=context;
        //LinearInterpolator,动画的变化率
        scroller =new Scroller(mContext,new LinearInterpolator());
        //构建滑动速度检测的类,顾名思义即速度跟踪
        velocityTracker =VelocityTracker.obtain();
    }

    public DeleteRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);

    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        velocityTracker.addMovement(e);
        //获取当前坐标
        int x = (int) e.getX();
        int y = (int) e.getY();
        switch (e.getAction()){
            case MotionEvent.ACTION_DOWN:
                //'删除图片'还没打开的状态
                if(status==CLOSE){
                    //寻找对应坐标下的View
                    View view =findChildViewUnder(x,y);
                    if(view == null){
                        return false;
                    }
                    //通过baseViewHolder获取对应的子View
                    BaseViewHolder viewHolder = (BaseViewHolder) getChildViewHolder(view);
                    itemView =viewHolder.getView(R.id.item_layout);
                    position =viewHolder.getAdapterPosition();
                    imageView =viewHolder.getView(R.id.item_delete);
                    maxLength =imageView.getWidth();

                    imageView.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            itemView.scrollTo(0,0);
                            status=CLOSE;
                            if(listener!=null){
                                listener.onDeleteClick(position);
                            }
                        }
                    });
                    //当'删除图片'已经完全显示的时候
                }else if(status == OPEN){
                    //从当前view的偏移点itemView.getScrollX(),位置maxLength长度
                    //时间DEFAULT_TIMEms,向左移动为正数
                    scroller.startScroll(itemView.getScrollX(),0,-maxLength,0,DEFAULT_TIME);
                    //刷新下一帧动画
                    invalidate();
                    status =CLOSE;
                    return false;
                }else {
                    return false;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                //获取上次的落点与当前的坐标之间的差值
                int dx =LastX -x;
                int dy =LastY -y;

                int scrollX =itemView.getScrollX();
                //水平滑动距离大于垂直距离
                if(Math.abs(dx)>Math.abs(dy)){
                    isHorMoving =true;
                    //向左滑动，直至显示删除图片,向左滑动的最大距离不超过删除图片de宽度
                    if(scrollX+dx>=maxLength){
                        itemView.scrollTo(maxLength,0);
                        return true;
                    }else if(scrollX+dx<=0){
                        //向右滑动,直至删除图片不显示,向右滑动的最大距离不超过初始位置
                        itemView.scrollTo(0,0);
                        return true;
                    }
                    //如果在图片还未完全显示的状态下，那么手指滑动多少，图片就移动多少
                    itemView.scrollBy(dx,0);
                }
                break;
            case MotionEvent.ACTION_UP:
                if(!isHorMoving && !isVerMoving && listener!=null){
                    listener.onItemClick(itemView,position);
                }
                isHorMoving=false;
                velocityTracker.computeCurrentVelocity(1000);//计算手指滑动的速度
                float xVelocity =velocityTracker.getXVelocity();//水平方向速度
                float yVelocity =velocityTracker.getYVelocity();//垂直方向速度
                int upScrollX =itemView.getScrollX();
                int deltaX =0;
                //向右滑动速度为正数
                //滑动速度快的状态下抬起手指，计算所需偏移量
                if(Math.abs(xVelocity)>Math.abs(yVelocity) && Math.abs(xVelocity)>=VELOCITY){
                    //向右隐藏
                    if(xVelocity>=VELOCITY){
                        deltaX =-upScrollX;
                        status =CLOSING;
                    }else if(xVelocity <= -VELOCITY){
                        deltaX =maxLength -upScrollX;
                        status =OPENING;
                    }else {
                        //滑动速度慢的状态下抬起手指，如果滑动距离1/2的图片宽度，计算偏移量
                        //不够的话恢复原点
                        if(upScrollX>=maxLength/2){
                            deltaX =maxLength-upScrollX;
                            status =OPENING;
                        }else {
                            deltaX =-upScrollX;
                            status =CLOSING;
                        }
                    }
                    scroller.startScroll(upScrollX,0,deltaX,0,DEFAULT_TIME);
                    isStartScroll =true;
                    invalidate();
                    velocityTracker.clear();
                    break;
                }
        }
        LastX=x;
        LastY=y;
        return super.onTouchEvent(e);
    }

    @Override
    public void computeScroll() {
        //滚动是否完成，true表示还未完成
        if(scroller.computeScrollOffset()){
            itemView.scrollTo(scroller.getCurrX(),scroller.getCurrY());
            invalidate();
        }else if(isStartScroll){
            //有滑动，并在滑动结束的时候
            isStartScroll =false;
            if(status ==CLOSING){
                status =CLOSE;
            }
            if(status == OPENING){
                status =OPEN;
            }
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        velocityTracker.recycle();
        super.onDetachedFromWindow();
    }

    @Override
    public void onScrollStateChanged(int state) {
        super.onScrollStateChanged(state);
        isVerMoving=state==SCROLL_STATE_DRAGGING;
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener =listener;
    }
}
