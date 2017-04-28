package com.example.lenovo_g50_70.recyclerview.DrawerDelete;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.lenovo_g50_70.recyclerview.R;
import com.example.lenovo_g50_70.recyclerview.Staggered.MainActivity;

import java.util.ArrayList;

public class DeleteActivity extends AppCompatActivity {

    private DeleteRecyclerView deleteRecyclerView;
    private ArrayList<String> stringArrayList;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        button = (Button) findViewById(R.id.intent);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(DeleteActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        deleteRecyclerView = (DeleteRecyclerView) findViewById(R.id.recyclerDelete);
        stringArrayList =new ArrayList<>();
        for(int i=0;i<10;i++){
            stringArrayList.add(i+"");
        }

        final DeleteAdapter adapter =new DeleteAdapter(this,stringArrayList);
        deleteRecyclerView.setAdapter(adapter);
        deleteRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        deleteRecyclerView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(DeleteActivity.this,"*"+stringArrayList.get(position),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDeleteClick(int position) {
                adapter.removeItem(position);
            }
        });
    }
}
