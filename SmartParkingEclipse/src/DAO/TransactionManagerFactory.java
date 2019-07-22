package DAO;


public class TransactionManagerFactory {
	//cambiare gli ingressi del db
	private static String databasePath = "jdbc:mysql://localhost:3306/dbsmartparking?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private static String databaseUsername = "root";
	private static String databasePassword = "1234";
	private static boolean initialized = false;

	public static void initializeTransactionManager(String databasePath, String databaseUsername,
			String databasePassword) {
		if (initialized) {
			throw new IllegalStateException("TransactionManager already initialized!");
		}
		TransactionManagerFactory.databasePath = databasePath;
		TransactionManagerFactory.databaseUsername = databaseUsername;
		TransactionManagerFactory.databasePassword = databasePassword;
		TransactionManagerFactory.initialized = true;
	}

	public static TransactionManager createTransactionManager() {
		return new TransactionManager(databasePath, databaseUsername, databasePassword);
	}

}