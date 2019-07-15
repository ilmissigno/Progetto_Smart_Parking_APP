package Controller;

import java.io.DataOutputStream;

public interface GestoreAcquistoTicket {
	void Login(String username , String password , DataOutputStream out);
	void OttieniCostoTicket(String CodiceArea, DataOutputStream out);
}
