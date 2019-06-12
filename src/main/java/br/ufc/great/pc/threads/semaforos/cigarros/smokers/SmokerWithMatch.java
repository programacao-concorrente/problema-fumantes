package br.ufc.great.pc.threads.semaforos.cigarros.smokers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Semaphore;

import br.ufc.great.pc.threads.semaforos.cigarros.Main;
import br.ufc.great.pc.threads.semaforos.cigarros.agentes.Agent;

public class SmokerWithMatch extends Smoker implements Runnable {
	public SmokerWithMatch(Semaphore tobaccoGlobal, Semaphore paperGlobal, Semaphore matchGlobal, Semaphore agentSemaphore) {
		super(tobaccoGlobal, paperGlobal, matchGlobal, agentSemaphore);
	}

	@Override
	public void makeCigarette() {
		if (Main.control) {
			try {
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				Date date = new Date();
				System.out.println("Smoker with match faz o cigarro."+dateFormat.format(date));
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("SmokerWithMatch fumando...");
		}
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void run() {
		while (Main.control) {
			try {
				matchGlobal.acquire();
				makeCigarette();
				System.out.println("Acorda Agente...\n");
				Agent.mutex2.release();
				agentSemaphore.release();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
