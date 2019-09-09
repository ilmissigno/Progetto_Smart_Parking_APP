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
import java.util.ArrayList;
import java.util.Date;

import Controller.IGestoreSmartParking;
import Entity.Ticket;

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
					if(iserver.Login(username, password)) {
						out.writeBoolean(true);
						out.flush();
					}else {
						out.writeBoolean(false);
						out.flush();
					}
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
					if(iserver.RegistraUtente(codicefiscale, cognome, nome, username, password, email)) {
						out.writeBoolean(true);
						out.flush();
					}else {
						out.writeBoolean(false);
						out.flush();
					}
					break;
				}
				case "costoticketsend":{
					String codicearea = in.readUTF();
					double durata = in.readDouble();
					double costoTotale = iserver.GetCostoTicket(codicearea, durata);
					out.writeDouble(costoTotale);
					out.flush();
					break;
				}
				case "acquistasend":{
					String targa = in.readUTF();
					String codicearea = in.readUTF();
					double Durata = in.readDouble();
					double CostoTotale = in.readDouble();
					String username = in.readUTF();
					String password = in.readUTF();
					Ticket t = iserver.AcquistaTicket(targa, codicearea, Durata, username, password, CostoTotale);
					out.writeBoolean(true);
					out.flush();
					out.writeInt(t.getIDTicket());
					out.flush();
					out.writeUTF(t.getAuto().getTarga());
					out.flush();
					// modificato qua perchè ora ho gli oggetti
					out.writeUTF(String.valueOf(t.getAreaParcheggio().getCodiceArea()));
					out.flush();
					out.writeUTF(t.getScadenzaTicket());
					out.flush();
					out.writeBoolean(true);
					out.flush();
					break;
				}
				case "addautosend":{
					String targa = in.readUTF();
					String CFProprietario=in.readUTF();
					String username = in.readUTF();
					System.out.println("\nSkeletonThread : username = "+username+" targa = "+targa+" CF= "+CFProprietario);
					if(iserver.AggiungiAuto(targa,CFProprietario, username)) {
						out.writeBoolean(true);
						out.flush();
					}else {
						out.writeBoolean(false);
						out.flush();
					}
					break;
				}
				
				case "caricaautosend":{
					String username = in.readUTF();
					System.out.println("\nSkeletonThread : username = "+username);
					ArrayList<String> listaAuto = iserver.OttieniListaAuto(username);
					out.writeBoolean(true);
					out.flush();
					out.writeInt(listaAuto.size());
					out.flush();
					for(int i=0;i<listaAuto.size();i++) {
						out.writeUTF(listaAuto.get(i));
						out.flush();
					}
					break;
				}
				case "dataorariosend":{
					Date date = new Date(); 
					//utilizzo tale formattazione cosï¿½ da aver una piena corrispondeza con il db
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
				
				case "rinnovosend":{ 
					int IDTicket=in.readInt();
					double durata=in.readDouble();
					String username=in.readUTF();
					String pass=in.readUTF();
					double costoTotale=in.readDouble();
					Ticket t = iserver.RinnovaTicket(IDTicket,durata,username,pass,costoTotale);
					out.writeBoolean(true);
					out.flush();
					out.writeInt(t.getIDTicket());
					out.flush();
					out.writeUTF(t.getScadenzaTicket());
					out.flush();
					break;
				}
				
				case "caricacontosend":{ 
					String username=in.readUTF();
					double quantita=in.readDouble();
					String password=in.readUTF();
					if(iserver.CaricaConto(username,password,quantita)) {
						out.writeBoolean(true);
						out.flush();
					}else {
						out.writeBoolean(false);
						out.flush();
					}
					break;
				}
				
				case "getcreditosend":{
					String username=in.readUTF();
					String password=in.readUTF();
					double Credito = iserver.LeggiCredito(username,password);
					out.writeBoolean(true);
					out.flush();
					out.writeDouble(Credito);
					out.flush();
				}
				case "eliminaticketsend":{ 
					int IDTicket=in.readInt();
					if(iserver.EliminaTicket(IDTicket)) {
						out.writeBoolean(true);
						out.flush();
					}else {
						out.writeBoolean(false);
						out.flush();
					}
					break;
				}
				
				case "deleteautosend":{ 
					String Targa=in.readUTF();
					String username=in.readUTF();
					if(iserver.EliminaAuto(Targa,username)) {
						out.writeBoolean(true);
						out.flush();
					}else {
						out.writeBoolean(false);
						out.flush();
					}
					break;
				}
				
				case "finesostasend":{ 
					int IDTicket=in.readInt();
					String username=in.readUTF();
					String password=in.readUTF();
					if(iserver.ArrestaSosta(IDTicket,username,password)) {
						out.writeBoolean(true);
						out.flush();
					}else {
						out.writeBoolean(false);
						out.flush();
					}
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


