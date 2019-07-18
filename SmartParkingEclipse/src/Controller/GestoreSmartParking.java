package Controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

import ControllerImpl.DefaultGestoreSmartParkingBuilder;
import ControllerImpl.GestoreAccountImpl;
import Entity.Ticket;
import SkeletonGestoreSmartParking.SkeletonServer;

public class GestoreSmartParking  extends SkeletonServer implements IGestoreSmartParking{
	
	private static GestoreSmartParking instance;
	private GestoreAccount account;
	private GestoreMulta multa;
	private GestoreTicket ticket;
	private Socket client;
	
	protected GestoreSmartParking(GestoreTicket ticket, GestoreMulta multa, GestoreAccount account) {
		super();
		this.account = account;
		this.multa=multa;
		this.ticket=ticket;
	}
	
	//Singleton
	public static GestoreSmartParking getInstance() {
		if(instance==null) {
			instance = new DefaultGestoreSmartParkingBuilder().build();
		}
		return instance;
	}
	
	public abstract static class GestoreSmartParkingBuilder{
		protected GestoreSmartParking build(GestoreTicket ticket,GestoreMulta multa,GestoreAccount account) {
			return new GestoreSmartParking(ticket,multa,account);
		}
		public abstract GestoreSmartParking build();
	}
	
	@Override
	public void Login(String username, String password, DataOutputStream out) {
		// TODO Auto-generated method stub
		if(account.Login(username,password)) {
			try {
				out.writeUTF("ok");
				out.flush();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}else {
			return;
		}
	}

	@Override
	
	public void AcquistaTicket(String targa, String codiceArea, double Durata,String username,String password,DataOutputStream out,DataInputStream in) {
		//double costoSingolo = ticket.OttieniCostoTicket(codiceArea);
		double costoSingolo=2;
		//Qui devo mandare alla boundary il costo totale del ticket
		//Pero una volta cliccato su acquista (bottone nella boundary) dovrebbe richiamare un altro metodo?
		//Non lo so, oppure dovrei solo leggere con lo stream?
		/*
		 * Cioe acquista ticket dovrebbe avere un outputstream e un input stream stesso che mi legge il comando
		 * di acquisto avviato
		 */
		//Funzionalità orario ecc...
		
		 
		
			
			//giorno successivo
		//orarioattuale+durata%24 resto mod 24
		//}
		double costoTotale = Durata*costoSingolo;
		try {
			out.writeDouble(costoTotale);
			out.flush();
			//qua ci posso mettere altro out?? con la data finale?
			//Qui leggo il comando
			String cmd = in.readUTF();
			if(cmd.equals("acquistasend")) {
				//verifico il conto
				double conto = account.getConto(username, password);
				if(conto>=costoTotale) {
					if(ticket.ConfermaTicket(targa, codiceArea, Durata, costoTotale, username,password,out)) {
						if(account.AggiornaConto(username,password,costoTotale)) {
							out.writeUTF("ok");
							out.flush();
						}else {
							out.writeUTF("no");
							out.flush();
						}
					}else {
						out.writeUTF("no");
						out.flush();
					}
				}else {
					out.writeUTF("no");
					out.flush();
				}
			}else {
				//Errore
				return;
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void VerificaTicket(String targa, String CodiceArea,DataOutputStream out) {
		try {
			Ticket t = new Ticket();
			t = ticket.VerificaCopertura(CodiceArea, targa);
			out.writeUTF("ok");
			out.flush();
			out.writeUTF(t.getTargaAuto());
			out.flush();
			out.writeUTF(t.getCodiceArea());
			out.flush();
			out.writeInt(t.getIDTicket());
			out.flush();
			out.writeDouble(t.getDurata());
			out.flush();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void RinnovoTicket(String targa, double Durata, int IDTicket, double costoTicket,DataOutputStream out) {
		// TODO Auto-generated method stub
		//AMALFITANO
	}

	@Override
	public void EffettuaMulta() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void PagaMulta() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void RicercaMulta() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void CaricaConto(String username, String password, double Importo, DataOutputStream out) {
		try {
			if(account.CaricaConto(username, password, Importo)) {
				out.writeUTF("ok");
				out.flush();
			}else {
				out.writeUTF("no");
				out.flush();
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void RegistraUtente(String CodiceFiscale, String Cognome, String Nome, String username, String password,
			String email,DataOutputStream out) {
		// TODO Auto-generated method stub
		if(account.RegistraUtente(CodiceFiscale, Cognome, Nome, username, password, email)) {
			try {
				out.writeUTF("ok");
				out.flush();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}else {
			return;
		}
	}
	
	public void AggiungiAuto(String username,String password) {
	}
	}


