package com.farisafra.projectuasmobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class DataAkun extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_button;
    ImageView empty_imageview;
    TextView no_data;
    ArrayList<Akun> AkunArrayList;

    DatabaseAkun DatabaseAkun;
    AkunAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_akun);

        recyclerView = findViewById(R.id.RV);
        adapter = new AkunAdapter(this);
        DatabaseAkun = new DatabaseAkun(this);
        AkunArrayList = DatabaseAkun.getAllUsers();
        adapter.setListAkun(AkunArrayList);

        add_button = findViewById(R.id.fab_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DataAkun.this, AddAkun.class);
                startActivity(intent);
            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(DataAkun.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        AkunArrayList = DatabaseAkun.getAllUsers();
        adapter.setListAkun(AkunArrayList);
        adapter.notifyDataSetChanged();
    }

}