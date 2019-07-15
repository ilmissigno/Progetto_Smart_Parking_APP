package Controller;

import java.io.DataOutputStream;

public interface IGestoreSmartParking {
	void Login(String username, String password, DataOutputStream out);
	void AcquistaTicket(String targa, String codiceArea, double Durata,DataOutputStream out);
	void VerificaTicket(String targa,DataOutputStream out);
	void RinnovoTicket(String targa, double Durata, int IDTicket, double costoTicket,DataOutputStream out);
	void EffettuaMulta();
	void PagaMulta();
	void RicercaMulta();
	void CaricaConto();
	void RegistraUtente(String CodiceFiscale, String Cognome, String Nome, String username, String password, String email,DataOutputStream out);
}
