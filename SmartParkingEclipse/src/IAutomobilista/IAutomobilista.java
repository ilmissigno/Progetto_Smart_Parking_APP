package IAutomobilista;
import java.io.DataOutputStream;

public interface IAutomobilista {
	
	void Login(String username , String password , DataOutputStream out);
	
	void OttieniCostoTicket(String CodiceArea, DataOutputStream out);
	
								}


