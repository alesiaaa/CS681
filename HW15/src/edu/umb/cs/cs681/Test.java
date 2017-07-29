package edu.umb.cs.cs681;

import java.util.concurrent.locks.ReentrantLock;

public class Test {
	
	private static ReentrantLock lock = new ReentrantLock();
	
	public static void main(String[] args) {
	
		Thread thread;
		
	    InterruptiblePrimeNumberGenerator interruptibleGen = new InterruptiblePrimeNumberGenerator(1L, 1000000L);
	    thread = new Thread(interruptibleGen, "Thread #1");
	    thread.start();
		
		lock.lock();
		
		try {
			thread.interrupt();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
		
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println(interruptibleGen.getPrimes().size() + " prime numbers were found."); 
	}

}
