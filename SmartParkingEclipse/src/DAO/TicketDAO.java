package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TicketDAO {
	
	public static boolean createTicket(TransactionManager tm, String Targa, String CodiceArea,
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
	
	
	
	public static int readTicket(TransactionManager tm, int ID) throws SQLException {
		
		int Identificativo = 0;
		tm.assertInTransaction();
		try (PreparedStatement pt = tm.getConnection()
				.prepareStatement("SELECT ID FROM TICKET WHERE ((ID=?))")) {

			pt.setInt(1, ID);
			try (ResultSet rs = pt.executeQuery()) {
				if (rs.next() == true) {
					Identificativo = rs.getInt("ID");
				}
			}

			System.out.println("ID:" + Identificativo);
		}

		return Identificativo;
	}
		

	
	
	
	public static int updateTicket() {
		return 0;
		
	}
	
	public static int deleteTicket() {
		return 0;
		
	}
	
	

}
