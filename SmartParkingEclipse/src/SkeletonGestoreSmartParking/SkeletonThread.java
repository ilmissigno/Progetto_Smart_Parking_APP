package SkeletonGestoreSmartParking;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import Controller.IGestoreSmartParking;

public class SkeletonThread extends Thread{
	IGestoreSmartParking iserver;
	Socket client;
	
	public SkeletonThread(IGestoreSmartParking iserver, Socket client) {
		this.iserver = iserver;
		this.client = client;
	}
	//
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
					String codicearea = in.readUTF();
					double durata = in.readDouble();
					iserver.GetCostoTicket(codicearea, durata, out);
					break;
				}
				case "acquistasend":{
					String targa = in.readUTF();
					String codicearea = in.readUTF();
					double Durata = in.readDouble();
					double CostoTotale = in.readDouble();
					String username = in.readUTF();
					String password = in.readUTF();
					iserver.AcquistaTicket(targa, codicearea, Durata, username, password, CostoTotale, out);
					break;
				}
				case "addautosend":{
					String targa = in.readUTF();
					String CFProprietario=in.readUTF();
					String username = in.readUTF();
					System.out.println("\nSkeletonThread : username = "+username+" targa = "+targa+" CF= "+CFProprietario);
					iserver.AggiungiAuto(targa,CFProprietario, username, out);
					break;
				}
				
				case "caricaautosend":{
					String username = in.readUTF();
					System.out.println("\nSkeletonThread : username = "+username);
					iserver.OttieniListaAuto(username, out);
					break;
				}
				case "dataorariosend":{
					Date date = new Date(); 
					//utilizzo tale formattazione cos� da aver una piena corrispondeza con il db
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					out.writeBoolean(true);
					out.flush();
					out.writeUTF(formatter.format(date));
					out.flush();
					break;
				}
				// mi arriva questo comando
				case "Notificasend":{ 
					String username = in.readUTF();
					int IDTicket=in.readInt();
					iserver.TimerTicket(username,IDTicket,out);
					break;
				}
			}
		}catch(IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}


