package edu.umb.cs.cs681;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 	HW11: Use ReentrantLock
	Use try-finally blocks.
	Call unlock() in a finally block. 
	Use balking: Do not surround the “for” loop with lock() and unlock().
 */

public class CancellablePrimeNumberGenerator extends PrimeNumberGenerator{
	private boolean done = false;
	ReentrantLock lock = null;

	public CancellablePrimeNumberGenerator(long from, long to) {
		super(from, to);
		lock = new ReentrantLock();
	}
		
	public void setDone(){
		lock.lock();
		
		try{
			done = true;
		} finally {
			lock.unlock();
		}
	}

	public void run(){
		// Stop generating prime numbers if done==true
		for (long n = from; n <= to; n++) {
			
			try {
				lock.lock();
				
				if( isPrime(n) ){ 
					 
					this.primes.add(n); 
					
					System.out.println(Thread.currentThread().getName()+ ", added prime value: " + n);
				} 
				
				if(done==true){
					
					System.out.println("Stopped generating prime numbers.");
					this.primes.clear();
					break; // balking
				}
				
			} finally {
				lock.unlock();
			}
			
		}
	}
}
