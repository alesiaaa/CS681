package edu.umb.cs.cs681;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class Observable {
	
	private CopyOnWriteArrayList<Observer> observers;
	private boolean changed = false;
	private ReentrantLock lock;

	public Observable() {
		observers = new CopyOnWriteArrayList<Observer>();
		lock = new ReentrantLock ();
	}
	
	public void addObserver(Observer o){
		
		this.observers.add(o);

	}
	
	public void deleteObserver(Observer o){
		
		this.observers.remove(o);
	
	}
	
	
	protected void setChanged(){
		lock.lock();
		try {
			this.changed = true;
		} finally {
			lock.unlock();
		}
	}
	
	public boolean hasChanged(){
		return this.changed;
	}
	
	protected void clearChanged(){
		lock.lock();
		try {
			this.changed = false;
		} finally {
			lock.unlock();
		}
	}
	
	// notify of new event
	public void notifyObservers (Object arg) {
		lock.lock();
		try {
			if (!hasChanged()){
				changed = false;
				return;
			} 
		} finally {
			lock.unlock();
		}
		
		Iterator<Observer> it = observers.iterator();
		
		while( it.hasNext() ){
			it.next().update(this, arg);
		}
	}
	

}
