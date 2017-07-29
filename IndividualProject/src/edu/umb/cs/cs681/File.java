package edu.umb.cs.cs681;

import java.util.Date;

public class File extends FSElement {
	
	public File(Directory parent, String name, int size) {
		
		super(parent, name, size, new Date());
		setFile();
		setLastModified();
		
	}
	
	public void setSize (int size) {
		this.size = size;
		setLastModified();
	}
	
	public int getSize () {
		return this.size;
	}
	
	//override toString() method
    @Override 
    public String toString(){
    	
    	String str = "- " + getName();
        
        return str;
    }
	
}
