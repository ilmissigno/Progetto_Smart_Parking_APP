package Interfacce;

public interface IAutomobilista{
    boolean Login(String username,String password);
    boolean AcquistaTicket(String Targa , String CodiceArea , int Durata , double CostoTotale);
    double OttieniCostoTicket(String CodiceArea);
}
