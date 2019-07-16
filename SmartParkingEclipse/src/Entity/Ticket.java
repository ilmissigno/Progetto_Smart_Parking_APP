package Entity;

import DAO.TicketDAO;
import DAO.TransactionManager;
import DAO.TransactionManagerFactory;

public class Ticket {
	
	private int IDTicket;
	private double Durata;
	private String TargaAuto;
	private String CodiceArea;
	
	public Ticket() {
		
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
	
	public boolean AcquistaTicket(String Targa, String CodiceArea, double Durata) {
		TicketDAO ticket = new TicketDAO();
		TransactionManager tm = TransactionManagerFactory.createTransactionManager();
		try {
			tm.beginTransaction();
			if(ticket.createTicket(tm, Targa, CodiceArea, Durata)) {
				return true;
			}else {
				return false;
			}
		}catch(Exception e) {
			tm.rollbackTransaction();
			return false;
		}
	}
	
	public Ticket OttieniTicket(String CodiceArea, String Targa) {
		TicketDAO ticket = new TicketDAO();
		TransactionManager tm = TransactionManagerFactory.createTransactionManager();
		try {
			tm.beginTransaction();
			return ticket.readTicket(tm, CodiceArea, Targa);
		}catch(Exception e) {
			tm.rollbackTransaction();
			return null;
		}
	}
	
}
