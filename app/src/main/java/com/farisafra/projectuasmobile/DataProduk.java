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

public class DataProduk extends AppCompatActivity {

    DatabaseProduk DatabaseProduk;
    RecyclerView recyclerView;
    FloatingActionButton add_button;
    ImageView empty_imageview;
    TextView no_data;
    ArrayList<Produk> ProdukArrayList;

    ProdukAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_produk);

        recyclerView = findViewById(R.id.RV2);
        adapter = new ProdukAdapter(this);
        DatabaseProduk = new DatabaseProduk(this);
        ProdukArrayList = DatabaseProduk.getAllUsers();
        adapter.setListProduk(ProdukArrayList);

        add_button = findViewById(R.id.fab_button2);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DataProduk.this, AddProduk.class);
                startActivity(intent);
            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(DataProduk.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }


    @Override
    protected void onResume() {
        super.onResume();
        ProdukArrayList = DatabaseProduk.getAllUsers();
        adapter.setListProduk(ProdukArrayList);
        adapter.notifyDataSetChanged();
    }

}