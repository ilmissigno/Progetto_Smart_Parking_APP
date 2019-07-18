package coreBusiness.utenti;

public class Privato extends Cliente {

	private String nome, cognome;
	
	public String getNome(){
		return nome;
	}
	
	public void setNome(String nome){
		this.nome = nome;
	}
	
	public String getCognome(){
		return cognome;
	}
	
	public void setCognome(String cognome){
		this.cognome = cognome;
	}
}
