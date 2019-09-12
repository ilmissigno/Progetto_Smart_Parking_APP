package com.example.smartparkingapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

public class AcquistaTicket extends AppCompatActivity {

    private String Targa;
    private String CodiceArea;
    private String OraDa;
    private int OraA;
    private double CostoTicket;
    private Socket s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_acquista_ticket);
        final Spinner listaauto = findViewById(R.id.listaAuto);
        final EditText codicearea = (EditText)findViewById(R.id.codiceArea);
        final TextView orada = findViewById(R.id.ora);
        final EditText oraa = findViewById(R.id.ora2);
        Button btnCalcola = (Button)findViewById(R.id.btnCalcolaCostoOra);
        final Button btnAcquista = (Button)findViewById(R.id.btnAcquista);
        final TextView costoTotale = (TextView)findViewById(R.id.costoTotale);
        Bundle bundle = getIntent().getExtras();
        final String Username = bundle.getString("username");
        final String Password = bundle.getString("password");
        final Handler handler = new Handler();
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    ProxyAutomobilista proxyAutomobilista = new ProxyAutomobilista();
                    final ArrayList<String> auto = proxyAutomobilista.getListaAuto(Username,Password);
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                    //Set auto to Spinner
                                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(AcquistaTicket.this,R.layout.spinner_item,auto);
                                arrayAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
                                    listaauto.setAdapter(arrayAdapter);
                            }
                        });
                    }
            });
            t.start();
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
                }
                */
            }
        };
        timer.schedule(timerTask,0,1000);
            btnAcquista.setEnabled(false);
            btnCalcola.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (oraa.getText().toString().isEmpty()||codicearea.getText().toString().isEmpty()) {
                        Toast.makeText(AcquistaTicket.this,"Inserire tutti i campi",Toast.LENGTH_LONG).show();
                        return;
                    }else {
                        OraA = Integer.parseInt(oraa.getText().toString().trim());
                        final double Durata = OraA;
                        CodiceArea = codicearea.getText().toString().trim();
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
                                            btnAcquista.setEnabled(true);
                                        }
                                    });
                            }
                        });
                        t.start();
                    }
                }
            });
            btnAcquista.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final Handler handler = new Handler();
                    Thread t = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            ProxyAutomobilista proxyAutomobilista = new ProxyAutomobilista();
                            proxyAutomobilista.acquistaTicket(listaauto,CodiceArea,Double.parseDouble(oraa.getText().toString().trim()),CostoTicket,Username,Password,handler,AcquistaTicket.this);
                        }
                    });
                    t.start();
                }
            });
    }
}
