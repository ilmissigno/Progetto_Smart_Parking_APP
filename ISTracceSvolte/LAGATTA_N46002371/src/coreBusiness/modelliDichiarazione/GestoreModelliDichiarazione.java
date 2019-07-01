package coreBusiness.modelliDichiarazione;


import coreBusiness.exception.ModelloNotFoundException;

public class GestoreModelliDichiarazione implements IModelloDichiarazione {
	
	private static GestoreModelliDichiarazione istance;
	
	protected GestoreModelliDichiarazione(){}
	
	public static GestoreModelliDichiarazione getGestoreModelliDichiarazione(){
		if(istance==null){
			GestoreModelliDichiarazione.istance=new GestoreModelliDichiarazione();
		}
		return istance;
	}

	@Override
	public float calcolaImpostaIRPEF(ModelloDichiarazione md) {
		float redditiLavDip=md.getRedditoLavoroDipendente();
		float redditoImmobiliareTotale=md.calcolaRedditoImmobiliareTotale();
		float impostaIRPEF=redditiLavDip+redditoImmobiliareTotale;
		if(md instanceof ModelloUnico){
			float totPlusMinusValenze=((ModelloUnico) md).getTotalePlusMinusValenze();
			impostaIRPEF+=totPlusMinusValenze;
		}
		impostaIRPEF=applicaIVA(impostaIRPEF);
		
		return impostaIRPEF;
	}

	private float applicaIVA(float totale){
		totale=totale*(float)0.21;
		return totale;
	}
	
	@Override
	public ModelloDichiarazione getModello(String codFis, Integer annoFiscale) throws ModelloNotFoundException{
		ModelloDichiarazione md=new ModelloDichiarazione();
		try{
			md=DAO.ModelloDichiarazioneDAO.read(codFis, annoFiscale);
		}catch(java.sql.SQLException e){
			System.out.println("Errore nell'accesso ai dati");
			//e.printStackTrace();
		}
		if(md==null){
			throw new coreBusiness.exception.ModelloNotFoundException();
		}
		return md;
	}
	
}