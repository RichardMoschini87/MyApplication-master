package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textTraining = (TextView)findViewById(R.id.training);
        Bundle b = getIntent().getExtras();
        textTraining.setText("Welcome "+b.getString("nome"));
    }

    public void clicked(View view) {

    }

}
