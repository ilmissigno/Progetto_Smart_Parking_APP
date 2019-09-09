package ControllerImpl;

import java.io.DataOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import Controller.GestoreTicket;
import Entity.AreaParcheggio;
import Entity.Auto;
import Entity.Ticket;

public class GestoreTicketImpl implements GestoreTicket{

	@Override
	public double OttieniCostoTicket(int CodiceArea, double Durata) {
		// è una read che farò dopo, creo un costruttore area parcheggio dal codice area
		// mi darà tutto l'oggetto è farò semplicemente getCosto per prendere il costo
		AreaParcheggio area = new AreaParcheggio(CodiceArea);
		double costo =area.getCostoTicket();
		double costoTotale = costo*Durata;
		return costoTotale;
	}

	@Override
	public Ticket ConfermaTicket(String Targa, String CodiceArea, double Durata, double CostoTicket, String username,String password) {
		//Costruttore per la creazione di un ticket
		//l'auto ed il codice Area devono esistere già prima del ticket
		//penso che devo crearmi prima l'oggetto auto e quello areaParcheggio
		Auto auto=new Auto(Targa); //in questo costruttore leggo l' auto
		AreaParcheggio area = new AreaParcheggio(Integer.parseInt(CodiceArea));
		Ticket ticket = new Ticket();
		return ticket.AcquistaTicket(auto,area,Durata,username,password);
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
		Ticket t=new Ticket(IDTicket,username);
		
		 double durata = t.TimerTicket();
		 InizializzaTimer(durata,out);
		 
	}

	@Override
	public Ticket RinnovaTicket(int IDTicket, double durata, double costoTotale, String username, String password) {
		// TODO Auto-generated method stub
		Ticket ticket = new Ticket();
		return ticket.RinnovaTicket(IDTicket,durata,username,password);
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
            	//significa che ï¿½ scattato
            }
            //Alla scadenza del timer parte la notifica e si ripete ogni 100 secondi
        },20000);
		return;
	}
	
	public double TrovaRimborso(int IDTicket, String username) {
		Ticket ticket= new Ticket(IDTicket,username);
		String DataScadenza=ticket.getScadenzaTicket();
		Date date = new Date(); 
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String DataString=formatter.format(date).toString();
	int MinDiRimborso=DifferenzaTraDate(DataScadenza,DataString);
	//il costo ticket è un double quindi la divisione dovrebbe venirmi precisa!
		double importoalMin=(ticket.getAreaParcheggio().getCostoTicket())/ 60;
		double ImportoRimborso= MinDiRimborso*importoalMin;
		//devo adesso verifivare se l'importo è minore di 1 centesimo
		DecimalFormat format = new DecimalFormat();
		format.setMaximumFractionDigits(2);
		format.format(ImportoRimborso);
		if(ImportoRimborso>=0.01D) {
			return ImportoRimborso;
		}
		return -1;
	}
	
	private int DifferenzaTraDate(String DataScadenza, String DataAttuale) {
		try {
			//dataAttuale
		 // String strDate1 = "2007/04/15 12:35:05";
			//DataScadenza
         // String strDate2 = "2009/08/02 20:45:07";
			
          SimpleDateFormat fmt = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
          fmt.setLenient(false);

          // Parses the two strings.
          Date d1 = fmt.parse(DataAttuale);
          Date d2 = fmt.parse(DataScadenza);

          // Calculates the difference in milliseconds.
          long millisDiff = d2.getTime() - d1.getTime();

          // Calculates days/hours/minutes/seconds.
          int seconds = (int) (millisDiff / 1000 % 60);
          int minutes = (int) (millisDiff / 60000 % 60);
          int hours = (int) (millisDiff / 3600000 % 24);
          int days = (int) (millisDiff / 86400000);

          System.out.println("Between " + DataAttuale + " and " + DataScadenza + " there are:");
          System.out.print(days + " days, ");
          System.out.print(hours + " hours, ");
          System.out.print(minutes + " minutes, ");
          System.out.println(seconds + " seconds");
          //la differenza è espressa in minuti
          int differenza=0;
          if(days>=1) {
        	  //ci sono 1440 minuti in un giorno
        	  differenza=differenza+(days*1440);
          }
          if(hours>=1) {
        	  differenza=differenza+(hours*60);  
          }
          if(minutes>=1) {
        	  differenza=differenza+minutes;
        	//ho un anticipo di un valore pari a differenza
          }
          return differenza;
		} catch (Exception e) {
            System.err.println(e);
        }
		return 0;
		
		
		
	}
	
	
	
}
	
	


