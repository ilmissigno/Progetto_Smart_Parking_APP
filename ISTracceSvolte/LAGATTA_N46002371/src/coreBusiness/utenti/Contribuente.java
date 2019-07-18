package coreBusiness.utenti;

public class Contribuente extends Utente {
	private String nome;
	private String cognome;
	private String codFis;
	private String dataNascita;
	private String comune;
	
	public Contribuente(String nome, String cognome, String codFis, String dataNascita, String comune) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.codFis = codFis;
		this.dataNascita = dataNascita;
		this.comune = comune;
	}
	public Contribuente() {
		super();
	}
	public Contribuente(String codFis) {
		super();
		this.codFis = codFis;
	}
	public String getDataNascita() {
		return dataNascita;
	}
	public void setDataNascita(String dataNascita) {
		this.dataNascita = dataNascita;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getCodFis() {
		return codFis;
	}
	public void setCodFis(String codFis) {
		this.codFis = codFis;
	}
	public String getComune() {
		return comune;
	}
	public void setComune(String comune) {
		this.comune = comune;
	}
}
