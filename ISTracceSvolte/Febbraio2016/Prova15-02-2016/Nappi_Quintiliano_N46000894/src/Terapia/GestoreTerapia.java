package Terapia;


import java.util.ArrayList;
import java.util.Date;

import DAO.DAO_InfoTerapia;
import DAO.DAO_Paziente;
import DAO.DAO_Terapia;
import Paziente.Paziente;

public class GestoreTerapia implements I_GestoreTerapia {
	
	private static GestoreTerapia instance;
	
	protected GestoreTerapia(){
		
	}

	public static GestoreTerapia getInstance(){
		if(instance==null) instance=new GestoreTerapia();
		return instance;
	}
	@Override
	public void AssociaInfermiere(String cf, Integer id,String cf2) {
		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList<InfoTerapia> VisualizzaTerapieInGiornata() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<InfoTerapia> VisualizzaTerapieInIntervalloDiTempo(Date di,Date df) {
		
		ArrayList<Terapia> listaTer= new ArrayList<Terapia>();
		ArrayList<InfoTerapia> listaInfo=new ArrayList<InfoTerapia>();
		
		listaTer=DAO_Terapia.readAll();
		for(Terapia ter: listaTer){
			if(di.compareTo(ter.getDataInizio())>=0 && df.compareTo(ter.getDataFine())<=0){
				ArrayList<InfoTerapia> listaIT=new ArrayList<InfoTerapia>();
				listaIT=DAO_InfoTerapia.readAll();
				ArrayList<Paziente> listaPaziente=new ArrayList<Paziente>();
				listaPaziente=DAO_Paziente.readAll();
				for(InfoTerapia it: listaIT){
					if(ter.getIdTerapia().equals(it.ottieniTerapiaCorrisp().getIdTerapia())){
						for(Paziente paz:listaPaziente){
							if(paz.getCodiceFiscale().equals(it.ottieniPazienteCorrisp().getCodiceFiscale())){
								it.setTerapiaCorrisp(ter);
								it.setPazienteCorrisp(paz);
								listaInfo.add(it);
						}
						}
					}
				}
			}
		}
	
		return listaInfo;
	}

	@Override
	public void AssociaTerapiaPaziente(String cf) {
		// TODO Auto-generated method stub

	}

	@Override
	public void InserisciTerapia(Terapia t) {
		// TODO Auto-generated method stub

	}

}
