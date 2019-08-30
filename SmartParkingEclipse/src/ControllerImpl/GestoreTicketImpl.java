package ControllerImpl;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import Controller.GestoreTicket;
import Entity.AreaParcheggio;
import Entity.Auto;
import Entity.Ticket;

public class GestoreTicketImpl implements GestoreTicket{

	@Override
	public double OttieniCostoTicket(int CodiceArea, double Durata) {
		AreaParcheggio area = new AreaParcheggio();
		double costo =area.OttieniCostoTicket(CodiceArea);
		double costoTotale = costo*Durata;
		return costoTotale;
	}

	@Override
	public boolean ConfermaTicket(String Targa, String CodiceArea, double Durata, double CostoTicket, String username,String password ,DataOutputStream out) {
		Ticket ticket = new Ticket();
			if(ticket.AcquistaTicket(Targa,CodiceArea,Durata,username,password,out)) {
				return true;
			}else {
				return false;
			}
	}

	@Override
	public boolean RinnovoTicket(String Targa, int Durata, int IDTicket, double CostoTicket) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int VerificaCopertura(String CodiceArea, String targa) {
		Ticket t = new Ticket();
		return t.OttieniTicket(CodiceArea,targa);
	}
	
	public void TimerTicket(String username, int IDTicket,DataOutputStream out) {
		Ticket t=new Ticket();
		 double durata = t.TimerTicket(username,IDTicket);
		 InizializzaTimer(durata,out);
	}

	@Override
	public boolean RinnovaTicket(int IDTicket, double durata, double costoTotale, String username, String password,
			DataOutputStream out) {
		// TODO Auto-generated method stub
		
		Ticket ticket = new Ticket();
		if(ticket.RinnovaTicket(IDTicket,durata,username,password,out)) {
			return true;
		}else {
			return false;
		}
	
	}

	@Override
	public boolean EliminaTicket(int IDTicket) {
		// TODO Auto-generated method stub
		Ticket ticket = new Ticket();
		if(ticket.EliminaTicket(IDTicket)) {
			return true;
		}else {
			return false;
		}
	}
	
	protected void InizializzaTimer(double durata, DataOutputStream out) {
		int durataInt=(int)durata;
		//forse posso modularizzare di piu il codice , per ora lo metto qua
		//calcolo il tempo dopo il quale deve scattare la notifica
		int ScattoTimer_secondi=durataInt*3600;
		//A meno di 10 minuti
		ScattoTimer_secondi = ScattoTimer_secondi-(600*1000);
		int ScattoTimer_millisecondi = ScattoTimer_secondi*1000;
	    Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			//questa funzione run vuole void e mi obbliga a scrivere qua e non nello skeleton
			//sideve provare
            @Override
            public void run() {
                //System.out.println("CIAO!");
            	try {
					out.writeBoolean(true);
					out.flush();
					return;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
            	//significa che � scattato
            }
            //Alla scadenza del timer parte la notifica e si ripete ogni 100 secondi
        },20000);
		return;
	}
	
}
	
	


