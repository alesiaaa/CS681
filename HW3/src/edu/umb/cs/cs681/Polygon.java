package edu.umb.cs.cs681;

import java.util.ArrayList;
import java.util.Objects;

public class Polygon{
	
	private ArrayList<Point> points = new ArrayList<>();
	private AreaCalculator areaCalc;
	
 	
	
	public Polygon (ArrayList<Point> points) {
	
		if( points.size()==3 ){
			//Polygon with 3 points 
			
			this.Polygon(points, 
					(Polygon p)->{
					// Calculate distance
					float a = (float) Math.sqrt( Math.pow((points.get(1).getX() - points.get(0).getX()),2) + Math.pow((points.get(1).getY() - points.get(0).getY()),2));
					float b = (float) Math.sqrt( Math.pow((points.get(2).getX() - points.get(1).getX()),2) + Math.pow((points.get(2).getY() - points.get(1).getY()),2));
					float c = (float) Math.sqrt( Math.pow((points.get(0).getX() - points.get(2).getX()),2) + Math.pow((points.get(0).getY() - points.get(2).getY()),2));
					
					// Heron's formula for triangle area
					float s = (float) 0.5*(a+b+c);
					return (float) Math.sqrt(s*(s-a)*(s-b)*(s-c));
					
				} 
			);	

		} else if (points.size()==4) {
			//Polygon with 4 points 
			
			this.Polygon(points, 
					(Polygon p)->{
			
				float area = 0;
				float [] distance = new float [points.size()]; 	
				int x = points.size()-1; 
				
				// Calculate distance	
				for (int i = 0; i < points.size(); i++){ 
					 distance[i] = (float) Math.sqrt(
								  ((points.get(x).getX()-points.get(i).getX()) * (points.get(x).getX()-points.get(i).getX())) + 
								  ((points.get(x).getY()-points.get(i).getY()) * (points.get(x).getY()-points.get(i).getY()))
								  );  
					 x = i;     
					 }
					  
						  
				if ((distance[0] == distance[2]) && (distance[1] == distance[3])){
					// Calculate area
					area = distance[0] * distance[1];
					
					
					
				} else { 
					 System.out.println("Cannot calculate area. Must check coordinates and re-order in clockwise or counter clockwise.");
				}
				
				return area;
			
			});
		
		}
		else {
			
			System.out.println("Not a valid number of points.");
			return;
		}
	}
	
	

	public void Polygon (ArrayList<Point> points, AreaCalculator areaCalc){
		this.points = Objects.requireNonNull(points);
		this.areaCalc = Objects.requireNonNull(areaCalc);
	}

	public float getArea(){
	
		return this.areaCalc.getArea(this);
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
