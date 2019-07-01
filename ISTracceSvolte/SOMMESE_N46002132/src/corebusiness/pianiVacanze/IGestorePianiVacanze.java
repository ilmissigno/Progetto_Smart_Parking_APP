/**
 * 
 */
package corebusiness.pianiVacanze;

import java.util.ArrayList;
import java.util.Date;

import corebusiness.pianiVacanze.exception.PianoVacanzaNotFound;
import corebusiness.pianiVacanze.exception.PortoNotFound;

/**
 * @author Raffaele
 *
 */
public interface IGestorePianiVacanze {

    /**
     * @param P
     */
    public void inserisciPianoVacanza(PianoVacanza P);

    /**
     * @param P
     */
    public void modificaPianoVacanza(PianoVacanza P);

    /**
     * @param P
     */
    public void eliminaPianoVacanza(PianoVacanza P);

    /**
     * @param P
     */
    public void verificaDate(PianoVacanza P);
    public ArrayList<Porto> listaPorti();
    /**
     * @param D Data di Partenza
     * @param P Porto di Partenza
     * @return Piani Vacanza
     * @throws PortoNotFound Porto non trovato 
     * @throws PianoVacanzaNotFound Piano Vacanza non trovato
     */
    public ArrayList<PianoVacanza> ricercaVacanza(Date D, Porto P) throws PortoNotFound, PianoVacanzaNotFound;
    /**
     * 
     * @param id ID Porto
     * @return Porto
     * @throws PortoNotFound Porto non trovato
     */
    public Porto getPorto(Integer id) throws PortoNotFound;
}
