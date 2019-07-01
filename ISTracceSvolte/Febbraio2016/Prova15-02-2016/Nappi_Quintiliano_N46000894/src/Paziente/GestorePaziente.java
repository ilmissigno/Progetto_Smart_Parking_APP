package Paziente;

public class GestorePaziente implements I_GestorePaziente {
	
	 private static GestorePaziente instance;

	@Override
	public void InserisciPaziente(Paziente p) {
		// TODO Auto-generated method stub

	}

	@Override
	public Paziente RicercaPaziente(String cf) {
		// TODO Auto-generated method stub
		return null;
	}

	public GestorePaziente getInstance(){
		if(instance==null) instance=new GestorePaziente();
		return instance;
	}
	protected GestorePaziente(){
		
	}
}
