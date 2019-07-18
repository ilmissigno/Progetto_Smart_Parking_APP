package ui;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;


import corebusiness.pianiVacanze.GestorePianiVacanze;
import corebusiness.pianiVacanze.PianoVacanza;
import corebusiness.pianiVacanze.Porto;
import corebusiness.pianiVacanze.exception.PianoVacanzaNotFound;
import corebusiness.pianiVacanze.exception.PortoNotFound;
public class UIUtenteRegistrato {
	/**
     * Costruttore
     */
    public UIUtenteRegistrato() {
    	
    }

    /**
     * Metodo di RicercaPianiVacanze
     */
    public void ricercaPianoVacanze() {    	
    	Porto portodipartenza=selezionaPorto();
        if(portodipartenza==null){
        	return;
        }
        Date datadipartenza=selezionaData();
        if(datadipartenza==null){
        	return;
        }
        System.out.println("Hai Scelto di partire in data "+ datadipartenza.toString()+ " Dal Porto di "+portodipartenza.getNome());
        GestorePianiVacanze gestore=GestorePianiVacanze.getistance();
        ArrayList<PianoVacanza> listapiani = null;
			try {
				listapiani = gestore.ricercaVacanza(datadipartenza, portodipartenza);
				System.out.println("Trovati "+ listapiani.size()+" Piani Vacanze Disponibili");
		        for(int j=0;j<listapiani.size();j++){
		        	PianoVacanza piano=listapiani.get(j);
		        	System.out.println("Piano Vacanza ID:"+piano.getIDPianoVacanza()+" Data Partenza: "+ piano.getDataPartenza().toString()+" Data Arrivo: " + piano.getDataArrivo().toString() + " Costo: "+piano.getCosto()+" Porto Partenza: " + piano.getPortoPartenza().getNome()+ " Porto Arrivo: " + piano.getPortoArrivo().getNome() );
		        }
			} catch (PortoNotFound e) {
				e.printErrorMessage();
			} catch (PianoVacanzaNotFound e) {
				e.printErrorMessage();
			}
    }

    /**
     * Metodo di selezione del Porto (Stampa la lista di porto e permette la scelta)
     */
    public Porto selezionaPorto(){
    	getListaPorti();
    	System.out.println("Digita l'ID del Porto Corrispondente");
        try {
			Integer i = getFormattedInteger();
			return impostaPorto(i);
		} catch (NumberFormatException e) {
			System.err.println("Numero inserito non valido");
		}
        return null;
    }
    public void getListaPorti() {
        System.out.println("Scegli il porto di partenza da questa lista");
        GestorePianiVacanze gestore=GestorePianiVacanze.getistance();
        ArrayList<Porto> listaporti = gestore.listaPorti();
        for(int i=0;i<listaporti.size();i++){
        	System.out.println("ID: "+listaporti.get(i).getIDPorto()+" Nome: "+listaporti.get(i).getNome());
        }
    }

    /**
     * Metodo di impostazione del porto di partenza
     * @param i id del porto
     * @return Porto di Partenza
     * 
     */
    public Porto impostaPorto(Integer i) {
        try {
        	return GestorePianiVacanze.getistance().getPorto(i);
		} catch (PortoNotFound e) {
			// TODO Auto-generated catch block
			e.printErrorMessage();
		}
		return null;
    }
    /**
     * Preleva un input intero
     * @return Intero Formattato
     * @throws NumberFormatException Numero non valido
     */
    public Integer getFormattedInteger() throws NumberFormatException {
    	Integer intero=null;
    	BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		boolean WFI = true;
		try {
			while (WFI) {
					String bufferinput = bufferedReader.readLine();
					intero = Integer.parseInt(bufferinput);
					WFI = false;
			}
			return intero;
		} catch (IOException e) {
			System.err.println("Errore nell'inserimento");
		}
		return null;
    }
    /**
     * Metodo per la selezione della data
     * @return Data
     * 
     */
	public Date selezionaData() {
    	try {
			System.out.println("Inserisci il giorno di Partenza");
			Integer Giorno = getFormattedInteger();
			System.out.println("Inserisci il mese di Partenza in formato decimale");
			Integer Mese = getFormattedInteger();
			System.out.println("Inserisci l'anno di Partenza");
			Integer Anno=getFormattedInteger();
			Date DataPartenza = formattaData(Anno,Mese,Giorno);
			return DataPartenza;
		} catch (NumberFormatException e) {
			System.err.println("Numero inserito non valido");
		}
		return null;
    }
	/** 
	 * @param year anno
	 * @param month mese
	 * @param day giorno
	 * @return Data Formattata
	 */
    @SuppressWarnings("deprecation")
	public Date formattaData(Integer year,Integer month,Integer day) {
    	if(year==null||month==null||day==null){
    		return null;
    	}
    	if(year<1970 || year>2038 || month<1 || month>12 || day<=0|| day>31){
    		System.err.println("Valori Invalidi per La Data");
    		return null;
    	}
    	return new java.sql.Date(year-1900, month-1, day);
    }



}
