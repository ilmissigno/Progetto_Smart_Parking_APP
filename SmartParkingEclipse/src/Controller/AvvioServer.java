package Controller;
import ControllerImpl.GestoreAcquistoTicketImpl;

public class AvvioServer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GestoreAcquistoTicketImpl server = new GestoreAcquistoTicketImpl();
		server.avvia_skeleton();
		//POSSO STESSO DA QUI AVVIARE GLI ALTRI GESTORI
	}
	}


