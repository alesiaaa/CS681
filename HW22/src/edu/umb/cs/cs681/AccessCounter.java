package edu.umb.cs.cs681;

import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;
import java.nio.file.Path;

public class AccessCounter {
	private HashMap<Path, Integer> counter;
	private HashMap<Path, Long> time = new HashMap<>();
	private ReentrantLock lock;
	
	
	public AccessCounter(ReentrantLock lock){
		this.counter = new HashMap<>();
		this.lock = lock;
		
	}
	
	public ReentrantLock getLock(){
		return this.lock;
	}
	
	public HashMap<Path, Integer> getCounter(){
		return this.counter;
	}
	
	public void increment(Path path){
		lock.lock();
		try {
			
			if (counter.containsKey(path)){
				//get last count and update
				counter.put(path, counter.get(path) + 1);
				
				
			} else {
				
				//add new path
				counter.put(path, 1);
			}	
			
			//Also update time of access
			time.put(path, System.currentTimeMillis());
			
		} finally {
			lock.unlock();
		}
		
	}

	public int getCount(Path path){
		lock.lock();
		try {
			int count = -1;
			
			if (counter.containsKey(path)){
				count = counter.get(path);
			
			} else {
				count = 0;
			}
			
			return count;
			
		} finally {
			lock.unlock();
		}
	}
	
	public long getTime(Path path){
		lock.lock();
		try {
			long timeOfAccess = -1;
			
			if (time.containsKey(path)){
				timeOfAccess = time.get(path);
			
			} else {
				
				timeOfAccess = 0;
			}
			
			return timeOfAccess;
			
		} finally {
			lock.unlock();
		}
	}

}
