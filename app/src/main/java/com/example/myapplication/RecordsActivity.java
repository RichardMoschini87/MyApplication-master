package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.database.dao.RecordDao;
import com.example.database.pojo.Record;
import com.example.database.pojo.Utente;

import java.util.ArrayList;
import java.util.List;

public class RecordsActivity extends AppCompatActivity {
private boolean table_flg=false;
private Utente utente;
private static  SQLiteDatabase mydb;
private static Record rec = new Record();
private String SQL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);
        mydb = this.openOrCreateDatabase("Utenti", MODE_PRIVATE, null);
        Bundle b = getIntent().getExtras();
        utente = new Utente();
        utente.setSurname(b.getString("nome"));
        rec = new Record();
        rec.setSurname(utente.getSurname());
        SQL = RecordDao.cercaRecord(rec);
        List<Record> recordList= RecordDao.resultRecord(SQL,mydb);
        TextView bestStrongman = (TextView)findViewById(R.id.bestStrongman);
     //   bestStrongman.setText("gf");


    }





}