package br.ufc.great.pc.threads.semaforos.cigarros.agentes;

import java.util.Random;
import java.util.concurrent.Semaphore;

import br.ufc.great.pc.threads.semaforos.cigarros.Main;

public class GenericAgent extends Agent implements Runnable {
	public GenericAgent(Semaphore agentSemaphore, Semaphore tobacco, Semaphore paper, Semaphore match) {
		super(agentSemaphore, tobacco, paper, match);
	}

	private int geraRandomico(int min, int max) {
		Random randomico = new Random();
		int item = randomico.nextInt((max-min)+1) + min;
		return item;
	}

	@Override
	public void run() {
		while (Main.control) {
			try {				
				agentSemaphore.acquire();
				mutex2.acquire();

				switch (geraRandomico(1, 3)) {
				case 1:
					//AgenteA 
					try {	
						System.out.println("Agente A - Tobaco & Paper");
						tobacco.release();
						paper.release();
						Thread.sleep(1500);
					} catch (InterruptedException e) {
						System.out.println(e.getMessage());
					}										
					break;
				case 2: 
					//AgenteB
					try {
						System.out.println("Agente B - Match & Paper");
						match.release();
						paper.release();
						Thread.sleep(1500);
					} catch (InterruptedException e) {
						System.out.println(e.getMessage());
					}									
					break;
				case 3: 
					//AgenteC
					try {
						System.out.println("Agente C - Tobaco & Match");
						tobacco.release();
						match.release();
						Thread.sleep(1500);
					} catch (InterruptedException e) {
						System.out.println(e.getMessage());
					}					
					break;
				}

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}