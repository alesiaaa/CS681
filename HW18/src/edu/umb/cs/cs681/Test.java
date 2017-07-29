package edu.umb.cs.cs681;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class Test {
	
	public static void main(String[] args) {
		
		Thread thread;
		ArrayList <Thread> threads = new ArrayList<>();
	
		ReentrantLock lock = new ReentrantLock();
		
		Path dir = Paths.get("src/edu/umb/cs/cs681/file_root");
		
		AccessCounter ac =  new AccessCounter(lock);
		
		RequestHandler rh = new RequestHandler(dir, ac);
		
		for (int x=0; x < 10; x++){
			
			thread = new Thread(rh);
			thread.start();
			
			threads.add(thread);
		}
		
		
		lock.lock();
		try {
			for (Thread t: threads){
				t.interrupt();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
		
	
		rh.setDone();
		
		
		try {
			for (Thread t: threads){
				t.join();
			}

		} catch (InterruptedException e) {
			e.printStackTrace();}
		
	}

}
