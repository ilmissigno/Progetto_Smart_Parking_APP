package DAO;

import java.sql.DriverManager;

public class H2ConnectionFactory implements ConnectionFactory {
	
	protected final static String DB_PATH = "~/SistemaIRPEF"; // Path relativo al progetto
	protected final static String CONNECTION_STRING = "jdbc:h2:" + DB_PATH;
	protected final static String DB_USER="valerio";
	protected final static String DB_PASS="";
	
	@Override
	public java.sql.Connection createConnection() throws Exception {
		Class.forName("org.h2.Driver");
        return DriverManager.getConnection(CONNECTION_STRING, DB_USER, DB_PASS);
	}
}
/*
 
 •	CONTRIBUENTI(cf,nome,cognome,dataNascita,comune, user, password, stato)
 •	MODELLI(id,tipo,annoFiscale,stato,redditoLavoroDipendente,contribuente: CONTRIBUENTI,totalePlusMinusValenza)
 •	FABBRICATI(id,rendita,giorniPoss,percPoss,comune,contribuente: CONTRIBUENTI)

 */
