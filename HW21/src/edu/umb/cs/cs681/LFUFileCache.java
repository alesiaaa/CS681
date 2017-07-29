package edu.umb.cs.cs681;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.locks.ReentrantLock;

public class LFUFileCache extends FileCache{
	// Replaces the least frequently requested file
	// with a new file.
	
	private Map <Path,String> cache;
	private ReentrantLock lock = new ReentrantLock();
	private int capacity = 3;
	private Path path;
	private AccessCounter ac;
	
	public LFUFileCache (){
		this.cache = new LinkedHashMap<Path,String> ();
		this.cache.clear();
	}

	public LFUFileCache (Path path, AccessCounter ac){
		this.cache = new HashMap<Path,String> ();
		this.cache.clear();
		this.path = path;
		this.ac = ac;
		
		System.out.println("LFU Cache instantiated.\n");
	}

	@Override
	public String fetch(Path file) {
		String temp = null;
		
		this.lock.lock();
		
		try {
			
			if (cache.containsKey(file)){
				
				temp = cache.get(file);
				
			} else {
				
				temp = cacheFile (file);
			}
			
		} finally {
			
			this.lock.unlock();
		}
		return temp;
	}


	@Override
	protected String cacheFile(Path file) {
		this.lock.lock();
		
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
		
			this.lock.unlock();
		}
		
		return file.toString();
	}


	@Override
	protected String replace(Path file) {
		this.lock.lock();
		
		try {
			
			System.out.println("");
			
			int frequency=100;
			int count; 
			Path keyToBeRemoved = null;
			
			HashMap.Entry<Path,String> pair = null;
			
			Iterator<HashMap.Entry<Path,String>> it = cache.entrySet().iterator();
		    while (it.hasNext()) {
		        pair = (Entry<Path, String>)it.next();
		       
		        count = ac.getCount(pair.getKey());
		        
		       
		        System.out.println(pair.getKey() + " count: " + count);
		        
		        if (count < frequency){
		        	// update the key for removal
		        	
		        	frequency = count;
		        	keyToBeRemoved = pair.getKey();
		        }
		    }
			
		    System.out.println("");
		   // System.out.println("To remove ---> "+ keyToBeRemoved);
		    
			// Removes least frequently used element in the collection
			this.cache.remove(keyToBeRemoved);
			
			if (!this.cache.containsKey(keyToBeRemoved)){
				//System.out.println("Key removed.");
			} else {
				System.out.println("Problem: Key remains!");
			}
			
			// Place new element in collection
			this.cacheFile(file);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.lock.unlock();
		}
				
		return this.cache.get(file).toString();
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
