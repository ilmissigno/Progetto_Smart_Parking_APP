package corebusiness.prenotazioni;

import java.sql.SQLException;
import java.util.ArrayList;

import corebusiness.pianiVacanze.PianoVacanza;
import corebusiness.pianiVacanze.exception.PianoVacanzaNotFound;
import corebusiness.pianiVacanze.exception.PortoNotFound;
import dao.Prenotazione_DAO;

public class GestorePrenotazioni implements IGestorePrenotazioni {

	@Override
	public void prenotaPianoVacanze(Prenotazione P) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Integer postiPrenotatiperPiano(PianoVacanza piano) throws PianoVacanzaNotFound, PortoNotFound{
		Integer posti=0;
		try {
			ArrayList<Prenotazione> listaprenotazioni=Prenotazione_DAO.readall();
			for(int i=0;i<listaprenotazioni.size();i++){
				if(listaprenotazioni.get(i).getPianoVacanza().equals(piano)){
					posti=posti+listaprenotazioni.get(i).getNumeroPasseggeri();
				}
			}
		} catch (SQLException e) {
			System.err.println("Errore nel recupero dei Dati");
			System.exit(-1);
		}
		return posti;
		
	}
	private static GestorePrenotazioni istance;
	protected GestorePrenotazioni(){};
	/**
	 * Singleton
	 * @return Istanza
	 */
	public static GestorePrenotazioni getistance(){
		if(istance==null){
			istance=new GestorePrenotazioni();
		}
		return istance;
	}


}
