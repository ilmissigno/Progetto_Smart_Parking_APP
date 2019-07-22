package Controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
import java.sql.SQLException;

public interface IGestoreSmartParking {
	void Login(String username, String password, DataOutputStream out);
	void GetCostoTicket(String codiceArea, double Durata, DataOutputStream out);
	void AcquistaTicket(String targa, String codiceArea, double Durata,String username,String password,double costoTotale,DataOutputStream out);
	void VerificaTicket(String targa,String CodiceArea,DataOutputStream out);
	void RinnovoTicket(String targa, double Durata, int IDTicket, double costoTicket,DataOutputStream out);
	void EffettuaMulta();
	void PagaMulta();
	void RicercaMulta();
	void CaricaConto(String username, String password, double Importo, DataOutputStream out);
	void RegistraUtente(String CodiceFiscale, String Cognome, String Nome, String username, String password, String email,DataOutputStream out);
	void AggiungiAuto(String Targa,String CFProprietario,String username,DataOutputStream out);
	void  OttieniListaAuto(String username, DataOutputStream out) throws SQLException;
	void TimerTicket(String username,int IDTicket, DataOutputStream out);
}
