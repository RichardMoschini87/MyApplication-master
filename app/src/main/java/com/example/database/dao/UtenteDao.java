package com.example.database.dao;

import com.example.database.pojo.Utente;

public class UtenteDao {
    private Utente utente;
    private static String SQL;

    public UtenteDao() {
        super();
        this.utente = utente;
    }

    public static  String cercaUtente(Utente utente) {
        if (utente != null)
            SQL = "";
        return SQL;
    }

    public static   String inserisciUtente(Utente utente) {
        SQL = "";
        if (utente != null && utente.getEmail() != null && utente.getPassword() != null)
            SQL = "INSERT INTO utente (email,password) VALUES('" + utente.getEmail() + "','" + utente.getPassword() + "')";
        return SQL;
    }

}
