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
                try {
                    final Socket s = new Socket(InetAddress.getByName(SocketHandler.URL_SERVER), SocketHandler.PORTA_SERVER);
                    final DataOutputStream out = new DataOutputStream(new BufferedOutputStream(s.getOutputStream()));
                    final DataInputStream in = new DataInputStream(new BufferedInputStream(s.getInputStream()));
                    out.writeUTF("caricaautosend");
                    out.flush();
                    out.writeUTF(Username);
                    out.flush();
                    final boolean ok = in.readBoolean();
                    int listaAutosize = in.readInt();
                    final ArrayList<String> auto = new ArrayList<String>();
                    for (int i = 0; i < listaAutosize; i++)
                        auto.add(in.readUTF());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (ok) {
                                //Set auto to Spinner
                                listaauto.setAdapter(new ArrayAdapter<String>(CancellaAutoActivity.this, R.layout.support_simple_spinner_dropdown_item, auto));
                            }
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
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
                        try{
                            Socket s = new Socket(InetAddress.getByName(SocketHandler.URL_SERVER), SocketHandler.PORTA_SERVER);
                            DataOutputStream out = new DataOutputStream(new BufferedOutputStream(s.getOutputStream()));
                            DataInputStream in = new DataInputStream(new BufferedInputStream(s.getInputStream()));
                            out.writeUTF("deleteautosend");
                            out.flush();
                            out.writeUTF(listaauto.getSelectedItem().toString().trim());
                            out.flush();
                            out.writeUTF(Username);
                            out.flush();
                            final boolean confirm = in.readBoolean();
                            handler1.post(new Runnable() {
                                @Override
                                public void run() {
                                    if(confirm){
                                        AlertDialog.Builder builder = new AlertDialog.Builder(CancellaAutoActivity.this);
                                        builder.setCancelable(true);
                                        builder.setTitle("Auto Cancellata");
                                        builder.setMessage("Auto cancellata correttamente!");
                                        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                Intent intent = new Intent(CancellaAutoActivity.this,HomePageActivity.class);
                                                Bundle bundle = new Bundle();
                                                bundle.putString("username",Username);
                                                bundle.putString("password",Password);
                                                intent.putExtras(bundle);
                                                CancellaAutoActivity.this.startActivity(intent);
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
                t.start();
            }
        });
    }
}
