package Controller;


public interface GestoreAccount {
	boolean Login(String username,String password);
	boolean RegistraUtente(String CodiceFiscale, String Cognome, String Nome, String username, String password, String email);
	double getConto(String username,String password);
	boolean AggiornaConto(String username,String password,double CostoTotale);
	boolean CaricaConto(String username, String password, double Importo);
	void AggiungiAuto(String username,String password);
}
