package com.example.smartparkingapp;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.Socket;

public class TicketInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_info);

        final String Username = getIntent().getExtras().getString("Username");
        final String Password = getIntent().getExtras().getString("Password");
        final int idticket = getIntent().getExtras().getInt("IDTicket");
        final String Targa = getIntent().getExtras().getString("Targa");
        final String CodiceArea = getIntent().getExtras().getString("CodiceArea");
        final String DataScadenza = getIntent().getExtras().getString("DataScadenza");
        TextView ticketinfo = findViewById(R.id.textViewTicket);
        Button btnTerminaSosta = findViewById(R.id.terminaSosta);
        ticketinfo.setText("Info Ticket: ID: "+idticket+"\nTarga:"+Targa+"\nCodiceArea: "+CodiceArea+"\nData Scadenza: "+DataScadenza);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel serviceChannel = new NotificationChannel(
                    "SmartParkingNotifica",
                    "Prova notifica App",
                    NotificationManager.IMPORTANCE_DEFAULT
            );

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(serviceChannel);
            Intent serviceIntent = new Intent(this, ServiceNotify.class);
            Bundle bundle = new Bundle();
            bundle.putString("Username",Username);
            bundle.putString("Password",Password);
            bundle.putInt("IDTicket",idticket);
            bundle.putString("Targa",Targa);
            bundle.putString("CodiceArea",CodiceArea);
            bundle.putString("DataScadenza",DataScadenza);
            serviceIntent.putExtras(bundle);
            ContextCompat.startForegroundService(this, serviceIntent);
        }

        btnTerminaSosta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Handler handler = new Handler();
                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        ProxyAutomobilista proxyAutomobilista = new ProxyAutomobilista();
                        proxyAutomobilista.EffettuaRimborso(idticket,Username,Password,handler,TicketInfoActivity.this);
                    }
                });
                t.start();
            }
        });

    }
}
