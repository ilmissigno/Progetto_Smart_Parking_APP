package Controller;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import ControllerImpl.DefaultGestoreSmartParkingBuilder;
import ControllerImpl.GestoreAccountImpl;

public class GestoreSmartParking implements IGestoreSmartParking{
	
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
			
		}
	}

	@Override
	public void AcquistaTicket(String targa, String codiceArea, double Durata,DataOutputStream out) {
		// TODO Auto-generated method stub
	}

	@Override
	public void VerificaTicket(String targa,DataOutputStream out) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void RinnovoTicket(String targa, double Durata, int IDTicket, double costoTicket,DataOutputStream out) {
		// TODO Auto-generated method stub
		
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
	public void CaricaConto() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void RegistraUtente(String CodiceFiscale, String Cognome, String Nome, String username, String password,
			String email,DataOutputStream out) {
		// TODO Auto-generated method stub
		
	}

}
