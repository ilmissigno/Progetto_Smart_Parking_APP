package com.example.smartparkingapp;

import android.app.AlertDialog;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class NotifyScheduler extends JobService {

    //Il metodo mi serve per mantenere attiva l'app quando viene acquistato il ticket per la notifica
    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        doBackgroundWork(jobParameters);
        return true;
    }

    private void doBackgroundWork(final JobParameters params){
        new Thread(new Runnable() {
            @Override
            public void run() {
                final Handler handler = new Handler();
                try{
                    Socket s = SocketHandler.getSocket();
                    final DataInputStream in = new DataInputStream(new BufferedInputStream(s.getInputStream()));
                    final DataOutputStream out = new DataOutputStream(new BufferedOutputStream(s.getOutputStream()));
                    String command = in.readUTF();
                    if(command.equals("notifyticket")){
                        final int IDTicket = in.readInt();
                        final String Targa = in.readUTF();
                        final String CodiceArea = in.readUTF();
                        final double Durata = in.readDouble();
                        AlertDialog.Builder builder = new AlertDialog.Builder(NotifyScheduler.this);
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
                                                AlertDialog.Builder builder1 = new AlertDialog.Builder(NotifyScheduler.this);
                                                builder1.setCancelable(true);
                                                builder1.setTitle("Rinnovo effettuato");
                                                builder1.setMessage("Ticket rinnovato correttamente");
                                                builder1.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                        Intent intent = new Intent(NotifyScheduler.this,MainActivity.class);
                                                        NotifyScheduler.this.startActivity(intent);
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
                jobFinished(params,false);
            }
        }).start();
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        return false;
    }
}
