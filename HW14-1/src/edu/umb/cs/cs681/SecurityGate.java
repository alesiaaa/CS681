package edu.umb.cs.cs681;

import java.util.concurrent.locks.ReentrantLock;


public class SecurityGate{
	
	
	private static SecurityGate instance;
	private static ReentrantLock lock = new ReentrantLock();
	private int counter = 0;
	
	
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
		print("gate", "try lock");
		lock.lock();
		print("gate", "got lock");
		try {
			counter++;
		} finally {
			lock.unlock();
			print("gate", "unlocked");
		}
	}
	
	public void exit(){
		print("gate", "try lock");
		lock.lock();
		print("gate", "got lock");
		try {
			counter--;
		} finally {
			lock.unlock();
			print("gate", "unlocked");
		}
	}
	
	public int getCount(){
		return this.counter;
	}
	
	
	 private void print(String tag, String p) {
	        System.out.println(Thread.currentThread().getName() + " -- " + tag + ": " + p + 
	        		" (ct. " + getCount() + ")");
	    }

	
}