package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Entity.Multa;
import Entity.Ticket;

public class AutoDAO {
	
	private String Targa;
	private String Proprietario;
	private ArrayList<Ticket> listaTicket;
	private ArrayList<Multa> listaMulte;
	
	public AutoDAO() {
		
	}
	
	public AutoDAO(String Targa,String Proprietario) {
		this.setTarga(Targa);
		this.setProprietario(Proprietario);
	}

	public String getTarga() {
		return Targa;
	}

	public void setTarga(String targa) {
		Targa = targa;
	}

	public String getProprietario() {
		return Proprietario;
	}

	public void setProprietario(String proprietario) {
		Proprietario = proprietario;
	}
	
	public ArrayList<Ticket> getListaTicket() {
		return listaTicket;
	}

	public void setListaTicket(ArrayList<Ticket> listaTicket) {
		this.listaTicket = listaTicket;
	}
	
	public void addTicket(Ticket t) {
		this.listaTicket.add(t);
	}

	public ArrayList<Multa> getListaMulte() {
		return listaMulte;
	}

	public void setListaMulte(ArrayList<Multa> listaMulte) {
		this.listaMulte = listaMulte;
	}
	
	public void addMulta(Multa m) {
		this.listaMulte.add(m);
	}

	public boolean createAuto(TransactionManager tm,String Targa,String proprietario) throws SQLException{
		//da implementare
		String query = "INSERT INTO AUTO(TARGA,CFPROPRIETARIO) VALUES(?,?)";
		tm.assertInTransaction();
		try (PreparedStatement pt = tm.getConnection()
				.prepareStatement(query)) {
			pt.setString(1,Targa);
			pt.setString(2,Proprietario);
			if(pt.executeUpdate()==1) {
				this.setTarga(Targa);
				this.setProprietario(proprietario);
				return true;
			}else {
				return false;
			}
		}catch(SQLException e) {
			throw e;
		}
	}
	
	public static String deleteAuto() {
		return null;
		
	}
	
	public static String updateAuto() {
		return null;
		
	}
	
	public boolean readAuto(TransactionManager tm,String Targa,String Proprietario) throws SQLException{
		tm.assertInTransaction();
		try(PreparedStatement pt = tm.getConnection().prepareStatement("SELECT * FROM auto WHERE Targa=? AND CFProprietario=?")){
			pt.setString(1, Targa);
			pt.setString(2, Proprietario);
			try(ResultSet rs = pt.executeQuery()){
				if(rs.next()==true) {
					return true;
				}else {
					return false;
				}
			}
		}
	}
	
}
