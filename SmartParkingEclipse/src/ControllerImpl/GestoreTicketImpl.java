package ControllerImpl;

import Controller.GestoreTicket;
import Entity.AreaParcheggio;

public class GestoreTicketImpl implements GestoreTicket{

	@Override
	public double OttieniCostoTicket(String CodiceArea) {
		AreaParcheggio area = new AreaParcheggio();
		double costo = area.OttieniCostoTicket(CodiceArea);
		return costo;
	}

	@Override
	public boolean AcquistaTicket(String Targa, String CodiceArea, double Durata, double CostoTicket) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean RinnovoTicket(String Targa, int Durata, int IDTicket, double CostoTicket) {
		// TODO Auto-generated method stub
		return false;
	}
	
}

