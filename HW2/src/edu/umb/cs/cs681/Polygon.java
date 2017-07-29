package edu.umb.cs.cs681;

import java.util.ArrayList;
import java.util.Objects;

public class Polygon{
	
	private ArrayList<Point> points = new ArrayList<Point>();
	
	private AreaCalculator areaCalc;
	
	
	
	Polygon (ArrayList<Point> pts, AreaCalculator areaCalc){
		
		this.points = Objects.requireNonNull(pts);
		this.areaCalc = Objects.requireNonNull(areaCalc);
		
	}
	
	public float getArea(){
		
		//return areaCalc.getArea(points, areaCalc);
		return areaCalc.getArea(points);
	}
	
	
	public void addPoint (Point point){
		
		points.add(point);
	}
	
	public void removePoint (Point point){
		
		points.remove(point);
	}
	
	public void setAreaCalc (AreaCalculator areaCalc){
		
		this.areaCalc = areaCalc;
	}
	
	public ArrayList<Point> getPoints(){
		
		return this.points;
		
	}
	
		
}
