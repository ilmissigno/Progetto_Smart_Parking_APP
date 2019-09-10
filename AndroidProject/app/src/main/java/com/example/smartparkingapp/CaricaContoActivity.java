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

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.text.DecimalFormat;

public class CaricaContoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carica_conto);
        final TextView Credito = (TextView)findViewById(R.id.oldCredito);
        final EditText ricarica = (EditText)findViewById(R.id.ricarica);
        Button btnRicarica = (Button)findViewById(R.id.btnRicarica);
        final String username = getIntent().getExtras().getString("username");
        final String password = getIntent().getExtras().getString("password");
        GetOldCredito(username,password,Credito);
        btnRicarica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final double nuovoCredito = Double.parseDouble(ricarica.getText().toString().trim());
                final Handler handler = new Handler();
                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        ProxyAutomobilista proxyAutomobilista = new ProxyAutomobilista();
                        proxyAutomobilista.caricaConto(username,nuovoCredito,password,handler,CaricaContoActivity.this);
                    }
                });
                t.start();
            }
        });
    }

    protected void GetOldCredito(final String username,final String password,final TextView Credito){
        final Handler handler = new Handler();
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
               ProxyAutomobilista proxyAutomobilista = new ProxyAutomobilista();
               double vecchioCredito = proxyAutomobilista.getVecchioCredito(username,password,handler);
                DecimalFormat formato = new DecimalFormat("##.##");
                String credits = formato.format(vecchioCredito);
               Credito.setText(credits+" \u20ac");
            }
        });
        t.start();
    }

}
