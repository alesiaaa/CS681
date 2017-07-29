package edu.umb.cs.cs681;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

class FileCrawler implements Runnable{
	
	private Directory dir; //root dir of a given drive (tree structure)
	private FileQueue queue;
	private ReentrantLock lock = new ReentrantLock();
	private boolean done = false;
	
	FileCrawler (FileQueue queue, Directory root){
		this.dir = root;
		this.queue = queue; 
	}

	public void run(){
		try {
			
			this.lock.lock();
			try {
				if (this.done == true){
					System.out.println(Thread.currentThread().getName() + " Terminated by main thread.");
					return;
					}
				} finally {
					this.lock.unlock();
				}
				
			if (this.dir.getSize() > 0 && this.dir.getChildren().size() > 0){
					
				crawl(this.dir);
					
			} else {
					
				System.out.println("No contents to index.");
			}
			
		} catch (Exception e){
			e.printStackTrace();
		}

	}
	
	public void setDone (){
		this.lock.lock();
		try {
			this.done = true;
		}finally {
			this.lock.unlock();
		}
	}
	
	private void crawl(Directory directory) throws InterruptedException{
		
		
		ArrayList<FSElement> temp = new ArrayList <>();
		// Crawl a given tree structure
		// Put files to a queue. Ignore directories and links.
		
		System.out.println(Thread.currentThread().getName() + " Crawling folder: " + directory.toString());
		
			temp.addAll(directory.getChildren());
			
			for (FSElement e: temp){
				
				 if (e.isFile()) {
					File f = (File) e;
					this.queue.put(f);
					
				 } else if(!e.isFile() && !e.isLink()){
						
					Directory dir1 = (Directory) e;
					
					Thread.sleep(2000);
					
					
					// Crawl current directory
					crawl(dir1);
					
					
						
				 	}
			
			}
		
	}
	
	
}