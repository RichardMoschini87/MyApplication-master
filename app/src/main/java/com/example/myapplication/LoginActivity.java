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

public class LoginActivity extends AppCompatActivity {
    Utente utente;
    UtenteDao utenteDao;
    SQLiteDatabase mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mydb = this.openOrCreateDatabase("Utenti", MODE_PRIVATE, null);
    }

    public void getLogin(View view) {
        EditText editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        EditText editTextpassword = (EditText) findViewById(R.id.editTextPassword);
        TextView readyExist2 = (TextView) findViewById(R.id.readyExist);
        if (!editTextEmail.getText().toString().isEmpty() && !editTextpassword.getText().toString().isEmpty()) {
            utente = new Utente();
            utente.setEmail(editTextEmail.getText().toString());
            utente.setPassword(editTextpassword.getText().toString());
            if (utente != null && utente.getEmail() != null && utente.getPassword() != null) {
                try {
                    utente = UtenteDao.resultUtente(UtenteDao.cercaUtente(utente),mydb);
                    Intent intent2 = new Intent(this, MainActivity.class);
                    intent2.putExtra("nome",utente.getSurname());
                    startActivity(intent2);
                } catch (Exception e) {
                    readyExist2.setVisibility(View.VISIBLE);
                }
            } else {
                readyExist2.setVisibility(View.VISIBLE);
            }
        }
        }

    public void getRegister(View view) {
        Intent intent1 = new Intent(this,RegisterActivity.class);
        startActivity(intent1);
    }
}


