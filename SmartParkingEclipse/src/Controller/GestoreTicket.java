package Controller;

public interface GestoreTicket {
	double OttieniCostoTicket(String CodiceArea);
	boolean AcquistaTicket(String Targa,String CodiceArea,double Durata,double CostoTicket);
	boolean RinnovoTicket(String Targa,int Durata,int IDTicket,double CostoTicket);
}
