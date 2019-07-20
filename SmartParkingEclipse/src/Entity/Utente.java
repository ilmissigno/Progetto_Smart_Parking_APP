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
				tm.commitTransaction();
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
				tm.commitTransaction();
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
}

