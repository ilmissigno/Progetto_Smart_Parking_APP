package ControllerImpl;

import java.io.DataOutputStream;

import Controller.GestoreTicket;
import Entity.AreaParcheggio;
import Entity.Auto;
import Entity.Ticket;

public class GestoreTicketImpl implements GestoreTicket{

	@Override
	public double OttieniCostoTicket(String CodiceArea) {
		AreaParcheggio area = new AreaParcheggio();
		double costo = area.OttieniCostoTicket(CodiceArea);
		return costo;
	}

	@Override
	public boolean ConfermaTicket(String Targa, String CodiceArea, double Durata, double CostoTicket, String username,String password ,DataOutputStream out) {
		Ticket ticket = new Ticket();
			if(ticket.AcquistaTicket(Targa,CodiceArea,Durata,username,password,out)) {
				return true;
			}else {
				return false;
			}
	}

	@Override
	public boolean RinnovoTicket(String Targa, int Durata, int IDTicket, double CostoTicket) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int VerificaCopertura(String CodiceArea, String targa) {
		Ticket t = new Ticket();
		return t.OttieniTicket(CodiceArea,targa);
	}
	
	public boolean TimerTicket(String username, String IDTicket,DataOutputStream out) {
		Ticket t=new Ticket();
		return t.TimerTicket(username,IDTicket,out);
		
		
		
		
		
		
	}

	
	
}

