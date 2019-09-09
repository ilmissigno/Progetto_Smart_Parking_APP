package Controller;

import java.io.DataOutputStream;

import Entity.Ticket;

public interface GestoreTicket {
	double OttieniCostoTicket(int CodiceArea, double Durata);
	Ticket ConfermaTicket(String Targa,String CodiceArea,double Durata,double CostoTicket,String username,String password);
	boolean RinnovoTicket(String Targa,int Durata,int IDTicket,double CostoTicket);
	int VerificaCopertura(String CodiceArea, String Targa);
	void TimerTicket(String username, int iDTicket,DataOutputStream out);
	Ticket RinnovaTicket(int iDTicket, double durata, double costoTotale, String username, String password);
	boolean EliminaTicket(int iDTicket);
	double TrovaRimborso(int IDTicket,String username);
}
