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
                                    try{
                                        //Sostituire InetAddress con l'indirizzo del controller dentro getByName
                                        Socket client = new Socket(InetAddress.getByName("10.0.2.2"),8000);
                                        SocketHandler s = new SocketHandler();
                                        s.setSocket(client);
                                        DataOutputStream out = new DataOutputStream(new BufferedOutputStream(client.getOutputStream()));
                                        out.writeUTF("registrazionesend");
                                        out.flush();
                                        out.writeUTF(CodiceFiscale);
                                        out.flush();
                                        out.writeUTF(Cognome);
                                        out.flush();
                                        out.writeUTF(Nome);
                                        out.flush();
                                        out.writeUTF(Username);
                                        out.flush();
                                        out.writeUTF(Password);
                                        out.flush();
                                        out.writeUTF(Email);
                                        out.flush();
                                        DataInputStream in = new DataInputStream(new BufferedInputStream(client.getInputStream()));
                                        final boolean confirm = in.readBoolean();
                                        handler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                if (confirm) {
                                                    AlertDialog.Builder builder = new AlertDialog.Builder(RegistrazioneActivity.this);
                                                    builder.setCancelable(true);
                                                    builder.setTitle("Registrazione Effettuata");
                                                    builder.setMessage("Registrazione Completata, effettuare il Login");
                                                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialogInterface, int i) {
                                                            Intent intent = new Intent(RegistrazioneActivity.this,MainActivity.class);
                                                            RegistrazioneActivity.this.startActivity(intent);
                                                        }
                                                    });
                                                    AlertDialog dialog = builder.create();
                                                    dialog.show();
                                                }
                                            }
                                        });
                                        out.close();
                                        client.close();
                                    }catch(IOException e){
                                        e.printStackTrace();
                                    }
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
