package SkeletonGestoreSmartParking;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import Controller.IGestoreSmartParking;

public class SkeletonThread extends Thread{
	IGestoreSmartParking iserver;
	Socket client;
	
	public SkeletonThread(IGestoreSmartParking iserver, Socket client) {
		this.iserver = iserver;
		this.client = client;
	}
	
	public void run() {
		try {
			DataInputStream in = new DataInputStream(new BufferedInputStream(client.getInputStream()));
			DataOutputStream out = new DataOutputStream(new BufferedOutputStream(client.getOutputStream()));
			String cmd = in.readUTF();
			switch(cmd) {
				case "loginsend":{
					String username = in.readUTF();
					String password = in.readUTF();
					System.out.println("\nSkeletonThread : username = "+username+" password = "+password);
					iserver.Login(username, password , out);
					break;
				}
				case "registrazionesend":{
					String codicefiscale = in.readUTF();
					String cognome = in.readUTF();
					String nome = in.readUTF();
					String username = in.readUTF();
					String password = in.readUTF();
					String email = in.readUTF();
					System.out.println("\nSkeletonThread : codicefiscale = "+codicefiscale+" cognome="+cognome+
							" nome="+nome+" username="+username+" password="+password+" email="+email);
					iserver.RegistraUtente(codicefiscale, cognome, nome, username, password, email, out);
					break;
				}
				case "costoticketsend":{
					String targa = in.readUTF();
					String codicearea = in.readUTF();
					double durata = in.readDouble();
					String username = in.readUTF();
					String password = in.readUTF();
					iserver.AcquistaTicket(targa, codicearea, durata, username, password, out, in);
					break;
				}
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
}


