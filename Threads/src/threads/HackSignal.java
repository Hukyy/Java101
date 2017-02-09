package threads;

import java.util.concurrent.locks.Lock;

public class HackSignal {
	private Lock lock;
	private boolean wasSignalled = false;
	
	public void doWait(){
		try{
			lock.lock();
			while(!wasSignalled){
				lock.wait();
			}
		} catch (InterruptedException e){
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	
	public void  doNotify(){
		try{
			lock.lock();
			wasSignalled = true;
			lock.notify();
		} finally {
			lock.unlock();
		}
	}
}
