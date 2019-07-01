package DAO;

import java.sql.DriverManager;

public class H2ConnectionFactory implements ConnectionFactory {	

	protected final static String CONNECTION_STRING = "jdbc:h2:~/esame15Feb2016" ;
	
	
	@Override
	public java.sql.Connection createConnection() throws Exception {
		Class.forName("org.h2.Driver");
        return DriverManager.getConnection(CONNECTION_STRING, "EMofIsland", "04031965x");
	}
}