package coreBusiness.utenti;

public class RapportoLavoro {
	private String dataInizio;
	private String dataFine;
	private float reddito;
	private float ammontareTrattenuteVersate;
	private String tipoContratto;
	private Contribuente contr;
	private DatoreLavoro dl;
	
	public String getDataInizio() {
		return dataInizio;
	}
	public void setDataInizio(String dataInizio) {
		this.dataInizio = dataInizio;
	}
	public String getDataFine() {
		return dataFine;
	}
	public void setDataFine(String dataFine) {
		this.dataFine = dataFine;
	}
	public float getReddito() {
		return reddito;
	}
	public void setReddito(float reddito) {
		this.reddito = reddito;
	}
	public float getAmmontareTrattenuteVersate() {
		return ammontareTrattenuteVersate;
	}
	public void setAmmontareTrattenuteVersate(float ammontareTrattenuteVersate) {
		this.ammontareTrattenuteVersate = ammontareTrattenuteVersate;
	}
	public String getTipoContratto() {
		return tipoContratto;
	}
	public void setTipoContratto(String tipoContratto) {
		this.tipoContratto = tipoContratto;
	}
	public Contribuente getContr() {
		return contr;
	}
	public void setContr(Contribuente contr) {
		this.contr = contr;
	}
	public DatoreLavoro getDl() {
		return dl;
	}
	public void setDl(DatoreLavoro dl) {
		this.dl = dl;
	}
	public float calcolaReddditoLavoroDipendente(){
		return this.reddito-this.ammontareTrattenuteVersate;
	}
	
}
