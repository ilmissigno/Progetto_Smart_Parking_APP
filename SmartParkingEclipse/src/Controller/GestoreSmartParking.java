package Controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import ControllerImpl.DefaultGestoreSmartParkingBuilder;
import ControllerImpl.GestoreAccountImpl;
import Entity.Corrispondenza;
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
				out.writeBoolean(true);;
				out.flush();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}else {
			return;
		}
	}

	@Override
	public void GetCostoTicket(String codiceArea, double Durata, DataOutputStream out) {
		double costoSingolo = ticket.OttieniCostoTicket(codiceArea);
		double costoTotale = Durata*costoSingolo;
		try {
			out.writeDouble(costoTotale);
			out.flush();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void AcquistaTicket(String targa, String codiceArea, double Durata,String username,String password,double costoTotale,DataOutputStream out) {
		//Qui devo mandare alla boundary il costo totale del ticket
		//Pero una volta cliccato su acquista (bottone nella boundary) dovrebbe richiamare un altro metodo?
		//Non lo so, oppure dovrei solo leggere con lo stream?
		/*
		 * Cioe acquista ticket dovrebbe avere un outputstream e un input stream stesso che mi legge il comando
		 * di acquisto avviato
		 */
		//Funzionalit� orario ecc...




		//giorno successivo
		//orarioattuale+durata%24 resto mod 24
		//}
		//verifico il conto
		try {
			double conto = account.getConto(username, password);
			if(conto>=costoTotale) {
				if(ticket.ConfermaTicket(targa, codiceArea, Durata, costoTotale, username,password,out)) {
					if(account.AggiornaConto(username,password,costoTotale)) {
						out.writeBoolean(true);
						out.flush();
					}else {
						out.writeBoolean(false);
						out.flush();
					}
				}else {
					out.writeBoolean(false);
					out.flush();
				}
			}else {
				out.writeBoolean(false);
				out.flush();
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void VerificaTicket(String targa, String CodiceArea,DataOutputStream out) {
		try {
			int t = ticket.VerificaCopertura(CodiceArea, targa);
			out.writeUTF("ok");
			out.flush();
			out.writeUTF(targa);
			out.flush();
			out.writeUTF(CodiceArea);
			out.flush();
			out.writeInt(t);
			out.flush();
			out.writeDouble(0);
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
	public void RegistraUtente(String CodiceFiscale, String Cognome, String Nome, String username, String password,
			String email,DataOutputStream out) {
		// TODO Auto-generated method stub
		if(account.RegistraUtente(CodiceFiscale, Cognome, Nome, username, password, email)) {
			try {
				out.writeBoolean(true);
				out.flush();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}else {
			return;
		}
	}
	
	public void AggiungiAuto(String Targa, String CFProprietario, String username, DataOutputStream out){
		if(account.AggiungiAuto(Targa,CFProprietario,username)) {
			try {
				out.writeBoolean(true);
				out.flush();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}else {
			return;
		}
			
		}

	
	public void  OttieniListaAuto(String username, DataOutputStream out) throws SQLException {
		ArrayList<String> listaAuto;
		try {
			listaAuto = new ArrayList<String>();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		listaAuto=account.OttieniLista(username);
		try {
			out.writeBoolean(true);
			out.flush();
			out.writeInt(listaAuto.size());
			out.flush();
			for(int i=0;i<listaAuto.size();i++) {
				out.writeUTF(listaAuto.get(i));
				out.flush();
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
		
	public void TimerTicket(String username, int IDTicket, DataOutputStream out) {
		ticket.TimerTicket(username,IDTicket,out);
			
		
	}

	@Override
	public void RinnovaTicket(int IDTicket, double durata, String username, String password,double costoTotale, DataOutputStream out) {
		// TODO Auto-generated method stub
		
		try {
			double conto = account.getConto(username, password);
			if(conto>=costoTotale) {
				if(ticket.RinnovaTicket(IDTicket, durata, costoTotale, username,password,out)) {
					if(account.AggiornaConto(username,password,costoTotale)) {
						out.writeBoolean(true);
						out.flush();
					}else {
						out.writeBoolean(false);
						out.flush();
					}
				}else {
					out.writeBoolean(false);
					out.flush();
				}
			}else {
				out.writeBoolean(false);
				out.flush();
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void CaricaConto(String username,String password ,double Importo, DataOutputStream out) {
		try {
			if(account.CaricaConto(username, password,Importo)) {
				out.writeBoolean(true);
				out.flush();
			}else {
				out.writeBoolean(false);
				out.flush();
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void EliminaTicket(int IDTicket, DataOutputStream out) {
		// TODO Auto-generated method stub
		try {
			if(ticket.EliminaTicket(IDTicket)) {
				out.writeBoolean(true);
				out.flush();
			}else {
				out.writeBoolean(false);
				out.flush();
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void EliminaAuto(String targa,String username, DataOutputStream out) {
		// TODO Auto-generated method stub
		
		try {
			if(account.EliminaAuto(targa,username)) {
				out.writeBoolean(true);
				out.flush();
			}else {
				out.writeBoolean(false);
				out.flush();
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void LeggiCredito(String username, String password,DataOutputStream out) {
			double Credito=account.getConto(username,password);
			try {
				out.writeBoolean(true);
				out.flush();
				out.writeDouble(Credito);
				out.flush();
			}catch(IOException e) {
				e.printStackTrace();
			}
			
		}

}
	
		
	

	
	
	
		
	

	
		
	
	
	


