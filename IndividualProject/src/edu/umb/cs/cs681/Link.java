package edu.umb.cs.cs681;

import java.util.Date;

public class Link extends FSElement{
	
	private FSElement actualElement;

	
	// Constructor 1
	public Link(Directory parent, String name) {
		super(parent, name, 0, new Date());
		setLink();
		setLastModified();
		
		// Will have to set original element manually
		// use setElement() below
	}
	
	// Constructor 2
	public Link(Directory parent, String name, FSElement element) {
		super(parent, name, 0, new Date());
		setLink();
		setLastModified();
		
		// Assign original element as part of constructor
		this.actualElement = element;
	}
	
	public int getSize () {
		return this.size;
	}
	
	public int getTargetSize() {
		int size = -1;
		
		Directory parentDirectory = actualElement.getParent(); 
		
		for (FSElement a : parentDirectory.getChildren()){
			
			if (a.getName() == actualElement.getName()){
				
				if (actualElement.isLink()==false){
					//is not a link
					
					if (actualElement.isFile()==true){
						// a file
						
						File file = (File) a;
						size = file.getSize(); 
						
					} else if (actualElement.isFile()==false) {
						// a directory
						
						Directory directory = (Directory) a;
						size = directory.getSize();
						
					} 
					
					
				} else {
					// actual element is a link
					// find actual element
						
					size = findLinkSize(actualElement, size);
						
				}
				
				
			}
			
		}
		
		
		
		return size;
	}
	
	public void setElement (FSElement element){
		
		this.actualElement = element;
		setLastModified();
		
	}
	
	private int findLinkSize(FSElement element, int size){
		int elementSize = 0;
		
		// element is a link
		Link link = (Link) element;
		
		if (link.actualElement.isLink()) {
			// actual element of the link is a link too
			// call getTargetSize() recursively 
			Link link1 = (Link) link.actualElement;
			elementSize = link1.getTargetSize();
			
		} else if (!link.actualElement.isLink()) {
			//actual element is not a link
			
			if (link.actualElement.isFile()){
				// a file
				
				File file = (File) link.actualElement;
				elementSize = file.getSize(); 
				
			} else if (!link.actualElement.isFile() && !link.actualElement.isLink()) {
				// a directory

				Directory directory = (Directory) link.actualElement;
				elementSize = directory.getSize();
			}
			
			
		}
			
		
		return elementSize;
	}
	
	//override toString() method
	//custom link display
    @Override 
    public String toString(){
    	
    	String str = "* " + getName();
        
        return str;
    }
	
	

}
