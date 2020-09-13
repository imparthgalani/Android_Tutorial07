package com.rku.tutorial07;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {


    private EditText edtUsername, edtPassword;
    private Button btnLogin;
  //  CheckBox rememberMe;
    DatabaseHelper databaseHelper;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.setTitle("Login Form");

        databaseHelper = new DatabaseHelper(this);

        edtUsername = (EditText) findViewById(R.id.edtUsername);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);

       // rememberMe = findViewById(R.id.rememberMe);
        preferences = getSharedPreferences("college", MODE_PRIVATE);
        editor = preferences.edit();

        String userPreference = preferences.getString("isLogin", "");
        String namePreference = preferences.getString("username", "");
        if (!userPreference.equals("")) {
            Intent intent = new Intent(Login.this, Welcome.class);
            intent.putExtra("username", namePreference);
            startActivity(intent);
            finish();
        }

        String username = preferences.getString("username", "");
        edtUsername.setText(username);
        final String password = preferences.getString("password", "");
        edtPassword.setText(password);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ValUsername = edtUsername.getText().toString();
                String ValPassword = edtPassword.getText().toString();
                String ValLogin = btnLogin.getText().toString();
                Log.i("Login Screen", "In Onclick");

                if (!Patterns.EMAIL_ADDRESS.matcher(ValUsername).matches()) {
                    edtUsername.setError("Email address format is not valid");
                    return;
                }

                if (TextUtils.isEmpty(ValPassword)){
                    edtPassword.setError("Password is Required.");
                    return;
                }

                if (ValPassword.length() <6){
                    edtPassword.setError("Password Must be >= 6 Characters");
                    return;
                }


                Boolean res = databaseHelper.checkUser(ValUsername, ValPassword);

                if(res == true) {

                    /*   -----SHARE PREFERENCES-----*/

                    editor.putString("username", ValUsername);
                    editor.putString("password", ValPassword);
                    editor.putString("isLogin", ValLogin);
                    editor.commit();

                    /* -----END SHARE PREFERENCES----- */

                    Intent HomePage = new Intent(Login.this,Welcome.class);
                    HomePage.putExtra("username", ValUsername);
                    startActivity(HomePage);
                    Toast.makeText(Login.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(Login.this,"Username or Password is wrong.",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void btnSignUp(View view) {
        Intent intent = new Intent(Login.this, RegistrationForm.class);
        startActivity(intent);
        finish();
    }
}