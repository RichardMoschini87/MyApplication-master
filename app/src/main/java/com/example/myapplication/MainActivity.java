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
    }

    public void clicked(View view) {

    }


    public void textChange(View view) {



      TextView textTraining = (TextView)findViewById(R.id.training);
        if(!textTraining.getText().toString().contains("ciao")){
        textTraining.setText("ciao fre");
        }
        else{
            textTraining.setText("Training");
        }
    }
}
