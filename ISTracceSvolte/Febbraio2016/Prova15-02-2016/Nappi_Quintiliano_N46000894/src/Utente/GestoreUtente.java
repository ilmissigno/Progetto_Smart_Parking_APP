package Utente;

public class GestoreUtente implements I_GestoreUtente {
	
	private static GestoreUtente instance;

	@Override
	public void Autenticazione(String us, String pw) {
		// TODO Auto-generated method stub

	}

	@Override
	public void InserisciInfermiere(Infermiere i) {
		// TODO Auto-generated method stub

	}

	@Override
	public void RimuoviInfermiere(String cf) {
		// TODO Auto-generated method stub

	}
	public GestoreUtente getInstance(){
		if(instance==null) instance=new GestoreUtente();
		return instance;
	}
	protected GestoreUtente(){
		
	}

}
