package interfacce;

public interface IMagazzino {
	void deposita(String articolo , int id);
	int preleva(String articolo);
}
