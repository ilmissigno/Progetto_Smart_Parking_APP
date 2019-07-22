package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Entity.Ticket;

public class TicketDAO {
	
	public boolean createTicket(TransactionManager tm, String DataScadenza, double Durata, String Targa,String username,String CodiceArea) throws Exception {
		tm.assertInTransaction();
		try (PreparedStatement ps = tm.getConnection()
				.prepareStatement("INSERT INTO TICKET(IDTicket,durata,datafine,Targa,username,CodiceArea) VALUES(NULL,?,?,?,?,?)")) {

			ps.setDouble(1, Durata);
			ps.setString(2, DataScadenza);
			ps.setString(3, Targa);
			ps.setString(4, username);
			ps.setInt(5, Integer.parseInt(CodiceArea));

			if(ps.executeUpdate()==1) {
				return true;
			}else {
				return false;
			}
		}
			
/*
			tm.beginTransaction();
			tm.assertInTransaction();
			try (PreparedStatement pt = tm.getConnection()
					.prepareStatement("SELECT IDTicket FROM TICKET WHERE ((TARGA=?)AND(CODICEAREA=?))")) {

				pt.setString(1, Targa);
				pt.setString(2, CodiceArea);
				try (ResultSet rs = pt.executeQuery()) {
					if (rs.next() == true) {
						idTicket = rs.getString("IDTicket");
						return true;
					}
				}

				//System.out.println("idTicket:" + idTicket);
			}
			tm.commitTransaction();
			
			}

		catch (SQLException e) {
			throw new SQLException("Impossibile InserireTicket", e);
		}
		
			return true;
*/
		} 
	
	
	
	public int readTicket(TransactionManager tm, String CodiceArea, String Targa) throws SQLException {
		
		tm.assertInTransaction();
		try (PreparedStatement pt = tm.getConnection()
				.prepareStatement("SELECT * FROM ticket WHERE (CodiceArea=? AND Targa=?)")) {

			pt.setString(1, CodiceArea);
			pt.setString(2, Targa);
			try (ResultSet rs = pt.executeQuery()) {
				if (rs.next() == true) {
					return rs.getInt("IDTicket");
				}else {
					System.out.println("Errore1");
				}
			}

		}

		return -1;
	}
		public double readTicket(TransactionManager tm, int IDTicket) throws SQLException {
		
		tm.assertInTransaction();
		try (PreparedStatement pt = tm.getConnection()
				.prepareStatement("SELECT * FROM ticket WHERE (IDTicket=?)")) {
			//ogni ID � univoco
			pt.setInt(1, IDTicket);
			try (ResultSet rs = pt.executeQuery()) {
				if (rs.next() == true) {
					//devo aggiungere nel db questo campo durata
					return rs.getDouble("durata");
					
				}else {
					System.out.println("Errore1");
				}
			}

		}

		return -1;
	}
	
	
	
	public static int updateTicket() {
		return 0;
		
	}
	
	public static int deleteTicket() {
		return 0;
		
	}
	
	

}
