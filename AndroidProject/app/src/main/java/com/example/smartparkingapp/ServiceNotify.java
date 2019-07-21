package com.example.smartparkingapp;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

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
        Intent notificationIntent = new Intent(this,TicketInfoActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,notificationIntent,0);
        Notification notification = new NotificationCompat.Builder(this, "SmartParkingNotifica")
                .setContentTitle("Example Service")
                .setContentText("Prova Funzionante")
                .setSmallIcon(R.drawable.ic_android)
                .setContentIntent(pendingIntent)
                .build();

        startForeground(1, notification);
        /*
        // Connessione in background con Socket
        new Thread(new Runnable() {
            @Override
            public void run() {
                final Handler handler = new Handler();
                try{
                    Socket s = new Socket(InetAddress.getByName("10.0.2.2"),8000);
                    final DataInputStream in = new DataInputStream(new BufferedInputStream(s.getInputStream()));
                    final DataOutputStream out = new DataOutputStream(new BufferedOutputStream(s.getOutputStream()));
                    String command = in.readUTF();
                    if(command.equals("notifyticket")){
                        final int IDTicket = in.readInt();
                        final String Targa = in.readUTF();
                        final String CodiceArea = in.readUTF();
                        final double Durata = in.readDouble();
                        AlertDialog.Builder builder = new AlertDialog.Builder(ServiceNotify.this);
                        builder.setCancelable(true);
                        builder.setTitle("ATTENZIONE! TICKET IN SCADENZA");
                        builder.setMessage("Attenzione, il Ticket acquistato è in scadenza, le informazioni sul" +
                                " ticket sono le seguenti: IDTicket "+IDTicket+" Targa "+Targa+" CodiceArea "+CodiceArea+" ; Vuoi Rinnovare il Ticket?");
                        builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                try {
                                    out.writeUTF("rinnovoticketsend");
                                    out.flush();
                                    out.writeInt(IDTicket);
                                    out.flush();
                                    out.writeUTF(Targa);
                                    out.flush();
                                    out.writeUTF(CodiceArea);
                                    out.flush();
                                    out.writeDouble(Durata);
                                    out.flush();
                                    final String confirm = in.readUTF();
                                    handler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            if(confirm.equals("ok")){
                                                AlertDialog.Builder builder1 = new AlertDialog.Builder(ServiceNotify.this);
                                                builder1.setCancelable(true);
                                                builder1.setTitle("Rinnovo effettuato");
                                                builder1.setMessage("Ticket rinnovato correttamente");
                                                builder1.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                        Intent intent = new Intent(ServiceNotify.this,MainActivity.class);
                                                        ServiceNotify.this.startActivity(intent);
                                                    }
                                                });
                                                AlertDialog dialog1 = builder1.create();
                                                dialog1.show();
                                            }else{
                                                //Errore
                                            }
                                        }
                                    });
                                }catch (IOException e){
                                    e.printStackTrace();
                                }
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Cancella il Ticket
                            }
                        });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }else return;
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        }).start();
        */
        return START_NOT_STICKY;
    }
}
