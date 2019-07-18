package coreBusiness.modelliDichiarazione;

import java.util.ArrayList;

import coreBusiness.utenti.Contribuente;
import coreBusiness.utenti.Fabbricato;
import coreBusiness.utenti.GestoreFabbricati;

public class ModelloDichiarazione {
	protected Integer id;
	protected Integer annoFiscale;
	protected String stato;
	protected float redditoLavoroDipendente;
	protected Contribuente contr;
	
	
	
	public ModelloDichiarazione() {
		super();
	}

	public ModelloDichiarazione(Integer annoFiscale, String stato, float redditoLavoroDipendente, Contribuente contr) {
		super();
		this.annoFiscale = annoFiscale;
		this.stato = stato;
		this.redditoLavoroDipendente = redditoLavoroDipendente;
		this.contr = contr;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAnnoFiscale() {
		return annoFiscale;
	}
	public void setAnnoFiscale(Integer annoFiscale) {
		this.annoFiscale = annoFiscale;
	}
	public String getStato() {
		return stato;
	}
	public void setStato(String stato) {
		this.stato = stato;
	}
	public Contribuente getContr() {
		return contr;
	}
	public void setContr(Contribuente contr) {
		this.contr = contr;
	}
	public float getRedditoLavoroDipendente() {
		return redditoLavoroDipendente;
	}
	public void setRedditoLavoroDipendente(float redditoLavoroDipendente) {
		this.redditoLavoroDipendente = redditoLavoroDipendente;
	}
	
	public float calcolaRedditoImmobiliareTotale(){
		String cfContribuente=this.getContr().getCodFis();
		ArrayList<Fabbricato> fabbricati=new ArrayList<Fabbricato>();
		GestoreFabbricati gf=GestoreFabbricati.getGestoreFabbricati();
		fabbricati=gf.getFabbricati(cfContribuente);
		float redditoImmobiliareTotale=0;
		for(Fabbricato f:fabbricati){
			if(f.getAnnoCessione()>=this.annoFiscale||f.getAnnoCessione()==0){
				redditoImmobiliareTotale+=f.calcolaRedditoFabbricato();
			}
		}	
		return redditoImmobiliareTotale;
	}
	
	
}
