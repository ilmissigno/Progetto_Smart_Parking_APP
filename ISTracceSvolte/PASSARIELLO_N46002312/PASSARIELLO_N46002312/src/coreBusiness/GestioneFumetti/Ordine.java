package coreBusiness.GestioneFumetti;

public class Ordine {

	private Integer id;
	private String cliente, backOrder, periodicità;
	
	public Ordine(String cliente, String backOrder){
		
		this.cliente = cliente;
		this.backOrder = backOrder;
	}
	
	public Ordine(){
		super();
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getCliente(){
		return cliente;
	}
	
	public void setCliente(String cliente){
		this.cliente = cliente;
	}
	
	public String getBackOrder(){
		return backOrder;
	}
	
	public void setBackOrder(String backOrder){
		this.backOrder = backOrder;
	}
	
	public String getPeriodicità(){
		return periodicità;
	}
}
