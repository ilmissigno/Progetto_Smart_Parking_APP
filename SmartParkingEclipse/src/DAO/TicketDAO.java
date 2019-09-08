package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Entity.Ticket;

public class TicketDAO {
	
	private int IDTicket;
	private double Durata;
	private String TargaAuto;
	private String CodiceArea;
	private String username;
	private String ScadenzaTicket;
	
	public TicketDAO() {
		
	}

	public int getIDTicket() {
		return IDTicket;
	}

	public void setIDTicket(int iDTicket) {
		IDTicket = iDTicket;
	}

	public double getDurata() {
		return Durata;
	}

	public void setDurata(double durata) {
		Durata = durata;
	}

	public String getScadenzaTicket() {
		return ScadenzaTicket;
	}

	public void setScadenzaTicket(String scadenzaTicket) {
		ScadenzaTicket = scadenzaTicket;
	}

	public String getTargaAuto() {
		return TargaAuto;
	}

	public void setTargaAuto(String targaAuto) {
		TargaAuto = targaAuto;
	}

	public String getCodiceArea() {
		return CodiceArea;
	}

	public void setCodiceArea(String codiceArea) {
		CodiceArea = codiceArea;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
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
				this.setCodiceArea(CodiceArea);
				this.setDurata(Durata);
				this.setTargaAuto(Targa);
				this.setUsername(username);
				this.setScadenzaTicket(DataScadenza);
				
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
	
	
	
	public int readTicket(TransactionManager tm, String Targa,String DataScadenza) throws SQLException {
		
		tm.assertInTransaction();
		try (PreparedStatement pt = tm.getConnection()
				.prepareStatement("SELECT * FROM ticket WHERE (datafine=? AND Targa=?)")) {

			pt.setString(1, DataScadenza);
			pt.setString(2, Targa);
			try (ResultSet rs = pt.executeQuery()) {
				if (rs.next() == true) {
					this.setIDTicket(rs.getInt("IDTicket"));
					System.out.println(this.getIDTicket());
					return 1;
				}else {
					System.out.println("Errore1");
				}
			}

		}

		return -1;
	}
	//questa la toglierei metterei una funzione più generale, per ora la lascio
		public double readTicket(TransactionManager tm, int IDTicket) throws SQLException {
		
		tm.assertInTransaction();
		try (PreparedStatement pt = tm.getConnection()
				.prepareStatement("SELECT * FROM ticket WHERE (IDTicket=?)")) {
			//ogni ID ï¿½ univoco
			pt.setInt(1, IDTicket);
			try (ResultSet rs = pt.executeQuery()) {
				if (rs.next() == true) {
					this.setCodiceArea(String.valueOf(rs.getInt("CodiceArea")));
					this.setDurata(rs.getDouble("durata"));
					this.setTargaAuto(rs.getString("Targa"));
					this.setUsername(rs.getString("username"));
					this.setScadenzaTicket(rs.getString("datafine"));
					this.setIDTicket(rs.getInt("IDTicket"));
					return this.getDurata();
				}else {
					System.out.println("Errore1");
				}
			}

		}

		return -1;
	}
	
		public void readsTicket(TransactionManager tm, int IDTicket) throws SQLException {
			
			tm.assertInTransaction();
			try (PreparedStatement pt = tm.getConnection()
					.prepareStatement("SELECT * FROM ticket WHERE (IDTicket=?)")) {
				//ogni ID ï¿½ univoco
				pt.setInt(1, IDTicket);
				try (ResultSet rs = pt.executeQuery()) {
					if (rs.next() == true) {
						this.setCodiceArea(String.valueOf(rs.getInt("CodiceArea")));
						this.setDurata(rs.getDouble("durata"));
						this.setTargaAuto(rs.getString("Targa"));
						this.setUsername(rs.getString("username"));
						this.setScadenzaTicket(rs.getString("datafine"));
						this.setIDTicket(rs.getInt("IDTicket"));
					}else {
						System.out.println("Errore1");
					}
				}

			}

		}
	
	
	public boolean updateTicket(TransactionManager tm, int IDTicket ,String DataScadenza, double Durata) throws Exception {
		tm.assertInTransaction();
		try (PreparedStatement ps = tm.getConnection()
				.prepareStatement("UPDATE TICKET SET durata=?, datafine=? where IDTicket=?")) {
			ps.setDouble(1, Durata);
			ps.setString(2, DataScadenza);
			ps.setInt(3, IDTicket);

			if(ps.executeUpdate()==1) {
				return true;
			}else {
				return false;
			}
		}
		
		
	}
	
		
	
	
	public boolean deleteTicket(TransactionManager tm, int IDTicket) throws SQLException {
		tm.assertInTransaction();
		try (PreparedStatement ps = tm.getConnection()
				.prepareStatement("DELETE FROM ticket WHERE IDTicket=?")) {

			ps.setInt(1, IDTicket);

			if(ps.executeUpdate()==1) {
				return true;
			}else {
				return false;
			}
		}
		
		
		
	}



	

}
