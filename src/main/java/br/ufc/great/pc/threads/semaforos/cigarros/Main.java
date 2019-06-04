package br.ufc.great.pc.threads.semaforos.cigarros;

import java.util.concurrent.Semaphore;

import br.ufc.great.pc.threads.semaforos.cigarros.agentes.GenericAgent;
import br.ufc.great.pc.threads.semaforos.cigarros.pushers.PusherA;
import br.ufc.great.pc.threads.semaforos.cigarros.pushers.PusherB;
import br.ufc.great.pc.threads.semaforos.cigarros.pushers.PusherC;
import br.ufc.great.pc.threads.semaforos.cigarros.smokers.SmokerWithMatch;
import br.ufc.great.pc.threads.semaforos.cigarros.smokers.SmokerWithPaper;
import br.ufc.great.pc.threads.semaforos.cigarros.smokers.SmokerWithTobacco;

public class Main {

	/**
	 * @author armandosoaressousa
	 * @param args
	 * @throws InterruptedException 
	 */
	public static boolean control=true;
	
	public static void main(String[] args) throws InterruptedException {
		Semaphore agentSemaphore = new Semaphore(1);
		Semaphore mutex = new Semaphore(1);
		Semaphore tobacco = new Semaphore(0);
		Semaphore paper = new Semaphore(0);
		Semaphore match = new Semaphore(0);
		Semaphore tobacoGlobal = new Semaphore(0); //Permite compartilhar o semaforo tobaco entre os pushers e os smokers
		Semaphore paperGlobal = new Semaphore(0); //Permite compartilhar o semaforo paper entre os pushers e os smokers
		Semaphore matchGlobal = new Semaphore(0); //Permite compartilhar o semaforo match entre os pushers e os smokers
	
		Boolean isMatch = new Boolean(false);
		Boolean isTobacco = new Boolean(false);
		Boolean isPaper = new Boolean(false);

		Thread agente = new Thread(new GenericAgent(agentSemaphore, tobacco, paper, match), "Agente");
		
		Thread pusherA = new Thread(new PusherA(isMatch, isTobacco, isPaper, tobacco, paper, match, tobacoGlobal, paperGlobal, matchGlobal, mutex), "PucherA");
		Thread pusherB = new Thread(new PusherB(isMatch, isTobacco, isPaper, tobacco, paper, match, tobacoGlobal, paperGlobal, matchGlobal, mutex), "PucherB");
		Thread pusherC = new Thread(new PusherC(isMatch, isTobacco, isPaper, tobacco, paper, match, tobacoGlobal, paperGlobal, matchGlobal, mutex), "PucherC");
		
		Thread smokerA = new Thread(new SmokerWithMatch(tobacoGlobal, paperGlobal, matchGlobal, agentSemaphore),"SmokerWithMatch");
		Thread smokerB = new Thread(new SmokerWithPaper(tobacoGlobal, paperGlobal, matchGlobal, agentSemaphore),"SmokerWithPaper");
		Thread smokerC = new Thread(new SmokerWithTobacco(tobacoGlobal, paperGlobal, matchGlobal, agentSemaphore),"SmokerWithTobacco");
		
		agente.start();
		
		pusherA.start();
		pusherB.start();
		pusherC.start();
		
		smokerA.start();
		smokerB.start();
		smokerC.start();

	}
}