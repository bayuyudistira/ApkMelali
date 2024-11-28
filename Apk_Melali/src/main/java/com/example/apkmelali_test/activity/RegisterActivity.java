package com.example.apkmelali_test.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.apkmelali_test.R;
import com.example.apkmelali_test.validator.RegisterValidator;
import com.example.apkmelali_test.repository.UserRepository;
import com.example.apkmelali_test.database.AppDatabase;
import com.example.apkmelali_test.entity.User;

public class RegisterActivity extends AppCompatActivity {

    private EditText emailEditText;
    private EditText usernameEditText;
    private EditText noHpEditText;
    private EditText passwordEditText;
    Button registerButton;

    // Instance of the new classes (no direct Android dependencies)
    private RegisterValidator registerValidator;
    private UserRepository userRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        emailEditText = findViewById(R.id.email);
        usernameEditText = findViewById(R.id.username);
        noHpEditText = findViewById(R.id.noHp);
        passwordEditText = findViewById(R.id.password);
        registerButton = findViewById(R.id.registerButton);

        // Initialize validator and repository
        registerValidator = new RegisterValidator();
        AppDatabase db = AppDatabase.getDatabase(this);
        userRepository = new UserRepository(db.userDao());

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString();
                String username = usernameEditText.getText().toString();
                String noHp = noHpEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                // Validasi input
                if (!registerValidator.validateInput(email, username, noHp, password)) {
                    Toast.makeText(RegisterActivity.this, "Please fill all fields correctly", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Insert data into the database
                new Thread(() -> {
                    User user = new User(email, username, noHp, password);
                    userRepository.registerUser(user);
                    runOnUiThread(() -> {
                        Toast.makeText(RegisterActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                        // Redirect to login activity
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish(); // Close RegisterActivity
                    });
                }).start();
            }
        });
    }
}
