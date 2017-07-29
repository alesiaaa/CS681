package edu.umb.cs.cs681;

import java.math.BigDecimal;

public class Car{
	
	private String name;
	private BigDecimal price;
	private int year;
	private float mileage;
	private int wheelDrive;
	private boolean cruiseControl;
	private boolean sunroof;
	
	// empty constructor to set default values
	public Car (){
		this.name = "None";
		this.price = new BigDecimal(0.0);
		this.year = 0;
		this.mileage = 0;
		
	}
	
	// basic constructor to set minimum values
	public Car (String name, BigDecimal price, int year, float mileage){
		this.name = name;
		this.price = price;
		this.year = year;
		this.mileage = mileage;		
	}
	
	
	// constructor to set all possible values
	public Car (String name, BigDecimal price, int year, float mileage, int wheelDrive, boolean cruiseControl, boolean sunroof){
		this.name = name;
		this.price = price;
		this.year = year;
		this.mileage = mileage;
		this.wheelDrive = wheelDrive;
		this.cruiseControl = cruiseControl;
		this.sunroof = sunroof;
	}
	
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setPrice(BigDecimal price){
		this.price = price;
	}
	public void setYear(int year){
		this.year = year;
	}
	public void setMileage(float mileage){
		this.mileage = mileage;
	}
	public void setWheelDrive(int wheelDrive){
		this.wheelDrive = wheelDrive;
	}
	public void setCruiseControl(boolean cruiseControl){
		this.cruiseControl = cruiseControl;
	}
	public void setSunroof(boolean sunroof){
		this.sunroof = sunroof;
	}
	
	public String getName(){
		return this.name;
	}
	public BigDecimal getPrice(){
		return this.price;
	}
	public int getYear(){
		return this.year;
	}
	public float getMileage(){
		return this.mileage;
	}
	public int getWheelDrive(){
		return this.wheelDrive;
	}
	public boolean getCruiseControl(){
		return this.cruiseControl;
	}
	public boolean getSunroof(){
		return this.sunroof;
	}
	
	public boolean equals(Car car) {
		if (this == car || (this.getName().equals(car.getName()) &&
				(this.getYear()==(car.getYear()) && car instanceof Car ))){
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public String toString()
	{
		return this.name;
	}
	
	
}
