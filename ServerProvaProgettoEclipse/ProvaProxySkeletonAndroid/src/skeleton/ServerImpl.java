package skeleton;

import java.io.DataOutputStream;
import java.io.IOException;

public class ServerImpl extends SkeletonServer {
	
	
	@Override
	public void Login(String username, String password, DataOutputStream out) {
		//Implementazione Control Login
		try {
			out.writeUTF("ok");
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void OttieniCostoTicket(String CodiceArea, DataOutputStream out) {
		//Implementazione Control CostoTicket Area
		try {
			out.writeDouble(1.4D);
			out.flush();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

}
