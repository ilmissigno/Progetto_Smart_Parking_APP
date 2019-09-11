package Entity;

import java.util.ArrayList;

import DAO.AutoDAO;
import DAO.TransactionManager;
import DAO.TransactionManagerFactory;

public class Auto {
	
	private String Targa;
	private String Proprietario;
	//posso anche toglierli?
	private ArrayList<Ticket> listaTicket;
	private ArrayList<Multa> listaMulte;

	
	
	public Auto() {
		
	}
	
	public Auto(String Targa, String CFProprietario) {
		AutoDAO aut = new AutoDAO();
		TransactionManager tm = TransactionManagerFactory.createTransactionManager();
		try {
			tm.beginTransaction();
			if(aut.readAuto(tm,Targa)) {
				//ok significa che l'auto � gi� inserita, devo inserire o meno la corrispondenza
				tm.commitTransaction();
				this.setTarga(aut.getTarga());
				this.setProprietario(aut.getProprietario());
				
			}
			else{
			//Altrimenti Creo l'auto
				if(aut.createAuto(tm,Targa,CFProprietario)) {
					tm.commitTransaction();
					this.setTarga(aut.getTarga());
					this.setProprietario(aut.getProprietario());
				}else {
					
				}
			}
			}
			catch(Exception e) {
			tm.rollbackTransaction();
			
		}
		
		
		
	}
	
	

	public Auto(String targa) {
		// TODO Auto-generated constructor stub
		//potrei pensare anche di accorpare tutto in una funzione
		AutoDAO aut = new AutoDAO();
		TransactionManager tm = TransactionManagerFactory.createTransactionManager();
		try {
			tm.beginTransaction();
			aut.readAuto(tm,targa);
				//ok significa che l'auto � gi� inserita, devo inserire o meno la corrispondenza
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

	public ArrayList<Multa> getListaMulte() {
		return listaMulte;
	}

	public void setListaMulte(ArrayList<Multa> listaMulte) {
		this.listaMulte = listaMulte;
	}
	
	public void addMulta(Multa m) {
		this.listaMulte.add(m);
	}
	
	
	
	}
	
	

