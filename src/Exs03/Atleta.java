package Exs03;

import java.util.concurrent.Semaphore;

public class Atleta extends Thread implements Comparable<Atleta> {
	int percCorrida, tiroAlvo, percCicl, id;
	Semaphore armas;
	int pont;
	
	static int indexRank;
	int rank;
	
	Semaphore chegada;
	
	Atleta(int id, Semaphore armas, Semaphore chegada) {
		this.id = id;
		this.armas = armas;
		this.chegada = chegada;
	}

	@Override
	public int compareTo(Atleta o2) {
		return pont > o2.pont ? -1 : 1;
	}
	
	@Override
	public void run() {
		try {
			
			while(percCorrida < 3000) {
				percCorrida += (Math.random()*6)+20;
				System.out.println("O atleta " + id + " percorreu " + percCorrida + " metros na corrida");
				sleep(30);
			}
			
			armas.acquire();
			
			for(int i = 0;i < 3;i++) {
				sleep((int)(Math.random()*2501)+500);
				int tiro = (int)(Math.random()*11);
				
				System.out.println("O tiro " + (i+1) + " do atleta " + id + " valeu " + tiro + " pontos");
				tiroAlvo += tiro;
			}
			
			armas.release();
			
			while(percCicl < 5000) {
				percCicl += (Math.random()*11)+30;
				System.out.println("O atleta " + id + " percorreu " + percCicl + " metros no ciclismo");
				sleep(40);
			}
			
			chegada.acquire();
			System.out.println("O atleta " + id + " terminou a prova em " + (indexRank+1) + " º lugar");
			
			rank = indexRank+1;
			pont += (25-indexRank++)*10 + tiroAlvo; 
			
			chegada.release();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			armas.release();
			chegada.release();			
		}		
	}	
}
