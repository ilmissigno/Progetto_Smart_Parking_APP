package corebusiness.pianiVacanze;

public class Porto {
	/**
     * 
     */
	private Integer IDPorto;
	/**
     * 
     */
	private String Nome;
	/**
	 * @param iDPorto
	 * @param nome
	 */
	public Porto(Integer iDPorto, String nome) {
		IDPorto = iDPorto;
		Nome = nome;
	}
	public Porto(Integer iDPorto2) {
		this.IDPorto=iDPorto2;
	}
	/**
	 * @return the iDPorto
	 */
	public Integer getIDPorto() {
		return IDPorto;
	}
	/**
	 * @param iDPorto the iDPorto to set
	 */
	public void setIDPorto(Integer iDPorto) {
		IDPorto = iDPorto;
	}
	/**
	 * @return the nome
	 */
	public String getNome() {
		return Nome;
	}
	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		Nome = nome;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Porto other = (Porto) obj;
		if (IDPorto == null) {
			if (other.IDPorto != null)
				return false;
		} else if (!IDPorto.equals(other.IDPorto))
			return false;
		return true;
	}

}
