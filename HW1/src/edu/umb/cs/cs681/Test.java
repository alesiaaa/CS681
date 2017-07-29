package edu.umb.cs.cs681;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Test {
	
	
	public static void main (String[] args){
		
		ArrayList<Car> actual = new ArrayList<Car>();
		actual.add(new Car("Toyota Corolla", new BigDecimal(4000), 2010, 74000f));
		actual.add(new Car("Volkswagen Gulf", new BigDecimal(7000), 2012, 170000f));
		actual.add(new Car("Honda Civic", new BigDecimal(6000), 2013, 100000f));
		actual.add(new Car("Audi A4", new BigDecimal(15000), 2014, 50000f));
		
		//lambda for BigDecimal object
		System.out.println("\nSorting cars by price (ascending): ");
		actual.sort((Car c1, Car c2) -> c1.getPrice().compareTo(c2.getPrice()));
		
		for (Car c: actual){
			System.out.println("\t" + c + ", $" + c.getPrice());
		}
		
		//lambda for BigDecimal object
		System.out.println("\nSorting cars by price (descending): ");
		actual.sort((Car c1, Car c2) -> c2.getPrice().compareTo(c1.getPrice()));
		
		for (Car c: actual){
			System.out.println("\t" + c + ", $" + c.getPrice());
		}
		
		//lambda for primitive type float
		System.out.println("\nSorting cars by mileage (ascending): ");
		actual.sort((Car c1, Car c2) -> (int)(c1.getMileage() - c2.getMileage()));
		
		for (Car c: actual){
			System.out.println("\t" + c + ", " + c.getMileage() + " miles");
		}
		
		//lambda for primitive type float
		System.out.println("\nSorting cars by mileage (descending): ");
		
		actual.sort((Car c1, Car c2) -> (int)(c2.getMileage() - c1.getMileage()));
		
		for (Car c: actual){
			System.out.println("\t" + c + ", " + c.getMileage() + " miles");
		}
		
		//lambda for primitive type
		System.out.println("\nSorting cars by year (ascending): ");
		actual.sort((Car c1, Car c2) -> c1.getYear() - c2.getYear());
		
		for (Car c: actual){
			System.out.println("\t" + c + ", " + c.getYear());
		}
		
		//lambda for primitive type
		System.out.println("\nSorting cars by year (descending): ");
		actual.sort((Car c1, Car c2) -> c2.getYear() - c1.getYear());
		
		for (Car c: actual){
			System.out.println("\t" + c + ", " + c.getYear());
		}
		
		System.out.println(" ");
	}

}
