package Terapia;

import Paziente.Paziente;
import Utente.Infermiere;

public class InfoTerapia {
	
	private Terapia terapia;
	private Paziente paziente;
	private Infermiere infermiere;
	
	public Terapia ottieniTerapiaCorrisp(){
		return this.terapia;
	}

	public Paziente ottieniPazienteCorrisp(){
		return this.paziente;
	}
	
	public Infermiere ottieniInfermiereCorrisp(){
		return this.infermiere;
		
	}
	
	public InfoTerapia(){

	}
	
	public InfoTerapia(Terapia tera, Paziente paz){
		super();
		this.terapia=tera;
		this.paziente=paz;		
	}
	
	public void setTerapiaCorrisp(Terapia tera){
		this.terapia=tera;
	}
	
	public Terapia getTerapiaCorrisp(){
		return this.terapia;
	}
	
	public void setPazienteCorrisp(Paziente paz){
		this.paziente=paz;
	}
	
	public Paziente getPazienteCorrisp(){
		return this.paziente;
	}
	
	
}
