package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.Constants;
import com.example.database.dao.RecordDao;
import com.example.database.pojo.Record;
import com.example.database.pojo.Utente;

public class StrongManActivity extends AppCompatActivity {
private Chronometer cronometroStart;
private Chronometer cronometroDuration;
private TextView cronometroRest;
private ImageView imgPull;
private ImageView imgFlex;
private CountDownTimer countDownTimer;
private long timeRestMill= 90000;//un minuto e mezzo in millesimi
private boolean runningDuration;
private boolean running;
private boolean runningRest;
public static  String records="";
Button buttonStart;
Button buttonRest;
private Utente utente;
SQLiteDatabase mydb;



    public static int numberOfRest=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_strong_man);
        Bundle b = getIntent().getExtras();
        utente  = new Utente();
        utente.setSurname(b.getString("nome"));
        mydb = this.openOrCreateDatabase("Utenti", MODE_PRIVATE, null);
        cronometroStart = findViewById(R.id.cronometro);
        cronometroDuration = findViewById(R.id.cronometroDuration);
        cronometroDuration.setBase(SystemClock.elapsedRealtime());
        buttonStart = findViewById(R.id.buttonStart);
        buttonRest = findViewById(R.id.buttonRest);
        cronometroRest = (TextView) findViewById(R.id.cronometroRest);
        imgPull = findViewById(R.id.imgPull);
        imgFlex = findViewById(R.id.imgFlex);
        buttonRest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start();
            }
        });
    }

    public void start() {
        if(!runningRest)
            startRest();
    }

    public void startRest() {
        cronometroStart.setVisibility(View.INVISIBLE);
        cronometroRest.setVisibility(View.VISIBLE);
        buttonRest.setVisibility(View.INVISIBLE);
        numberOfRest = numberOfRest+1;
        countDownTimer = new CountDownTimer(timeRestMill,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                String v = String.format("%02d", millisUntilFinished/60000);
                int va = (int)( (millisUntilFinished%60000)/1000);
                cronometroRest.setText("" +v+":"+String.format("%02d",va));
            }
            @Override
            public void onFinish() {
                cronometroRest.setVisibility(View.INVISIBLE);
                cronometroStart.setVisibility(View.VISIBLE);
                buttonRest.setVisibility(View.VISIBLE);
                running = false;
            }
        }.start();
    }

    public void startCronometro(View view) {
        if(!running){
            cronometroStart.start();
            cronometroStart.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
                @Override
                public void onChronometerTick(Chronometer chronometer) {
                //    minuti =  ( ( SystemClock . elapsedRealtime ( )  - matchChron . getBase ( ) )  /  1000 )  /  60 ; questo per calcolare i minuti
                    long  seconds =  ( ( SystemClock. elapsedRealtime ( )  - cronometroStart . getBase ( ) )  /  1000 )  %  60 ;
                   if(seconds > 30){
                       imgFlex.setVisibility(View.INVISIBLE);
                       imgPull.setVisibility(View.VISIBLE);
                   }
                   else if (seconds < 30){
                       imgFlex.setVisibility(View.VISIBLE);
                       imgPull.setVisibility(View.INVISIBLE);
                   }
                }
            });
            running = true;
            if(!runningDuration){
            cronometroDuration.start();
            runningDuration = true;
            }
            buttonStart.setVisibility(View.INVISIBLE);
            buttonRest.setVisibility(View.VISIBLE);
        }
    }

    public void stopCronometro(View view) {
        cronometroStart.setVisibility(View.INVISIBLE);
        cronometroRest.setVisibility(View.INVISIBLE);
        buttonRest.setVisibility(View.INVISIBLE);
        buttonStart.setVisibility(View.INVISIBLE);
        imgFlex.setVisibility(View.INVISIBLE);
        imgPull.setVisibility(View.INVISIBLE);
        cronometroStart.stop();
        records = cronometroDuration.getContentDescription().toString();
        long time = SystemClock.elapsedRealtime() - cronometroDuration.getBase();
        records += " "+numberOfRest +" di Rest";
        Record rec = new Record();
        rec.setValue(time);
        rec.setRecord(records);
        rec.setWorkout(Constants.WORKOUT);
        rec.setSurname(utente.getSurname());
        try {
            mydb.execSQL(RecordDao.inserisciRecord(rec));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Intent intent2 = new Intent(this, MainActivity.class);
        startActivity(intent2);
    }


}