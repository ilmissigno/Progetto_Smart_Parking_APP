package Control;

import java.sql.SQLException;

public interface AuthenticationManager {

	String Login(String username, String password) throws SQLException;

}
