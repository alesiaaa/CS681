package edu.umb.cs.cs681;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;


public class RequestHandler implements Runnable{
	
	private Path path;
	private AccessCounter accessCounter;
	private ArrayList<String> files;
	private volatile boolean done = false;
	
	public RequestHandler(Path path, AccessCounter accessCounter){
		this.accessCounter = accessCounter;
		this.path = path;
		this.files = new ArrayList <> ();
	}
	
	public void setDone(){
		done = true;
	}

	@Override
	public void run() {
		
		String path = this.path.toAbsolutePath().toString();
		
		//System.out.println(path);
		
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
				//choose file at random
				int rand = new Random().nextInt(files.size());
				String randFile = files.get(rand);
				
				try {
				
					Path path1 =  Objects.requireNonNull(Paths.get(randFile));
				
					accessCounter.increment(path1);
					accessCounter.getCount(path1); 
					
				} catch (Exception e){
					// do nothing
				}
			}
		
		}
		
			
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// do nothing
		}
		
		
	}

}
