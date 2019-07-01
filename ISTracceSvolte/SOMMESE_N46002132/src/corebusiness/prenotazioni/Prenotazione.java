/**
 * 
 */
package corebusiness.prenotazioni;

import java.util.Date;

import corebusiness.pianiVacanze.PianoVacanza;
import corebusiness.utenti.Utente;

/**
 * @author Raffaele
 *
 */
public class Prenotazione {




	/**
	 * @param iDPrenotazione
	 * @param dataPrenotazione
	 * @param numeroPasseggeri
	 * @param pianoVacanza
	 * @param utente
	 */
	public Prenotazione(Integer iDPrenotazione, Date dataPrenotazione, Integer numeroPasseggeri,
			PianoVacanza pianoVacanza, Utente utente) {
		IDPrenotazione = iDPrenotazione;
		DataPrenotazione = dataPrenotazione;
		NumeroPasseggeri = numeroPasseggeri;
		this.pianoVacanza = pianoVacanza;
		this.utente = utente;
	}
	public Prenotazione(Integer iDPrenotazione2) {
		this.IDPrenotazione=iDPrenotazione2;
	}
	/**
	 * @return the iDPrenotazione
	 */
	public Integer getIDPrenotazione() {
		return IDPrenotazione;
	}
	/**
	 * @param iDPrenotazione the iDPrenotazione to set
	 */
	public void setIDPrenotazione(Integer iDPrenotazione) {
		IDPrenotazione = iDPrenotazione;
	}
	/**
	 * @return the dataPrenotazione
	 */
	public Date getDataPrenotazione() {
		return DataPrenotazione;
	}
	/**
	 * @param dataPrenotazione the dataPrenotazione to set
	 */
	public void setDataPrenotazione(Date dataPrenotazione) {
		DataPrenotazione = dataPrenotazione;
	}
	/**
	 * @return the numeroPasseggeri
	 */
	public Integer getNumeroPasseggeri() {
		return NumeroPasseggeri;
	}
	/**
	 * @param numeroPasseggeri the numeroPasseggeri to set
	 */
	public void setNumeroPasseggeri(Integer numeroPasseggeri) {
		NumeroPasseggeri = numeroPasseggeri;
	}
	/**
	 * @return the pianoVacanza
	 */
	public PianoVacanza getPianoVacanza() {
		return pianoVacanza;
	}
	/**
	 * @param pianoVacanza the pianoVacanza to set
	 */
	public void setPianoVacanza(PianoVacanza pianoVacanza) {
		this.pianoVacanza = pianoVacanza;
	}
	/**
	 * @return the utente
	 */
	public Utente getUtente() {
		return utente;
	}
	/**
	 * @param utente the utente to set
	 */
	public void setUtente(Utente utente) {
		this.utente = utente;
	}
	/**
     * 
     */
    private Integer IDPrenotazione;

    /**
     * 
     */
    private Date DataPrenotazione;

    /**
     * 
     */
    private Integer NumeroPasseggeri;
    
    private PianoVacanza pianoVacanza;
    private Utente utente;
    
}
