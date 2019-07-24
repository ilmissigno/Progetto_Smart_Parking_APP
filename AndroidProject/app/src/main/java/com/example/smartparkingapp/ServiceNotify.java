package com.example.smartparkingapp;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class ServiceNotify extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        final String TAG = "ServiceNotify";
        final String username = intent.getExtras().getString("Username");
        final String Password = intent.getExtras().getString("Password");
        final int IDTicket = intent.getExtras().getInt("IDTicket");
        final String Targa = intent.getExtras().getString("Targa");
        final String CodiceArea = intent.getExtras().getString("CodiceArea");
        final String DataScadenza = intent.getExtras().getString("DataScadenza");
        Intent notificationIntent = new Intent(this,TicketInfoActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,1,notificationIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        Notification notification = new NotificationCompat.Builder(this, "SmartParkingNotifica")
                .setContentTitle("Info Ticket")
                .setContentText("Info Ticket: ID: "+IDTicket+"\n Targa:"+Targa+"\n CodiceArea: "+CodiceArea+"\n Data Scadenza: "+DataScadenza)
                .setSmallIcon(R.drawable.ic_android)
                .setContentIntent(pendingIntent)
                .build();

        startForeground(1, notification);
        // Connessione in background con Socket
        final Handler handler = new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
               ProxyAutomobilista proxyAutomobilista = new ProxyAutomobilista();
               proxyAutomobilista.sendNotify(username,IDTicket,Password,Targa,CodiceArea,DataScadenza,handler,ServiceNotify.this);
            }
        }).start();
        return START_NOT_STICKY;
    }
}
