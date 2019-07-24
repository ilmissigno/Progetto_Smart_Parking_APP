package com.example.smartparkingapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

public class RinnovoActivity extends AppCompatActivity {

    private int OraA;
    private double CostoTicket;
    private Socket s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rinnovo);
        final TextView targa = findViewById(R.id.targa);
        final TextView codarea = findViewById(R.id.codArea);
        final TextView orada = findViewById(R.id.ora);
        final EditText oraa = findViewById(R.id.ora3);
        Button btnCalcola = (Button)findViewById(R.id.btnCalcolaCostoOra);
        final Button btnRin = (Button)findViewById(R.id.btnRin);
        final TextView costoTotale = (TextView)findViewById(R.id.costoTotale);
        final String username = getIntent().getExtras().getString("Username");
        final String Password = getIntent().getExtras().getString("Password");
        final int IDTicket = getIntent().getExtras().getInt("IDTicket");
        final String Targa = getIntent().getExtras().getString("Targa");
        final String CodiceArea = getIntent().getExtras().getString("CodiceArea");
        final String DataScadenza = getIntent().getExtras().getString("DataScadenza");
        targa.setText(Targa);
        codarea.setText(CodiceArea);
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                orada.setText(simpleDateFormat.format(Calendar.getInstance(TimeZone.getTimeZone("Italy/Rome")).getTime()));
                /*
                try {
                    final Socket s = new Socket(InetAddress.getByName("47.53.90.210"), 8001);
                    final DataOutputStream out = new DataOutputStream(new BufferedOutputStream(s.getOutputStream()));
                    final DataInputStream in = new DataInputStream(new BufferedInputStream(s.getInputStream()));
                    out.writeUTF("dataorariosend");
                    out.flush();
                    boolean checkora = in.readBoolean();
                    if (checkora) {
                        String orario = in.readUTF();
                        orada.setText(orario);
                    } else {
                        return;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }*/
            }
        };
        timer.schedule(timerTask,0,1000);
        btnRin.setEnabled(false);
        btnCalcola.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    OraA = Integer.parseInt(oraa.getText().toString().trim());
                    final double Durata = OraA;
                    final Handler handler = new Handler();
                    Thread t = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            ProxyAutomobilista proxyAutomobilista = new ProxyAutomobilista();
                            CostoTicket = proxyAutomobilista.calcolaCosto(CodiceArea,Durata);
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    costoTotale.setText(String.valueOf(CostoTicket)+" \u20ac");
                                    btnRin.setEnabled(true);
                                }
                            });
                        }
                    });
                    t.start();
            }
        });
        btnRin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Handler handler = new Handler();
                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                       ProxyAutomobilista proxyAutomobilista = new ProxyAutomobilista();
                       proxyAutomobilista.rinnovoTicket(targa.getText().toString(),codarea.getText().toString(),IDTicket,Double.parseDouble(oraa.getText().toString().trim()),username,Password,CostoTicket,handler,RinnovoActivity.this);
                    }
                });
                t.start();
            }
        });
    }
}
