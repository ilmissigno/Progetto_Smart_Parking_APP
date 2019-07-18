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
		Auto auto = new Auto();
		if(auto.checkAuto(Targa)) { //Devo vedere se l'auto e' presente nel DB
			if(ticket.AcquistaTicket(Targa,CodiceArea,Durata,username,password,out)) {
				return true;
			}else {
				return false;
			}
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

