package com.example.lenovo_g50_70.recyclerview.Staggered;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.example.lenovo_g50_70.recyclerview.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Dog> mDogList;
    private int cons[]={R.drawable.dog1,R.drawable.dog2,R.drawable.dog3,
            R.drawable.dog4,R.drawable.dog5,R.drawable.dog6,R.drawable.dog7,
            R.drawable.dog8,R.drawable.dog9,R.drawable.dog10,
            R.drawable.dog1,R.drawable.dog2,R.drawable.dog3,
            R.drawable.dog4,R.drawable.dog5,R.drawable.dog6,R.drawable.dog7,
            R.drawable.dog8,R.drawable.dog9,R.drawable.dog10 };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListData();
        initView();
    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
        MyAdapter adapter=new MyAdapter(mDogList);
        recyclerView.setAdapter(adapter);
    }

    private void ListData() {
        mDogList=new ArrayList<Dog>();
        for(int i=0;i<cons.length;i++){
           Dog dog=new Dog("小狗"+(i+1),cons[i]);
            mDogList.add(dog);
        }
    }
}
