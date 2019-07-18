package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DAO.TransactionManager.TransactionStateException;

public class MainProva {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
TransactionManager man=TransactionManagerFactory.createTransactionManager();
try {
	man.beginTransaction();
	man.assertInTransaction();
	
	

} catch (TransactionStateException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} catch (ClassNotFoundException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}

	}}
