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
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class RegistrazioneActivity extends AppCompatActivity {

    private String CodiceFiscale;
    private String Cognome;
    private String Nome;
    private String Username;
    private String Password;
    private String Email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_registrazione);
        final EditText codicefiscale = (EditText)findViewById(R.id.codiceFiscale);
        final EditText cognome = (EditText)findViewById(R.id.cognome);
        final EditText nome = (EditText)findViewById(R.id.nome);
        final EditText username = (EditText)findViewById(R.id.username);
        final EditText password = (EditText)findViewById(R.id.password);
        final EditText email = (EditText)findViewById(R.id.email);
        Button btnRegistrati = (Button)findViewById(R.id.btnRegistrati);
        btnRegistrati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CodiceFiscale = codicefiscale.getText().toString();
                Cognome = cognome.getText().toString();
                Nome = nome.getText().toString();
                Username = username.getText().toString();
                Password = password.getText().toString();
                Email = email.getText().toString();
                if(CodiceFiscale.isEmpty()||Cognome.isEmpty()||Nome.isEmpty()||Username.isEmpty()||Password.isEmpty()||Email.isEmpty()){
                    Toast.makeText(RegistrazioneActivity.this,"Inserire tutti i campi",Toast.LENGTH_LONG).show();
                    return;
                }else {
                    if (CodiceFiscale.length() != 16) {
                        Toast.makeText(RegistrazioneActivity.this,"Il Codice Fiscale deve essere di 16 Caratteri!",Toast.LENGTH_LONG).show();
                        return;
                    } else {
                        if (!Cognome.matches("[a-zA-Z]+") || !Nome.matches("[a-zA-Z]+")) {
                            Toast.makeText(RegistrazioneActivity.this,"Il Nome e Cognome non devono contenere numeri!",Toast.LENGTH_LONG).show();
                            return;
                        } else {
                            final Handler handler = new Handler();
                            Thread t = new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    ProxyAutomobilista proxyAutomobilista = new ProxyAutomobilista();
                                    proxyAutomobilista.Registrati(CodiceFiscale,Cognome,Nome,Username,Password,Email,handler,RegistrazioneActivity.this);
                                }
                            });
                            t.start();
                        }
                    }
                }
            }
        });
    }
}
