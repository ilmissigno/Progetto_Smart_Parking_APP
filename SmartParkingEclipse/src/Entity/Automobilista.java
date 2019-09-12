package Entity;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import DAO.AutoDAO;
import DAO.AutomobilistaDAO;
import DAO.CorrispondenzaDAO;
import DAO.TicketDAO;
import DAO.TransactionManager;
import DAO.TransactionManagerFactory;

public class Automobilista extends Utente {
private double Credito;
private ArrayList<Auto> ListaAuto;

	public Automobilista() {
		
	}

	public Automobilista(String username, String Password) {
		this.ListaAuto = new ArrayList<Auto>();
		AutomobilistaDAO autoDAO=new AutomobilistaDAO();
		TransactionManager tm = TransactionManagerFactory.createTransactionManager();
		try {
			tm.beginTransaction();
		autoDAO.readAutomobilista(tm,username,Password);
		tm.commitTransaction();
		this.CodiceFiscale=autoDAO.getCodiceFiscale();
		this.Cognome=autoDAO.getCognome();
		this.Email=autoDAO.getEmail();
		this.Nome=autoDAO.getNome();
		this.password=autoDAO.getPassword();
		this.username=autoDAO.getUsername();
		this.Credito=autoDAO.getCredito();
	}catch(Exception e) {
		tm.rollbackTransaction();
		
	}
	}
	
	/*
	public Automobilista(String username) {
		
		this.ListaAuto = new ArrayList<Auto>();
		AutomobilistaDAO autoDAO=new AutomobilistaDAO();
		TransactionManager tm = TransactionManagerFactory.createTransactionManager();
		try {
			tm.beginTransaction();
		autoDAO.readAutomobilista(tm,username);
		tm.commitTransaction();
		this.CodiceFiscale=autoDAO.getCodiceFiscale();
		this.Cognome=autoDAO.getCognome();
		this.Email=autoDAO.getEmail();
		this.Nome=autoDAO.getNome();
		this.password=autoDAO.getPassword();
		this.username=username;
		this.Credito=autoDAO.getCredito();
		System.out.println(this.password);
	}catch(Exception e) {
		tm.rollbackTransaction();
		
	}
	}
	
	*/
	public double getCredito() {
		return Credito;
	}

	public void setCredito(double credito) {
		this.Credito = credito;
	}
	
	public ArrayList<Auto> getListaAuto() {
		return ListaAuto;
	}

	public void setListaAuto(ArrayList<Auto> listaAuto) {
		this.ListaAuto = listaAuto;
	}
	/*
	public double getConto(String username,String password) {
		AutomobilistaDAO auto = new AutomobilistaDAO();
		TransactionManager tm = TransactionManagerFactory.createTransactionManager();
		try {
			tm.beginTransaction();
			auto.readContoAutomobilista(tm, username, password);
			tm.commitTransaction();
			this.setUsername(username);
			this.setPassword(password);
			this.setCredito(auto.getCredito());
			return this.getCredito();
		}catch(Exception e) {
			tm.rollbackTransaction();
			return -1;
		}
	}
	*/
	public boolean AggiornaConto(double CostoTotale) {
		AutomobilistaDAO auto = new AutomobilistaDAO(this.getUsername(),this.getPassword());
		TransactionManager tm = TransactionManagerFactory.createTransactionManager();
		try {
			tm.beginTransaction();
			if(auto.updateContoAutomobilista(tm,CostoTotale)) {
				tm.commitTransaction();
				this.setUsername(this.getUsername());
				this.setPassword(this.getPassword());
				this.setCredito(this.getCredito()-CostoTotale);
				return true;
			}else {
				return false;
			}
		}catch(Exception e) {
			tm.rollbackTransaction();
			return false;
		}
	}
/*	
	public boolean CaricaConto(double Importo) {
		AutomobilistaDAO auto = new AutomobilistaDAO(this.getUsername(),this.getPassword());
		TransactionManager tm = TransactionManagerFactory.createTransactionManager();
		double CostoTotale = Importo*-1;
		try {
			tm.beginTransaction();
			if(auto.updateContoAutomobilista(tm,CostoTotale)) {
				tm.commitTransaction();
				this.setUsername(this.getUsername());
				this.setPassword(this.getPassword());
				this.setCredito(this.getCredito()+Importo);
				return true;
			}else {
				return false;
			}
		}catch(Exception e) {
			tm.rollbackTransaction();
			return false;
		}
	}
	*/

	public boolean AggiungiAuto(String targa, String CFProprietario) {
		// TODO Auto-generated method stub
		Auto auto=new Auto(targa,CFProprietario);
		//this.ListaAuto.add(auto);
		AutomobilistaDAO automobilista = new AutomobilistaDAO(this.getUsername(),this.getPassword());
		TransactionManager tm = TransactionManagerFactory.createTransactionManager();
		try {
			tm.beginTransaction();
			if(automobilista.addAtList(tm,auto)) {
				tm.commitTransaction();
				return true;
			}
			else {
				return false;
			}
		
			
		}catch(Exception e) {
			tm.rollbackTransaction();
			return false;
		}
		
	}

	public ArrayList<String> OttieniListaAuto() throws SQLException {
		// TODO Auto-generated method stub
		AutomobilistaDAO automobilistaDAO=new AutomobilistaDAO();
		TransactionManager tm = TransactionManagerFactory.createTransactionManager();
		ArrayList<String> listaAuto = new ArrayList<String>();
		try {
			tm.beginTransaction();

			listaAuto = automobilistaDAO.readList(tm, this.getUsername());
			tm.commitTransaction();
			for(int i=0;i<listaAuto.size();i++) {
				Auto auto= new Auto(listaAuto.get(i));
				this.ListaAuto.add(auto);
		}

		}catch (Exception e) {
			tm.rollbackTransaction();
			throw new SQLException("Impossibile ottenere Lista.");
		}
		return listaAuto;
		

	}
		
	
		
	

public Ticket AcquistaTicket(Auto auto, AreaParcheggio area, double Durata) {
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
	Ticket t= new Ticket(ScadenzaTicket,Durata,auto,area,this);
	return t;
	
	
}


public Ticket RinnovaTicket(int IDTicket, double durata) {
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
	Ticket t = new Ticket(IDTicket,this.getUsername());
	String DataString=t.getScadenzaTicket();
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
	Ticket tick= new Ticket(t.getIDTicket(),durata, this,ScadenzaTicket);
	return tick;
}
	
}
		

	
	

	
	
	

	

