package com.example.crud_api;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class SignUp extends AppCompatActivity {

    private AppDatabase appdatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        appdatabase = new AppDatabase(this);

        TextView move_login = findViewById(R.id.tv_sudah);
        TextInputEditText name = findViewById(R.id.fullname);
        TextInputEditText usn = findViewById(R.id.username);
        TextInputEditText email = findViewById(R.id.email);
        TextInputEditText pass = findViewById(R.id.password);
        Button register = findViewById(R.id.btn_register);
        move_login.setOnClickListener(v -> {
            Intent intent = new Intent(SignUp.this, Login.class);
            startActivity(intent);
        });
        register.setOnClickListener(v -> {
            if (name.getText().toString().isEmpty() || usn.getText().toString().isEmpty() ||
                    email.getText().toString().isEmpty() || pass.getText().toString().isEmpty()) {
                Toast.makeText(this, "field tidak boleh kosong", Toast.LENGTH_LONG).show();
            } else {
                SQLiteDatabase create_db = appdatabase.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(AppDatabase.COLUMN_NAME, name.getText().toString());
                values.put(AppDatabase.COLUMN_USERNAME, usn.getText().toString());
                values.put(AppDatabase.COLUMN_EMAIL, email.getText().toString());
                values.put(AppDatabase.COLUMN_PASSWORD, pass.getText().toString());
                long newRowId = create_db.insert(AppDatabase.TABLE_ACCOUNTS, null, values);
                if (newRowId > 0) {
                    Toast.makeText(this, "akun berhasil dibuat", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(SignUp.this, Login.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "akun gagal dibuat", Toast.LENGTH_LONG).show();

                    create_db.close();
                }
            }
        });
    }
}
