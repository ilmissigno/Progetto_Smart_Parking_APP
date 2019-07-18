package Boutique;

public class Prodotto {
	private String nome;
	private String zonaGeografica;
	private Integer prezzo;
	private Integer qta;
	private Integer id; //serve per la comunicazione con il DB
	
	
	public Prodotto(String nome, String zonaGeografica, Integer prezzo, Integer qta) {
		super();
		this.nome = nome;
		this.zonaGeografica = zonaGeografica;
		this.prezzo = prezzo;
		this.qta = qta;
	}
	public Prodotto(String nome, String zonaGeografica, Integer prezzo, Integer qta, Integer id) {
		super();
		this.nome = nome;
		this.zonaGeografica = zonaGeografica;
		this.prezzo = prezzo;
		this.qta = qta;
		this.id = id;
	}
	public String getZonaGeografica() {
		return zonaGeografica;
	}
	public void setZonaGeografica(String zonaGeografica) {
		this.zonaGeografica = zonaGeografica;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(Integer prezzo) {
		this.prezzo = prezzo;
	}
	public Integer getQta() {
		return qta;
	}
	public void setQta(Integer qta) {
		this.qta = qta;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Prodotto(Integer id) {
		super();
		this.id = id;
	}
	public Prodotto() {
		super();
	}
	
	
	
	
}
