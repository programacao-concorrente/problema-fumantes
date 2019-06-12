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
		Semaphore mutex = new Semaphore(1); //Garante acesso atômico na região crítica do agent
		Semaphore tobacco = new Semaphore(0);
		Semaphore paper = new Semaphore(0);
		Semaphore match = new Semaphore(0);
		
		//Foram criados semaforos globais para evitar Deadlock entre os Smokers que dependem dos mesmos ingredientes
		Semaphore tobacoGlobal = new Semaphore(0); //Sinaliza o Smoker with tobacco
		Semaphore paperGlobal = new Semaphore(0); //Sinaliza o Smoker with paper
		Semaphore matchGlobal = new Semaphore(0); //Sinaliza o Smoker with match
	
		//As variaveis booleanas indicam se o ingrediente já está ou não na mesa
		Boolean isMatch = new Boolean(false); 
		Boolean isTobacco = new Boolean(false);
		Boolean isPaper = new Boolean(false);

		Thread agente = new Thread(new GenericAgent(agentSemaphore, tobacco, paper, match), "Agente");
		
		//Processo PucherA coloca tobacco na mesa, se paper já está na mesa acorda o Smoker with match, se não se o match já está na mesa acorda o Smoker with Paper
		Thread pusherA = new Thread(new PusherA(isMatch, isTobacco, isPaper, tobacco, null, null, null, paperGlobal, matchGlobal, mutex), "PusherA");
		
		//Processo PucherB coloca paper na mesa, se o tobacco já está na mesa acorda o Smoker with match, se não o match já está na mesa acorda o Smoker with Tobacco
		Thread pusherB = new Thread(new PusherB(isMatch, isTobacco, isPaper, null, paper, null, tobacoGlobal, null, matchGlobal, mutex), "PusherB");
		
		//Processo PusherC coloca match na mesa, se o tobacco já está na mesa acorda o Smoker with paper, se não o paper jestá na mesa acorda o Smoker with Tobacco
		Thread pusherC = new Thread(new PusherC(isMatch, isTobacco, isPaper, null, null, match, tobacoGlobal, paperGlobal, null, mutex), "PusherC");
		
		Thread smokerA = new Thread(new SmokerWithMatch(null, null, matchGlobal, agentSemaphore),"SmokerWithMatch");
		Thread smokerB = new Thread(new SmokerWithPaper(null, paperGlobal, null, agentSemaphore),"SmokerWithPaper");
		Thread smokerC = new Thread(new SmokerWithTobacco(tobacoGlobal, null, null, agentSemaphore),"SmokerWithTobacco");
		
		agente.start();
		
		pusherA.start();
		pusherB.start();
		pusherC.start();
		
		smokerA.start();
		smokerB.start();
		smokerC.start();

	}
}