package edu.umb.cs.cs681;

import java.util.concurrent.locks.ReentrantLock;

public class Editor implements Runnable{
	
	private File aFile;
	private boolean done = false;
	private ReentrantLock lock = null;
	
	public Editor (File file){
		this.lock = new ReentrantLock();
		this.aFile = file;
		this.done = false;
	}


	@Override
	public void run() {
		while(this.done !=true){
				
			this.lock.lock();
			try {
				
				if(this.done==true){
					System.out.println(Thread.currentThread().getName() + ": Editor has been terminated");
					break; // balking
				}
				
				this.aFile.change();
				this.aFile.save();
				
				Thread.sleep(1000);
		
			} catch (Exception e) {
				
				e.printStackTrace();
				
			} finally {
				this.lock.unlock();
			}
					
		}
	}
	
	public void setDone(){
		this.lock.lock();
		try{
			this.done = true;
		} finally {
			this.lock.unlock();
		}
	}

}
