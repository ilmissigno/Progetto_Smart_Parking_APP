package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Entity.Ticket;

public class TicketDAO {
	
	public boolean createTicket(TransactionManager tm, String DataScadenza, String Targa,String username,String CodiceArea) throws Exception {
		tm.assertInTransaction();

		try (PreparedStatement ps = tm.getConnection()
				.prepareStatement("INSERT INTO TICKET(IDTicket,datainizio,datafine,Targa,username,CodiceArea) VALUES(NULL,NULL,?,?,?,?)")) {

			ps.setString(1, DataScadenza);
			ps.setString(2, Targa);
			ps.setString(3, username);
			ps.setInt(4, Integer.parseInt(CodiceArea));

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
				}
			}

		}

		return -1;
	}
		public String readTicket(TransactionManager tm, String IDTicket) throws SQLException {
		
		tm.assertInTransaction();
		try (PreparedStatement pt = tm.getConnection()
				.prepareStatement("SELECT * FROM ticket WHERE (IDTicket=?)")) {
			//ogni ID è univoco
			pt.setString(1, IDTicket);
			try (ResultSet rs = pt.executeQuery()) {
				if (rs.next() == true) {
					//devo aggiungere nel db questo campo durata
					return rs.getString("durata");
					
				}
			}

		}

		return "-1";
	}
	
	
	
	public static int updateTicket() {
		return 0;
		
	}
	
	public static int deleteTicket() {
		return 0;
		
	}
	
	

}
