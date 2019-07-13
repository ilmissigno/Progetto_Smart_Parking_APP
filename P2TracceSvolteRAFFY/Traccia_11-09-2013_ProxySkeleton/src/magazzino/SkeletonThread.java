package magazzino;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import interfacce.IMagazzino;

public class SkeletonThread extends Thread{
	
	IMagazzino magazzino;
	Socket client;
	
	public SkeletonThread(IMagazzino magazzino , Socket client){
		this.magazzino=magazzino;
		this.client=client;
	}
	
	public void run(){
		try{
			DataOutputStream out = new DataOutputStream(new BufferedOutputStream(client.getOutputStream()));
			DataInputStream in = new DataInputStream(new BufferedInputStream(client.getInputStream()));
			String command = in.readUTF();
			switch(command){
			case "deposita":{
				String articolo = in.readUTF();
				int id = in.readInt();
				magazzino.deposita(articolo, id);
				System.out.println("\nSkeletonThread : Valore depositato.");
				break;
			}
			case "preleva":{
				String articolo = in.readUTF();
				int id = magazzino.preleva(articolo);
				System.out.println("\nSkeletonThread : Valore prelevato "+id+" scrivo in uscita");
				out.writeInt(id);
				out.flush();
				break;
			}
			default:{
				System.out.println("\nSkeletonThread : Errore");
				break;
			}
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
}
