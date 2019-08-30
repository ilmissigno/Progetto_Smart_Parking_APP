package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Entity.Ticket;

public class AreaParcheggioDAO {

	private ArrayList<Ticket> listaTicket;
	private int CodiceArea;
	private double CostoTicket;
	
	public AreaParcheggioDAO() {
		
	}

	public int getCodiceArea() {
		return CodiceArea;
	}

	public void setCodiceArea(int codiceArea) {
		CodiceArea = codiceArea;
	}

	public double getCostoTicket() {
		return CostoTicket;
	}

	public void setCostoTicket(double costoTicket) {
		CostoTicket = costoTicket;
	}
	public ArrayList<Ticket> getListTicket() {
		return listaTicket;
	}

	public void setListTicket(ArrayList<Ticket> ticket) {
		this.listaTicket = ticket;
	}
	
	public void addTicket(Ticket t) {
		this.listaTicket.add(t);
	}
	public static String createAreaParcheggio() {
		return null;
		
	}
	
	public static String deleteAreaParcheggio() {
		return null;
		
	}
	
	public static String updateAreaParcheggio() {
		return null;
		
	}
	
	public void readAreaParcheggio(TransactionManager tm,int CodiceArea) throws SQLException{
		tm.assertInTransaction();
		try(PreparedStatement pt = tm.getConnection().prepareStatement("SELECT * FROM areaparcheggio WHERE CodiceArea=?")){
			pt.setInt(1, CodiceArea);
			try(ResultSet rs = pt.executeQuery()){
				if(rs.next()==true) {
					this.setCodiceArea(CodiceArea);
					this.setCostoTicket(rs.getDouble("CostoOrario"));
					this.setListTicket(null);
				}
			}
		}
	}
	
}
