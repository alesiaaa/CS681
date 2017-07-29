package edu.umb.cs.cs681;

import java.util.*;

public abstract class FSElement {
	
	private String name;
	private String owner;
	private Date created; //read only
	private Date lastModified;
	protected int size;
	
	private Directory parent;
	private boolean isFile = false;
	private boolean isLink = false;
	
	
	public FSElement (Directory parent, String name, int size, Date created){
		this.parent = parent;
		this.name = name;
		this.size = size;
		this.created = created;
		this.lastModified = this.created;
	}
	
	public Directory getParent () {
		return this.parent;
	}
	
	public void setFile (){
		this.isFile = true;	
	}
	
	public boolean isFile (){
		return this.isFile;	
	}
	
	public void setLink (){
		this.isLink = true;	
	}
	
	public boolean isLink (){
		return this.isLink;	
	}
	
	public void setName (String name){
		this.name = name;
		setLastModified();
	}
	
	public void setOwner (String owner){
		this.owner = owner;
		setLastModified();
	}
	
	protected void setLastModified (){
		this.lastModified = new Date ();
	}
	
	public void setSize (int size) {}
	
	public String getName (){
		return this.name;
	}
	
	public String getOwner (){
		return this.owner;
	}
	
	public Date getCreatedDate (){
		return this.created;
	}
	
	public Date getLastModified (){
		return this.lastModified;
	}
	
	public int getSize () {
		return this.size;
	}

}
