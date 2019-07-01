package coreBusiness.GestioneFumetti;

import java.sql.Date;

public class Prenotazione {

	private Integer id, ordine;
	private Integer fumetto;
	private Integer quantit‡Fumetto;
	private Date data;
	
	public Prenotazione(Integer ordine, Integer fumetto, Integer quantit‡Fumetto, Date data){
		
		this.ordine = ordine;
		this.fumetto = fumetto;
		this.quantit‡Fumetto = quantit‡Fumetto;
		this.data = data;
	}
	
	public Prenotazione() {
		super();
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}	
	
	public Integer getOrdine(){
		return ordine;
	}
	
	public void setOrdine(Integer ordine){
		this.ordine = ordine;
	}
	
	public Integer getFumetto(){
		return fumetto;
	}
	
	public void setFumetto(Integer fumetto){
		this.fumetto = fumetto;
	}
	
	public Integer getQuantitaFumetto(){
		return quantit‡Fumetto;
	}
	
	public void setQuantitaFumetto(Integer quantit‡Fumetto){
		this.quantit‡Fumetto = quantit‡Fumetto;
	}
	
	public Date getData(){
		return data;
	}
	
	public void setDate(Date data){
		this.data = data;
	}
}
