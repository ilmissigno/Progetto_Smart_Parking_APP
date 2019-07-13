package magazzino;

import java.io.FileWriter;
import java.io.IOException;

import adt.*;

public class MagazzinoImpl extends SkeletonMagazzino{
	
	CodaWrapper coda1 = new CodaWrapperLock(new CodaCircolare());
	CodaWrapper coda2 = new CodaWrapperLock(new CodaCircolare());
	FileWriter file1;
	FileWriter file2;
	
	public MagazzinoImpl(){
		try{
			file1 = new FileWriter("file_laptop.txt",true);
			file2 = new FileWriter("file_smartphone.txt",true);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void deposita(String articolo , int id){
		switch(articolo){
		case "laptop":{
			coda1.add(id);
			System.out.println("\nMagazzinoImpl : Aggiunto Laptop con id "+id);
			break;
		}
		case "smartphone":{
			coda2.add(id);
			System.out.println("\nMagazzinoImpl : Aggiunto smartphone con id "+id);
			break;
		}
		default:{
			System.out.println("\nMagazzinoImpl : Errore");
			break;
		}
		}
	}
	
	public int preleva(String articolo){
		int id = -1;
		switch(articolo){
		case "laptop":{
			id = coda1.delete();
			try{
				if(id!=0){
					file1.write(Integer.toString(id)+"\n");
					file1.flush();
					System.out.println("\nMagazzinoImpl : ID Laptop scritto su File_Laptop.txt");
				}else{
					System.out.println("\nCoda 1 Vuota!");
				}
			}catch(IOException e){
				e.printStackTrace();
			}
			break;
		}
		case "smartphone":{
			id = coda2.delete();
			try{
				if(id!=0){
					file2.write(Integer.toString(id)+"\n");
					file2.flush();
					System.out.println("\nMagazzinoImpl : ID Smartphone scritto su File_Smartphone.txt");
				}else{
					System.out.println("\nCoda 2 Vuota!");
				}
			}catch(IOException e){
				e.printStackTrace();
			}
			break;
		}
		default:{
			System.out.println("\nMagazzinoImpl : Errore");
			break;
		}
		}
		return id;
	}
	
}
