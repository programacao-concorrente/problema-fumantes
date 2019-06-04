package br.ufc.great.pc.threads.semaforos.cigarros.pushers;

import java.util.concurrent.Semaphore;

public class PusherA extends Pusher implements Runnable{
	public PusherA(boolean isMatch, boolean isTobacco, boolean isPaper,
			Semaphore tobacco, Semaphore paper, Semaphore match,
			Semaphore tobaccoGlobal, Semaphore paperGlobal, Semaphore matchGlobal,
			Semaphore mutex) {
		super(isMatch, isTobacco, isPaper, tobacco, paper, match, tobaccoGlobal, paperGlobal, matchGlobal, mutex);
	}

	@Override
	public void scheduleSmoker() {
		try {
			tobacco.acquire();
			mutex.acquire();			
			if(isPaper){
				Pusher.isPaper=false;
				matchGlobal.release();
			}
			else if(isMatch){
				Pusher.isMatch=false;
				paperGlobal.release();
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
