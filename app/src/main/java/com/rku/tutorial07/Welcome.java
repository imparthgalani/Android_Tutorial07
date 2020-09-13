package com.rku.tutorial07;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class Welcome extends AppCompatActivity {

    TextView txtWelcomeMessage;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        txtWelcomeMessage = findViewById(R.id.txtWelcomeMessage);
        preferences = getSharedPreferences("college", MODE_PRIVATE);
        editor = preferences.edit();

        Intent intent = getIntent();
        String userdatavalue = intent.getStringExtra("username");
        txtWelcomeMessage.setText("Welcome, " + userdatavalue);

        Toast.makeText(this, userdatavalue, Toast.LENGTH_SHORT).show();
    }

   /* public void Logout(View view) {
        //editor.putString("username","");
        editor.remove("username");
        editor.commit();
        Intent intent = new Intent(Welcome.this,Login.class);
        startActivity(intent);
        finish();
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cutsome_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnuLogout:
                editor.remove("isLogin");
                editor.commit();
                Intent intent = new Intent(Welcome.this, Login.class);
                startActivity(intent);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}