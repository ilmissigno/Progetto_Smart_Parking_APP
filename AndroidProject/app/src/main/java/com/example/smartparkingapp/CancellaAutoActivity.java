package com.example.smartparkingapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

public class CancellaAutoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancella_auto);
        final Spinner listaauto = findViewById(R.id.listaAuto2);
        Button btnCancAuto = findViewById(R.id.btnCancAuto);
        final String Username = getIntent().getExtras().getString("username");
        final String Password = getIntent().getExtras().getString("password");
        final Handler handler = new Handler();
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                ProxyAutomobilista proxyAutomobilista = new ProxyAutomobilista();
                final ArrayList<String> auto = proxyAutomobilista.getListaAuto(Username);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        //Set auto to Spinner
                        listaauto.setAdapter(new ArrayAdapter<String>(CancellaAutoActivity.this, R.layout.support_simple_spinner_dropdown_item, auto));
                    }
                });
            }
        });
        t.start();
        btnCancAuto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Handler handler1 = new Handler();
                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        ProxyAutomobilista proxyAutomobilista = new ProxyAutomobilista();
                        proxyAutomobilista.deleteAuto(listaauto,Username,Password,handler1,CancellaAutoActivity.this);
                    }
                });
                t.start();
            }
        });
    }
}
