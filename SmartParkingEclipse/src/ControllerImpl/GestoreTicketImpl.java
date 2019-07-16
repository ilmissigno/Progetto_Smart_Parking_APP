package ControllerImpl;

import Controller.GestoreTicket;
import Entity.AreaParcheggio;
import Entity.Ticket;

public class GestoreTicketImpl implements GestoreTicket{

	@Override
	public double OttieniCostoTicket(String CodiceArea) {
		AreaParcheggio area = new AreaParcheggio();
		double costo = area.OttieniCostoTicket(CodiceArea);
		return costo;
	}

	@Override
	public boolean ConfermaTicket(String Targa, String CodiceArea, double Durata, double CostoTicket) {
		Ticket ticket = new Ticket();
		if(ticket.AcquistaTicket(Targa,CodiceArea,Durata)) {
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
	public Ticket VerificaCopertura(String CodiceArea, String targa) {
		Ticket t = new Ticket();
		return t.OttieniTicket(CodiceArea,targa);
	}
	
}

