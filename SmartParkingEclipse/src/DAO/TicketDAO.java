package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Entity.Ticket;

public class TicketDAO {
	
	public boolean createTicket(TransactionManager tm, String Targa, String CodiceArea,
			double Durata) throws Exception {
		String idTicket = null;
		tm.assertInTransaction();

		try (PreparedStatement ps = tm.getConnection()
				.prepareStatement("INSERT INTO TICKET(TARGA,DURATA,COSTO) VALUES(?,?,?)")) {

			ps.setString(1, Targa);
			ps.setString(2, CodiceArea);
			ps.setDouble(3, Durata);

			ps.executeUpdate();
			tm.commitTransaction();

			tm.beginTransaction();
			tm.assertInTransaction();
			try (PreparedStatement pt = tm.getConnection()
					.prepareStatement("SELECT ID FROM TICKET WHERE ((TARGA=?)AND(CODICEAREA=?))")) {

				pt.setString(1, Targa);
				pt.setString(2, CodiceArea);
				try (ResultSet rs = pt.executeQuery()) {
					if (rs.next() == true) {
						idTicket = rs.getString("ID");
						return true;
					}
				}

				System.out.println("idTicket:" + idTicket);
			}
			tm.commitTransaction();
			
			}

		catch (SQLException e) {
			throw new SQLException("Impossibile InserireTicket", e);
		}
		
			return true;

		} 
	
	
	
	public Ticket readTicket(TransactionManager tm, String CodiceArea, String Targa) throws SQLException {
		
		Ticket t = new Ticket();
		tm.assertInTransaction();
		try (PreparedStatement pt = tm.getConnection()
				.prepareStatement("SELECT * FROM TICKET WHERE (CODICEAREA=? AND TARGA=?)")) {

			pt.setString(1, CodiceArea);
			pt.setString(2, Targa);
			try (ResultSet rs = pt.executeQuery()) {
				if (rs.next() == true) {
					t.setIDTicket(rs.getInt("ID"));
					t.setCodiceArea(CodiceArea);
					t.setTargaAuto(Targa);
					t.setDurata(rs.getDouble("DURATA"));
					return t;
				}
			}

		}

		return null;
	}
		

	
	
	
	public static int updateTicket() {
		return 0;
		
	}
	
	public static int deleteTicket() {
		return 0;
		
	}
	
	

}
