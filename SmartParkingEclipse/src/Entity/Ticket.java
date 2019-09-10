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
	//Devo leggermi il ticket lo faccio con un costruttore avente solo l'ID
	public Ticket() {
		
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

	public Ticket AcquistaTicket(Auto auto, AreaParcheggio area, double Durata,String username,String password) {
		String OraScadenza="";
		//Qui devo mandare alla boundary il costo totale del ticket
		//Pero una volta cliccato su acquista (bottone nella boundary) dovrebbe richiamare un altro metodo?
		//Non lo so, oppure dovrei solo leggere con lo stream?
		/*
		 * Cioe acquista ticket dovrebbe avere un outputstream e un input stream stesso che mi legge il comando
		 * di acquisto avviato
		 */
		//Funzionalità orario ecc...
		boolean OrarioMattina=false;
		Date date = new Date(); 
		//utilizzo tale formattazione così da aver una piena corrispondeza con il db
		//faccio una modifica qui al formato data->necessaria ad ottenere il rimborso
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String DataString=formatter.format(date).toString();
		//Le cifre sono intese da sinistra verso destra
		char Data1=DataString.charAt(8);
		char Data2=DataString.charAt(9);
		String data=new StringBuilder().append(Data1).append(Data2).toString();
		int  DataInt=Integer.parseInt(data);
		char Cifra1= DataString.charAt(11);
		char Cifra2=DataString.charAt(12);
		String orario=new StringBuilder().append(Cifra1).append(Cifra2).toString();
		int  OrarioInt=Integer.parseInt(orario);
		Durata=(int)Durata;
		int OraScadenzaTicket=(int)Durata+OrarioInt;
		OraScadenza=Integer.toString(OraScadenzaTicket);
		if(OraScadenzaTicket<10)
			OrarioMattina=true;
		if(OraScadenzaTicket>=24) {
			//passo al giorno successivo
			DataInt=DataInt+1;
			//la  mia base è 24, devo passare ad orario mattutino dopo le 24
			OraScadenzaTicket=OraScadenzaTicket-24;
			if(OraScadenzaTicket<10)
				OrarioMattina=true;
		}
		if(OrarioMattina) {
			OraScadenza=Integer.toString(OraScadenzaTicket);
			OraScadenza="0"+OraScadenza;

		}
		Cifra1=OraScadenza.charAt(0);
		Cifra2=OraScadenza.charAt(1);
		String DataScadenza=Integer.toString(DataInt);
		Data1=DataScadenza.charAt(0);
		Data2=DataScadenza.charAt(1);
		StringBuilder Scadenza=new StringBuilder();
		Scadenza.append(DataString);
		Scadenza.setCharAt(8, Data1);
		Scadenza.setCharAt(9, Data2);
		Scadenza.setCharAt(11, Cifra1);
		Scadenza.setCharAt(12, Cifra2);
		String ScadenzaTicket=Scadenza.toString();
		System.out.println(ScadenzaTicket);
		final double durat = Durata;
		TicketDAO ticket = new TicketDAO();
		TransactionManager tm = TransactionManagerFactory.createTransactionManager();
		try {
			tm.beginTransaction();
			if(ticket.createTicket(tm,ScadenzaTicket, Durata,auto.getTarga(),username,String.valueOf(area.getCodiceArea()))) {
				tm.commitTransaction();
				/*
				 * ATTIVAZIONE TIMER DI NOTIFICA
				 */
				//riempio l'oggetto ticket
				this.setAreaParcheggio(area);
				this.setDurata(ticket.getDurata());
				this.setAuto(auto);
				this.setUsername(username);
				this.setScadenzaTicket(ScadenzaTicket);
				tm.beginTransaction();
				ticket.readTicket(tm, auto.getTarga(), ScadenzaTicket);
				tm.commitTransaction();
				this.setIDTicket(ticket.getIDTicket());
				System.out.println(this.getIDTicket());
				/*Ticket outTicket = new Ticket();
				outTicket.setIDTicket(this.getIDTicket());
				outTicket.setCodiceArea(CodiceArea);
				outTicket.setDurata(Durata);
				outTicket.setTargaAuto(Targa);
				outTicket.setUsername(username);
				outTicket.setScadenzaTicket(ScadenzaTicket);
				return outTicket;
				*/
				//ritorno l'oggetto che ho creato
				return this;
			}else {
				return null;
			}
		}catch(Exception e) {
			tm.rollbackTransaction();
			return null;
		}
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
	
	public double TimerTicket() {
		return this.getDurata();
		
	}

	public Ticket RinnovaTicket(int IDTicket, double durata, String username, String password) {
		String OraScadenza="";
		//Qui devo mandare alla boundary il costo totale del ticket
		//Pero una volta cliccato su acquista (bottone nella boundary) dovrebbe richiamare un altro metodo?
		//Non lo so, oppure dovrei solo leggere con lo stream?
		/*
		 * Cioe acquista ticket dovrebbe avere un outputstream e un input stream stesso che mi legge il comando
		 * di acquisto avviato
		 */
		//Funzionalità orario ecc...
		boolean OrarioMattina=false;
		/*
		Date date = new Date(); 
		//utilizzo tale formattazione così da aver una piena corrispondeza con il db
		//faccio una modifica qui al formato data->necessaria ad ottenere il rimborso
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		*/
		String DataString=this.getScadenzaTicket();
		//Le cifre sono intese da sinistra verso destra
		char Data1=DataString.charAt(8);
		char Data2=DataString.charAt(9);
		String data=new StringBuilder().append(Data1).append(Data2).toString();
		int  DataInt=Integer.parseInt(data);
		char Cifra1= DataString.charAt(11);
		char Cifra2=DataString.charAt(12);
		String orario=new StringBuilder().append(Cifra1).append(Cifra2).toString();
		int  OrarioInt=Integer.parseInt(orario);
		durata=(int)durata;
		int OraScadenzaTicket=(int)durata+OrarioInt;
		OraScadenza=Integer.toString(OraScadenzaTicket);
		if(OraScadenzaTicket<10)
			OrarioMattina=true;
		if(OraScadenzaTicket>=24) {
			//passo al giorno successivo
			DataInt=DataInt+1;
			//la  mia base è 24, devo passare ad orario mattutino dopo le 24
			OraScadenzaTicket=OraScadenzaTicket-24;
			if(OraScadenzaTicket<10)
				OrarioMattina=true;
		}
		if(OrarioMattina) {
			OraScadenza=Integer.toString(OraScadenzaTicket);
			OraScadenza="0"+OraScadenza;

		}
		Cifra1=OraScadenza.charAt(0);
		Cifra2=OraScadenza.charAt(1);
		String DataScadenza=Integer.toString(DataInt);
		Data1=DataScadenza.charAt(0);
		Data2=DataScadenza.charAt(1);
		StringBuilder Scadenza=new StringBuilder();
		Scadenza.append(DataString);
		Scadenza.setCharAt(8, Data1);
		Scadenza.setCharAt(9, Data2);
		Scadenza.setCharAt(11, Cifra1);
		Scadenza.setCharAt(12, Cifra2);
		String ScadenzaTicket=Scadenza.toString();
		System.out.println(ScadenzaTicket);
		final double durat = durata;
		TicketDAO ticket = new TicketDAO();
		TransactionManager tm = TransactionManagerFactory.createTransactionManager();
		try {
			tm.beginTransaction();
			if(ticket.updateTicket(tm, IDTicket,ScadenzaTicket, durata)) {
				tm.commitTransaction();
				this.setDurata(durata);
				this.setUsername(username);
				this.setScadenzaTicket(ScadenzaTicket);
				this.setIDTicket(IDTicket);
				return this;
			}else {
				return null;
			}
		}catch(Exception e) {
			tm.rollbackTransaction();
			return null;
		}
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
	
	

