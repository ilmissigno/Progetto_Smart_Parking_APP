package com.example.smartparkingapp;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

import Interfacce.ITicket;

public class SkeletonThread extends Thread {
    ITicket ticket;
    Socket client;

    public SkeletonThread(ITicket ticket , Socket client){
        this.ticket = ticket;
        this.client = client;
    }

    public void run(){
        try{
            DataInputStream in = new DataInputStream(new BufferedInputStream(client.getInputStream()));
            String command = in.readUTF();
            switch (command){
                case "loginreceive":{
                    String auth = in.readUTF();
                    ticket.ConfirmLogin(auth);
                    break;
                }
                case "costoticketreceive":{
                    double costoTicket = in.readDouble();
                    ticket.RiceviCostoTicket(costoTicket);
                    break;
                }
            }

        }catch(IOException e){
            e.printStackTrace();
        }
    }

}
