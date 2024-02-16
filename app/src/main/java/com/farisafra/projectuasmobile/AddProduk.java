package com.farisafra.projectuasmobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class AddProduk extends AppCompatActivity {

    EditText judul_input, penulis_input, deskripsi_input, harga_input,
                spek_input, kategori_input;
    Button  simpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_produk);

        judul_input = findViewById(R.id.edittext_judul);
        penulis_input = findViewById(R.id.edittext_penulis);
        deskripsi_input = findViewById(R.id.edittext_deskripsi);
        harga_input = findViewById(R.id.edittext_harga);
        spek_input = findViewById(R.id.edittext_spek);
        kategori_input = findViewById(R.id.edittext_kategori);
        simpan = findViewById(R.id.addbtn);


        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseProduk myDB = new DatabaseProduk(AddProduk.this);
                if (judul_input.getText().toString().equals("") ||
                        penulis_input.getText().toString().equals("") ||
                        deskripsi_input.getText().toString().equals("") ||
                        harga_input.getText().toString().equals("") ||
                        spek_input.getText().toString().equals("") ||
                        kategori_input.getText().toString().equals("")){
                    Toast.makeText(AddProduk.this, "Error: Data Harus Diisi Semua!", Toast.LENGTH_SHORT).show();
                }else {
                    myDB.addProduk(judul_input.getText().toString().trim(),
                            penulis_input.getText().toString().trim(),
                            deskripsi_input.getText().toString().trim(),
                            harga_input.getText().toString().trim(),
                            spek_input.getText().toString().trim(),
                            kategori_input.getText().toString().trim());
                    Intent intent = new Intent(AddProduk.this, DataProduk.class);
                    startActivity(intent);
                    finish();
                }
            }
        });


    }


}