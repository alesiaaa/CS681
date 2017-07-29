package edu.umb.cs.cs681;

public class Test {
	
	public static void main(String[] args) {
		
		Thread t1, t3;
		Thread t2, t4;
			
		CancellablePrimeNumberGenerator cancellableGen = new CancellablePrimeNumberGenerator(1L, 1000000L);
		t1 = new Thread(cancellableGen);
		t3 = new Thread(cancellableGen);
		
		t1.start();
		t3.start();
		System.out.println("Starting CancellablePrimeNumberGenerator threads");
		
		cancellableGen.setDone();
		
		try {
			t1.join();
			t3.join();
		} catch (InterruptedException e) {e.printStackTrace();}
		
		System.out.println(cancellableGen.getPrimes().size() + " prime numbers generated.");

	    InterruptiblePrimeNumberGenerator interruptibleGen = new InterruptiblePrimeNumberGenerator(1000001L, 2000000L);
		t2 = new Thread(interruptibleGen);
		t4 = new Thread(interruptibleGen);
		t2.start();
		t4.start();
		System.out.println("\nStarting InterruptiblePrimeNumberGenerator threads");
		t2.interrupt();
		t4.interrupt();
		
		try {
			t2.join();
			t4.join();
		} catch (InterruptedException e) {e.printStackTrace();}
		
		System.out.println(interruptibleGen.getPrimes().size() + " prime numbers generated."); 
	}

}
