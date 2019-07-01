package coreBusiness.utenti;

public class Fornitore {

	private String ragioneSociale, email;
	private Integer telefono;
	
	public String getRagioneSociale(){
		return ragioneSociale;
	}
	
	public void setRagioneSociale(String ragioneSociale){
		this.ragioneSociale = ragioneSociale;
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
