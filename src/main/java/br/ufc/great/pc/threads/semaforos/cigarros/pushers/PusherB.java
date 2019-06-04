package br.ufc.great.pc.threads.semaforos.cigarros.pushers;

import java.util.concurrent.Semaphore;

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
				matchGlobal.release();
			} else if (isMatch) {
				Pusher.isMatch = false;
				tobaccoGlobal.release();
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
