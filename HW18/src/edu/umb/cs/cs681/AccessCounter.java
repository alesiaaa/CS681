package edu.umb.cs.cs681;

import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;
import java.nio.file.Path;

public class AccessCounter {
	
	private HashMap<Path, Integer> counter;
	private ReentrantLock lock;
	
	
	public AccessCounter(ReentrantLock lock){
		counter = new HashMap<>();
		this.lock = lock;
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
			
			System.out.println(Thread.currentThread().getName() + 
					": "+ path+", "+ count);

			
			return count;
			
		} finally {
			lock.unlock();
		}
	}

}
