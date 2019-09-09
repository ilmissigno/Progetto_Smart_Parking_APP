package com.example.smartparkingapp;

import android.content.Context;
import android.os.Handler;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public interface IAutomobilista {
    ArrayList<String> getListaAuto(String username);
    double calcolaCosto(String codiceArea, double Durata);
    void acquistaTicket(Spinner listaAuto, String codiceArea, double Durata, double CostoTicket, final String Username, final String Password, Handler handler, Context acquistaActivity);
    void addAuto(String Targa, String CF , final String username,final String password, Handler handler, Context aggiungiAutoActivity);
    void deleteAuto(final Spinner listaAuto, final String username, final String password,Handler handler, final Context context);
    double getVecchioCredito(String username, String password, Handler handler);
    void caricaConto(final String username , double Credito , String password, Handler handler, Context context);
    void Login(final String username, final String password, Handler handler, Context context);
    void deleteTicket(int IDTicket, String username, String password, Context context);
    void Registrati(String CF, String Cognome, String Nome, String Username, String Password, String Email, Handler handler, Context context);
    void rinnovoTicket(final String Targa, final String CodiceArea, final int IDTicket, double Durata, String Username, String Password, double CostoTicket, Handler handler, Context context);
    void sendNotify(final String username, final int IDTicket, final String Password , final String Targa, final String CodiceArea, final String DataScadenza, final Handler handler , final Context context);
    void EffettuaRimborso(final int idTicket, final String Username, final String Password, Handler handler, Context ticketinfoactivity);
}
