package com.example.smartparkingapp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import Interfacce.ITicket;

public abstract class SkeletonTicket implements ITicket {
    ServerSocket serverSocket;
    Socket client;
    final int porta = 8000; //Porta in ricezione sul secondo proxy-skeleton

    public void avvia_skeleton(){
        try{
            serverSocket = new ServerSocket(porta);
            while(true){
                client = serverSocket.accept();
                Thread t = new SkeletonThread(this,client);
                t.start();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

}
