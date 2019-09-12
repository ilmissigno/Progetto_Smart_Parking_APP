package Controller;

import java.io.DataOutputStream;
import java.sql.SQLException;
import java.util.ArrayList;

import Entity.Ticket;

public interface IGestoreSmartParking {
	boolean Login(String username, String password);
	double GetCostoTicket(String codiceArea, double Durata);
	Ticket AcquistaTicket(String targa, String codiceArea, double Durata,String username,String password,double costoTotale);
	void VerificaTicket(String targa,String CodiceArea,DataOutputStream out);
	void EffettuaMulta();
	void PagaMulta();
	void RicercaMulta();
	boolean RegistraUtente(String CodiceFiscale, String Cognome, String Nome, String username, String password, String email);
	boolean AggiungiAuto(String Targa,String CFProprietario,String username, String password);
	ArrayList<String>  OttieniListaAuto(String username, String password) throws SQLException;
	void AvviaTimer(String username,int IDTicket, DataOutputStream out);
	Ticket RinnovaTicket(int iDTicket, double durata, String username,String password, double costoTotale);
	boolean CaricaConto(String username,String password, double importo);
	boolean EliminaTicket(int iDTicket);
	boolean EliminaAuto(String targa, String username,String password);
	double LeggiCredito(String username, String password);
	boolean ArrestaSosta(int iDTicket, String username , String password);
}
