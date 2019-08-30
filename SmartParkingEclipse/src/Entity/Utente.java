package Entity;

import DAO.AutomobilistaDAO;
import DAO.TransactionManager;
import DAO.TransactionManagerFactory;
import DAO.UtenteDAO;

public class Utente {
	private String CodiceFiscale;
	private String Cognome;
	private String Nome;
	private String username;
	private String password;
	private String Email;
	
	public Utente() {
		
	}
	
	public Utente(String CodiceFiscale,String Cognome,String Nome,String username,String password,String email) {
		this.setCodiceFiscale(CodiceFiscale);
		this.setCognome(Cognome);
		this.setNome(Nome);
		this.setUsername(username);
		this.setPassword(password);
		this.setEmail(email);
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

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
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

