package edu.umb.cs.cs681;
 
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.TimeUnit;


public class Future implements Pizza{
	private RealPizza realPizza = null;
	private ReentrantLock lock;
	private Condition ready;
	
	public Future(){
		lock = new ReentrantLock();
		ready = lock.newCondition();
	}
	
	public void setRealPizza( RealPizza real ){
		lock.lock();
		try{
			if( realPizza != null ){ return; }
			realPizza = real;
			ready.signalAll();
		}
		finally{
			lock.unlock();
		}
	}

	public String getPizza(long timeout) throws CasherTimeoutException{
		String pizzaData = null;
		lock.lock();
		try{
			
			if( realPizza == null ){
				
				System.out.println("Currently waiting for pizza..");
				// wait for the pizza a short time
				ready.await(timeout, TimeUnit.MILLISECONDS);
				
				// if pizza is still unavailable, then throw new exception
				if (realPizza == null){
					 throw new CasherTimeoutException("Timeout! Waiting too long!");
				}
			}
			
			pizzaData = realPizza.getPizza();
			
		} catch (InterruptedException e){
			e.printStackTrace();
			
		} finally{
			lock.unlock();
		}
		return pizzaData;
	}
	
	public boolean isReady(){
		lock.lock();
		try {
			return ready != null;
		} finally {
			lock.unlock();
		}
	}

	@Override
	public String getPizza() {
		
		String pizzaData = null;
		lock.lock();
		try{
			if( realPizza == null ){
				ready.await();
			}
			pizzaData = realPizza.getPizza();
		}
		catch(InterruptedException e){
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
		return pizzaData;
	}
	
	
}
