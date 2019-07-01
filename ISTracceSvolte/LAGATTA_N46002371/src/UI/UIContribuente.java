package UI;

import java.io.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

import coreBusiness.exception.AnnoNotValidException;
import coreBusiness.exception.ContribuenteNotValidException;
import coreBusiness.exception.ModelloNotFoundException;
import coreBusiness.modelliDichiarazione.*;

public class UIContribuente {
	public void calcolaImpostaIRPEF(){
		String codFis=null;
		Integer annoFiscale=null;
		try{
			System.out.println("Inserisci codice fiscale: ");
			codFis=getCodFisInput();
			System.out.println("Inserisci anno fiscale: ");
			annoFiscale=getAnnoInput();
		}catch(ContribuenteNotValidException e){
			e.printMessage();
			System.exit(-1);
		}catch(AnnoNotValidException e){
			e.printMessage();
			System.exit(-1);
		}catch(NumberFormatException e){
			System.err.println("Errore nell'inserimento");
			System.exit(-1);
		}
		GestoreModelliDichiarazione gmd=GestoreModelliDichiarazione.getGestoreModelliDichiarazione();
		ModelloDichiarazione md=new ModelloDichiarazione();
		try{
			md=gmd.getModello(codFis, annoFiscale);
		}catch(ModelloNotFoundException e){
			e.printMessage();
			//e.printStackTrace();
			System.exit(-1);
		}
		float impostaIRPEF=gmd.calcolaImpostaIRPEF(md);
		
		System.out.println("L'imposta IRPEF da versare è di: "+impostaIRPEF);
		
		
	}

	public String getCodFisInput() throws ContribuenteNotValidException{
		InputStreamReader reader = new InputStreamReader(System.in);
		BufferedReader myInput = new BufferedReader(reader);
		String str= new String();
		try {
			str = myInput.readLine();
			if(str.length()!=5||str.isEmpty()){
				throw new ContribuenteNotValidException();
			}
		} catch (IOException e) {
			System.out.println ("Si è verificato un errore: " + e);
			System.exit(-1);
			}
		return str;
	}
	
	Integer getAnnoInput() throws AnnoNotValidException{
		GregorianCalendar gc = new GregorianCalendar();
		Integer annoCorrente = gc.get(Calendar.YEAR);
		Integer input=null;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean WFI = true;
		try {
			while (WFI) {
					String bufferinput = br.readLine();
					input = Integer.parseInt(bufferinput);
					WFI = false;
					if(input>=annoCorrente){
						throw new AnnoNotValidException();
					}
			}
			return input;
		} catch (IOException e) {
			System.err.println("Errore nell'inserimento");
		}
		return null;
	}
}
