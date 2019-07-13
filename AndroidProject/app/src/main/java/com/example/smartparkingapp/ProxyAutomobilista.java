package com.example.smartparkingapp;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import Interfacce.IAutomobilista;

public class ProxyAutomobilista implements IAutomobilista {

    private static final String URL_CONTROLLER = ""; //Qui va l'indirizzo del control futuro server
    private int porta = 8000; //

    @Override
    public boolean Login(String username , String password) {
        try{
            Socket client = new Socket(InetAddress.getByName(URL_CONTROLLER),porta);
            DataOutputStream out = new DataOutputStream(new BufferedOutputStream(client.getOutputStream()));
            out.writeUTF("loginsend");
            out.flush();
            out.writeUTF(username);
            out.flush();
            out.writeUTF(password);
            out.flush();
            return true;
        }catch (IOException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean AcquistaTicket(String Targa , String CodiceArea , int Durata , double CostoTotale) {
        try{
            Socket client = new Socket(InetAddress.getByName(URL_CONTROLLER),porta);
            DataOutputStream out = new DataOutputStream(new BufferedOutputStream(client.getOutputStream()));
            out.writeUTF("acquistasend");
            out.flush();
            out.writeUTF(Targa);
            out.flush();
            out.writeUTF(CodiceArea);
            out.flush();
            out.writeInt(Durata);
            out.flush();
            out.writeDouble(CostoTotale);
            out.flush();
            return true;
        }catch(IOException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public double OttieniCostoTicket(String CodiceArea) {
        try{
            Socket client = new Socket(InetAddress.getByName(URL_CONTROLLER),porta);
            DataOutputStream out = new DataOutputStream(new BufferedOutputStream(client.getOutputStream()));
            out.writeUTF("costoticketsend");
            out.flush();
            out.writeUTF(CodiceArea);
            out.flush();
            return 0;
        }catch (Exception e){
            e.printStackTrace();
        }
        return -1;
    }
}
