package edu.umb.cs.cs681;

import java.util.concurrent.locks.ReentrantLock;

public class InterruptiblePrimeNumberGenerator extends PrimeNumberGenerator{
	
	private static ReentrantLock lock = new ReentrantLock();
	
	public InterruptiblePrimeNumberGenerator(long from, long to) {
		super(from, to);
	}
			
	public void run(){
		
		for (long n = from; n <= to; n++) {
			
			lock.lock();
			try {
				if( isPrime(n) ){ 
					this.primes.add(n); 
					
				}
				
				// Detect if another thread has interrupted. Balk if yes.
				if(Thread.interrupted()){
					
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
