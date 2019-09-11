package Entity;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import java.util.concurrent.TimeUnit;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import DAO.TicketDAO;
import DAO.TransactionManager;
import DAO.TransactionManagerFactory;
import DAO.AutoDAO;
import DAO.AutomobilistaDAO;

public class Ticket {
	//Il ticket non deve settarlo l'utente, va in automatico da DB
	private int IDTicket;
	private double Durata;
	private Auto auto;
	private AreaParcheggio AreaParcheggio;
	//dovrei crearmi anche un istanza di automobilista?
	private String username;
	private String ScadenzaTicket;
	private Timer Timer;
	private Automobilista automobilista;
	//Devo leggermi il ticket lo faccio con un costruttore avente solo l'ID


public Ticket() {
		
	}

public Ticket (int IDTicket, double durata, Automobilista automobilista, String ScadenzaTicket) {

	TicketDAO ticket = new TicketDAO();
	TransactionManager tm = TransactionManagerFactory.createTransactionManager();
	try {
		tm.beginTransaction();
		if(ticket.updateTicket(tm, IDTicket,ScadenzaTicket, durata)) {
			tm.commitTransaction();
			this.setDurata(durata);
			this.setAutomobilista(automobilista);;
			this.setScadenzaTicket(ScadenzaTicket);
			this.setIDTicket(IDTicket);
			
		}else {
			
		}
	}catch(Exception e) {
		tm.rollbackTransaction();
	}
}
public Ticket(String ScadenzaTicket,double Durata,Auto auto,AreaParcheggio area, Automobilista automobilista) {

	TicketDAO ticket = new TicketDAO();
	TransactionManager tm = TransactionManagerFactory.createTransactionManager();
	try {
		tm.beginTransaction();
		if(ticket.createTicket(tm,ScadenzaTicket, Durata,auto.getTarga(),automobilista.getUsername(),String.valueOf(area.getCodiceArea()))) {
			tm.commitTransaction();
			/*
			 * ATTIVAZIONE TIMER DI NOTIFICA
			 */
			//riempio l'oggetto ticket
			this.setAutomobilista(automobilista);
			this.setAreaParcheggio(area);
			this.setDurata(ticket.getDurata());
			this.setAuto(auto);
			this.setScadenzaTicket(ScadenzaTicket);
			tm.beginTransaction();
			ticket.readTicket(tm, auto.getTarga(), ScadenzaTicket);
			tm.commitTransaction();
			this.setIDTicket(ticket.getIDTicket());
			System.out.println(this.getIDTicket());
			
		}
	}catch(Exception e) {
		tm.rollbackTransaction();
		
	}
}


	
	public Ticket(int IDTicket, String username) {
		this.IDTicket=IDTicket;
		TicketDAO ticket = new TicketDAO();
		TransactionManager tm = TransactionManagerFactory.createTransactionManager();
		try {
			tm.beginTransaction();
			ticket.readsTicket(tm, this.IDTicket);
			tm.commitTransaction();
			this.setDurata(ticket.getDurata());
			this.setUsername(username);
			this.setScadenzaTicket(ticket.getScadenzaTicket());
			AreaParcheggio area= new AreaParcheggio(Integer.parseInt(ticket.getCodiceArea()));
			Auto auto=new Auto(ticket.getTargaAuto());
			this.setAreaParcheggio(area);
			this.setAuto(auto);
		}catch(Exception e) {
			tm.rollbackTransaction();
		}
	}
	
	public Ticket( String  Targa, String CodiceArea) {
			
	}
	

	public int getIDTicket() {
		return IDTicket;
	}

	public void setIDTicket(int iDTicket) {
		IDTicket = iDTicket;
	}

	public double getDurata() {
		return Durata;
	}

	public void setDurata(double durata) {
		Durata = durata;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getScadenzaTicket() {
		return ScadenzaTicket;
	}

	public void setScadenzaTicket(String scadenzaTicket) {
		ScadenzaTicket = scadenzaTicket;
	}

	public Auto getAuto() {
		return auto;
	}

	public void setAuto(Auto auto) {
		this.auto = auto;
	}
	
	public AreaParcheggio getAreaParcheggio() {
		return this.AreaParcheggio;
	}

	public void setAreaParcheggio(AreaParcheggio area) {
		this.AreaParcheggio = area;
	}


	public Timer getTimer() {
		return Timer;
	}

	public void setTimer(Timer timer) {
		Timer = timer;
	}
	
	public Automobilista getAutomobilista() {
		return automobilista;
	}

	public void setAutomobilista(Automobilista automobilista) {
		automobilista=automobilista;
	}

	
	
	public int OttieniTicket(String CodiceArea, String Targa) {
		TicketDAO ticket = new TicketDAO();
		TransactionManager tm = TransactionManagerFactory.createTransactionManager();
		try {
			tm.beginTransaction();
			int ID = ticket.readTicket(tm, CodiceArea, Targa);
			tm.commitTransaction();
			return ID;
		}catch(Exception e) {
			tm.rollbackTransaction();
			return -1;
		}
	}
	
	public double AvviaTimer() {
		return this.getDurata();
		
	}

	

	public boolean EliminaTicket(int IDTicket) {
		// TODO Auto-generated method stub
			TicketDAO ticket = new TicketDAO();
			TransactionManager tm = TransactionManagerFactory.createTransactionManager();
			try {
				tm.beginTransaction();
				if(ticket.deleteTicket(tm,IDTicket)) {
					tm.commitTransaction();
					return true;
				}else {
					return false;
				}
			}catch(Exception e) {
				tm.rollbackTransaction();
				return false;
			}
	}
	


	
}
	
	

