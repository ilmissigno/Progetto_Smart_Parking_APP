package com.example.smartparkingapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

public class PopupWindow extends Activity {

    private boolean isRinnovato = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popwindow);
        Intent intent = getIntent();
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int)(width*.9),(int)(height*.8));
        final String username = intent.getExtras().getString("Username");
        final String Password = intent.getExtras().getString("Password");
        final int IDTicket = intent.getExtras().getInt("IDTicket");
        final String Targa = intent.getExtras().getString("Targa");
        final String CodiceArea = intent.getExtras().getString("CodiceArea");
        final String DataScadenza = intent.getExtras().getString("DataScadenza");
        TextView textView = findViewById(R.id.textViewNotify);
        textView.setText("ATTENZIONE! TICKET IN SCADENZA\n"+"Attenzione, il Ticket acquistato è in scadenza, le " +
                "informazioni sul ticket sono le seguenti:\n" +
                "IDTicket "+IDTicket+" Targa "+Targa+" CodiceArea "+CodiceArea+"\n Vuoi Rinnovare il Ticket? \nSe si cliccare su Rinnova, altrimenti cliccare su Non Rinnova");
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if(!isRinnovato){
                    DeleteTicket(IDTicket);
                }else return;
            }
        },(600*1000));
        Button btnRinnovo = findViewById(R.id.btnRinnovo);
        btnRinnovo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isRinnovato = true;
                Intent intent1 = new Intent(PopupWindow.this, RinnovoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("Username",username);
                bundle.putString("Password",Password);
                bundle.putInt("IDTicket",IDTicket);
                bundle.putString("Targa",Targa);
                bundle.putString("CodiceArea",CodiceArea);
                bundle.putString("DataScadenza",DataScadenza);
                intent1.putExtras(bundle);
                startActivity(intent1);
            }
        });
    }

    protected void DeleteTicket(final int IDTicket){
        final Handler handler = new Handler();
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Socket s = new Socket(InetAddress.getByName("47.53.90.210"),8001);
                    DataInputStream in = new DataInputStream(new BufferedInputStream(s.getInputStream()));
                    DataOutputStream out = new DataOutputStream(new BufferedOutputStream(s.getOutputStream()));
                    out.writeUTF("eliminaticketsend");
                    out.flush();
                    out.writeInt(IDTicket);
                    out.flush();
                    final boolean confirm = in.readBoolean();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            if(confirm){
                                Toast.makeText(PopupWindow.this,"Ticket Scaduto",Toast.LENGTH_LONG).show();
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