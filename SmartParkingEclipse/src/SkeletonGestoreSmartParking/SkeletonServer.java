package SkeletonGestoreSmartParking;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import Controller.IGestoreSmartParking;

public  abstract class SkeletonServer  implements IGestoreSmartParking{
	ServerSocket server;
	Socket client;
	final int porta = 8001;
	
	public void avvia_skeleton() {
		try {
			server = new ServerSocket(porta);
			while(true) {
				client = server.accept();
				Thread t = new SkeletonThread(this,client);
				t.start();
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
}
