package corebusiness.utenti;

import java.util.*;

/**
 * 
 */
public class ProprietarioBarca extends Utente {

    /**
     * 
     */
    public ProprietarioBarca(String Username,String Password,String Email,Integer Eta,Integer NumeroPatente,Date DatadiRilascio,Date DatadiScadenza) {
    	super(Username,Password,Email,Eta);
    	this.NumeroPatente=NumeroPatente;
    	this.DatadiRilascio=DatadiRilascio;
    	this.DatadiScadenza=DatadiScadenza;
    }

    /**
	 * @return the numeroPatente
	 */
	public Integer getNumeroPatente() {
		return NumeroPatente;
	}

	/**
	 * @param numeroPatente the numeroPatente to set
	 */
	public void setNumeroPatente(Integer numeroPatente) {
		NumeroPatente = numeroPatente;
	}

	/**
	 * @return the datadiRilascio
	 */
	public Date getDatadiRilascio() {
		return DatadiRilascio;
	}

	/**
	 * @param datadiRilascio the datadiRilascio to set
	 */
	public void setDatadiRilascio(Date datadiRilascio) {
		DatadiRilascio = datadiRilascio;
	}

	/**
	 * @return the datadiScadenza
	 */
	public Date getDatadiScadenza() {
		return DatadiScadenza;
	}

	/**
	 * @param datadiScadenza the datadiScadenza to set
	 */
	public void setDatadiScadenza(Date datadiScadenza) {
		DatadiScadenza = datadiScadenza;
	}

	/**
     * 
     */
    private Integer NumeroPatente;

    /**
     * 
     */
    private Date DatadiRilascio;

    /**
     * 
     */
    private Date DatadiScadenza;


}