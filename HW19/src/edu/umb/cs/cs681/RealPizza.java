package edu.umb.cs.cs681;
 
public class RealPizza implements Pizza{
	private String realPizza;
	
	public RealPizza(){
		try{
			Thread.sleep(3000);
		}catch(InterruptedException e){}
		
		System.out.println("A real pizza is made!");
		
		realPizza = "REAL PIZZA!";
	}

	public String getPizza(){
		return realPizza;
	}
}
