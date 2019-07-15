package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UtenteDAO {
	
	
	//Chiamata readUtente al posto di Login
	public boolean readUtente(TransactionManager tm, String username, String password) throws SQLException {
		
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
	
	
	public boolean createUtente(TransactionManager tm,String CodiceFiscale, String Cognome, String Nome, String username, String password,
			String email) throws SQLException{
		tm.assertInTransaction();
		try (PreparedStatement pt = tm.getConnection()
				.prepareStatement("INSERT INTO UTENTI(CF,COGNOME,NOME,USERNAME,PASSWORD,EMAIL) VALUES(?,?,?,?,?,?)")) {
			pt.setString(1, CodiceFiscale);
			pt.setString(2, Cognome);
			pt.setString(3, Nome);
			pt.setString(4, username);
			pt.setString(5, password);
			pt.setString(6, email);
			try (ResultSet rs = pt.executeQuery()) {
				if (rs.next() == true) {
					return true;
				}
			}

		}
		return false;
	}
	
	public static String deleteUtente() {
		return null;
		
	}
	
}
