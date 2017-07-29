package edu.umb.cs.cs681;

public class Test {
	
	public static void main (String[] args){
		Observable o = new Observable ();
		
		o.addObserver(
				(Observable observable,Object object)->{System.out.println(object);}
				);
		
		for (int i=10; i >= 0; i--){	
			
			o.notifyObservers("Notification #" + i);
		
			o.setChanged(); 
		
			
		}

	}

}
