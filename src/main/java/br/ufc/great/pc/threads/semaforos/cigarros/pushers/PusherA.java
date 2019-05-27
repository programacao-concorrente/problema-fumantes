package br.ufc.great.pc.threads.semaforos.cigarros.pushers;

import java.util.concurrent.Semaphore;

public class PusherA extends Pusher implements Runnable{
	public PusherA(boolean isMatch, boolean isTobacco, boolean isPaper,
			Semaphore tobacco, Semaphore paper, Semaphore match,
			Semaphore tobaccoSem, Semaphore paperSem, Semaphore matchSem,
			Semaphore mutex) {
		super(isMatch, isTobacco, isPaper, tobacco, paper, match, tobaccoSem, paperSem, matchSem, mutex);
	}

	@Override
	public void scheduleSmoker() {
		try {
			tobacco.acquire();
			mutex.acquire();
			
			if(isPaper){
				Pusher.isPaper=false;
				matchSem.release();
			}
			else if(isMatch){
				Pusher.isMatch=false;
				paperSem.release();
			}
			else {
				Pusher.isTobacco=true;
			}
			mutex.release();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while(true) {
			scheduleSmoker();	
		}

	}

}
