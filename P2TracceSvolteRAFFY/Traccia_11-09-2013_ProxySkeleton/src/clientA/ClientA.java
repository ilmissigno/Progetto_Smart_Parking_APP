package clientA;

import java.util.Random;

public class ClientA {

	public static void main(String[] args) {
		Thread[] worker = new Thread[5];
		String[] lista_art = new String[2];
		lista_art[0]="laptop";
		lista_art[1]="smartphone";
		ProxyA proxy = new ProxyA();
		for(int i=0;i<worker.length;i++){
			worker[i]=new Thread(new Runnable(){
				public void run(){
					for(int k=0;k<3;k++){
						int attesa = new Random().nextInt(3)+2;
						try{
							Thread.sleep(attesa*1000);
						}catch(InterruptedException e){
							e.printStackTrace();
						}
						attesa=new Random().nextInt(2);
						String articolo = lista_art[attesa];
						attesa=new Random().nextInt(100)+1;
						proxy.deposita(articolo,attesa);
						System.out.println("\nClientA : Chiamato il ProxyA");
					}
				}
			});
			worker[i].start();
		}
		for(int j=0;j<worker.length;j++){
			try{
				worker[j].join();
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}

}
