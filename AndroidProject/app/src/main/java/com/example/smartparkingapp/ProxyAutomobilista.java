package com.example.smartparkingapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

public class ProxyAutomobilista implements IAutomobilista{

    @Override
    public ArrayList<String> getListaAuto(String username,String password) {
        SocketHandler socketHandler = new SocketHandler();
        Socket s = socketHandler.getSocket();
        DataOutputStream out = socketHandler.getOutputStream();
        DataInputStream in = socketHandler.getInputStream();
        try {
            out.writeUTF("caricaautosend");
            out.flush();
            out.writeUTF(username);
            out.flush();
            out.writeUTF(password);
            out.flush();
            final boolean ok = in.readBoolean();
            int listaAutosize = in.readInt();
            final ArrayList<String> auto = new ArrayList<String>();
            for (int i = 0; i < listaAutosize; i++)
                auto.add(in.readUTF());
           if(ok){
               return auto;
           }else{
               return null;
           }
        }catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public double calcolaCosto(String codiceArea, double Durata) {
        double CostoTotale = 0;
        SocketHandler socketHandler = new SocketHandler();
        Socket s = socketHandler.getSocket();
        DataInputStream in = socketHandler.getInputStream();
        DataOutputStream out = socketHandler.getOutputStream();
        try{
            out.writeUTF("costoticketsend");
            out.flush();
            out.writeUTF(codiceArea);
            out.flush();
            out.writeDouble(Durata);
            out.flush();
            CostoTotale = in.readDouble();
            return CostoTotale;
        }catch (IOException e){
            e.printStackTrace();
        }
            return -1;
    }

    @Override
    public void acquistaTicket(Spinner listaAuto, String codiceArea, double Durata, double CostoTicket, final String Username, final String Password, Handler handler, final Context acquistaActivity) {
        SocketHandler socketHandler = new SocketHandler();
        Socket s = socketHandler.getSocket();
        DataInputStream in = socketHandler.getInputStream();
        DataOutputStream out = socketHandler.getOutputStream();
        try{
            out.writeUTF("acquistasend");
            out.flush();
            out.writeUTF(listaAuto.getSelectedItem().toString());
            out.flush();
            out.writeUTF(codiceArea);
            out.flush();
            out.writeDouble(Durata);
            out.flush();
            out.writeDouble(CostoTicket);
            out.flush();
            out.writeUTF(Username);
            out.flush();
            out.writeUTF(Password);
            out.flush();
            final boolean notify = in.readBoolean();
            if(notify) {
                final int IDTicket = in.readInt();
                final String targa = in.readUTF();
                final String codarea = in.readUTF();
                final String datascadenza = in.readUTF();
                final boolean confirm = in.readBoolean();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (confirm) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(acquistaActivity);
                            builder.setCancelable(true);
                            builder.setTitle("Acquisto effettuato");
                            builder.setMessage("Ticket acquistato correttamente");
                            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    // NUOVA PAGINA DI CONTROLLO DEL TICKET
                                    Intent intent = new Intent(acquistaActivity, TicketInfoActivity.class);
                                    Bundle bundle2 = new Bundle();
                                    bundle2.putString("Username",Username);
                                    bundle2.putString("Password",Password);
                                    bundle2.putInt("IDTicket", IDTicket);
                                    bundle2.putString("Targa", targa);
                                    bundle2.putString("CodiceArea", codarea);
                                    bundle2.putString("DataScadenza", datascadenza);
                                    intent.putExtras(bundle2);
                                    acquistaActivity.startActivity(intent);
                                }
                            });
                            AlertDialog dialog = builder.create();
                            dialog.show();
                        } else {
                            Toast.makeText(acquistaActivity, "Impossibile inserire l'acquisto", Toast.LENGTH_LONG).show();
                            return;
                        }
                    }
                });
            }else{
                //Errore
            }
            out.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void addAuto(String Targa, String CF, final String username, final String password, Handler handler, final Context aggiungiAutoActivity) {
        SocketHandler socketHandler = new SocketHandler();
        Socket s = socketHandler.getSocket();
        DataInputStream in = socketHandler.getInputStream();
        DataOutputStream out = socketHandler.getOutputStream();
        try{
            out.writeUTF("addautosend");
            out.flush();
            out.writeUTF(Targa);
            out.flush();
            out.writeUTF(CF);
            out.flush();
            out.writeUTF(username);
            out.flush();
            out.writeUTF(password);
            out.flush();
            final boolean confirm = in.readBoolean();
            handler.post(new Runnable() {
                @Override
                public void run() {
                    if(confirm){
                        AlertDialog.Builder builder = new AlertDialog.Builder(aggiungiAutoActivity);
                        builder.setCancelable(true);
                        builder.setTitle("Aggiunta Auto");
                        builder.setMessage("Auto aggiunta correttamente!");
                        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(aggiungiAutoActivity,HomePageActivity.class);
                                Bundle bundle = new Bundle();
                                bundle.putString("username",username);
                                bundle.putString("password",password);
                                intent.putExtras(bundle);
                                aggiungiAutoActivity.startActivity(intent);
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

    @Override
    public void deleteAuto(Spinner listaAuto, final String username, final String password,Handler handler, final Context context) {
        SocketHandler socketHandler = new SocketHandler();
        Socket s = socketHandler.getSocket();
        DataInputStream in = socketHandler.getInputStream();
        DataOutputStream out = socketHandler.getOutputStream();
        try{
            out.writeUTF("deleteautosend");
            out.flush();
            out.writeUTF(listaAuto.getSelectedItem().toString().trim());
            out.flush();
            out.writeUTF(username);
            out.flush();
            out.writeUTF(password);
            out.flush();
            final boolean confirm = in.readBoolean();
            handler.post(new Runnable() {
                @Override
                public void run() {
                    if(confirm){
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setCancelable(true);
                        builder.setTitle("Auto Cancellata");
                        builder.setMessage("Auto cancellata correttamente!");
                        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(context,HomePageActivity.class);
                                Bundle bundle = new Bundle();
                                bundle.putString("username",username);
                                bundle.putString("password",password);
                                intent.putExtras(bundle);
                               context.startActivity(intent);
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

    @Override
    public double getVecchioCredito(String username, String password, Handler handler) {
        SocketHandler socketHandler = new SocketHandler();
        Socket s = socketHandler.getSocket();
        DataInputStream in = socketHandler.getInputStream();
        DataOutputStream out = socketHandler.getOutputStream();
        try{
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
                    if(!confirm){
                       return;
                    }
                }
            });
            return vecchioCredito;
        }catch (IOException e){
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public void caricaConto(final String username, double Credito, String password, Handler handler, final Context context) {
        SocketHandler socketHandler = new SocketHandler();
        Socket s = socketHandler.getSocket();
        DataInputStream in = socketHandler.getInputStream();
        DataOutputStream out = socketHandler.getOutputStream();
        try{
            out.writeUTF("caricacontosend");
            out.flush();
            out.writeUTF(username);
            out.flush();
            out.writeDouble(Credito);
            out.flush();
            out.writeUTF(password);
            out.flush();
            final boolean confirm = in.readBoolean();
            handler.post(new Runnable() {
                @Override
                public void run() {
                    if(confirm){
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setCancelable(true);
                        builder.setTitle("Credito caricato");
                        builder.setMessage("Credito caricato correttamente");
                        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(context,HomePageActivity.class);
                                Bundle bundle = new Bundle();
                                bundle.putString("username",username);
                                intent.putExtras(bundle);
                                context.startActivity(intent);
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

    @Override
    public void Login(final String username, final String password, Handler handler, final Context context) {
        SocketHandler socketHandler = new SocketHandler();
        Socket s = socketHandler.getSocket();
        DataInputStream in = socketHandler.getInputStream();
        DataOutputStream out = socketHandler.getOutputStream();
        try{
            out.writeUTF("loginsend");
            out.flush();
            out.writeUTF(username);
            out.flush();
            out.writeUTF(password);
            out.flush();
            final boolean auth = in.readBoolean();
            handler.post(new Runnable() {
                @Override
                public void run() {
                    if (auth) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setCancelable(true);
                        builder.setTitle("Login Effettuato");
                        builder.setMessage("Benvenuto " + username + "!");
                        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Bundle bundle = new Bundle();
                                bundle.putString("username", username);
                                bundle.putString("password", password);
                                Intent intent = new Intent(context, HomePageActivity.class);
                                intent.putExtras(bundle);
                                context.startActivity(intent);
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

    @Override
    public void deleteTicket(int IDTicket, String username, String password, Context context) {
        SocketHandler socketHandler = new SocketHandler();
        Socket s = socketHandler.getSocket();
        DataInputStream in = socketHandler.getInputStream();
        DataOutputStream out = socketHandler.getOutputStream();
        try{
            out.writeUTF("eliminaticketsend");
            out.flush();
            out.writeInt(IDTicket);
            out.flush();
            final boolean confirm = in.readBoolean();
            if(confirm){
                Intent intent = new Intent(context,HomePageActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("username",username);
                bundle.putString("password",password);
                bundle.putBoolean("cancellato",true);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void Registrati(String CF, String Cognome, String Nome, String Username, String Password, String Email, Handler handler, final Context context) {
        SocketHandler socketHandler = new SocketHandler();
        Socket s = socketHandler.getSocket();
        DataInputStream in = socketHandler.getInputStream();
        DataOutputStream out = socketHandler.getOutputStream();
        try{
            out.writeUTF("registrazionesend");
            out.flush();
            out.writeUTF(CF);
            out.flush();
            out.writeUTF(Cognome);
            out.flush();
            out.writeUTF(Nome);
            out.flush();
            out.writeUTF(Username);
            out.flush();
            out.writeUTF(Password);
            out.flush();
            out.writeUTF(Email);
            out.flush();
            final boolean confirm = in.readBoolean();
            handler.post(new Runnable() {
                @Override
                public void run() {
                    if (confirm) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setCancelable(true);
                        builder.setTitle("Registrazione Effettuata");
                        builder.setMessage("Registrazione Completata, effettuare il Login");
                        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(context,MainActivity.class);
                                context.startActivity(intent);
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

    @Override
    public void rinnovoTicket(final String Targa, final String CodiceArea, int IDTicket, double Durata, final String Username, final String Password, double CostoTicket, Handler handler , final Context context) {
        SocketHandler socketHandler = new SocketHandler();
        Socket s = socketHandler.getSocket();
        DataInputStream in = socketHandler.getInputStream();
        DataOutputStream out = socketHandler.getOutputStream();
        try{
            out.writeUTF("rinnovosend");
            out.flush();
            out.writeInt(IDTicket);
            out.flush();
            out.writeDouble(Durata);
            out.flush();
            out.writeUTF(Username);
            out.flush();
            out.writeUTF(Password);
            out.flush();
            out.writeDouble(CostoTicket);
            out.flush();
            boolean confirm = in.readBoolean();
            if(confirm){
                final int idTicket = in.readInt();
                final String datascad = in.readUTF();
                final boolean ok = in.readBoolean();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if(ok){
                            AlertDialog.Builder builder = new AlertDialog.Builder(context);
                            builder.setCancelable(true);
                            builder.setTitle("Rinnovo Effettuato");
                            builder.setMessage("Rinnovo del Ticket effettuato correttamente");
                            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Intent intent = new Intent(context,TicketInfoActivity.class);
                                    Bundle bundle2 = new Bundle();
                                    bundle2.putString("Username",Username);
                                    bundle2.putString("Password",Password);
                                    bundle2.putInt("IDTicket", idTicket);
                                    bundle2.putString("Targa",Targa);
                                    bundle2.putString("CodiceArea",CodiceArea);
                                    bundle2.putString("DataScadenza", datascad);
                                    intent.putExtras(bundle2);
                                    context.startActivity(intent);
                                }
                            });
                            AlertDialog dialog = builder.create();
                            dialog.show();
                        }else{
                            Toast.makeText(context, "Impossibile Effettuare il Rinnovo", Toast.LENGTH_LONG).show();
                            return;
                        }
                    }
                });
            }else{
                Toast.makeText(context, "Impossibile Effettuare il Rinnovo", Toast.LENGTH_LONG).show();
                return;
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void sendNotify(final String username, final int IDTicket, final String Password , final String Targa, final String CodiceArea, final String DataScadenza, final Handler handler , final Context context) {
        SocketHandler socketHandler = new SocketHandler();
        Socket s = socketHandler.getSocket();
        DataInputStream in = socketHandler.getInputStream();
        DataOutputStream out = socketHandler.getOutputStream();
        try{
            out.writeUTF("Notificasend");
            out.flush();
            out.writeUTF(username);
            out.flush();
            out.writeInt(IDTicket);
            out.flush();
            final boolean command = in.readBoolean();
            handler.post(new Runnable() {
                @Override
                public void run() {
                    if(command) {
                        Intent intent1 = new Intent(context, PopupWindow.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("Username", username);
                        bundle.putString("Password",Password);
                        bundle.putInt("IDTicket", IDTicket);
                        bundle.putString("Targa", Targa);
                        bundle.putString("CodiceArea", CodiceArea);
                        bundle.putString("DataScadenza", DataScadenza);
                        intent1.putExtras(bundle);
                        intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent1);
                    }
                }
            });
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void EffettuaRimborso(final int idTicket, final String Username, final String Password, Handler handler, final Context context) {
        SocketHandler socketHandler = new SocketHandler();
        Socket s = socketHandler.getSocket();
        DataInputStream in = socketHandler.getInputStream();
        DataOutputStream out = socketHandler.getOutputStream();
        try{
            out.writeUTF("finesostasend");
            out.flush();
            out.writeInt(idTicket);
            out.flush();
            out.writeUTF(Username);
            out.flush();
            out.writeUTF(Password);
            out.flush();
            final boolean command = in.readBoolean();
            handler.post(new Runnable() {
                @Override
                public void run() {
                    if(command){
                        Intent intent1 = new Intent(context,HomePageActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("username", Username);
                        bundle.putString("password",Password);
                        intent1.putExtras(bundle);
                        context.startActivity(intent1);
                    }
                }
            });
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void getDisponibilita(final String codiceArea, final String username, final String password , Handler handler, final Context ottieniDispActivity) {
        SocketHandler socketHandler = new SocketHandler();
        Socket s = socketHandler.getSocket();
        DataInputStream in = socketHandler.getInputStream();
        DataOutputStream out = socketHandler.getOutputStream();
        try{
            out.writeUTF("DisponibilitaSend");
            out.flush();
            out.writeInt(Integer.parseInt(codiceArea));
            out.flush();
            final boolean command = in.readBoolean();
            final int disponibilita = in.readInt();
            handler.post(new Runnable() {
                @Override
                public void run() {
                    if(command){
                            AlertDialog.Builder builder = new AlertDialog.Builder(ottieniDispActivity);
                            builder.setCancelable(true);
                            builder.setTitle("Posti Disponibili");
                            builder.setMessage("L'Area Parcheggio di Codice : "+codiceArea+" ha "+String.valueOf(disponibilita)+" posti disponibili");
                            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Intent intent = new Intent(ottieniDispActivity,HomePageActivity.class);
                                    Bundle bundle2 = new Bundle();
                                    bundle2.putString("username",username);
                                    bundle2.putString("password",password);
                                    intent.putExtras(bundle2);
                                    ottieniDispActivity.startActivity(intent);
                                }
                            });
                            AlertDialog dialog = builder.create();
                            dialog.show();
                        }else{
                            Toast.makeText(ottieniDispActivity, "Impossibile ottenere i posti", Toast.LENGTH_LONG).show();
                        }
                    }
            });
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
