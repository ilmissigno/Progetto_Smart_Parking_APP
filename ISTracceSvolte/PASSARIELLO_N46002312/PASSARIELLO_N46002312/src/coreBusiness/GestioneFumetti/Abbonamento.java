package coreBusiness.GestioneFumetti;

public class Abbonamento {

	private String cliente, fumetto, periodicit�;
	private int quantit�;
	
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
	
	public Integer getQuantit�(){
		return quantit�;
	}
	
	public void setQuantit�(Integer quantit�){
		this.quantit� = quantit�;
	}
	
	public String getPeriodicit�(){
		return periodicit�;
	}
	
	public void setPeriodicit�(String periodicit�){
		this.periodicit� = periodicit�;
	}
}
