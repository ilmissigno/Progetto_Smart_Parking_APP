package coreBusiness.modelliDichiarazione;

import coreBusiness.utenti.Contribuente;

public class Modello730 extends ModelloDichiarazione {

	public Modello730(Integer annoFiscale, String stato, float redditoLavoroDipendente, Contribuente contr) {
		super(annoFiscale, stato, redditoLavoroDipendente, contr);
	}
	
}
