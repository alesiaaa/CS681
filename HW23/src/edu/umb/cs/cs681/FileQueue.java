package edu.umb.cs.cs681;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class FileQueue {
	
	private ArrayList<File> queue;
	private ReentrantLock lock;
	
	public FileQueue (){
		this.queue = new ArrayList <> ();
		this.lock = new ReentrantLock();
	}
	
	protected ReentrantLock getLock(){
		return this.lock;
	}
	
	public void put(File file){
		this.lock.lock();
		try {
			this.queue.add(file);
		} finally {
			this.lock.unlock();
		}
		
	}
	
	public File get() {
		this.lock.lock();
		try {
			if (this.queue.size() > 0){
				
				File file = this.queue.remove(0);
				return file;
				
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
