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
                        try{
                            Socket s = new Socket(InetAddress.getByName("47.53.90.210"),8001);
                            DataInputStream in = new DataInputStream(new BufferedInputStream(s.getInputStream()));
                            DataOutputStream out = new DataOutputStream(new BufferedOutputStream(s.getOutputStream()));
                            out.writeUTF("caricacontosend");
                            out.flush();
                            out.writeUTF(username);
                            out.flush();
                            out.writeDouble(nuovoCredito);
                            out.flush();
                            out.writeUTF(password);
                            out.flush();
                            final boolean confirm = in.readBoolean();
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    if(confirm){
                                        AlertDialog.Builder builder = new AlertDialog.Builder(CaricaContoActivity.this);
                                        builder.setCancelable(true);
                                        builder.setTitle("Credito caricato");
                                        builder.setMessage("Credito caricato correttamente");
                                        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                Intent intent = new Intent(CaricaContoActivity.this,HomePageActivity.class);
                                                Bundle bundle = new Bundle();
                                                bundle.putString("username",username);
                                                intent.putExtras(bundle);
                                                CaricaContoActivity.this.startActivity(intent);
                                            }
                                        });
                                        AlertDialog dialog = builder.create();
                                        dialog.show();
                                    }
                                }
                            });
                        }catch (IOException e){
                            e.printStackTrace();
                        }
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
                try{
                    Socket s = new Socket(InetAddress.getByName("47.53.90.210"), 8001);
                    DataInputStream in = new DataInputStream(new BufferedInputStream(s.getInputStream()));
                    DataOutputStream out = new DataOutputStream(new BufferedOutputStream(s.getOutputStream()));
                    out.writeUTF("getcreditosend");
                    out.flush();
                    out.writeUTF(username);
                    out.flush();
                    out.writeUTF(password);
                    out.flush();
                    final boolean confirm = in.readBoolean();
                    final double vecchioCredito = in.readDouble();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            if(confirm){
                                Credito.setText(vecchioCredito+" \u20ac");
                            }
                        }
                    });
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        });
        t.start();
    }

}
