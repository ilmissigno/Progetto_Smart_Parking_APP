package coreBusiness.modelliDichiarazione;

import coreBusiness.utenti.Contribuente;

public class ModelloUnico extends ModelloDichiarazione{
	private float totalePlusMinusValenze;

	public ModelloUnico(Integer annoFiscale, String stato, float redditoLavoroDipendente, Contribuente contr) {
		super(annoFiscale, stato, redditoLavoroDipendente, contr);
	}

	public ModelloUnico(Integer annoFiscale, String stato, float redditoLavoroDipendente, Contribuente contr,
			float totalePlusMinusValenze) {
		super(annoFiscale, stato, redditoLavoroDipendente, contr);
		this.totalePlusMinusValenze = totalePlusMinusValenze;
	}

	public float getTotalePlusMinusValenze() {
		return totalePlusMinusValenze;
	}

	public void setTotalePlusMinusValenze(float totalePlusMinusValenze) {
		this.totalePlusMinusValenze = totalePlusMinusValenze;
	}
}
