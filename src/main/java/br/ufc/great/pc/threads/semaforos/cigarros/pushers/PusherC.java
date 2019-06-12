package br.ufc.great.pc.threads.semaforos.cigarros.pushers;

import java.util.concurrent.Semaphore;

/**
 * Processo PusherC coloca match na mesa, 
 * se o tobacco já está na mesa acorda o Smoker with paper, se não o paper jestá na mesa acorda o Smoker with Tobacco
 * @author armandosoaressousa
 *
 */
public class PusherC extends Pusher implements Runnable {

	public PusherC(boolean isMatch, boolean isTobacco, boolean isPaper,
			Semaphore tobacco, Semaphore paper, Semaphore match,
			Semaphore tobaccoGlobal, Semaphore paperGlobal, Semaphore matchGlobal,
			Semaphore mutex) {
		super(isMatch, isTobacco, isPaper, tobacco, paper, match, tobaccoGlobal,paperGlobal, matchGlobal, mutex);
	}

	@Override
	public void scheduleSmoker() {
		try {
			match.acquire();
			mutex.acquire();
			if (isPaper) {
				Pusher.isPaper = false;
				tobaccoGlobal.release(); //sinalizar o Smoker with tobacco
			} else if (isTobacco) {
				Pusher.isTobacco = false;
				paperGlobal.release(); //sinalizar o Smoker with paper
			} else {
				Pusher.isMatch = true;
			}
			mutex.release();
		} catch (InterruptedException e) {
		}
	}

	@Override
	public void run() {
		while(true) {
			scheduleSmoker();			
		}

	}

}
