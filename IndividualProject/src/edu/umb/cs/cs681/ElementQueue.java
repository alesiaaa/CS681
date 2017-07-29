package edu.umb.cs.cs681;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class ElementQueue {
	
	private ArrayList<FSElement> queue;
	private ReentrantLock lock;
	
	public ElementQueue (){
		this.queue = new ArrayList <> ();
		this.lock = new ReentrantLock();
	}
	
	protected ReentrantLock getLock(){
		return this.lock;
	}
	
	public void put(FSElement element){
		this.lock.lock();
		try {
			this.queue.add(element);
		} finally {
			this.lock.unlock();
		}
		
	}
	
	public FSElement get() {
		this.lock.lock();
		try {
			if (this.queue.size() > 0){
				
				FSElement element = this.queue.remove(0);
				return element;
				
			} else {
				return null;
			}
		} finally {
			this.lock.unlock();
		}
	}
	
	
	public boolean hasContents(){
		return !this.queue.isEmpty();
	}
	
	public String toString (){
		return this.queue.toString();
	}
	
}
