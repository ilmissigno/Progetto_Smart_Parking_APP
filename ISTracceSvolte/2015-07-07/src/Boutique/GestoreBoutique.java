package Boutique;

import java.sql.SQLException;
import java.util.ArrayList;
import Boutique.exception.*;

public class GestoreBoutique implements IProdotto {
	
	private static GestoreBoutique istance;
	
	protected GestoreBoutique(){}
	
	public static GestoreBoutique getGestoreBoutique(){
		if(istance==null){
			GestoreBoutique.istance=new GestoreBoutique();
		}
		return istance;
	}
	
	public void decrementaQtaProdotto(Prodotto prod, Integer qtaVend) throws QtaVendutaMoreThanQtaDisponibileException{
		if(prod.getQta()<qtaVend){
			throw new QtaVendutaMoreThanQtaDisponibileException();
		}
		prod.setQta(prod.getQta()-qtaVend);
		try{
			DAO.ProdottoDAO.update(prod);
		}catch(SQLException e){
			System.err.println("Errore nell'aggiornamento del prodotto");
			e.printStackTrace();
		}
		
	}
	
	public ArrayList<Boutique.Prodotto> getProdotti(){
		ArrayList<Boutique.Prodotto> listaProdotti= new ArrayList<Boutique.Prodotto>();		
		try{
			listaProdotti=DAO.ProdottoDAO.readAll();
		}catch(SQLException e){
			System.err.println("Errore nel recupero dei dati");
			e.printStackTrace();
			System.exit(-1);
		}
		
		return listaProdotti;
	}
	
	public Prodotto getProdotto(ArrayList<Boutique.Prodotto> listaProdotti,int id) throws ProdottoNotFoundException{
		int i=0;
		boolean found=false;
		Boutique.Prodotto p=null;	
		while(i<listaProdotti.size()&&found==false){
			if(listaProdotti.get(i).getId()==id){
				p=listaProdotti.get(i);
				found=true;
			}
			i++;
		}
		
		if(p==null){
			throw new ProdottoNotFoundException();
		}
		return p;
	}
	
}
