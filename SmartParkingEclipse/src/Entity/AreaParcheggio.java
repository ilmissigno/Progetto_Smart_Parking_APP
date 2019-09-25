package Entity;

import java.util.ArrayList;

import DAO.AreaParcheggioDAO;
import DAO.TransactionManager;
import DAO.TransactionManagerFactory;

public class AreaParcheggio {
	private ArrayList<Ticket> listaTicket;
	private int CodiceArea;
	private int Disponibilita;
	private double CostoTicket;

	public AreaParcheggio() {

	}

	public AreaParcheggio(int codiceArea) {
		PrelevaAreaParcheggio(codiceArea);
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

	public boolean AggiornaDisponibilitaInDB(int disponibilita) {
		AreaParcheggioDAO a = new AreaParcheggioDAO();
		TransactionManager tm = TransactionManagerFactory.createTransactionManager();
		try {
			tm.beginTransaction();
			if(a.updateAreaParcheggio(tm, disponibilita)) {
				tm.commitTransaction();
				this.setDisponibilita(a.getDisponibilita());
				return true;
			}else {
				return false;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	private void PrelevaAreaParcheggio(int codiceArea) {
		AreaParcheggioDAO a = new AreaParcheggioDAO();
		TransactionManager tm = TransactionManagerFactory.createTransactionManager();
		try {
			tm.beginTransaction();
			a.readAreaParcheggio(tm, codiceArea);
			tm.commitTransaction();
			this.setCodiceArea(a.getCodiceArea());
			this.setDisponibilita(a.getDisponibilita());
			this.setCostoTicket(a.getCostoTicket());
			this.setListTicket(null);

		}catch(Exception e) {
			tm.rollbackTransaction();
		}

	}
}
