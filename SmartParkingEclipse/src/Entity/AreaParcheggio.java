package Entity;

import java.util.ArrayList;

import DAO.AreaParcheggioDAO;
import DAO.TransactionManager;
import DAO.TransactionManagerFactory;

public class AreaParcheggio {
	private ArrayList<Ticket> listaTicket;
	private int CodiceArea;
	private double CostoTicket;
	
	public AreaParcheggio() {
		
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
	
	public double OttieniCostoTicket(int codiceArea) {
		AreaParcheggioDAO a = new AreaParcheggioDAO();
		TransactionManager tm = TransactionManagerFactory.createTransactionManager();
		try {
			tm.beginTransaction();
			a.readAreaParcheggio(tm, codiceArea);
			tm.commitTransaction();
			this.setCodiceArea(codiceArea);
			this.setCostoTicket(a.getCostoTicket());
			this.setListTicket(null);
			return this.getCostoTicket();
		}catch(Exception e) {
			tm.rollbackTransaction();
		}
		return -1;
	}
	
}
