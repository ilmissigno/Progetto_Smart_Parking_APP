package Entity;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import javax.swing.Timer;
import java.util.concurrent.TimeUnit;

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
	
	public boolean AcquistaTicket(String Targa, String CodiceArea, double Durata,DataOutputStream out) {
		TicketDAO ticket = new TicketDAO();
		TransactionManager tm = TransactionManagerFactory.createTransactionManager();
		try {
			tm.beginTransaction();
			if(ticket.createTicket(tm, Targa, CodiceArea, Durata)) {
				
				/*
				 * ATTIVAZIONE TIMER DI NOTIFICA
				 */
				Timer timer = new Timer((int) TimeUnit.HOURS.toMillis((long)Durata),new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						try {
							Ticket t = ticket.readTicket(tm, CodiceArea, Targa);
							out.writeUTF("notifyticket");
							out.flush();
							out.writeInt(t.getIDTicket());
							out.flush();
							out.writeUTF(Targa);
							out.flush();
							out.writeUTF(CodiceArea);
							out.flush();
							out.writeDouble(Durata);
							out.flush();
						} catch (IOException | SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
				timer.setRepeats(false);
				timer.start();
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
