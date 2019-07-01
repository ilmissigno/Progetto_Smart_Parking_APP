package UIDipendente;

import java.util.ArrayList;

import Boutique.exception.ProdottoNotFoundException;
import Boutique.exception.QtaVendutaMoreThanQtaDisponibileException;

import java.io.*;
public class UIDipendente {
	
	public void decrementaDisponibilit‡Prodotto(){
		System.out.println("Scegli il prodotto da questa lista:\n ");		
		Boutique.GestoreBoutique gestore= Boutique.GestoreBoutique.getGestoreBoutique();
		ArrayList<Boutique.Prodotto> listaProdotti=gestore.getProdotti();
		stampaProdotti(listaProdotti);
		int idProd=0,qtaVend=0;
		System.out.println("Inserire l'id del prodotto scelto: ");
		idProd=getIntegerInput();
		System.out.println("Inserire la quantit‡ venduta: ");
		qtaVend=getIntegerInput();
		System.out.println("Hai scelto: "+idProd+' '+qtaVend);
		Boutique.Prodotto prod=null;
		try{
			prod= gestore.getProdotto(listaProdotti, idProd);
			gestore.decrementaQtaProdotto(prod, qtaVend);
		}catch(ProdottoNotFoundException e){
			System.out.println(e.getMessage());
			//e.printStackTrace();
			System.exit(-1);
		}catch(QtaVendutaMoreThanQtaDisponibileException e){
			System.out.println(e.getMessage());
			//e.printStackTrace();
			System.exit(-1);
		}
		System.out.println("Aggiornamento eseguito con successo!");
	}
	
	void stampaProdotti(ArrayList<Boutique.Prodotto> listaProdotti){
		System.out.println("Sono disponibili: "+listaProdotti.size()+" prodotti");
		for(int i=0;i<listaProdotti.size();i++){
			Boutique.Prodotto p=listaProdotti.get(i);
			System.out.println(p.getId().toString()+' '+p.getNome()+' '+p.getZonaGeografica()+' '+p.getPrezzo()
							+' '+p.getQta()+'\n');
		}
	}
	
	Integer getIntegerInput(){
		Integer input=null;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean WFI = true;
		try {
			while (WFI) {
					String bufferinput = br.readLine();
					input = Integer.parseInt(bufferinput);
					WFI = false;
			}
			return input;
		} catch (IOException e) {
			System.err.println("Errore nell'inserimento");
		}
		return null;
	}
	
}
