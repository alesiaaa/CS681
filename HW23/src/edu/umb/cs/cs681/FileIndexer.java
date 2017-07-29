package edu.umb.cs.cs681;

import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;

class FileIndexer implements Runnable{
	
	private FileQueue queue;
	private HashMap<Integer, File> index;
	private ReentrantLock lock = new ReentrantLock();
	private boolean done = false;

	public FileIndexer(FileQueue queue){
		this.queue = queue;
		this.index = new HashMap<>();
		//this.lock = queue.getLock();
	}
	
	public void setDone (){
		this.lock.lock();
		try {
			this.done = true;
		}finally {
			this.lock.unlock();
		}
	}
	
	public void run(){
		while(true){
			if (!this.queue.hasContents()){
				try {
					
					Thread.sleep(3000);
					
					//attempt to see if there are more files to index 
					//after a few seconds, if not then break and finish
					
					this.lock.lock();
					try {
						if (!this.queue.hasContents()){
							System.out.println(Thread.currentThread().getName() + " No contents found in queue.");
							break;
						} else if (this.done==true){
							System.out.println(Thread.currentThread().getName() + " Terminated by main thread.");
							break;
						}
					} finally {
						this.lock.unlock();
					}
						
					
				} catch (InterruptedException e){
					
					System.out.println(Thread.currentThread().getName() + " Waiting for contents to index...");
				}
			
				
			} else {
				//Index the file
				indexFile( this.queue.get() );
				
			}
				
		}
		
		System.out.println(Thread.currentThread().getName() + " Indexing complete!");
		
		System.out.println("\n"+ Thread.currentThread().getName() + 
				" Final index: " + index.toString()+"\n");
	}
	
	private void indexFile(File file){
		// Index a given file.
		this.lock.lock();
		try {
			index.put(index.size(), file);
			System.out.println(Thread.currentThread().getName() +" Indexing: " + file.getName());
		} finally {
			this.lock.unlock();
		}
	}
}
