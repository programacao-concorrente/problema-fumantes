package br.ufc.great.pc.threads.semaforos.cigarros.pushers;

import java.util.concurrent.Semaphore;

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
				tobaccoGlobal.release();
			} else if (isTobacco) {
				Pusher.isTobacco = false;
				paperGlobal.release();
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
