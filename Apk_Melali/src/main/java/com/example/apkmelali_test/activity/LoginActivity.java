package com.example.apkmelali_test.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.apkmelali_test.handler.LoginHandler;
import com.example.apkmelali_test.R;
import com.example.apkmelali_test.database.AppDatabase;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameEditText, passwordEditText;
    private Button loginButton;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);

        db = AppDatabase.getDatabase(this);

        loginButton.setOnClickListener(v -> {
            String username = usernameEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            new Thread(() -> {
                // Membuat LoginHandler dengan UserDao dan SharedPreferences
                LoginHandler loginHandler = new LoginHandler(
                        db.userDao(),
                        getSharedPreferences("user_prefs", MODE_PRIVATE)
                );

                // Proses login menggunakan LoginHandler
                boolean success = loginHandler.login(username, password);

                runOnUiThread(() -> {
                    if (success) {
                        // Login berhasil, pindah ke Dashboard_Activity
                        Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        // Login gagal, tampilkan pesan kesalahan
                        Toast.makeText(LoginActivity.this, "Login failed. Check your username and password.", Toast.LENGTH_SHORT).show();
                    }
                });
            }).start();
        });

    }
}
