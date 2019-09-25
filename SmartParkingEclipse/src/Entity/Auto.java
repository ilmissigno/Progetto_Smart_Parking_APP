package Entity;

import java.util.ArrayList;

import DAO.AutoDAO;
import DAO.TransactionManager;
import DAO.TransactionManagerFactory;

public class Auto {

	private String Targa;
	private String Proprietario;
	private ArrayList<Ticket> listaTicket;


	public Auto() {

	}

	public Auto(String Targa, String CFProprietario) {
		AutoDAO aut = new AutoDAO();
		TransactionManager tm = TransactionManagerFactory.createTransactionManager();
		try {
			tm.beginTransaction();
			if(aut.readAuto(tm,Targa)) {
				tm.commitTransaction();
				this.setTarga(aut.getTarga());
				this.setProprietario(aut.getProprietario());
			}
			else{
				if(aut.createAuto(tm,Targa,CFProprietario)) {
					tm.commitTransaction();
					this.setTarga(aut.getTarga());
					this.setProprietario(aut.getProprietario());
				}
			}
		}
		catch(Exception e) {
			tm.rollbackTransaction();
		}
	}
	
	public Auto(String targa) {
		// TODO Auto-generated constructor stub
		AutoDAO aut = new AutoDAO();
		TransactionManager tm = TransactionManagerFactory.createTransactionManager();
		try {
			tm.beginTransaction();
			aut.readAuto(tm,targa);
			tm.commitTransaction();
			this.setTarga(aut.getTarga());
			this.setProprietario(aut.getProprietario());
		}
		catch(Exception e) {
			tm.rollbackTransaction();
		}
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

}



