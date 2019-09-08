package Entity;

import java.sql.SQLException;
import java.util.ArrayList;

import DAO.AutoDAO;
import DAO.AutomobilistaDAO;
import DAO.CorrispondenzaDAO;
import DAO.TransactionManager;
import DAO.TransactionManagerFactory;

public class Automobilista extends Utente {
private double Credito;
private ArrayList<Auto> ListaAuto;

	public Automobilista() {
		
	}
	
	public Automobilista(String username, String Password) {
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
	
	public Automobilista(String username) {
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
		this.username=autoDAO.getUsername();
		this.Credito=autoDAO.getCredito();
	}catch(Exception e) {
		tm.rollbackTransaction();
		
	}
	}
	
	
	public double getCredito() {
		return Credito;
	}

	public void setCredito(double credito) {
		Credito = credito;
	}
	
	public ArrayList<Auto> getListaAuto() {
		return ListaAuto;
	}

	public void setListaAuto(ArrayList<Auto> listaAuto) {
		ListaAuto = listaAuto;
	}
	
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
	
	public boolean addAutoAtList(Auto a) {
		//Aggiunta l'auto alla lista dell'utente
		this.ListaAuto.add(a);
		//Cosa fare?
		return true;
	}



	
	
}
		
	
	
	

	
	
	

	

