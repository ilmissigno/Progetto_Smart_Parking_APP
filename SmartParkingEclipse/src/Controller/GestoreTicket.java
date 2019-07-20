package Controller;

import java.io.DataOutputStream;

import Entity.Ticket;

public interface GestoreTicket {
	double OttieniCostoTicket(String CodiceArea);
	boolean ConfermaTicket(String Targa,String CodiceArea,double Durata,double CostoTicket,String username,String password ,DataOutputStream out);
	boolean RinnovoTicket(String Targa,int Durata,int IDTicket,double CostoTicket);
	int VerificaCopertura(String CodiceArea, String Targa);
}
