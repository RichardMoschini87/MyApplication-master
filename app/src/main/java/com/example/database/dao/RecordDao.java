package com.example.database.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.database.pojo.Record;

import java.util.ArrayList;
import java.util.List;

public class RecordDao {
    private static String SQL;


    public static String inserisciRecord(Record records) {
        SQL = "";
        if (records != null && records.getSurname() != null && records.getWorkout() != null && records.getRecord() != null)
            SQL = "INSERT INTO records (surname,workout,record,value) VALUES('" + records.getSurname() + "','" + records.getWorkout() + "','" + records.getRecord() + "',"+records.getValue()+")";
        return SQL;
    }

    public static String cercaRecord(Record records) {
        SQL = "";
        if (records != null)
            SQL = "SELECT surname,workout,record,value FROM records WHERE surname = '" + records.getSurname() + "'";
        return SQL;
    }

    public static List<Record> resultRecord(String SQL, SQLiteDatabase mydb) {
        List<Record> recordList = new ArrayList<>();
        Record r;

        Cursor c = mydb.rawQuery(SQL, null);
        String[] columns = {"surname",
                "workout",
                "record",
        "value"};
        Cursor cursor = mydb.query("records",
                columns,
                null,
                null,
                null,
                null,
                null
        );
        while (cursor.moveToNext()) {
            int index;
            r = new Record();

            index = cursor.getColumnIndexOrThrow("surname");
            r.setSurname(cursor.getString(index));

            index = cursor.getColumnIndexOrThrow("workout");
            r.setWorkout(cursor.getString(index));

            index = cursor.getColumnIndexOrThrow("record");
            r.setRecord(cursor.getString(index));

            index = cursor.getColumnIndexOrThrow("value");
            r.setValue(cursor.getLong(index));

            recordList.add(r);
        }
        return recordList;
    }
}
