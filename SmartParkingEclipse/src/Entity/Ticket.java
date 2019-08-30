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
import DAO.AutomobilistaDAO;

public class Ticket {
	//Il ticket non deve settarlo l'utente, va in automatico da DB
	private int IDTicket;
	private double Durata;
	private String TargaAuto;
	private String CodiceArea;
	private String username;
	private String ScadenzaTicket;
	
	public Ticket() {
		
	}

	public int getIDTicket() {
		return IDTicket;
	}

	public void setIDTicket(int iDTicket) {
		//IDTicket = iDTicket;
	}

	public double getDurata() {
		return Durata;
	}

	public void setDurata(double durata) {
		Durata = durata;
	}

	public String getTargaAuto() {
		return TargaAuto;
	}

	public void setTargaAuto(String targaAuto) {
		TargaAuto = targaAuto;
	}

	public String getCodiceArea() {
		return CodiceArea;
	}

	public void setCodiceArea(String codiceArea) {
		CodiceArea = codiceArea;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean AcquistaTicket(String Targa, String CodiceArea, double Durata,String username,String password,DataOutputStream out) {
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
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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
			if(ticket.createTicket(tm,ScadenzaTicket, Durata,Targa,username,CodiceArea)) {
				tm.commitTransaction();
				/*
				 * ATTIVAZIONE TIMER DI NOTIFICA
				 */
				this.setCodiceArea(CodiceArea);
				this.setDurata(Durata);
				this.setTargaAuto(Targa);
				this.setUsername(username);
				this.setScadenzaTicket(ScadenzaTicket);
				
				tm.beginTransaction();
							
							this.setIDTicket(ticket.readTicket(tm, Targa,ScadenzaTicket));
							tm.commitTransaction();
							out.writeBoolean(true);
							out.flush();
							out.writeInt(this.getIDTicket());
							out.flush();
							out.writeUTF(Targa);
							out.flush();
							out.writeUTF(CodiceArea);
							out.flush();
							out.writeUTF(ScadenzaTicket);
							out.flush();
				return true;
			}else {
				return false;
			}
		}catch(Exception e) {
			tm.rollbackTransaction();
			return false;
		}
	}
	
	public int OttieniTicket(String CodiceArea, String Targa) {
		TicketDAO ticket = new TicketDAO();
		TransactionManager tm = TransactionManagerFactory.createTransactionManager();
		try {
			tm.beginTransaction();
			return ticket.readTicket(tm, CodiceArea, Targa);
		}catch(Exception e) {
			tm.rollbackTransaction();
			return -1;
		}
	}
	
	public double TimerTicket(String username, int IDTicket) {
		TicketDAO ticket = new TicketDAO();
		TransactionManager tm = TransactionManagerFactory.createTransactionManager();
		try {
			tm.beginTransaction();
			this.setDurata(ticket.readTicket(tm, IDTicket));
			this.setCodiceArea(ticket.getCodiceArea());
			this.setTargaAuto(ticket.getUsername());
			this.setUsername(username);
			this.setScadenzaTicket(ticket.getScadenzaTicket());
			this.setIDTicket(IDTicket);
			return this.getDurata();
		}catch(Exception e) {
			tm.rollbackTransaction();
			return -1;
		}
		
	}

	public boolean RinnovaTicket(int IDTicket, double durata, String username, String password,
			DataOutputStream out) {
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
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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
							out.writeBoolean(true);
							out.flush();
							out.writeInt(IDTicket);
							out.flush();
							out.writeUTF(ScadenzaTicket);
							out.flush();
				return true;
			}else {
				return false;
			}
		}catch(Exception e) {
			tm.rollbackTransaction();
			return false;
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

	public String getScadenzaTicket() {
		return ScadenzaTicket;
	}

	public void setScadenzaTicket(String scadenzaTicket) {
		ScadenzaTicket = scadenzaTicket;
	}
}
	
	

