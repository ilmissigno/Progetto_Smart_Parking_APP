package coreBusiness.GestioneFumetti;

public class Abbonamento {

	private String cliente, fumetto, periodicità;
	private int quantità;
	
	public String getCliente(){
		return cliente;
	}
	
	public void setCliente(String cliente){
		this.cliente = cliente;
	}
	
	public String getFumetto(){
		return fumetto;
	}
	
	public void setFumetto(String fumetto){
		this.fumetto = fumetto;
	}
	
	public Integer getQuantità(){
		return quantità;
	}
	
	public void setQuantità(Integer quantità){
		this.quantità = quantità;
	}
	
	public String getPeriodicità(){
		return periodicità;
	}
	
	public void setPeriodicità(String periodicità){
		this.periodicità = periodicità;
	}
}
