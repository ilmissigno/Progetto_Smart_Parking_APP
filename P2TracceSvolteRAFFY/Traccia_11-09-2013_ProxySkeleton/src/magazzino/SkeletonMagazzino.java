package magazzino;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import interfacce.IMagazzino;

public abstract class SkeletonMagazzino implements IMagazzino{
	
	ServerSocket server;
	Socket client;
	final int porta = 8000;
	
	public void avvia_skeleton(){
		try{
			server = new ServerSocket(porta);
			System.out.println("\nSkeletonMagazzino : Avviato il server..");
			while(true){
				client = server.accept();
				Thread t = new SkeletonThread(this,client);
				t.start();
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
}
