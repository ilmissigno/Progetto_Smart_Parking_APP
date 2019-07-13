package adt;

public abstract class CodaWrapper implements ICoda{
	ICoda coda;
	
	public CodaWrapper(ICoda coda){
		this.coda = coda;
	}
	
	public boolean is_empty(){
		return coda.is_empty();
	}
	
	public boolean is_full(){
		return coda.is_full();
	}
	
}
