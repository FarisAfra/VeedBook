package com.farisafra.projectuasmobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class DashboardAdmin extends AppCompatActivity {

    ImageButton lihatakun,tambahakun,updateakun,lihatproduk,tambahproduk,updateproduk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_admin);

        lihatakun = findViewById(R.id.lihatakun);
        tambahakun = findViewById(R.id.tambahakun);
        updateakun = findViewById(R.id.updateakun);
        lihatproduk = findViewById(R.id.lihatbuku);
        tambahproduk = findViewById(R.id.tambahbuku);
        updateproduk = findViewById(R.id.updatebuku);

        lihatakun.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent akun = new Intent(DashboardAdmin.this, DataAkun.class);
            startActivity(akun);
        }
    });

        tambahakun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent regis = new Intent(DashboardAdmin.this, AddAkun.class);
                startActivity(regis);
            }
        });

        updateakun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent upakun = new Intent(DashboardAdmin.this, DataAkun.class);
                startActivity(upakun);
            }
        });

        lihatproduk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent produk = new Intent(DashboardAdmin.this, DataProduk.class);
                startActivity(produk);
            }
        });

        tambahproduk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tampro = new Intent(DashboardAdmin.this, AddProduk.class);
                startActivity(tampro);
            }
        });

        updateproduk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent updak = new Intent(DashboardAdmin.this, DataAkun.class);
                startActivity(updak);
            }
        });
    }
}