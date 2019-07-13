package clientB;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import interfacce.IMagazzino;

public class ProxyB implements IMagazzino{
	
	Socket client;
	final int porta = 8000;
	
	public void deposita(String articolo , int id){
		//Non implementato da B
	}
	
	public int preleva(String articolo){
		int val=-1;
		try{
			client = new Socket(InetAddress.getLocalHost(),porta);
			DataOutputStream out = new DataOutputStream(new BufferedOutputStream(client.getOutputStream()));
			DataInputStream in = new DataInputStream(new BufferedInputStream(client.getInputStream()));
			out.writeUTF("preleva");
			out.flush();
			out.writeUTF(articolo);
			out.flush();
			val = in.readInt();
			System.out.println("\nProxyB : Prelevati i dati : "+val);
		}catch(IOException e){
			e.printStackTrace();
		}
		return val;
	}
	
}
