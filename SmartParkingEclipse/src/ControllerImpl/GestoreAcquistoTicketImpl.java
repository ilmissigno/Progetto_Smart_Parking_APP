package ControllerImpl;

import java.io.DataOutputStream;
import java.io.IOException;
import Controller.GestoreAcquistoTicket;
import SkeletonGestoreAcquisto.SkeletonServerAcquisto;

//Attenzione qua!!!!!!!!!!!!
//non metto che implementa l 'interfaccia perchè già lo skeleton la implementa
public class GestoreAcquistoTicketImpl  extends SkeletonServerAcquisto {
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

