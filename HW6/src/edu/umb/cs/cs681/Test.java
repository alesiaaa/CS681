package edu.umb.cs.cs681;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;

public class Test {
	
	
	public static void main (String[] args){
		
ArrayList<Car> cars = new ArrayList<> ();
		
		Car car1 = new Car("Toyota Corolla", new BigDecimal(4000), 2009, 74000f);
		Car car2 = new Car("Volkswagen Gulf", new BigDecimal(7000), 2012, 170000f);
		Car car3 = new Car("Honda Civic", new BigDecimal(6000), 2013, 100000f);
		Car car4 = new Car("Audi A4", new BigDecimal(15000), 2014, 50000f);
		
		cars.add(car4);
		cars.add(car3);
		cars.add(car2);
		cars.add(car1);
		
		System.out.println("Cars: ");
		for(Car c: cars){
			System.out.println(c + ", $" + c.getPrice() + ", " + c.getYear());
		}
		
		// Lowest price
		Optional<BigDecimal> price1 = cars.stream()
				.map((Car car)-> car.getPrice())
				.reduce((result, carPrice)->{
				if(result.equals(0)) return carPrice;
				else if(carPrice.compareTo(result) == -1) return carPrice;
				else return result;} );
		
		price1.ifPresent(x -> System.out.println("\n\nLowest price: $" + x));
		
		
		// Earliest year
		Integer year = cars.stream()
				.map((Car car)-> car.getYear())
				.reduce(0, (result, carYear)->{
				if(result==0) return carYear;
				else if(carYear < result) return carYear;
				else return result;} );
		
		System.out.println("Earliest year: "+ year);
		
		
		
	}

}
