package Ex01;

import java.util.concurrent.Semaphore;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Semaphore getTocha = new Semaphore(1);
		Semaphore getPedra = new Semaphore(1);
		Semaphore getPorta = new Semaphore(1);
		
		Cavaleiro.monsters = new boolean[4];
		Cavaleiro.monsters[(int)(Math.random()*4)] = true;
		Cavaleiro.portas = new boolean[4];
		
		for(int i = 0;i < 4;i++) {
			Cavaleiro c = new Cavaleiro(i+1, getTocha, getPedra, getPorta);
			
			c.start();
		}
	}

}
