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
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class AggiungiAuto extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
                                try{
                                    Socket client = new Socket(InetAddress.getByName("10.0.2.2"),8000);
                                    SocketHandler s = new SocketHandler();
                                    s.setSocket(client);
                                    DataOutputStream out = new DataOutputStream(new BufferedOutputStream(client.getOutputStream()));
                                    out.writeUTF("addautosend");
                                    out.flush();
                                    out.writeUTF(Targa);
                                    out.flush();
                                    out.writeUTF(CF);
                                    out.flush();
                                    out.writeUTF(username);
                                    out.flush();
                                    DataInputStream in = new DataInputStream(new BufferedInputStream(client.getInputStream()));
                                    final boolean confirm = in.readBoolean();
                                    handler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            if(confirm){
                                                AlertDialog.Builder builder = new AlertDialog.Builder(AggiungiAuto.this);
                                                builder.setCancelable(true);
                                                builder.setTitle("Aggiunta Auto");
                                                builder.setMessage("Auto aggiunta correttamente!");
                                                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        Intent intent = new Intent(AggiungiAuto.this,HomePageActivity.class);
                                                        Bundle bundle = new Bundle();
                                                        bundle.putString("username",username);
                                                        bundle.putString("password",password);
                                                        intent.putExtras(bundle);
                                                        AggiungiAuto.this.startActivity(intent);
                                                    }
                                                });
                                                AlertDialog dialog = builder.create();
                                                dialog.show();
                                            }
                                        }
                                    });
                                }catch(IOException e){
                                    e.printStackTrace();
                                }
                            }
                        });
                        thread.start();
                    }
                }
            }
        });
    }
}
