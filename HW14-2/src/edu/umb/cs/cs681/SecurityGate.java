package edu.umb.cs.cs681;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;


public class SecurityGate{

	private static SecurityGate instance;
	private static ReentrantLock lock = new ReentrantLock();
	private AtomicInteger counter = new AtomicInteger(0);
	
	
	private SecurityGate (){}
	
	public static SecurityGate getInstance() {
		
		lock.lock();
		
		try {
		
			if (instance==null){
				instance = new SecurityGate();
			}
			
			return instance;
			
		} finally {
			lock.unlock();
		}
		
		
	}
	
	public void enter(){
		print("gate", "trying to enter");
		counter.updateAndGet(n -> n + 1);
		print("gate", "inside         ");
	}
	
	public void exit(){
		print("gate", "trying to exit ");
		counter.updateAndGet(n -> n - 1);
		print("gate", "outside        ");
	}
	
	public AtomicInteger getCount(){
		return this.counter;
	}
	
	
	 private void print(String tag, String p) {
	        System.out.println(Thread.currentThread().getName() + " -- " + tag + ": " + p + 
	        		" (ct. " + getCount() + ")");
	    }

	
}