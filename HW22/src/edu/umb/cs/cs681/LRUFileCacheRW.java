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

public class LRUFileCacheRW extends FileCache{

	//Replaces the least recently requested file
	//with a new file.
	

	private Map <Path,String> cache;
	private ReentrantReadWriteLock rwlock = new ReentrantReadWriteLock();
	private int capacity = 3;
	private Path path;
	private AccessCounter ac;
	
	public LRUFileCacheRW (){
		this.cache = new LinkedHashMap<Path,String> ();
		this.cache.clear();
	}

	public LRUFileCacheRW (Path path, AccessCounter ac){
		this.cache = new HashMap<Path,String> ();
		this.cache.clear();
		this.path = path;
		this.ac = ac;
		
		System.out.println("LRU Cache instantiated.\n");
	}

	@Override
	public String fetch(Path file) {
		
		try {
			
			if (cache.containsKey(file)){
				
				return cache.get(file);
				
			} else {
				
				 return cacheFile (file);
			}
			
		} finally {
		}

	}


	@Override
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


	@Override
	protected String replace(Path file) {
		this.rwlock.writeLock().lock();
		
		try {
			
			//find least recently used 
		
			System.out.println("");
			
			long leastRecentTime=System.currentTimeMillis()+1;
			long time; 
			Path keyToBeRemoved = null;
			
			HashMap.Entry<Path,String> pair = null;
			
			Iterator<HashMap.Entry<Path,String>> it = cache.entrySet().iterator();
		    while (it.hasNext()) {
		        pair = (Entry<Path, String>)it.next();
		       
		        time = ac.getTime(pair.getKey());
		        
		       
		        System.out.println(pair.getKey() + " time: " + time);
		        
		        if (time < leastRecentTime && time!=0){
		        	// update the key for removal
		        	
		        	leastRecentTime = time;
		        	keyToBeRemoved = pair.getKey();
		        }
		     
		    }
			
		    System.out.println("");
		    
			// Removes least frequently used element in the collection
			this.cache.remove(keyToBeRemoved);
			
			if (!this.cache.containsKey(keyToBeRemoved)){
				//System.out.println("Key removed.");
			} else {
				System.out.println("Problem: Key remains!");
			}
			

			// Place new element in collection
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
        //System.out.println(contents);
        
        bufferReader.close();
        
        return contents;

	}

	
}
