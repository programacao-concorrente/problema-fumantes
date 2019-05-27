package br.ufc.great.pc.threads.semaforos.cigarros.pushers;

import java.util.concurrent.Semaphore;

public class PusherB extends Pusher implements Runnable {

	public PusherB(boolean isMatch, boolean isTobacco, boolean isPaper,
			Semaphore tobacco, Semaphore paper, Semaphore match,
			Semaphore tobaccoSem, Semaphore paperSem, Semaphore matchSem,
			Semaphore mutex) {
		super(isMatch, isTobacco, isPaper, tobacco, paper, match, tobaccoSem, paperSem, matchSem, mutex);
	}

	@Override
	public void scheduleSmoker() {
		try {
			paper.acquire();
			mutex.acquire();
			
			if (isTobacco) {
				Pusher.isTobacco = false;
				matchSem.release();
			} else if (isMatch) {
				Pusher.isMatch = false;
				tobaccoSem.release();
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
