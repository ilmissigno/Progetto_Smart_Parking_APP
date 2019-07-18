package Terapia;

import java.util.ArrayList;
import java.util.Date;

public interface I_GestoreTerapia {
	public void AssociaInfermiere(String cf,Integer id,String cf2);
	public ArrayList<InfoTerapia> VisualizzaTerapieInGiornata();
	public ArrayList<InfoTerapia> VisualizzaTerapieInIntervalloDiTempo(Date di, Date df);
	public void AssociaTerapiaPaziente(String cf);
	public void InserisciTerapia(Terapia t);

}
