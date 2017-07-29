package edu.umb.cs.cs681;

import java.util.ArrayList;

public class Test {
	
	public static void main(String[] args) throws Exception {
		
		ArrayList<Thread> threads = new ArrayList<Thread>();
		
		CancellablePrimeNumberGenerator cpng = new CancellablePrimeNumberGenerator(1L, 100L);
		
	    final int nThread = 4;
	   
	    
	    for (int i = 0; i < nThread; i++) {
	        Thread t = new Thread(cpng);
	       
	        threads.add(t);
	        t.start();
	       
	      }
	    
	      
	    
	      threads.forEach(
	    		  (Thread thread)->{
	    			  try {
	    			  		thread.join();
	    			  } catch (Exception e) {
	    				  	e.printStackTrace();}
	    		  }
	      );
	      
	      System.out.println("\nTotal of " + cpng.getPrimes().size() + " prime numbers were found in " + 
	    	      Thread.currentThread().getName() + " for "+ nThread + " running threads (range 1 to 100)");
	      
	    
	   
	}

}
