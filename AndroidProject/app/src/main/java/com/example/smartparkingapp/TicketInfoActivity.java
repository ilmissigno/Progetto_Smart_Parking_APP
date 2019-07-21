package com.example.smartparkingapp;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class TicketInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_info);

        final int idticket = getIntent().getExtras().getInt("IDTicket");
        final String Targa = getIntent().getExtras().getString("Targa");
        final String CodiceArea = getIntent().getExtras().getString("CodiceArea");
        final double durata = getIntent().getExtras().getDouble("Durata");
        TextView ticketinfo = findViewById(R.id.textViewTicket);
        ticketinfo.setText("Info Ticket: ID: "+idticket+" Targa:"+Targa+" CodiceArea: "+CodiceArea+" Durata in Ore: "+durata);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel serviceChannel = new NotificationChannel(
                    "SmartParkingNotifica",
                    "Prova notifica App",
                    NotificationManager.IMPORTANCE_DEFAULT
            );

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(serviceChannel);
            Intent serviceIntent = new Intent(this, ServiceNotify.class);
            ContextCompat.startForegroundService(this, serviceIntent);
        }

    }
}
