package com.farisafra.projectuasmobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 10;
    EditText username, password;
    Button btnLogin;
    TextView registrasi;

    com.google.android.material.floatingactionbutton.FloatingActionButton exit;

    DatabaseAkun db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new DatabaseAkun(this);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        btnLogin = (Button)findViewById(R.id.login);


        registrasi = (TextView) findViewById(R.id.registrasi);
        registrasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, AddAkun.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameKey = username.getText().toString();
                String passwordKey = password.getText().toString();
                Boolean res = db.periksaUser(usernameKey,passwordKey);
                if (usernameKey.equals("Admin") && passwordKey.equals("123")){
                    //jika login berhasil
                    Toast.makeText(getApplicationContext(), "Selamat Datang Admin "+ usernameKey,
                            Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, DashboardAdmin.class);
                    //  MainActivity.this.startActivity(intent); aktifkan jika menghapus bagian bawah
                    // Data username di ambil menuju ke activity2
                    String value = username.getText().toString();
                    intent.putExtra("yourkey1", value);
                    startActivityForResult(intent, REQUEST_CODE);
                }else if (res ==true)
                {
                    //jika gagal maka akan menjalangkan pojek di bawah
                    Intent moveToHome = new Intent(LoginActivity.this, DashboardUser.class);
                    //startActivity(moveToHome);
                    // Data username di ambil menuju ke activity2
                    String value = username.getText().toString();
                    moveToHome.putExtra("userkey1", value);
                    startActivityForResult(moveToHome, REQUEST_CODE);
                    Toast.makeText(getApplicationContext(), "Selamat Datang User "+usernameKey,Toast.LENGTH_SHORT).show();
                }else
                {
                    //jika login gagal
                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                    builder.setMessage("Username atau Password Anda salah!")
                            .setNegativeButton("Retry", null).create().show();
                }
            }
        });
    }
}