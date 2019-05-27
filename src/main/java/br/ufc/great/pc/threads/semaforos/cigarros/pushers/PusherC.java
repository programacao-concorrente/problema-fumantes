package br.ufc.great.pc.threads.semaforos.cigarros.pushers;

import java.util.concurrent.Semaphore;

public class PusherC extends Pusher implements Runnable {

	public PusherC(boolean isMatch, boolean isTobacco, boolean isPaper,
			Semaphore tobacco, Semaphore paper, Semaphore match,
			Semaphore tobaccoSem, Semaphore paperSem, Semaphore matchSem,
			Semaphore mutex) {
		super(isMatch, isTobacco, isPaper, tobacco, paper, match, tobaccoSem,paperSem, matchSem, mutex);
	}

	@Override
	public void scheduleSmoker() {
		try {
			match.acquire();
			mutex.acquire();

			if (isPaper) {
				Pusher.isPaper = false;
				tobaccoSem.release();
			} else if (isTobacco) {
				Pusher.isTobacco = false;
				paperSem.release();
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
