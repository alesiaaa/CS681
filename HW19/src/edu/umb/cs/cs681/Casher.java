package edu.umb.cs.cs681;

import java.util.concurrent.locks.ReentrantLock;

public class Casher{
	private Future future = new Future();

	public Pizza order(){
		System.out.println("An order is made.");
		Thread t = new Thread( 
			()->{ RealPizza realPizza = new RealPizza();
			future.setRealPizza( realPizza ); });
		
		t.start();
		return future;
	}

	public static void main(String[] args){
		ReentrantLock lock = new ReentrantLock();
		Casher casher = new Casher();
		System.out.println("Ordering pizzas at a casher counter.");
		Pizza pizza1 = casher.order();
		Pizza pizza2 = casher.order();
		Pizza pizza3 = casher.order();
		
		System.out.println("Doing something, reading newspapers, magazines, etc., " +
				"until pizzas are ready to pick up...");
		try{
			Thread.sleep(1000);
		} catch(InterruptedException e){}
		
		while(true){
			
				lock.lock();
				
			try {
				if( casher.future.isReady() ){
					System.out.println("Let's see if pizzas are ready to pick up...");
					System.out.println( ((Future) pizza1).getPizza(4000L) );
					System.out.println( ((Future) pizza2).getPizza(3000L) );
					System.out.println( ((Future) pizza3).getPizza(6000L) );
					break; } 
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				lock.unlock();
			}
			
			System.out.println("Doing something...");
	
		}
		
		
	}
}
