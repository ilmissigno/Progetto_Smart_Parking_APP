package coreBusiness.utenti;

public class Fabbricato {
	private Integer id;
	private float rendita;
	private int giorniPossesso;
	private float percProprietaPossesso;
	private String comune;
	private Contribuente contr;
	private int annoCessione;
	
	public Fabbricato(Integer id) {
		super();
		this.id = id;
	}

	public Fabbricato() {
		super();
	}
	
	public Fabbricato(Integer id, float rendita, int giorniPossesso, float percProprietaPossesso, String comune,
			Contribuente contr) {
		super();
		this.id = id;
		this.rendita = rendita;
		this.giorniPossesso = giorniPossesso;
		this.percProprietaPossesso = percProprietaPossesso;
		this.comune = comune;
		this.contr = contr;
	}
	
	
	public int getAnnoCessione() {
		return annoCessione;
	}

	public void setAnnoCessione(int annoCessione) {
		this.annoCessione = annoCessione;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public float getRendita() {
		return rendita;
	}
	public void setRendita(float rendita) {
		this.rendita = rendita;
	}
	public int getGiorniPossesso() {
		return giorniPossesso;
	}
	public void setGiorniPossesso(int giorniPossesso) {
		this.giorniPossesso = giorniPossesso;
	}
	public float getPercProprietaPossesso() {
		return percProprietaPossesso;
	}
	public void setPercProprietaPossesso(float percProprietaPossesso) {
		this.percProprietaPossesso = percProprietaPossesso;
	}
	public String getComune() {
		return comune;
	}
	public void setComune(String comune) {
		this.comune = comune;
	}
	public Contribuente getContr() {
		return contr;
	}
	public void setContr(Contribuente contr) {
		this.contr = contr;
	}
	
	public float calcolaRedditoFabbricato(){
		float reddito=this.rendita*this.percProprietaPossesso*this.giorniPossesso/365;
		return reddito;
	}
}
