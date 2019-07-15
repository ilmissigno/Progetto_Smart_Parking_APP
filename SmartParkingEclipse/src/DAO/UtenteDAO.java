package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UtenteDAO {
	
	
	//INVECE DI LOGIN CHIAMARLA READ UTENTE
	public boolean Login(TransactionManager tm, String username, String password) throws SQLException {
		
		tm.assertInTransaction();
		try (PreparedStatement pt = tm.getConnection()
				.prepareStatement("SELECT CF FROM UTENTI WHERE ((USERNAME=?)AND(PASSWORD=?))")) {

			pt.setString(1, username);
			pt.setString(2, password);
			try (ResultSet rs = pt.executeQuery()) {
				if (rs.next() == true) {
					
					return true;
				}
			}

		}
		return false;
	}
	
	/*
	public static String readUtente(TransactionManager tm, String username, String password) throws SQLException {

		String Utente = new String();
		Utente = "valorenullo";
		tm.assertInTransaction();
		try (PreparedStatement pt = tm.getConnection()
				.prepareStatement("SELECT CF FROM UTENTI WHERE ((USERNAME=?)AND(PASSWORD=?))")) {

			pt.setString(1, username);
			pt.setString(2, password);
			try (ResultSet rs = pt.executeQuery()) {
				if (rs.next() == true) {
					Utente = rs.getString("CF");
				}
			}

			System.out.println("Utente:" + Utente);
		}

		return Utente;
	}
	*/
	
	public static String updateUtente() {
		return null;
		
		
	}
	
	
	public static String createUtente() {
		return null;
		
	}
	
	public static String deleteTicket() {
		return null;
		
	}
	
}
