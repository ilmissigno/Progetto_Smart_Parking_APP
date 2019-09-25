package ControllerImpl;

import java.io.DataOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import Controller.GestoreTicket;
import Entity.AreaParcheggio;
import Entity.Auto;
import Entity.Automobilista;
import Entity.Ticket;

public class GestoreTicketImpl implements GestoreTicket{
	private ArrayList<Ticket> listaTicket;

	public GestoreTicketImpl() {
		this.listaTicket = new ArrayList<Ticket>();
	}

	@Override
	public double OttieniCostoTicket(int CodiceArea, double Durata) {
		AreaParcheggio area = new AreaParcheggio(CodiceArea);
		double costo =area.getCostoTicket();
		double costoTotale = costo*Durata;
		return costoTotale;
	}

	@Override
	public Ticket ConfermaTicket(String Targa, String CodiceArea, double Durata, double CostoTicket, String username,String password) {
		Automobilista automobilista= new Automobilista(username,password);
		Auto auto=new Auto(Targa);
		AreaParcheggio area = new AreaParcheggio(Integer.parseInt(CodiceArea));
		String ScadenzaTicket=OttieniDataScadenzaTicket(auto,area,Durata);
		Ticket ticket = new Ticket(ScadenzaTicket,Durata,auto,area,automobilista);
		System.out.println(ticket.getAutomobilista().getUsername());
		listaTicket.add(ticket);
		return ticket; 
	}

	@Override
	public int VerificaCopertura(String CodiceArea, String targa) {
		Ticket t = new Ticket();
		return t.OttieniTicket(CodiceArea,targa);
	}

	public void AvviaTimer(String username, int IDTicket, DataOutputStream out) {
		double durata=0;
		int i=0;
		for( i=0;i<listaTicket.size();i++) {
			if(listaTicket.get(i).getIDTicket()==IDTicket) {
				durata= listaTicket.get(i).getDurata();
				break;
			}
		}
		InizializzaTimer(durata,listaTicket.get(i),out);
	}

	@Override
	public Ticket RinnovaTicket(int IDTicket, double durata) {
		int i=0;
		for( i=0;i<listaTicket.size();i++) {
			if(listaTicket.get(i).getIDTicket()==IDTicket) {
				break;
			}
		}
		System.out.println(listaTicket.get(i).getIDTicket());
		DeterminaNuovaDataScadenza(listaTicket.get(i), durata);
		listaTicket.get(i).SalvaTicketInDB();
		return listaTicket.get(i);
	}

	@Override
	public boolean EliminaTicket(int IDTicket) {
		int i=0;
		for( i=0;i<listaTicket.size();i++) {
			if(listaTicket.get(i).getIDTicket()==IDTicket) {
				break;
			}
		}
		if(listaTicket.get(i).EliminaTicketdaDB(IDTicket)) {
			if(AggiornaDisponibilita(listaTicket.get(i).getAreaParcheggio().getCodiceArea(),"elimina")) {
				listaTicket.get(i).getTimer().cancel();
				listaTicket.get(i).getTimer().purge();
				listaTicket.remove(i);
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}
	}

	public double TrovaRimborso(int IDTicket, String username) {
		Ticket ticket= new Ticket(IDTicket,username);
		String DataScadenza=ticket.getScadenzaTicket();
		Date date = new Date(); 
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String DataString=formatter.format(date).toString();
		int MinDiRimborso=DifferenzaTraDate(DataScadenza,DataString);
		double importoalMin=(ticket.getAreaParcheggio().getCostoTicket())/ 60;
		double ImportoRimborso= MinDiRimborso*importoalMin;
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
			SimpleDateFormat fmt = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			fmt.setLenient(false);
			Date d1 = fmt.parse(DataAttuale);
			Date d2 = fmt.parse(DataScadenza);
			long millisDiff = d2.getTime() - d1.getTime();
			int seconds = (int) (millisDiff / 1000 % 60);
			int minutes = (int) (millisDiff / 60000 % 60);
			int hours = (int) (millisDiff / 3600000 % 24);
			int days = (int) (millisDiff / 86400000);
			System.out.println("Between " + DataAttuale + " and " + DataScadenza + " there are:");
			System.out.print(days + " days, ");
			System.out.print(hours + " hours, ");
			System.out.print(minutes + " minutes, ");
			System.out.println(seconds + " seconds");
			int differenza=0;
			if(days>=1) {
				differenza=differenza+(days*1440);
			}
			if(hours>=1) {
				differenza=differenza+(hours*60);  
			}
			if(minutes>=1) {
				differenza=differenza+minutes;
			}
			return differenza;
		} catch (Exception e) {
			System.err.println(e);
		}
		return 0;
	}

	protected void InizializzaTimer(double durata, Ticket ticket, DataOutputStream out) {
		int durataInt=(int)durata;
		int ScattoTimer_secondi=durataInt*3600;
		ScattoTimer_secondi = ScattoTimer_secondi-(600*1000);
		//int ScattoTimer_millisecondi = ScattoTimer_secondi*1000;
		Timer t= new Timer();
		ticket.setTimer(t);
		ticket.getTimer().schedule(new TimerTask() {
			@Override
			public void run() {
				try {
					out.writeBoolean(true);
					out.flush();
					return;
				} catch (IOException e) {
					e.printStackTrace();
				} 
			}
		},20000);
		return;
	}

