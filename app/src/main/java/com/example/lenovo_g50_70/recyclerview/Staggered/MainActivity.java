package com.example.lenovo_g50_70.recyclerview.Staggered;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.example.lenovo_g50_70.recyclerview.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.example.lenovo_g50_70.recyclerview.R.id.recyclerView;

public class MainActivity extends AppCompatActivity {
    private List<Dog> mDogList;
    private int cons[]={R.drawable.dog1,R.drawable.dog2,R.drawable.dog3,
            R.drawable.dog4,R.drawable.dog5,R.drawable.dog6,R.drawable.dog7,
            R.drawable.dog8,R.drawable.dog9,R.drawable.dog10,
            R.drawable.dog1,R.drawable.dog2,R.drawable.dog3,
            R.drawable.dog4,R.drawable.dog5,R.drawable.dog6,R.drawable.dog7,
            R.drawable.dog8,R.drawable.dog9,R.drawable.dog10 };
    private MyAdapter mAdapter;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListData();
        initView();
        initHelper();
    }

    private void initHelper() {
        ItemTouchHelper helper =new ItemTouchHelper(new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                //设置拖拽方向为以下情况的时候，实现拖拽效果
                int dragFlags =ItemTouchHelper.UP | ItemTouchHelper.DOWN |ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
                //侧滑删除,当前没有设置拖拽删除的方向
                int swipeFlags =0;
                //首先回调的方法 返回int表示是否监听该方向
                return makeMovementFlags(dragFlags,swipeFlags);
            }

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                //得到当拖拽的viewHolder的Position
                int fromPosition = viewHolder.getAdapterPosition();
                //拿到当前拖拽到的item的viewHolder
                int toPosition = target.getAdapterPosition();
                if (fromPosition < toPosition) {
                    for (int i = fromPosition; i < toPosition; i++) {
                        Collections.swap(mDogList, i, i + 1);
                    }
                } else {
                    for (int i = fromPosition; i > toPosition; i--) {
                        Collections.swap(mDogList, i, i - 1);
                    }
                }
                mAdapter.notifyItemMoved(fromPosition, toPosition);
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                //侧滑事件
                mDogList.remove(viewHolder.getAdapterPosition());
                mAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
            }

            @Override
            public boolean isLongPressDragEnabled() {
                //长按可拖拽
                return true;
            }

            @Override
            public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
                //当长按选中item的时候（拖拽开始的时候）调用
                if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
                    viewHolder.itemView.setBackgroundColor(Color.LTGRAY);
                }
                super.onSelectedChanged(viewHolder, actionState);
            }

            @Override
            public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                super.clearView(recyclerView, viewHolder);
                //当手指松开的时候（拖拽完成的时候）调用
                viewHolder.itemView.setBackgroundColor(0);
            }
        });
        //helper.startDrag();
        helper.attachToRecyclerView(mRecyclerView);
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(recyclerView);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //滑动方向,StaggeredGridLayoutManager.VERTICAL
        StaggeredGridLayoutManager manager =new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        mAdapter = new MyAdapter(mDogList);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void ListData() {
        mDogList=new ArrayList<Dog>();
        for(int i=0;i<cons.length;i++){
           Dog dog=new Dog("小狗"+(i+1),cons[i]);
            mDogList.add(dog);
        }
    }
}
