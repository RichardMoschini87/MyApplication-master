package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.database.pojo.Utente;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase mydb;
    Utente utente ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydb = this.openOrCreateDatabase("Utenti", MODE_PRIVATE, null);
        mydb.execSQL("CREATE TABLE IF NOT EXISTS records (surname VARCHAR, workout VARCHAR, record VARCHAR, value BIGINT)");
        TextView textTraining = (TextView)findViewById(R.id.training);
        Bundle b = getIntent().getExtras();
        b.getString("nome");
        utente = new Utente();
        utente.setSurname(b.getString("nome"));
        textTraining.setText("Welcome "+ b.getString("nome")+"\n are you ready ?");

    }


    public void getStrongMan(View view) {
        Intent intent2 = new Intent(this, StrongManActivity.class);
        intent2.putExtra("nome",utente.getSurname());
        startActivity(intent2);
    }

    public void getRecords(View view) {
        Intent intent2 = new Intent(this, RecordsActivity.class);
        intent2.putExtra("nome",utente.getSurname());
        startActivity(intent2);
    }
}
