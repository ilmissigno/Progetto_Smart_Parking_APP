package coreBusiness.utenti;

public class GestoreUtenti implements IUtente{

	private static GestoreUtenti istance;
	
	protected GestoreUtenti(){}
	
	public static GestoreUtenti getGestoreUtenti(){
		if(istance==null){
			GestoreUtenti.istance=new GestoreUtenti();
		}
		return istance;
	}
	
	

	@Override
	public Contribuente getContribuente(String codFis) {
		Contribuente contr=new Contribuente();
		try{
			contr=DAO.ContribuenteDAO.read(codFis);
		}catch(java.sql.SQLException e){
			System.out.println("Errore nell'accesso ai dati");
			e.printStackTrace();
		}		
		return contr;
	}

}