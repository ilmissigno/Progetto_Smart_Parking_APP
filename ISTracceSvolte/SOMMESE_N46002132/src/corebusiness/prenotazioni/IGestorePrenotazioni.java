/**
 * 
 */
package corebusiness.prenotazioni;

import corebusiness.pianiVacanze.PianoVacanza;
import corebusiness.pianiVacanze.exception.PianoVacanzaNotFound;
import corebusiness.pianiVacanze.exception.PortoNotFound;

/**
 * @author Raffaele
 *
 */
public interface IGestorePrenotazioni {
	/**
     * @param P
     */
    public void prenotaPianoVacanze(Prenotazione P);
    /**
     * Ritorna i posti prenotati per piano vacanza
     * @param piano PianoVacanza
     * @return	posti prenotati per piano vacanza
     * @throws PianoVacanzaNotFound Piano Vacanza non trovato
     * @throws PortoNotFound Porto non trovato
     */
    public Integer postiPrenotatiperPiano(PianoVacanza piano) throws PianoVacanzaNotFound, PortoNotFound;
}
