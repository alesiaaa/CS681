package edu.umb.cs.cs681;

import java.io.BufferedReader;
import java.io.FileReader;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.locks.ReentrantReadWriteLock;


//FIFO, First-in, First-out

public class FIFOFileCacheRW extends FileCache{
	
	private Map <Path,String> cache;
	ReentrantReadWriteLock rwlock = new ReentrantReadWriteLock();
	private int capacity = 3;
	
	private Path path;
	
	public FIFOFileCacheRW (){
		this.cache = new LinkedHashMap<Path,String> ();
		this.cache.clear();
	}
	
	public FIFOFileCacheRW (Path path){
		this.cache = new HashMap<Path,String> ();
		this.cache.clear();
		this.path = path;
		
		System.out.println("FIFO Cache instantiated.\n");
	}

	@Override
	public String fetch(Path file) {
		
		//this.rwlock.readLock().lock();

		try {
			
			if (cache.containsKey(file)){
				//this.rwlock.readLock().unlock();
				return cache.get(file);
				
			} else {
				
				return cacheFile (file);
			}
			
		} finally {
			
			//this.rwlock.readLock().unlock();
		}
	}
	
	
	
	protected String cacheFile(Path file) {
	
		this.rwlock.writeLock().lock();
		
		try {
			
			//Make sure last updated is not greater than capacity
			if (this.cache.size() != capacity){
				
				this.cache.put(file, this.readFromFile(path.toString() + "/" + file));
				
			} else {
				
				this.replace(file);
			}
			
			System.out.println("Cache: "+ this.cache);
			System.out.println("");
	
			
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
	
	
	public String readFromFile(String path) throws Exception{
		
		String contents;
		
        FileReader fileReader = new FileReader(path);
        BufferedReader bufferReader = new BufferedReader(fileReader);
        
        
        String line = null;
        StringBuilder sb = new StringBuilder();
        while ((line = bufferReader.readLine()) != null) {
            sb.append(line);
            
        }
       
        contents = sb.toString();
        
        bufferReader.close();
        
        return contents;

	}
	

}
