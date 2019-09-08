package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UtenteDAO {
	
	private String CodiceFiscale;
	private String Cognome;
	private String Nome;
	private String username;
	private String password;
	private String Email;
	
	public UtenteDAO() {
		
	}
	
	public UtenteDAO(String CodiceFiscale,String Cognome,String Nome,String username,String password,String email) {
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
	
	
	//Chiamata readUtente al posto di Login
	public boolean readUtente(TransactionManager tm, String username, String password) throws SQLException {
		
		tm.assertInTransaction();
		try (PreparedStatement pt = tm.getConnection()
				.prepareStatement("SELECT * FROM AUTOMOBILISTI WHERE ((USERNAME=?)AND(PASSWORD=?))")) {
			
			pt.setString(1, username);
			pt.setString(2, password);
			try (ResultSet rs = pt.executeQuery()) {
				if (rs.next() == true) {
					this.setCodiceFiscale(rs.getString("CF"));
					this.setCognome(rs.getString("Cognome"));
					this.setEmail(rs.getString("email"));
					this.setNome(rs.getString("nome"));
					this.setPassword(rs.getString("password"));
					this.setUsername(rs.getString("username"));
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
				.prepareStatement("INSERT INTO AUTOMOBILISTI(CF,NOME,COGNOME,EMAIL,USERNAME,PASSWORD,CREDITO,ATTIVO) VALUES(?,?,?,?,?,?,?,?)")) {
			pt.setString(1, CodiceFiscale);
			pt.setString(2, Nome);
			pt.setString(3, Cognome);
			pt.setString(4, email);
			pt.setString(5, username);
			pt.setString(6, password);
			pt.setDouble(7,0);
			pt.setInt(8, 0);
			if(pt.executeUpdate()==1) {
				return true;
			}else {
				return false;
			}

		}
	}
	
	public static String deleteUtente() {
		return null;
		
	}
	
}
