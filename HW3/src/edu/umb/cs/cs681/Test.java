package edu.umb.cs.cs681;

import java.util.ArrayList;

public class Test {
	
	public static void main (String[] args){
		
		ArrayList<Point> al = new ArrayList <> ();
		al.add(new Point(18,-5));
		al.add(new Point(-5,-5));
		al.add(new Point(-5,-13));
		Polygon p = new Polygon(al);
		// triangle’s area
		System.out.println("Triangle Area: " + String.format("%.2f", p.getArea()));
		
		al = new ArrayList <> ();
		al.add(new Point(18,-5));
		al.add(new Point(-5,-5));
		al.add(new Point(-5,-13));
		al.add( new Point(18,-13));// add the 4th point
		p = new Polygon(al);
		// rectangle’s area
		System.out.println("Rectangle Area: " + String.format("%.2f", p.getArea()));
		
		
		al = new ArrayList <> ();
		al.add(new Point(18,-5));
		al.add(new Point(-5,-5));
		al.add(new Point(-5,-13));
		p = new Polygon(al);
		// again traingle’s area
		System.out.println("Triangle Area: " + String.format("%.2f", p.getArea()));
		
		
		
	}

}
