package com.example.smartparkingapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class AggiungiAuto extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_aggiungi_auto);
        final EditText targa = findViewById(R.id.targaAuto);
        final EditText cf = findViewById(R.id.cfProprietario);
        Button btnInserisci = findViewById(R.id.btnInserisciAuto);
        final String username = getIntent().getExtras().getString("username");
        final String password = getIntent().getExtras().getString("password");
        btnInserisci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String Targa = targa.getText().toString();
                final String CF = cf.getText().toString();
                if(Targa.isEmpty()||CF.isEmpty()){
                    Toast.makeText(AggiungiAuto.this,"Inserire tutti i campi",Toast.LENGTH_LONG).show();
                    return;
                }else{
                    if(CF.length()!=16){
                        Toast.makeText(AggiungiAuto.this,"Il Codice Fiscale deve essere di 16 Caratteri",Toast.LENGTH_LONG).show();
                    }else{
                        final Handler handler = new Handler();
                        Thread thread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                ProxyAutomobilista proxyAutomobilista = new ProxyAutomobilista();
                                proxyAutomobilista.addAuto(Targa,CF,username,password,handler,AggiungiAuto.this);
                            }
                        });
                        thread.start();
                    }
                }
            }
        });
    }
}
