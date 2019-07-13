package adt;

public class CodaCircolare implements ICoda{
	
	int testa=0;
	int coda=0;
	int riemp=0;
	final int DIM = 6; //Le code hanno dimensioni pari a 5 (da 0 a 5)
	int[] vettore = new int[DIM];
	
	public void add(int id){
		vettore[coda]=id;
		coda = (coda+1)%DIM;
		riemp++;
	}
	
	public int delete(){
		int val = vettore[testa];
		testa=(testa+1)%DIM;
		riemp--;
		return val;
	}
	
	public boolean is_empty(){
		return riemp==0;
	}
	
	public boolean is_full(){
		return riemp==DIM;
	}
	
}
