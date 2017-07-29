package edu.umb.cs.cs681;

import java.util.ArrayList;
import java.util.Date;

public class Directory extends FSElement {
	
	private ArrayList <FSElement> children;

	public Directory(Directory parent, String name) {
		super(parent, name, 0, new Date ());
		children = new ArrayList <FSElement>();
		setLastModified();
	}

	public ArrayList <FSElement> getChildren() {
		return this.children;
		
	}
	
	public Directory getDirectory(String directory) {
		
		String foundDirectory = null;
		Directory dir = null;
		
		for (int x = 0; x < children.size(); x++){
			
			foundDirectory = children.get(x).getName();
			
			if (foundDirectory == directory){
				
				dir = (Directory) children.get(x);
			}
			
		}
		
		return dir;
	}
	
	public void appendChild (FSElement element) {
		children.add(element);
		setLastModified();
	}
	
	
	public int getSize () {
		
		int totalSize = 0;
		
		for (FSElement element: children){
		
				totalSize+= element.getSize();
			
		}
		
		return totalSize;
	}
	
	//override toString() method
    @Override 
	public String toString() {
		return getName();
	}

}