	private  String OttieniDataScadenzaTicket(Auto auto, AreaParcheggio area, double Durata) {
		String OraScadenza="";
		boolean OrarioMattina=false;
		Date date = new Date(); 
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String DataString=formatter.format(date).toString();
		char Data1=DataString.charAt(8);
		char Data2=DataString.charAt(9);
		String data=new StringBuilder().append(Data1).append(Data2).toString();
		int  DataInt=Integer.parseInt(data);
		char Cifra1= DataString.charAt(11);
		char Cifra2=DataString.charAt(12);
		String orario=new StringBuilder().append(Cifra1).append(Cifra2).toString();
		int  OrarioInt=Integer.parseInt(orario);
		Durata=(int)Durata;
		int OraScadenzaTicket=(int)Durata+OrarioInt;
		OraScadenza=Integer.toString(OraScadenzaTicket);
		if(OraScadenzaTicket<10)
			OrarioMattina=true;
		if(OraScadenzaTicket>=24) {
			DataInt=DataInt+1;
			OraScadenzaTicket=OraScadenzaTicket-24;
			if(OraScadenzaTicket<10)
				OrarioMattina=true;
		}
		if(OrarioMattina) {
			OraScadenza=Integer.toString(OraScadenzaTicket);
			OraScadenza="0"+OraScadenza;
		}
		Cifra1=OraScadenza.charAt(0);
		Cifra2=OraScadenza.charAt(1);
		String DataScadenza=Integer.toString(DataInt);
		Data1=DataScadenza.charAt(0);
		Data2=DataScadenza.charAt(1);
		StringBuilder Scadenza=new StringBuilder();
		Scadenza.append(DataString);
		Scadenza.setCharAt(8, Data1);
		Scadenza.setCharAt(9, Data2);
		Scadenza.setCharAt(11, Cifra1);
		Scadenza.setCharAt(12, Cifra2);
		String ScadenzaTicket=Scadenza.toString();
		System.out.println(ScadenzaTicket);
		return ScadenzaTicket;
	}

	private void DeterminaNuovaDataScadenza(Ticket t,double durata) {
		String OraScadenza="";
		boolean OrarioMattina=false;
		String DataString=t.getScadenzaTicket();
		char Data1=DataString.charAt(8);
		char Data2=DataString.charAt(9);
		String data=new StringBuilder().append(Data1).append(Data2).toString();
		int  DataInt=Integer.parseInt(data);
		char Cifra1= DataString.charAt(11);
		char Cifra2=DataString.charAt(12);
		String orario=new StringBuilder().append(Cifra1).append(Cifra2).toString();
		int  OrarioInt=Integer.parseInt(orario);
		durata=(int)durata;
		int OraScadenzaTicket=(int)durata+OrarioInt;
		OraScadenza=Integer.toString(OraScadenzaTicket);
		if(OraScadenzaTicket<10)
			OrarioMattina=true;
		if(OraScadenzaTicket>=24) {
			DataInt=DataInt+1;
			OraScadenzaTicket=OraScadenzaTicket-24;
			if(OraScadenzaTicket<10)
				OrarioMattina=true;
		}
		if(OrarioMattina) {
			OraScadenza=Integer.toString(OraScadenzaTicket);
			OraScadenza="0"+OraScadenza;
		}
		Cifra1=OraScadenza.charAt(0);
		Cifra2=OraScadenza.charAt(1);
		String DataScadenza=Integer.toString(DataInt);
		Data1=DataScadenza.charAt(0);
		Data2=DataScadenza.charAt(1);
		StringBuilder Scadenza=new StringBuilder();
		Scadenza.append(DataString);
		Scadenza.setCharAt(8, Data1);
		Scadenza.setCharAt(9, Data2);
		Scadenza.setCharAt(11, Cifra1);
		Scadenza.setCharAt(12, Cifra2);
		String ScadenzaTicket=Scadenza.toString();
		System.out.println(ScadenzaTicket);
		t.setDurata(durata);
		t.setIDTicket(t.getIDTicket());
		t.setScadenzaTicket(ScadenzaTicket);
	}

	public int OttieniDisponibilita(int codiceArea) {
		int i=0;
		int numeroPostiDisponibili=0;
		AreaParcheggio area = new AreaParcheggio(codiceArea);
		for( i=0;i<listaTicket.size();i++) {
			if(listaTicket.get(i).getAreaParcheggio().getCodiceArea()==codiceArea) {
				numeroPostiDisponibili=numeroPostiDisponibili+1;
			}
		}
		return (area.getDisponibilita()-numeroPostiDisponibili);
	}

	@Override
	public boolean AggiornaDisponibilita(int codiceArea, String tipo) {
		AreaParcheggio area = new AreaParcheggio(codiceArea);
		int disponibilita = area.getDisponibilita();
		if(tipo.equals("acquista")) {
			disponibilita = disponibilita - 1;
		}else if(tipo.equals("elimina")) {
			disponibilita = disponibilita + 1;
		}else {
			return false;
		}
		area.AggiornaDisponibilitaInDB(disponibilita);
		return true;
	}
}



