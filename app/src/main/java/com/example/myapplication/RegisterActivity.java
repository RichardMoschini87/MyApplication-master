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
        mydb.execSQL("CREATE TABLE IF NOT EXISTS utente2 (surname VARCHAR, email VARCHAR, password VARCHAR)");
    }

    public void getRegistrazione(View view) {
        EditText editTextTextPersonName = (EditText) findViewById(R.id.editTextTextPersonName);
        EditText editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        EditText editTextpassword = (EditText) findViewById(R.id.editTextPassword);
        TextView readyExist = (TextView) findViewById(R.id.readyExist);

        if (!editTextTextPersonName.getText().toString().isEmpty() && !editTextEmail.getText().toString().isEmpty() && !editTextpassword.getText().toString().isEmpty()) {
            utente = new Utente();
            utente.setSurname(editTextTextPersonName.getText().toString());
            utente.setEmail(editTextEmail.getText().toString());
            utente.setPassword(editTextpassword.getText().toString());

            if (utente != null && utente.getSurname() != null && !utente.getSurname().contains("Surname")
                    && utente.getEmail() != null && utente.getPassword() != null && !utente.getEmail().contains("Email")) {
                try {
                    mydb.execSQL(UtenteDao.inserisciUtente(utente));
                    Intent intent2 = new Intent(this, LoginActivity.class);
                    startActivity(intent2);
                } catch (Exception e) {
                    readyExist.setVisibility(View.VISIBLE);
                }
            } else {
                readyExist.setVisibility(View.VISIBLE);
            }
        }
    }
}
