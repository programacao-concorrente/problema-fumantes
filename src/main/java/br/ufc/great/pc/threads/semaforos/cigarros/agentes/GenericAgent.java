package br.ufc.great.pc.threads.semaforos.cigarros.agentes;

import java.util.Random;
import java.util.concurrent.Semaphore;

import br.ufc.great.pc.threads.semaforos.cigarros.Main;

public class GenericAgent extends Agent implements Runnable {
	public GenericAgent(Semaphore agentSemaphore, Semaphore tobacco, Semaphore paper, Semaphore match) {
		super(agentSemaphore, tobacco, paper, match);
	}

	@Override
	public void run() {
		while (Main.control) {
			try {				
				agentSemaphore.acquire();
				mutex2.acquire();

				Random randomico = new Random();
				int item = randomico.nextInt(4);

				if (item == 1) {
					//AgenteA 
					try {	
						if (Main.control){
							System.out.println("Agente A - Tobaco & Paper");
							tobacco.release();
							paper.release();
						}
						Thread.sleep(1500);
					} catch (InterruptedException e) {
						System.out.println(e.getMessage());
					}					
				}else if (item == 2) {
					//AgenteB
					try {
						if (Main.control){
							System.out.println("Agente B - Match & Paper");
							match.release();
							paper.release();
						}
						Thread.sleep(1500);
					} catch (InterruptedException e) {
						System.out.println(e.getMessage());
					}									
				}else {
					//AgenteC
					try {
						if (Main.control){
							System.out.println("Agente C - Tobaco & Match");
							tobacco.release();
							match.release();
						}
						Thread.sleep(1500);
					} catch (InterruptedException e) {
						System.out.println(e.getMessage());
					}					
				}

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}