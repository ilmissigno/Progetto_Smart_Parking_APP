package corebusiness.barche;

public class GestoreBarche implements IGestoreBarche {

	@Override
	public void InserisciBarca(Barca B) {
		// TODO Auto-generated method stub

	}

	@Override
	public void ModificaBarca(Barca B) {
		// TODO Auto-generated method stub

	}

	@Override
	public void RimuoviBarca(Barca B) {
		// TODO Auto-generated method stub

	}

	/**
	 * 
	 */
	private static GestoreBarche istance;

	/**
	 * 
	 */
	protected GestoreBarche() {
		// TODO implement here
	}

	/**
	 * Singleton
	 * @return Istanza
	 */
	public static GestoreBarche getistance() {
		if (istance == null) {
			GestoreBarche.istance = new GestoreBarche();
		}
		return istance;
	}

}
