package edu.umb.cs.cs681;

import java.util.concurrent.locks.ReentrantLock;

public class CancellablePrimeNumberGenerator extends PrimeNumberGenerator{
	private volatile boolean done = false;
	
	private static ReentrantLock lock = new ReentrantLock();

	public CancellablePrimeNumberGenerator(long from, long to) {
		super(from, to);
	}
		
	public void setDone(){
		done = true;
		System.out.println(Thread.currentThread().getName() + ": Done.");
	}

	public void run(){
		for (long n = from; n <= to; n++) {
	
			lock.lock();
			try {
				
				// Detect if a thread is done==true. Balk if yes.
				if(done==true){				
					System.out.println(Thread.currentThread().getName() + ": Stopped generating prime numbers.");
					this.primes.clear();
					break; // balking
				}
				
			} finally {
				lock.unlock();
			}
			
			if( isPrime(n) ){ this.primes.add(n); }
			
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// do nothing
			}
		}
	}
}
