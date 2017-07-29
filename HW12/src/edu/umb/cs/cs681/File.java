package edu.umb.cs.cs681;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 *  Define a lock in File. Use the lock in change() and save()
	Use try-finally blocks
	Call unlock in a finally block
 */

public class File {
	
	private boolean changed = false;
	private ReentrantLock lock = null;
	private String name;
	
	public File (String name){
		this.lock = new ReentrantLock();
		this.name = name;
	}
	
	@Override
	public String toString(){
		return name;
		
	}	
	
	public void change (){
		this.lock.lock();
		try {
			this.changed = true; 
		} finally {
			this.lock.unlock();
		}
		
	}
	
	public void save (){
		
		this.lock.lock();
		try {
			if (this.changed == true){
				System.out.println(Thread.currentThread().getName() + ": saved "+ name);
				this.changed = false;

			} else {
				return; // balking
			}
			
		} finally {
			this.lock.unlock();
		}
		
	}
	
	
}
