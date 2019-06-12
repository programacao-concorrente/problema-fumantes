package br.ufc.great.pc.threads.semaforos.cigarros.pushers;

import java.util.concurrent.Semaphore;

/**
 * Processo PucherB coloca paper na mesa, 
 * se o tobacco já está na mesa acorda o Smoker with match, se não o match já está na mesa acorda o Smoker with Tobacco
 * @author armandosoaressousa
 *
 */
public class PusherB extends Pusher implements Runnable {

	public PusherB(boolean isMatch, boolean isTobacco, boolean isPaper,
			Semaphore tobacco, Semaphore paper, Semaphore match,
			Semaphore tobaccoGlobal, Semaphore paperGlobal, Semaphore matchGlobal,
			Semaphore mutex) {
		super(isMatch, isTobacco, isPaper, tobacco, paper, match, tobaccoGlobal, paperGlobal, matchGlobal, mutex);
	}

	@Override
	public void scheduleSmoker() {
		try {
			paper.acquire();
			mutex.acquire();			
			if (isTobacco) {
				Pusher.isTobacco = false;
				matchGlobal.release(); //sinaliza o Smoker with match
			} else if (isMatch) {
				Pusher.isMatch = false;
				tobaccoGlobal.release(); //sinalizar o Smoker with tobacco
			} else {
				Pusher.isPaper = true;
			}
			mutex.release();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void run() {
		while (true) {
			scheduleSmoker();
		}
	}
}
