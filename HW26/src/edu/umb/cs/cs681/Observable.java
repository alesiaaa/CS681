package edu.umb.cs.cs681;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class Observable {
	
	private ArrayList<Observer> observers;
	private boolean changed = false;
	private ReentrantLock lockObs;

	public Observable() {
		observers = new ArrayList<Observer>();
		lockObs = new ReentrantLock ();
	}
	
	public void addObserver(Observer o){
		lockObs.lock();
		try {
			this.observers.add(o);
		} finally {
			lockObs.unlock();
		}
	}
	
	public void deleteObserver(Observer o){
		lockObs.lock();
		try {
			this.observers.remove(o);
		} finally {
			lockObs.unlock();
		}
	}
	
	protected void setChanged(){
		lockObs.lock();
		try {
			this.changed = true;
		} finally {
			lockObs.unlock();
		}
	}
	
	public boolean hasChanged(){
		return this.changed;
	}
	
	protected void clearChanged(){
		lockObs.lock();
		try {
			this.changed = false;
		} finally {
			lockObs.unlock();
		}
	}
	
	// notify of new event
	public void notifyObservers (Object obj) {
		lockObs.lock();
		try {
			
			if (!hasChanged()){
				return; // balking
			} else {
				//notify of changes
				for (Observer obs: this.observers){
					obs.update(this, obj);
				}
				//reset changed status after called in each observer
				clearChanged();
			}
		} finally {
			lockObs.unlock();
		}
	}
	

}
