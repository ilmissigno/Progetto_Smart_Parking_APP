package skeleton;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import interfacce.IServer;;

public class SkeletonThread extends Thread {
	
	IServer iserver;
	Socket client;
	
	public SkeletonThread(IServer iserver, Socket client) {
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
				case "costoticketsend":{
					String codicearea = in.readUTF();
					System.out.println("\nSkeletonThread : codicearea = "+codicearea);
					iserver.OttieniCostoTicket(codicearea , out);
					break;
				}
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
}
