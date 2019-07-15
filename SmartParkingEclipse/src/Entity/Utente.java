package Entity;

import DAO.AutomobilistaDAO;
import DAO.TransactionManager;
import DAO.TransactionManagerFactory;
import DAO.UtenteDAO;

public class Utente {
	private String username;
	private String password;
	
	public Utente() {
		
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean Login(String username, String password) {
		UtenteDAO u = new UtenteDAO();
		TransactionManager tm = TransactionManagerFactory.createTransactionManager();
		try {
			tm.beginTransaction();
			if(u.readUtente(tm,username,password)) {
				//ok
				return true;
			}else {
				//errore
				return false;
			}
		}catch(Exception e) {
			tm.rollbackTransaction();
			return false;
		}
	}

	public boolean RegistraUtente(String CodiceFiscale, String Cognome, String Nome, String username, String password,
			String email) {
		UtenteDAO u = new UtenteDAO();
		TransactionManager tm = TransactionManagerFactory.createTransactionManager();
		try {
			tm.beginTransaction();
			if(u.createUtente(tm,CodiceFiscale,Cognome,Nome,username,password,email)) {
				//ok
				return true;
			}else {
				//errore
				return false;
			}
		}catch(Exception e) {
			tm.rollbackTransaction();
			return false;
		}
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
	
}

