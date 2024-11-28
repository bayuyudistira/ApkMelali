package com.example.apkmelali_test.validator;

import java.util.regex.Pattern;

public class RegisterValidator {

    // Regular expression for validating email format
    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");

    // Fungsi untuk validasi input
    public boolean validateInput(String email, String username, String noHp, String password) {
        if (email.isEmpty() || username.isEmpty() || noHp.isEmpty() || password.isEmpty()) {
            return false;
        }

        // Validasi email menggunakan regex standar Java
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            return false;
        }

        if (password.length() < 8) {
            return false;
        }

        return true; // Semua validasi berhasil
    }
}


