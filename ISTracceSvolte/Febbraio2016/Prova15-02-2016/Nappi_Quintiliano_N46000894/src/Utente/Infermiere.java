package Utente;

public class Infermiere extends Utente{
	
	private String CodiceFiscale;
	private String Nome;
	private String Cognome;
	private String Indirizzo;
	private Integer NumCellulare;
	
	public Infermiere(){
		
	}
	
	public String getNome() {
		return Nome;
	}
	public void setNome(String nome) {
		Nome = nome;
	}
	public String getCodiceFiscale() {
		return CodiceFiscale;
	}
	public void setCodiceFiscale(String codiceFiscale) {
		CodiceFiscale = codiceFiscale;
	}
	public String getCognome() {
		return Cognome;
	}
	public void setCognome(String cognome) {
		Cognome = cognome;
	}
	public String getIndirizzo() {
		return Indirizzo;
	}
	public void setIndirizzo(String indirizzo) {
		Indirizzo = indirizzo;
	}
	public Integer getNumCellulare() {
		return NumCellulare;
	}
	public void setNumCellulare(Integer numCellulare) {
		NumCellulare = numCellulare;
	}
	

}
