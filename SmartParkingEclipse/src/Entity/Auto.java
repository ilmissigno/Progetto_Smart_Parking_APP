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
	
	public Auto(String Targa,String Proprietario) {
		this.setTarga(Targa);
		this.setProprietario(Proprietario);
	}

	public Auto(String targa) {
		// TODO Auto-generated constructor stub
		//potrei pensare anche di accorpare tutto in una funzione
		AutoDAO aut = new AutoDAO();
		TransactionManager tm = TransactionManagerFactory.createTransactionManager();
		try {
			tm.beginTransaction();
			aut.readAuto(tm,Targa);
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
	
	public boolean AggiungiAuto(String Targa,String proprietario){
		//boolean autopresente=false;
		//boolean autoassociata=false;
		AutoDAO aut = new AutoDAO();
		TransactionManager tm = TransactionManagerFactory.createTransactionManager();
		try {
			tm.beginTransaction();
			if(aut.readAuto(tm,Targa)) {
				//ok significa che l'auto � gi� inserita, devo inserire o meno la corrispondenza
				tm.commitTransaction();
				this.setTarga(aut.getTarga());
				this.setProprietario(aut.getProprietario());
				return true;
			}
			else{
			//Altrimenti Creo l'auto
				if(aut.createAuto(tm,Targa,proprietario)) {
					tm.commitTransaction();
					this.setTarga(aut.getTarga());
					this.setProprietario(aut.getProprietario());
					return true;
				}else {
					return false;
				}
			}
			}
			catch(Exception e) {
			tm.rollbackTransaction();
			return false;
		}
		}
	
	}
	
	

