package Entity;

import DAO.UtenteDAO;

public class Utente {
	String username;
	String password;
	
	public boolean Login(String username, String password) {
		UtenteDAO u = new UtenteDAO();
		if(u.Login(username,password)) {
			//ok
			return true;
		}else {
			//errore
			return false;
		}
	}
	
}

