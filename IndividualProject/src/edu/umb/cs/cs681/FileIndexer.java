package edu.umb.cs.cs681;

import java.nio.file.Paths;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

class FileIndexer implements Runnable{
	
	ElementQueue queue;
	private ConcurrentHashMap<Integer, FSElement> index;
	private ReentrantLock lock = new ReentrantLock();
	private boolean done = false;
	private FileCache cache;
	
	public FileIndexer(ElementQueue queue){
		this.queue = queue;
		this.index = new ConcurrentHashMap<>();
		
	}
	
	public FileIndexer(ElementQueue queue, FileCache cache){
		this.queue = queue;
		this.index = new ConcurrentHashMap<>();
		this.cache = cache;
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
				//Index the element
				indexElement( this.queue.get() );
				
			}
				
		}
		
		System.out.println(Thread.currentThread().getName() + " Indexing complete!");
		
		System.out.println("\n"+ Thread.currentThread().getName() + 
				" Final index: " + index.toString()+"\n");
		
		// get final cache
		((FIFOFileCache) this.cache).getCacheContents();
	}
	
	private void indexElement(FSElement e){
		// Index a given element.
		this.lock.lock();
		try {
			index.put(index.size(), e);
			System.out.println(Thread.currentThread().getName() +" Indexing: " + e.getName());
			
			
			// Add file to cache
			if (e.isFile()){
				cache.fetch(Paths.get(e.getName()));
			}
			
		} finally {
			this.lock.unlock();
		}
	}
	
	
}
