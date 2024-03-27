package Ex02;

import java.util.concurrent.Semaphore;

public class Aviao extends Thread{
	Semaphore pista[];
	
	int norte;
	int id;
	static boolean ocupados[];
	
	Aviao(int id, Semaphore pista[]) {
		this.pista = pista;
		this.id = id;
	}
	
	@Override
	public void run() {
		try {
			norte = (int)(Math.random()*2);
			
			pista[norte].acquire();
			System.out.println("O avião " + id + " entrou na pista " + (norte == 0 ? "norte" : "sul"));
			
			sleep((int)(Math.random()*401)+300);
			System.out.println("O avião " + id + " manobrou na pista " + (norte == 0 ? "norte" : "sul"));

			sleep((int)(Math.random()*501)+500);
			System.out.println("O avião " + id + " taxiou na pista " + (norte == 0 ? "norte" : "sul"));
			
			sleep((int)(Math.random()*201)+600);
			System.out.println("O avião " + id + " decolou na pista " + (norte == 0 ? "norte" : "sul"));
			
			sleep((int)(Math.random()*501)+300);
			System.out.println("O avião " + id + " afastou da pista " + (norte == 0 ? "norte" : "sul"));
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			pista[norte].release();
		}
	}
}
