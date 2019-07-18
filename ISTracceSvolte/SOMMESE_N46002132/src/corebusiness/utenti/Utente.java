package corebusiness.utenti;

/**
 * 
 */
public class Utente  {

    /**
     * 
     */
    public Utente(String Username,String Password,String Email,Integer Eta) {
    	this.Username=Username;
    	this.Email=Email;
    	this.Password=Password;
    	this.Eta=Eta;
    }

    /**
     * 
     */
    protected String Username;

    /**
     * 
     */
    protected String Password;

    /**
     * 
     */
    protected String Email;

    /**
     * 
     */
    protected Integer Eta;

	/**
	 * @return the username
	 */
	public String getUsername() {
		return Username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		Username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return Password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		Password = password;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return Email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		Email = email;
	}

	/**
	 * @return the eta
	 */
	public Integer getEta() {
		return Eta;
	}

	/**
	 * @param eta the eta to set
	 */
	public void setEta(Integer eta) {
		Eta = eta;
	}




}
