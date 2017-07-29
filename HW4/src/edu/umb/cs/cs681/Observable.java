package edu.umb.cs.cs681;

import java.util.ArrayList;

public class Observable {
	
	private ArrayList<Observer> observerList;
	private boolean changed = false;
	

	public Observable() {
		observerList = new ArrayList<Observer>();
	}
	
	public void addObserver(Observer o){
		this.observerList.add(o);
	}
	
	public void deleteObserver(Observer o){
		this.observerList.remove(o);
		
	}
	
	public void getSize(){
		System.out.println("# "+ this.observerList.size());
	}
	
	protected void setChanged(){
		this.changed = true;
	}
	
	public boolean hasChanged(){
		return this.changed;
	}
	
	protected void clearChanged(){
		this.changed = false;
	}
	
	public void notifyObservers (Object obj) {
		
		if (this.hasChanged() == true){
			
			//notify of changes
			for (Observer obs: this.observerList){
				obs.update(this, obj);
			}
			
			//reset changed status after called in each observer
			clearChanged();
			
		}
	}
	

}
