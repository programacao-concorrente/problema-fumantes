package br.ufc.great.pc.threads.semaforos.cigarros.smokers;

import java.util.concurrent.Semaphore;

public abstract class Smoker {
	protected Semaphore tobaccoGlobal;
	protected Semaphore paperGlobal;
	protected Semaphore matchGlobal;
	protected Semaphore agentSemaphore;
	
	public Smoker(Semaphore tobacco, Semaphore paper, Semaphore match,Semaphore agentSemaphore) {
		super();
		this.tobaccoGlobal = tobacco;
		this.paperGlobal = paper;
		this.matchGlobal = match;
		this.agentSemaphore=agentSemaphore;
	}
	public abstract void makeCigarette();
}
