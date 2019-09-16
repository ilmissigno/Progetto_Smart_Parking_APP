package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Entity.Ticket;

public class AreaParcheggioDAO {

	private ArrayList<Ticket> listaTicket;
	private int CodiceArea;
	private int Disponibilita;
	private double CostoTicket;
	
	public AreaParcheggioDAO() {
		
	}

	public int getCodiceArea() {
		return CodiceArea;
	}

	public void setCodiceArea(int codiceArea) {
		CodiceArea = codiceArea;
	}

	public int getDisponibilita() {
		return Disponibilita;
	}

	public void setDisponibilita(int disponibilita) {
		Disponibilita = disponibilita;
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
	
	public boolean updateAreaParcheggio(TransactionManager tm, int disponibilita) throws SQLException {
		tm.assertInTransaction();
		try(PreparedStatement pt = tm.getConnection().prepareStatement("UPDATE areaparcheggio SET disponibilita=? WHERE CodiceArea=?")){
			pt.setInt(1, disponibilita);
			pt.setInt(2, this.getCodiceArea());
			if(pt.executeUpdate()==1) {
				this.setDisponibilita(disponibilita);
				return true;
			}else {
				return false;
			}
		}
	}
	
	public void readAreaParcheggio(TransactionManager tm,int CodiceArea) throws SQLException{
		tm.assertInTransaction();
		try(PreparedStatement pt = tm.getConnection().prepareStatement("SELECT * FROM areaparcheggio WHERE CodiceArea=?")){
			pt.setInt(1, CodiceArea);
			try(ResultSet rs = pt.executeQuery()){
				if(rs.next()==true) {
					this.setCodiceArea(CodiceArea);
					this.setDisponibilita(rs.getInt("disponibilita"));
					this.setCostoTicket(rs.getDouble("CostoOrario"));
					this.setListTicket(null);
				}
			}
		}
	}
	
}
