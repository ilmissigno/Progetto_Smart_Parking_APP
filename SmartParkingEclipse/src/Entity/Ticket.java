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
				tm.beginTransaction();
							int ID = ticket.readTicket(tm, CodiceArea, Targa);
							tm.commitTransaction();
							out.writeBoolean(true);
							out.flush();
							out.writeInt(ID);
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
	
	public void TimerTicket(String username, int IDTicket,DataOutputStream out) {
		TicketDAO ticket = new TicketDAO();
		TransactionManager tm = TransactionManagerFactory.createTransactionManager();
		try {
			tm.beginTransaction();
			double durata= ticket.readTicket(tm, IDTicket);
			int durataInt=(int)durata;
			//forse posso modularizzare di piu il codice , per ora lo metto qua
			//calcolo il tempo dopo il quale deve scattare la notifica
			int ScattoTimer_secondi=durataInt*3600;
		    Timer timer = new Timer();
			timer.schedule(new TimerTask() {
				//questa funzione run vuole void e mi obbliga a scrivere qua e non nello skeleton
				//sideve provare
	            @Override
	            public void run() {
	                //System.out.println("CIAO!");
	            	try {
						out.writeBoolean(true);
						out.flush();
						return;
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
	            	//significa che � scattato
	            }
	            //Alla scadenza del timer parte la notifica e si ripete ogni 100 secondi
	        },5000);
			return;
		}catch(Exception e) {
			tm.rollbackTransaction();
			
		}
		
	}
	
}
