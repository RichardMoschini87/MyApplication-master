package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.database.dao.UtenteDao;
import com.example.database.pojo.Utente;

public class RegisterActivity extends AppCompatActivity {
    Utente utente;
    UtenteDao utenteDao;
    SQLiteDatabase mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mydb = this.openOrCreateDatabase("Utenti", MODE_PRIVATE, null);
        mydb.execSQL("CREATE TABLE IF NOT EXISTS utente (id INT(6), nome VARCHAR, cognome VARCHAR, email VARCHAR, password VARCHAR, recordTime VARCHAR)");
    }

    public void getRegistrazione(View view) {
        EditText editTextEmail  = (EditText)findViewById(R.id.editTextEmail);
        EditText editTextpassword  = (EditText)findViewById(R.id.editTextPassword);
        TextView readyExist = (TextView)findViewById(R.id.readyExist);

        if(!editTextEmail.getText().toString().isEmpty() && !editTextpassword.getText().toString().isEmpty()){
            utente = new Utente();
            utente.setEmail(editTextEmail.getText().toString());
            utente.setPassword(editTextpassword.getText().toString());
            if(utente != null){
                try{
                mydb.execSQL(UtenteDao.inserisciUtente(utente));
                    Intent intent2 = new Intent(this, LoginActivity.class);
                    startActivity(intent2);
                }
                catch (Exception e){
                    readyExist.setVisibility(View.VISIBLE);
                }
            }
        }
    }
}
