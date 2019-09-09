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
	//
	@Override
	public boolean Login(String username, String password) {
		// TODO Auto-generated method stub
		if(account.Login(username,password)) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public double GetCostoTicket(String codiceArea, double Durata) {
		double costoTotale = ticket.OttieniCostoTicket(Integer.parseInt(codiceArea),Durata);
		//double costoTotale = Durata*costoSingolo;
		return costoTotale;
	}
	
	public Ticket AcquistaTicket(String targa, String codiceArea, double Durata,String username,String password,double costoTotale) {
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
		//Ticket t = new Ticket();
		if(account.ControllaConto(username, password,costoTotale)) {
		Ticket	t = ticket.ConfermaTicket(targa, codiceArea, Durata, costoTotale, username,password);
			if(account.AggiornaConto(username,password,costoTotale)) {
				return t;
			}else {
				return null;
			}
		}else {
			return null;
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
	public boolean RegistraUtente(String CodiceFiscale, String Cognome, String Nome, String username, String password,
			String email) {
		// TODO Auto-generated method stub
		if(account.RegistraUtente(CodiceFiscale, Cognome, Nome, username, password, email)) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean AggiungiAuto(String Targa, String CFProprietario, String username){
		if(account.AggiungiAuto(Targa,CFProprietario,username)) {
			return true;
		}else {
			return false;
		}
			
		}

	
	public ArrayList<String> OttieniListaAuto(String username) throws SQLException {
		ArrayList<String> listaAuto;
		try {
			listaAuto = new ArrayList<String>();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		listaAuto=account.OttieniLista(username);
		return listaAuto;
	}
		
	public void TimerTicket(String username, int IDTicket, DataOutputStream out) {
		ticket.TimerTicket(username,IDTicket,out);
			
		
	}

	@Override
	public Ticket RinnovaTicket(int IDTicket, double durata, String username, String password,double costoTotale) {
		// TODO Auto-generated method stub
			double conto = account.getConto(username, password);
			if(conto>=costoTotale) {
				Ticket t = ticket.RinnovaTicket(IDTicket, durata, costoTotale, username,password);
					if(account.AggiornaConto(username,password,costoTotale)) {
						return t;
					}else {
						return null;
					}
			}else {
				return null;
			}
	}

	@Override
	public boolean CaricaConto(String username,String password ,double Importo) {
			if(account.CaricaConto(username, password,Importo)) {
				return true;
			}else {
				return false;
			}
	}

	@Override
	public boolean EliminaTicket(int IDTicket) {
		// TODO Auto-generated method stub
			if(ticket.EliminaTicket(IDTicket)) {
				return true;
			}else {
				return false;
			}
	}

	@Override
	public boolean EliminaAuto(String targa,String username) {
		// TODO Auto-generated method stub
			if(account.EliminaAuto(targa,username)) {
				return true;
			}else {
				return false;
			}
	}

	@Override
	public double LeggiCredito(String username, String password) {
			double Credito=account.getConto(username,password);
			return Credito;
		}
	public boolean ArrestaSosta(int IDTicket, String username) {
		
		double ImportoRimborso=ticket.TrovaRimborso(IDTicket,username);
		
		
		return false;
		
		
		
	}
}
	
		
	

	
	
	
		
	

	
		
	
	
	


