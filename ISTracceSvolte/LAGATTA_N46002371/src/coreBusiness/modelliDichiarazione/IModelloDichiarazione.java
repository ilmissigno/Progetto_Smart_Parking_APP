package coreBusiness.modelliDichiarazione;

public interface IModelloDichiarazione {
	public float calcolaImpostaIRPEF(ModelloDichiarazione md);
	public ModelloDichiarazione getModello(String codFis, Integer annoFiscale) throws coreBusiness.exception.ModelloNotFoundException;
}
