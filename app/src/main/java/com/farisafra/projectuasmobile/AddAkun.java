package com.farisafra.projectuasmobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddAkun extends AppCompatActivity {

    private EditText nama_input,username_input, password_input, confir_password_input;
    private Button add_button;
    TextView login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_akun);

        nama_input = findViewById(R.id.edittext_nama);
        username_input = findViewById(R.id.edittext_username);
        password_input = findViewById(R.id.edittext_password);
        confir_password_input = findViewById(R.id.edittext_confir_pasword);
        add_button = findViewById(R.id.add);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseAkun myDB = new DatabaseAkun(AddAkun.this);
                if (nama_input.getText().toString().isEmpty()) {
                    Toast.makeText(AddAkun.this, "Error: Nama harus diisi!", Toast.LENGTH_SHORT).show();
                } else if (username_input.getText().toString().isEmpty()) {
                    Toast.makeText(AddAkun.this, "Error: Username harus diisi!", Toast.LENGTH_SHORT).show();
                } else if (password_input.getText().toString().isEmpty()) {
                    Toast.makeText(AddAkun.this, "Error: Password harus diisi!", Toast.LENGTH_SHORT).show();
                } else if (confir_password_input.getText().toString().isEmpty()) {
                    Toast.makeText(AddAkun.this, "Error: Password harus diisi!", Toast.LENGTH_SHORT).show();
                } else {
                myDB.addAkun(nama_input.getText().toString().trim(),
                        username_input.getText().toString().trim(),
                        password_input.getText().toString().trim());
                Intent intent = new Intent(AddAkun.this, DataAkun.class);
                startActivity(intent);
                finish();
            }
        }
    });
        login = (TextView)  findViewById(R.id.lojin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddAkun.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}