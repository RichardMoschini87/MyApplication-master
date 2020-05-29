package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void getLogin(View view) {
    }

    public void getRegister(View view) {
        Intent intent1 = new Intent(this,RegisterActivity.class);
        startActivity(intent1);
    }
}


