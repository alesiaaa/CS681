package edu.umb.cs.cs681;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.locks.ReentrantReadWriteLock;


//FIFO, First-in, First-out

public class FIFOFileCache extends FileCache{
	
	private Map <Path,String> cache;
	ReentrantReadWriteLock rwlock = new ReentrantReadWriteLock();
	private int capacity = 5;
	
	public FIFOFileCache (){
		this.cache = new LinkedHashMap<Path,String> ();
		this.cache.clear();
		
		System.out.println(Thread.currentThread().getName() +" FIFO Cache instantiated. Capacity: 5");
	}


	@Override
	public String fetch(Path file) {
		
		if (cache.containsKey(file)){
			
				return cache.get(file);
				
			} else {
				
				return cacheFile (file);
			}
			
	}
	
	
	
	protected String cacheFile(Path file) {
	
		this.rwlock.writeLock().lock();
		
		try {
			
			//Make sure last updated is not greater than capacity
			if (this.cache.size() != capacity){
				
				this.cache.put(file, file.toString());
				
			} else {
				
				this.replace(file);
			}
		
		} catch(Exception e){
			
			e.printStackTrace();
		
		} finally {
			
			this.rwlock.writeLock().unlock();
		}
		
		return file.toString();
	}

	
	protected String replace(Path file) {
		
		this.rwlock.writeLock().lock();
		
		try {
			
			int i=0;
			HashMap.Entry<Path,String> pair = null;
			
			Iterator<HashMap.Entry<Path,String>> it = cache.entrySet().iterator();
		    
			while (it.hasNext() && i < 1) {
		        pair = (Entry<Path, String>)it.next();
		     
		        i++;
		    }
		
		   
			// Removes first element in the collection
			this.cache.remove(pair.getKey());
			
			
			if (!this.cache.containsKey(pair.getKey())){
				//System.out.println("Key removed.");
			} else {
				System.out.println("Problem: Key remains!");
			}
			
			//String contents = this.readFromFile(path.toString() + "/" + file);
			
			// Place new element at the end of the collection
			//this.cache.put(file, contents);
			
			
			
			this.cacheFile(file);
			
			
			this.rwlock.readLock().lock();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.rwlock.writeLock().unlock();
		}
				
		try {
			return this.cache.get(file).toString();
		} finally {
			this.rwlock.readLock().unlock();
		}
	}
	
	public void getCacheContents (){
		this.rwlock.writeLock().lock();
		
		try {
			System.out.println(Thread.currentThread().getName() +" Final FIFO Cache: "+ this.cache);
			System.out.println("");

		
		} catch(Exception e){
		
			e.printStackTrace();
	
		} finally {
		
			this.rwlock.writeLock().unlock();
		}
	}
	

}
