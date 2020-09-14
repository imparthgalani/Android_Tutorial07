package com.rku.tutorial07;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegistrationForm extends AppCompatActivity {
    EditText edtUsername, edtPassword;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrationform);
        this.setTitle("Registration Form");

        databaseHelper = new DatabaseHelper(this);

        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);

    }

    public void saveRecord(View view) {
        String ValUsername, ValPassword;

        ValUsername = edtUsername.getText().toString();
        ValPassword = edtPassword.getText().toString();

        /*------------------- Validation Start ---------------------*/

        if (TextUtils.isEmpty(ValUsername)) {
            edtUsername.setError("Please Enter Email Address");
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(ValUsername).matches()) {
            Toast.makeText(RegistrationForm.this, "Email address format is not valid", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(ValPassword)) {
            edtPassword.setError("Password is Required.");
            return;
        }

        if (ValPassword.length() < 6) {
            edtPassword.setError("Password Must be >= 6 Characters");
            return;
        }

        /*------------------- Validation End ---------------------*/

        if (databaseHelper.insertData(ValUsername, ValPassword)) {
            Toast.makeText(this, "User Created", Toast.LENGTH_SHORT).show();
            edtUsername.setText("");
            edtPassword.setText("");
        }

        Intent intent = new Intent(RegistrationForm.this, Login.class);
        startActivity(intent);
    }

    public void btnLogin(View view) {
        Intent intent = new Intent(RegistrationForm.this, Login.class);
        startActivity(intent);
    }
}