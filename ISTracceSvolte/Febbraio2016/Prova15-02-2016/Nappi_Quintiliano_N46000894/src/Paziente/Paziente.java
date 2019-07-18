package Paziente;

public class Paziente {
	
	private String CodiceFiscale;
	private String Nome;
	private String Cognome;
	private String Domicilio;
	
	public Paziente(){
		
	}
	public Paziente(String cf, String n, String c, String d){
		super();
		this.CodiceFiscale=cf;
		this.Nome=n;
		this.Cognome=c;
		this.Domicilio=d;
	}
	public String getCodiceFiscale() {
		return CodiceFiscale;
	}
	public void setCodiceFiscale(String codiceFiscale) {
		CodiceFiscale = codiceFiscale;
	}
	public String getNome() {
		return Nome;
	}
	public void setNome(String nome) {
		Nome = nome;
	}
	public String getCognome() {
		return Cognome;
	}
	public void setCognome(String cognome) {
		Cognome = cognome;
	}
	public String getDomicilio() {
		return Domicilio;
	}
	public void setDomicilio(String domicilio) {
		Domicilio = domicilio;
	}
	
}
