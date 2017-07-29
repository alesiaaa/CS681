package edu.umb.cs.cs681;


import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;


public class RequestHandler implements Runnable{
	
	private Path path;
	private AccessCounter accessCounter;
	private ArrayList<String> files;
	private FileCache cache;
	private volatile boolean done = false;
	private ReentrantLock lock;
	
	public RequestHandler(Path path, AccessCounter accessCounter){
		this.accessCounter = accessCounter;
		this.path = path;
	
		this.files = new ArrayList <> ();
		this.lock = accessCounter.getLock();
		
		// Set policy
		this.cache = new LRUFileCacheRW(this.path, this.accessCounter);
				//new LRUFileCacheRW(this.path, this.accessCounter);
				//new FIFOFileCacheRW (this.path);
				//new LFUFileCacheRW (this.path, this.accessCounter); 
	}
	
	public void setDone(){
		this.lock.lock();
		try {
			done = true;
		} finally {
			this.lock.unlock();
		}
	}
	
	@Override
	public void run() {
		
		this.lock.lock();
		
		try {
		
			String path = this.path.toAbsolutePath().toString();
			
			
			File dir = new File (path);
					
			if (dir.listFiles() == null){
				
				System.out.println("No files found.");

			} else if (dir.listFiles() != null) {
		
				for (File file : dir.listFiles()){
					
					try {
						files.add(file.getName());
					} catch(Exception e) {
						// 
					}
				}
				
				// Detect if a thread is done==true.
				if(done==true){				
					System.out.println(Thread.currentThread().getName()+ ": Stopped the handler.");
					this.files.clear();
				
				} else {
					
					int rand = new Random().nextInt(files.size());
					String randFile = files.get(rand);
					try {	
						Path path1 =  Paths.get(randFile);
					
						System.out.println("    Current file: " + path1);
					
						cache.fetch(path1);
					
					
						accessCounter.increment(path1);
						accessCounter.getCount(path1);
					} catch (Exception e){
						// do nothing
					}
				}
		
		}
		
			Thread.sleep(1000);
			
		} catch (Exception e) {
			// do nothing
		} finally {
			this.lock.unlock();
		}
		
		
	}
	

}

