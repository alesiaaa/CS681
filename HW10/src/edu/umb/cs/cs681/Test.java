package edu.umb.cs.cs681;

import java.util.ArrayList;

public class Test{
	  
	public static void main (String[] args){
	    ArrayList<Thread> threads = new ArrayList<Thread>();
	    
	    
	    ArrayList<String> values = new ArrayList<String>();
	    values.add("1");  values.add("2");
	    values.add("4");  values.add("8");
	    values.add("16");
	    
	    for(int k = 0; k < values.size(); k++){
	    
		    final long nTimes  = Long.parseLong("10000000000");
		    final int nThread = Integer.parseInt(values.get(k));
		
		    System.out.println("\nRunning "+ values.get(k) + " threads");
		    
		    for (int i = 0; i < nThread; i++) {
		      Thread t = new Thread(
		    		  
		    		 () -> { 
		    			 	
		    			 	int n = 25;
		    		  		for (long j = 0; j < nTimes; j++) {n *= 25;};
		    		  		System.out.println(Thread.currentThread().getName() + 
		    		  				" result is: " + n + " total calculated values");
		    		  })
		    		  ;
		      threads.add(t);
		      t.start();
		    }
		    
		    threads.forEach(
		    		(Thread thread)->{
		    			try {
		    				thread.join();
		    			} catch (Exception e) {
		    				e.printStackTrace();
		    			}
		    			});
	    }
	    
	    
  }
}
