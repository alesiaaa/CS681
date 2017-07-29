package edu.umb.cs.cs681;

import java.util.Objects;

public class Point {
	
	private float x;
	private float y;
	
	// constructor
	public Point (float x, float y){
		
		this.x = x;
		this.y = y;
	}
	
	public float getX (){
		return x;
	}
	
	public float getY (){
		return y;
	}
	
	//override toString() method
    @Override 
    public String toString(){
        return "(" + getX() + ", " + getY() + ")";
    }
    
    
    //override hashCode() method
    @Override
	public int hashCode (){
		
		return Objects.hash(this.x,this.y);
    }
    
    
    //override equals() method
    @Override
	public boolean equals(Object o){
		
		 // If the object is compared with itself then return true  
        if (o == this) {
            return true;
        } else if (o == null){
        	return false;
        }
        
        Point z = (Point) o;
        
        return Float.compare(x,z.x) == 0 && Float.compare(y,z.y) == 0;
	}
		



}
