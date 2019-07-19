package Entity;

import java.sql.SQLException;

import DAO.AutoDAO;
import DAO.AutomobilistaDAO;
import DAO.CorrispondenzaDAO;
import DAO.TransactionManager;
import DAO.TransactionManagerFactory;

public class Automobilista extends Utente {
	private String CodiceFiscale;
	private String Cognome;
	private String Nome;
	private String Email;
	
	public Automobilista() {
	}

	public String getCodiceFiscale() {
		return CodiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		CodiceFiscale = codiceFiscale;
	}

	public String getCognome() {
		return Cognome;
	}

	public void setCognome(String cognome) {
		Cognome = cognome;
	}

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		Nome = nome;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}
	
	public double getConto(String username,String password) {
		AutomobilistaDAO auto = new AutomobilistaDAO();
		TransactionManager tm = TransactionManagerFactory.createTransactionManager();
		try {
			tm.beginTransaction();
			return auto.readContoAutomobilista(tm, username, password);
		}catch(Exception e) {
			tm.rollbackTransaction();
			return -1;
		}
	}
	
	public boolean AggiornaConto(String username,String password,double CostoTotale) {
		AutomobilistaDAO auto = new AutomobilistaDAO();
		TransactionManager tm = TransactionManagerFactory.createTransactionManager();
		try {
			tm.beginTransaction();
			return auto.updateContoAutomobilista(tm, username, password, CostoTotale);
		}catch(Exception e) {
			tm.rollbackTransaction();
			return false;
		}
	}
	
	public boolean CaricaConto(String username, String password, double Importo) {
		AutomobilistaDAO auto = new AutomobilistaDAO();
		TransactionManager tm = TransactionManagerFactory.createTransactionManager();
		try {
			tm.beginTransaction();
			Importo = Importo * -1; //Aggiorno il conto in positivo
			return auto.updateContoAutomobilista(tm, username, password, Importo);
		}catch(Exception e) {
			tm.rollbackTransaction();
			return false;
		}
	}
	
	
	@SuppressWarnings("finally")
	public boolean AggiungiAuto(String Targa,String CFProprietario,String username){
	//boolean autopresente=false;
	//boolean autoassociata=false;
	AutoDAO aut = new AutoDAO();
	TransactionManager tm = TransactionManagerFactory.createTransactionManager();
	try {
		tm.beginTransaction();
		if(aut.readAuto(tm,Targa)) {
			//ok significa che l'auto � gi� inserita,
			return false;
		}
			
		else{
		//Altrimenti Creo l'auto
		
			tm.beginTransaction();
			if(aut.createAuto( tm, Targa, CFProprietario,username))
				return true;
	
		}
		}
	
		catch(Exception e) {
		tm.rollbackTransaction();
		return false;
	}
		finally {
			return true;
		}
	}
}
		
	
	
	

	
	
	

	

