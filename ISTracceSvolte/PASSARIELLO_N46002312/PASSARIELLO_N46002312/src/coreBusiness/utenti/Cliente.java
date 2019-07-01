package coreBusiness.utenti;

public class Cliente {

	private String indirizzoFatturazione, indirizzoSpedizione, email;
	private Integer telefono;
	
	public String getIndirizzoFatturazione(){
		return indirizzoFatturazione;
	}
	
	public void setIndirizzoFatturazione(String indirizzoFatturazione){
		this.indirizzoFatturazione = indirizzoFatturazione;
	}
	
	public String getIndirizzoSpedizione(){
		return indirizzoSpedizione;
	}
	
	public void setIndirizzoSpedizione(String indirizzoSpedizione){
		this.indirizzoSpedizione = indirizzoSpedizione;
	}
	
	public String getEmail(){
		return email;
	}
	
	public void setEmail(String email){
		this.email = email;
	}
	
	public Integer getTelefono(){
		return telefono;
	}
	
	public void setTelefono(Integer telefono){
		this.telefono = telefono;
	}
}
