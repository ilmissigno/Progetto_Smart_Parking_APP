package coreBusiness.GestioneFumetti;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

import DAO.PrenotazioneDAO;
import coreBusiness.exception.FumettiNotFoundException;

public class GestoreBusiness implements IBusiness {

private static GestoreBusiness istance;
	
	protected GestoreBusiness(){}
	
	public static GestoreBusiness getGestoreBusiness(){
		if(istance==null){
			GestoreBusiness.istance=new GestoreBusiness();
		}
		return istance;
	}

	@Override
	public Prenotazione inviaEmailRiepilogo(){
		
		Prenotazione p = new Prenotazione();
		Date data = getGiornoPrecedente();
		
		System.out.println("Email di riepilogo: \n");
		p = PrenotazioneDAO.read(data);
		if( p == null)
			try {
				throw new FumettiNotFoundException();
			} catch (FumettiNotFoundException e) {
				e.gestisciErrore();
			}
		return p;
	}

	private Date getGiornoPrecedente() {
		
		GregorianCalendar gc = new GregorianCalendar();
		@SuppressWarnings("deprecation")
		Date data = new Date((gc.get(Calendar.YEAR)-1900), gc.get(Calendar.MONTH), (gc.get(Calendar.DATE)-1));
		return data ;
	}
}
