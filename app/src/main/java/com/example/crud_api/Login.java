package com.example.crud_api;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class Login extends AppCompatActivity {

    private AppDatabase appdatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        appdatabase = new AppDatabase(this);


        TextInputEditText usn = findViewById(R.id.tiet_username);
        TextInputEditText pass = findViewById(R.id.tiet_password);
        Button login = findViewById(R.id.btn_login);
        TextView move_signup = findViewById(R.id.tv_belum);
        move_signup.setOnClickListener(v -> {
            Intent intent = new Intent(Login.this, SignUp.class);
            startActivity(intent);
        });

        login.setOnClickListener(v -> {
            if(usn.getText().toString().isEmpty() || pass.getText().toString().isEmpty()){
                Toast.makeText(this,"Password/Username tidak boleh kosong",Toast.LENGTH_LONG).show();
            } else {
                SQLiteDatabase read_db = appdatabase.getReadableDatabase();
                Cursor cursor = read_db.query(
                        AppDatabase.TABLE_ACCOUNTS,
                        null,
                        AppDatabase.COLUMN_USERNAME + "=? AND " + AppDatabase.COLUMN_PASSWORD + "=?",
                        new String[]{usn.getText().toString(), pass.getText().toString()},
                        null, null, null
                );

                boolean isValid = cursor.moveToFirst();

                cursor.close();
                read_db.close();
                if(isValid){
                    Intent intent_masuk = new Intent(Login.this, MainActivityNav.class);
                    startActivity(intent_masuk);
                    AppPreferences appPreferences = new AppPreferences(Login.this);
                    appPreferences.setCurrentAccountId(usn.getText().toString());
                }
                else{
                    Toast.makeText(this,"Password/Username salah",Toast.LENGTH_LONG).show();
                }

            }
        });



    }
}