package com.example.smartparkingapp;

import android.content.DialogInterface;
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
import java.sql.Time;
import java.util.Date;

public class AcquistaTicket extends AppCompatActivity {

    private String Targa;
    private String CodiceArea;
    private int OraDa;
    private int OraA;
    private double CostoTicket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acquista_ticket);
        final EditText targa = (EditText)findViewById(R.id.targa);
        final EditText codicearea = (EditText)findViewById(R.id.codiceArea);
        final EditText orada = findViewById(R.id.ora);
        final EditText oraa = findViewById(R.id.ora2);
        Button btnCalcola = (Button)findViewById(R.id.btnCalcolaCostoOra);
        final Button btnAcquista = (Button)findViewById(R.id.btnAcquista);
        final TextView costoTotale = (TextView)findViewById(R.id.costoTotale);
        btnAcquista.setEnabled(false);
        btnCalcola.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (targa.getText().toString().isEmpty()||orada.getText().toString().isEmpty()||oraa.getText().toString().isEmpty()||codicearea.getText().toString().isEmpty()) {
                    Toast.makeText(AcquistaTicket.this,"Inserire tutti i campi",Toast.LENGTH_LONG).show();
                    return;
                }else {
                    CodiceArea = codicearea.getText().toString().trim();
                    OraDa = Integer.parseInt(orada.getText().toString().trim());
                    OraA = Integer.parseInt(oraa.getText().toString().trim());
                    if (OraDa > OraA) {
                       Toast.makeText(AcquistaTicket.this,"Inserire un orario corretto",Toast.LENGTH_LONG).show();
                       return;
                    } else {
                        final Handler handler = new Handler();
                        Thread t = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Socket s = new Socket(InetAddress.getByName("10.0.2.2"), 8000);
                                    DataOutputStream out = new DataOutputStream(new BufferedOutputStream(s.getOutputStream()));
                                    out.writeUTF("costoticketsend");
                                    out.flush();
                                    out.writeUTF(CodiceArea);
                                    out.flush();
                                    DataInputStream in = new DataInputStream(new BufferedInputStream(s.getInputStream()));
                                    CostoTicket = in.readDouble();
                                    handler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            //Calcolo l'importo totale facendo la differenza delle ore
                                            costoTotale.setText(String.valueOf((OraA - OraDa) * CostoTicket));
                                            btnAcquista.setEnabled(true);
                                        }
                                    });
                                    out.close();
                                    s.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                        t.start();
                    }
                }
            }
        });
        btnAcquista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Targa = targa.getText().toString().trim();
                OraDa = Integer.parseInt(orada.getText().toString().trim());
                OraA = Integer.parseInt(oraa.getText().toString().trim());
                int Durata = OraA-OraDa;
                double costoTot = Double.parseDouble(costoTotale.getText().toString().trim());
            }
        });
    }
}
