package edu.umb.cs.cs681;

import java.util.ArrayList;

@FunctionalInterface
public interface AreaCalculator{

	public abstract float getArea (ArrayList<Point> points);
	
}
