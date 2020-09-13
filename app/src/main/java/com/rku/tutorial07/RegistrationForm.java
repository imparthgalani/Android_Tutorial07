package com.rku.tutorial07;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
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
        String  ValUsername, ValPassword;

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
        Intent intent = new Intent(RegistrationForm.this, Welcome.class);
        startActivity(intent);

    }


//    public void saveRecord(View view) {
//        Cursor cursor = databaseHelper.selectData();
//        String data = "";
//        if (cursor != null && cursor.getCount() > 0) {
//            cursor.moveToFirst();
//            do {
//                String username = cursor.getString(3);
//                String password = cursor.getString(4);
//
//                data += username + " " + password + " \n";
//
//            } while (cursor.moveToNext());
//
//            /* txtData.setText(data);*/
//
//            Intent intent = new Intent(RegistrationForm.this, Welcome.class);
//            intent.putExtra("username", data);
//            startActivity(intent);
//
//        } else {
//            Toast.makeText(this, "No Record Found", Toast.LENGTH_SHORT).show();
//        }
//    }

}