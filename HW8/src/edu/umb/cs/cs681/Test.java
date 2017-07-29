package edu.umb.cs.cs681;

import java.util.ArrayList;

public class Test {
	
	public static void main(String[] args) {
		System.out.println("Starting prime number generater 1 to 1000000...");
		PrimeNumberGenerator gen = new PrimeNumberGenerator(1L, 1000000L);
		
		Thread thread = new Thread(gen);
		
		try {
			thread.start();
			thread.join();
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// Store collection in ArrayList
		ArrayList<Long> list1 = new ArrayList<> ();
		gen.getPrimes().forEach(x -> list1.add(x));
		System.out.println("Prime numbers have been collected. Total number of values: " + list1.size());
		
		PrimeNumberGenerator gen1 = new PrimeNumberGenerator(1L, 1000000L);
		PrimeNumberGenerator gen2 = new PrimeNumberGenerator(1000001L, 2000000L);
		
		Thread t1 = new Thread(gen1);
		Thread t2 = new Thread (gen2);
		
		try {
			
			System.out.println("\nStarting prime number generater 1 to 1000000...");
			t1.start(); 
			System.out.println("Starting prime number generater 1000001 to 2000000...");
			t2.start();
			
			t1.join(); 
			t2.join();
		
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// Store collection in ArrayList
		ArrayList<Long> list = new ArrayList<> ();
		gen1.getPrimes().forEach(x -> list.add(x));
		gen2.getPrimes().forEach(x -> list.add(x));
		
		System.out.println("Prime numbers have been collected. Total number of values: " + list.size());
		// Print numbers
		//System.out.println(list);
		
		System.out.println("\nPrint out the first and last prime values generated (second run): ");
		
		list.stream()
        .filter(x -> x.equals(2L))
        .forEach(System.out::println);
		
		list.stream()
        .filter(x -> x.equals(1999993L))
        .forEach(System.out::println);
   
		
		
	}
}
