/**
 * 
 */
package corebusiness.pianiVacanze;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import corebusiness.pianiVacanze.exception.PianoVacanzaNotFound;
import corebusiness.pianiVacanze.exception.PortoNotFound;
import corebusiness.prenotazioni.GestorePrenotazioni;
import dao.PianoVacanza_DAO;
import dao.Porto_DAO;




/**
 * @author Raffaele
 *
 */
public class GestorePianiVacanze implements IGestorePianiVacanze {

	@Override
	public void inserisciPianoVacanza(PianoVacanza P) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modificaPianoVacanza(PianoVacanza P) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminaPianoVacanza(PianoVacanza P) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void verificaDate(PianoVacanza P) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public ArrayList<PianoVacanza> ricercaVacanza(Date D, Porto P) throws PortoNotFound, PianoVacanzaNotFound {
		GestorePrenotazioni gestore=GestorePrenotazioni.getistance();
		ArrayList<PianoVacanza> listapiani=new ArrayList<PianoVacanza>();
		try {
			ArrayList<PianoVacanza> piano=PianoVacanza_DAO.readall();
			for(int i=0;i<piano.size();i++){
				if(piano.get(i).getDataPartenza().equals(D) && piano.get(i).getPortoPartenza().equals(P)){
					if((piano.get(i).getNumeroMassimo()-gestore.postiPrenotatiperPiano(piano.get(i)))>0){
						listapiani.add(piano.get(i));
					}
				}
			}
		} catch (SQLException e) {
			System.err.println("Errore nel recupero dei Dati");
			System.exit(-1);
		}
		return listapiani;
	}
	
	private static GestorePianiVacanze istance;
	
	protected GestorePianiVacanze(){};
	/**
	 * Singleton
	 * @return Istanza
	 */
	public static GestorePianiVacanze getistance(){
		if (istance == null) {
			GestorePianiVacanze.istance = new GestorePianiVacanze();
		}
		return istance;
	}

	@Override
	public ArrayList<Porto> listaPorti() {
		try {
			return Porto_DAO.readall();
		} catch (SQLException e) {
			System.err.println("Errore nel recupero dei Dati");
			System.exit(-1);
		}
		return null;		
	}

	@Override
	public Porto getPorto(Integer id) throws PortoNotFound {
		try {
			return Porto_DAO.read(id);
		} catch (SQLException e) {
			System.err.println("Errore nel recupero dei Dati");
			System.exit(-1);
		}
		return null;
	}


	
}
