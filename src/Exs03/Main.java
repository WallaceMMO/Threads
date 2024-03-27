package Exs03;

import java.util.Arrays;
import java.util.concurrent.Semaphore;

public class Main {

	public static void main(String[] args) {
		Atleta[] ats = new Atleta[25];
		Semaphore armas = new Semaphore(5);
		Semaphore chegada = new Semaphore(1);
		
		for(int i = 0;i < 25;i++) {
			ats[i] = new Atleta(i+1, armas, chegada); 
			
			ats[i].start();
		}
				
		while(Atleta.indexRank < 25) System.out.print("");
		
		Arrays.sort(ats);
		
		System.out.println(".Id..Ordem.término.da.prova...Posição.Tiro.ao.alvo....Pontuação.Geral");
		for(int i = 0;i < 25;i++) {
			System.out.printf("%3s %-23s%23s%19s%n", ats[i].id, ats[i].rank + "° lugar", ats[i].tiroAlvo, ats[i].pont);
		}
	}

}
