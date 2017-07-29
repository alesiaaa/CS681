package edu.umb.cs.cs681;

import java.util.concurrent.locks.ReentrantLock;

public class Singleton {
	private static Singleton instance = null;
	private static ReentrantLock lock = new ReentrantLock();
	private static int counter = 0;
	
	private Singleton(){
		if (this.getClass() == Singleton.class) {
			lock.lock();
			try {
				counter++;
			} finally {
				lock.unlock();
			}
		}
		
	};
	
	
// Factory method to create or return a singleton instance
	public static Singleton getInstance(){
		
		lock.lock();
		
		try {
			
			if(instance==null){
				instance = new Singleton();
				
				System.out.println(Thread.currentThread().getName() + 
						" created new instance of Singleton.");
			}
			
			System.out.println(Thread.currentThread().getName() + " is running.");
			
		} finally {
			lock.unlock();
		}

	
		
		return instance;
	}
	
	public static String getInstances(){
		return "Total number of instances: "+ counter;
	}
	
}