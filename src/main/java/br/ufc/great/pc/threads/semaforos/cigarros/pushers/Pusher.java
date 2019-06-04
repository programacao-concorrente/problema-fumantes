package br.ufc.great.pc.threads.semaforos.cigarros.pushers;

import java.util.concurrent.Semaphore;

public abstract class Pusher {
	protected static boolean isMatch;
	protected static boolean isTobacco;
	protected static boolean isPaper;
	protected Semaphore tobacco;
	protected Semaphore paper;
	protected Semaphore match;
	protected Semaphore tobaccoGlobal;
	protected Semaphore paperGlobal;
	protected Semaphore matchGlobal;
	protected Semaphore mutex;
	
	public Pusher(boolean isMatch, boolean isTobacco, boolean isPaper,
			Semaphore tobacco, Semaphore paper, Semaphore match,
			Semaphore tobaccoGlobal, Semaphore paperGlobal, Semaphore matchGlobal,
			Semaphore mutex) {
		super();
		
		this.tobacco = tobacco;
		this.paper = paper;
		this.match = match;
		this.tobaccoGlobal = tobaccoGlobal;
		this.paperGlobal = paperGlobal;
		this.matchGlobal = matchGlobal;
		this.mutex = mutex;
	}

	public abstract void scheduleSmoker();

}
