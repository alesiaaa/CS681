package edu.umb.cs.cs681;

public class Test {
	
	public static void main (String[] args){
	
		Observable observable = new Observable();
		
		observable.setChanged();
		observable.addObserver( 
				
				(Observable o, Object obj)->
				{System.out.println(obj);} 
				
				);
		
		observable.addObserver( 
				
				(Observable o, Object obj)->
				{System.out.println(obj);} 
				
				);
		
		
		observable.notifyObservers("Hello World!");
		
		
		observable.deleteObserver(
				
				(Observable o, Object obj)->
				{}
				
				);
		
		
		
	}

}
