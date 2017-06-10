package com.example.lenovo_g50_70.recyclerview.Staggered;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo_g50_70.recyclerview.R;

import java.util.List;

/**
 * Created by lenovo-G50-70 on 2017/3/3.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{
    private List<Dog> mDogList;
    static class MyViewHolder extends RecyclerView.ViewHolder{
        View dogView;
        ImageView dogImage;
        TextView  dogName;
        public MyViewHolder(View view){
            super(view);
            dogView=view;
            dogImage= (ImageView) view.findViewById(R.id.iv);
            dogName = (TextView) view.findViewById(R.id.tv);
        }
    }
    public MyAdapter(List<Dog> dogList){
        mDogList=dogList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dog_item,parent,false);
        final MyViewHolder holder=new MyViewHolder(view);
        holder.dogView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position =holder.getAdapterPosition();
                Dog dog =mDogList.get(position);
                Toast.makeText(v.getContext(),dog.getName(),Toast.LENGTH_SHORT).show();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Dog dog =mDogList.get(position);
        holder.dogImage.setImageResource(dog.getImage());
        holder.dogName.setText(dog.getName());
    }

    @Override
    public int getItemCount() {
        return mDogList.size();
    }


}
