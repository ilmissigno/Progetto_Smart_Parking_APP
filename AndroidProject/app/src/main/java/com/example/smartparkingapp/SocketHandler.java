package com.example.smartparkingapp;

import java.net.InetAddress;
import java.net.Socket;

public class SocketHandler {
    private static Socket socket;
    public static final String URL_SERVER = "10.0.2.2"; //Altro indirizzo = 47.53.90.210
    public static final int PORTA_SERVER = 8001;

    public static synchronized Socket getSocket(){
        return socket;
    }

    public static synchronized void setSocket(Socket socket){
        SocketHandler.socket = socket;
    }

}
