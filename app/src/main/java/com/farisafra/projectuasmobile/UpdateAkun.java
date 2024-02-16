package com.farisafra.projectuasmobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateAkun extends AppCompatActivity {

    private EditText etNama, etUsername, etPassword;
    private Button btnSave;
    private DatabaseAkun DatabaseAkun;
    private Akun akun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_akun);

        DatabaseAkun = new DatabaseAkun(this);

        etNama = findViewById(R.id.edt_nama);
        etUsername = findViewById(R.id.edt_username);
        etPassword = findViewById(R.id.edt_password);
        btnSave = findViewById(R.id.btn_submit);

        Intent intent = getIntent();
        akun = (Akun) intent.getSerializableExtra("user");

        etNama.setText(akun.getNama());
        etUsername.setText(akun.getUsername());
        etPassword.setText(akun.getPassword());

        btnSave.setOnClickListener((View v) -> {
            DatabaseAkun.updateUser(akun.getId(),
                    etNama.getText().toString().trim(),
                    etUsername.getText().toString().trim(),
                    etPassword.getText().toString().trim());
            Intent in = new Intent(UpdateAkun.this, DataAkun.class);
            startActivity(in);
            finish();
        });
    }
}