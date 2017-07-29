package edu.umb.cs.cs681;

import java.util.concurrent.locks.ReentrantLock;

public class Test {
	
	private static ReentrantLock lock = new ReentrantLock();
	
	public static void main(String[] args) {
		
		Thread thread1, thread2;
		CancellablePrimeNumberGenerator cg = new CancellablePrimeNumberGenerator(1L, 100L);
			
		thread1 = new Thread(cg, "Thread #1");
		thread2 = new Thread(cg, "Thread #2");
		
		thread1.start();
		thread2.start();

		
		lock.lock();
		try {
			thread1.interrupt();
			thread2.interrupt();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
		
		cg.setDone();
		
		try {
			thread1.join();
			thread2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
		System.out.println(cg.getPrimes().size() + " prime numbers were found.");

	    
	}

}
