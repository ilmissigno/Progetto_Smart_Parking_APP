package Terminale;

import Terapia.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Terminale {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Date datain=null;
		Date datafi=null;
		GestoreTerapia gestore= GestoreTerapia.getInstance();
		Scanner scanner = new Scanner(System.in);
		System.out.println("Inserisci data di inizio");
		String date= scanner.next();
		SimpleDateFormat dateFormat= new SimpleDateFormat("dd-mm-yy");
		try {
			datain=dateFormat.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Inserisci data di fine");
		date=scanner.next();
		try {
			datafi=dateFormat.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("data inizio "+datain+" data fine "+datafi+"\n");
		if(datain.compareTo(datafi)<=0){
		ArrayList<InfoTerapia> listaterapie=null;
		listaterapie=gestore.VisualizzaTerapieInIntervalloDiTempo(datain, datafi);
		for(int i=0;i<listaterapie.size();i++){
			System.out.println("Id_Terapia: "+listaterapie.get(i).getTerapiaCorrisp().getIdTerapia()+"\nData di inizio:"+listaterapie.get(i).getTerapiaCorrisp().getDataInizio().toString()+"\nDataFine: "+listaterapie.get(i).getTerapiaCorrisp().getDataFine().toString()+"\nDescrizione:"+listaterapie.get(i).getTerapiaCorrisp().getDescrizione());
			System.out.println("\nAl paziente:"+listaterapie.get(i).getPazienteCorrisp().getNome()+" "+listaterapie.get(i).getPazienteCorrisp().getCognome()+"\nCodice Fiscale"+listaterapie.get(i).getPazienteCorrisp().getCodiceFiscale()+"\nDomicilio:"+listaterapie.get(i).getPazienteCorrisp().getDomicilio()+"\n");
		}
		
		

	}
		else{
			System.err.println("Errore inserimento data");
		}

	}
	
}
