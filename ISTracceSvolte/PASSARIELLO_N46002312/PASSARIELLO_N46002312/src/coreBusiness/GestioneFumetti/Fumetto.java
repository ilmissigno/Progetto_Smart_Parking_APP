package coreBusiness.GestioneFumetti;

public class Fumetto {

	private Integer id, prezzoVendita, prezzoAcquisto;
	private String collana, statoCollana, fornitore, stato, etichettaNovità;
	
	public Fumetto(String collana, String statoCollana, Integer prezzoVendita,
			Integer prezzoAcquisto, String fornitore, String stato, String etichettaNovità){
		
		this.collana = collana;
		this.statoCollana = statoCollana;
		this.prezzoVendita = prezzoVendita;
		this.prezzoAcquisto = prezzoAcquisto;
		this.fornitore = fornitore;
		this.stato = stato;
		this.etichettaNovità = etichettaNovità;
	}
	
	public Fumetto() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getCollana(){
		return collana;
	}
	
	public void setCollana(String collana){
		this.collana = collana;
	}
	
	public String getStatoCollana(){
		return statoCollana;
	}
	
	public void setStatoCollana(String statoCollana){
		this.statoCollana = statoCollana;
	}
	
	public Integer getPrezzoVendita(){
		return prezzoVendita;
	}
	
	public void setPrezzoVendita(Integer prezzoVendita){
		this.prezzoVendita = prezzoVendita;
	}
	
	public Integer getPrezzoAcquisto(){
		return prezzoAcquisto;
	}
	
	public void setPrezzoAcquisto(Integer prezzoAcquisto){
		this.prezzoAcquisto = prezzoAcquisto;
	}
	
	public String getFornitore(){
		return fornitore;
	}
	
	public void setFornitore(String fornitore){
		this.fornitore = fornitore;
	}
	
	public String getStato(){
		return stato;
	}
	
	public void setStato(String stato){
		this.stato = stato;
	}
	
	public String getEtichettaNovita(){
		return etichettaNovità;
	}
	
	public void setEtichettaNovità(String etichettaNovità){
		this.etichettaNovità = etichettaNovità;
	}
}
