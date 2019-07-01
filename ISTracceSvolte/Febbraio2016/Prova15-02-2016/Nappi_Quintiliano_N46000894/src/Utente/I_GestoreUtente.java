package Utente;

public interface I_GestoreUtente {
	
	public void Autenticazione(String us, String pw);
	public void InserisciInfermiere( Infermiere i);
	public void RimuoviInfermiere (String cf);

}
