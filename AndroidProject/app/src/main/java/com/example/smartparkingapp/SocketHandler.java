package com.example.smartparkingapp;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class SocketHandler {
    private Socket socket;
    private DataOutputStream outputStream;
    private DataInputStream inputStream;
    public static final String URL_SERVER = "93.151.160.118"; //Altro indirizzo = 93.151.160.118
    public static final int PORTA_SERVER = 8001;

    public SocketHandler(){
        try {
            this.socket = new Socket(InetAddress.getByName(URL_SERVER), PORTA_SERVER);
            this.outputStream = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            this.inputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public Socket getSocket(){
        return this.socket;
    }

    public void setSocket(Socket socket){
        this.socket = socket;
    }

    public DataOutputStream getOutputStream() {
        return this.outputStream;
    }

    public void setOutputStream(DataOutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public DataInputStream getInputStream() {
        return this.inputStream;
    }

    public void setInputStream(DataInputStream inputStream) {
        this.inputStream = inputStream;
    }
}
