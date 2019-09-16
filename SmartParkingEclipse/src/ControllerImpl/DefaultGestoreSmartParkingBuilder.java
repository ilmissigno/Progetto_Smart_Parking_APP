package ControllerImpl;

import Controller.GestoreSmartParking;
import Controller.GestoreTicket;
import Controller.GestoreMulta;
import Controller.GestoreAccount;

public class DefaultGestoreSmartParkingBuilder extends GestoreSmartParking.GestoreSmartParkingBuilder{

	public DefaultGestoreSmartParkingBuilder() {
		
	}
	
	public GestoreSmartParking build() {
		GestoreTicket ticket = new GestoreTicketImpl();
		GestoreMulta multa = new GestoreMultaImpl();
		GestoreAccount account = new GestoreAccountImpl();
		return super.build(ticket,multa,account);
	}
	
}
