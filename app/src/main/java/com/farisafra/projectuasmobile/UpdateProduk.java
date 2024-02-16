package com.farisafra.projectuasmobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateProduk extends AppCompatActivity {

    private EditText judul_input, penulis_input, deskripsi_input, harga_input,
            spek_input, kategori_input;
    private Button update;
    private DatabaseProduk DatabaseProduk;
    private Produk produk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_produk);

        DatabaseProduk = new DatabaseProduk(this);

        judul_input = findViewById(R.id.edt_judul);
        penulis_input = findViewById(R.id.edt_penulis);
        deskripsi_input = findViewById(R.id.edt_deskripsi);
        harga_input = findViewById(R.id.edt_harga);
        spek_input = findViewById(R.id.edt_spek);
        kategori_input = findViewById(R.id.edt_kategori);
        update = findViewById(R.id.btn_update);

        Intent intent = getIntent();
        produk = (Produk) intent.getSerializableExtra("user");

        judul_input.setText(produk.getJudul());
        penulis_input.setText(produk.getPenulis());
        deskripsi_input.setText(produk.getDeskripsi());
        harga_input.setText(produk.getHarga());
        spek_input.setText(produk.getSpek());
        kategori_input.setText(produk.getKategori());

        update.setOnClickListener((View v) -> {
            DatabaseProduk.updateUser(produk.getId(),
                    judul_input.getText().toString().trim(),
                    penulis_input.getText().toString().trim(),
                    deskripsi_input.getText().toString().trim(),
                    harga_input.getText().toString().trim(),
                    spek_input.getText().toString().trim(),
                    kategori_input.getText().toString().trim());
            Intent in = new Intent(UpdateProduk.this, DataProduk.class);
            startActivity(in);
            finish();
        });

    }
}