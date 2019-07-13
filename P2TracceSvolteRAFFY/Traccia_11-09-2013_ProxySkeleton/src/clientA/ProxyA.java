package clientA;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import interfacce.IMagazzino;

public class ProxyA implements IMagazzino{
	Socket client;
	final int porta = 8000;
	
	public void deposita(String articolo , int id){
		try{
			client = new Socket(InetAddress.getLocalHost(),porta);
			DataOutputStream out = new DataOutputStream(new BufferedOutputStream(client.getOutputStream()));
			out.writeUTF("deposita");
			out.flush();
			out.writeUTF(articolo);
			out.flush();
			out.writeInt(id);
			out.flush();
			System.out.println("\nProxyA : Inviati i dati : "+articolo+" "+id+" al magazzino.");
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public int preleva(String articolo){
		//non implementato da ClientA
		return -1;
	}
	
}
