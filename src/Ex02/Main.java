package Ex02;

import java.util.concurrent.Semaphore;

public class Main {

	public static void main(String[] args) {		
		Semaphore pista[] = new Semaphore[2];
		
		for(int i = 0;i < 2;i++) pista[i] = new Semaphore(1);
		
		for(int i = 0;i < 12;i++) {
			Aviao av = new Aviao(i+1, pista);
			
			av.start();
		}
	}

}
