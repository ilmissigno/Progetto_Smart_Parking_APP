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

public class MainActivity extends AppCompatActivity {

    private String Username;
    private String Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText username = (EditText)findViewById(R.id.username);
        final EditText password = (EditText)findViewById(R.id.password);
        Button login_button = (Button)findViewById(R.id.login);
        Button registrati_button = (Button)findViewById(R.id.registrati);
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Username = username.getText().toString();
                Password = password.getText().toString();
                if(Username.isEmpty()||Password.isEmpty()){
                    Toast.makeText(MainActivity.this,"Inserire tutti i campi",Toast.LENGTH_LONG).show();
                    return;
                }else{
                    final Handler handler = new Handler();
                    Thread t = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                final Socket s = new Socket(InetAddress.getByName("47.53.90.210"), 8001); //Devo connettermi al server
                                final DataOutputStream out = new DataOutputStream(new BufferedOutputStream(s.getOutputStream()));
                                out.writeUTF("loginsend");
                                out.flush();
                                out.writeUTF(Username);
                                out.flush();
                                out.writeUTF(Password);
                                out.flush();
                                DataInputStream in = new DataInputStream(new BufferedInputStream(s.getInputStream()));
                                final boolean auth = in.readBoolean();
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                            if (auth) {
                                                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                                                builder.setCancelable(true);
                                                builder.setTitle("Login Effettuato");
                                                builder.setMessage("Benvenuto " + Username + "!");
                                                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                        Bundle bundle = new Bundle();
                                                        bundle.putString("username", Username);
                                                        bundle.putString("password", Password);
                                                        Intent intent = new Intent(MainActivity.this, HomePageActivity.class);
                                                        intent.putExtras(bundle);
                                                        MainActivity.this.startActivity(intent);
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
            }
        });
        registrati_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,RegistrazioneActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
    }

}