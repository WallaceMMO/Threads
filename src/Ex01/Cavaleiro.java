package Ex01;

import java.util.concurrent.Semaphore;

public class Cavaleiro extends Thread {
	Semaphore getTocha, getPedra, getPorta;
	static boolean monsters[], portas[];
	int vel=2, percorrido, id;
	
	static boolean tocha, pedra;
	
	Cavaleiro(int id, Semaphore getTocha, Semaphore getPedra, Semaphore getPorta) {
		this.id = id;		
		this.getPedra = getPedra;
		this.getTocha = getTocha;
		this.getPorta = getPorta;
	}
	
	@Override
	public void run() {
		
		while(percorrido <= 2000) {
			percorrido += (int)(Math.random()*3)+vel;
			
			if(percorrido >= 500 && !tocha) {
				try {
					getTocha.acquire();
					vel += 2;
					System.out.println("O Cavaleiro " + id + " pegou a tocha");	
					tocha = true;
				} catch (InterruptedException e) { 
					e.printStackTrace();
				}				
			}
			
			if(percorrido >= 1500 && !pedra) {
				try {
					getPedra.acquire();
					vel += 2;
					System.out.println("O Cavaleiro " + id + " pegou a pedra");
					pedra = true;
				} catch (InterruptedException e) { 
					e.printStackTrace();
				}				
			}		
			
			System.out.println("O Cavaleiro " + id + " percorreu " + percorrido + " kilometros");
			
			try {
				sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		int indexPorta = (int)(Math.random()*4);
		
		while(portas[indexPorta])
			indexPorta = (int)(Math.random()*4);
		
		try {
			getPorta.acquire();
			portas[indexPorta] = true;
			System.out.println("O Cavaleiro " + id + " entrou na porta " + (indexPorta+1) + " e " + (monsters[indexPorta] ? "saiu com segurança"  : "foi devorado"));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			getPorta.release();
		}
	}
}
