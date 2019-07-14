package interfacce;

import java.io.DataOutputStream;

public interface IServer {
	void Login(String username , String password , DataOutputStream out);
	void OttieniCostoTicket(String CodiceArea, DataOutputStream out);
}
