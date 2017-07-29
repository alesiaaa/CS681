package edu.umb.cs.cs681;

import java.util.ArrayList;

public class Test {
	
	public static void main (String[] args){
		
		ArrayList<Point> al = new ArrayList <> ();
		al.add(new Point(18,-5));
		al.add(new Point(-5,-5));
		al.add(new Point(-5,-13));
		
		
		Polygon p = new Polygon(al, (ArrayList<Point> points)->{

			// Calculate distance
			float a = (float) Math.sqrt( Math.pow((points.get(1).getX() - points.get(0).getX()),2) + Math.pow((points.get(1).getY() - points.get(0).getY()),2));
			float b = (float) Math.sqrt( Math.pow((points.get(2).getX() - points.get(1).getX()),2) + Math.pow((points.get(2).getY() - points.get(1).getY()),2));
			float c = (float) Math.sqrt( Math.pow((points.get(0).getX() - points.get(2).getX()),2) + Math.pow((points.get(0).getY() - points.get(2).getY()),2));
			
			// Heron's formula for triangle area
			float s = (float) 0.5*(a+b+c);
			float area = (float) Math.sqrt(s*(s-a)*(s-b)*(s-c));
			return area;
		});
		
		p.getArea(); // triangle’s area
		
		System.out.println("Triangle Area: " + String.format("%.2f", p.getArea()));
		
		p.addPoint( new Point(18,-13) ); // add the 4th point
		
		p.setAreaCalc( (ArrayList<Point> points)->{
			
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
				return area = distance[0] * distance[1];
				
			} else { 
				 System.out.println("Cannot calculate area. Must check coordinates and re-order in clockwise or counter clockwise.");
			}
			
			return area;
			
			
			
		} );
		
		p.getArea(); // rectangle’s area
		
		
		System.out.println("Rectangle Area: " + String.format("%.2f", p.getArea()));
		
		
		p.removePoint(new Point(18,-13));
		
		p.setAreaCalc((ArrayList<Point> points)->{

			// Calculate distance
			float a = (float) Math.sqrt( Math.pow((points.get(1).getX() - points.get(0).getX()),2) + Math.pow((points.get(1).getY() - points.get(0).getY()),2));
			float b = (float) Math.sqrt( Math.pow((points.get(2).getX() - points.get(1).getX()),2) + Math.pow((points.get(2).getY() - points.get(1).getY()),2));
			float c = (float) Math.sqrt( Math.pow((points.get(0).getX() - points.get(2).getX()),2) + Math.pow((points.get(0).getY() - points.get(2).getY()),2));
			
			// Heron's formula for triangle area
			float s = (float) 0.5*(a+b+c);
			float area = (float) Math.sqrt(s*(s-a)*(s-b)*(s-c));
			return area;
		});
				
				
		p.getArea(); // again traingle’s area
		System.out.println("Triangle Area: " + String.format("%.2f", p.getArea()));
		
		
		
		
		
	}

}
