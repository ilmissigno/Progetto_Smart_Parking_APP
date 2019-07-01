import java.sql.SQLException;

import UIDipendente.*;
public class Main {

	public static void main(String[] args) throws SQLException{
		
		UIDipendente uiDip=new UIDipendente();
		uiDip.decrementaDisponibilit‡Prodotto();
		DAO.DBManager.closeConnection();
	}

}
