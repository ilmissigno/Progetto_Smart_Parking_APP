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

	public AreaParcheggio(int codiceArea) {
		// TODO Auto-generated constructor stub
		//potrei evitare una read ma usare direttamente il costruttore DAO -> sono scelte
		PrelevaAreaParcheggio(codiceArea);
		
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
	//privata è usabile solo nell'ambito di questa classe-> nel costruttore
	private void PrelevaAreaParcheggio(int codiceArea) {
		AreaParcheggioDAO a = new AreaParcheggioDAO();
		TransactionManager tm = TransactionManagerFactory.createTransactionManager();
		try {
			tm.beginTransaction();
			a.readAreaParcheggio(tm, codiceArea);
			tm.commitTransaction();
			this.setCodiceArea(a.getCodiceArea());
			this.setCostoTicket(a.getCostoTicket());
			this.setListTicket(null);
			
		}catch(Exception e) {
			tm.rollbackTransaction();
		}
		
	}
	
}
