package DAO;

public interface ConnectionFactory {
	java.sql.Connection createConnection() throws Exception;
}
