package coreBusiness.utenti;

import java.util.ArrayList;

public class GestoreFabbricati implements IFabbricato{
	
	private static GestoreFabbricati istance;
	
	protected GestoreFabbricati(){}
	
	public static GestoreFabbricati getGestoreFabbricati(){
		if(istance==null){
			GestoreFabbricati.istance=new GestoreFabbricati();
		}
		return istance;
	}

	@Override
	public ArrayList<Fabbricato> getFabbricati(String codFis) {
		ArrayList<Fabbricato> fabbricati= new ArrayList<Fabbricato>();
		try{
			fabbricati=DAO.FabbricatoDAO.read(codFis);
		}catch(java.sql.SQLException e){
			System.out.println("Errore nell'accesso ai dati");
			e.printStackTrace();
		}
		return fabbricati;
	}

	
	
	
	
}