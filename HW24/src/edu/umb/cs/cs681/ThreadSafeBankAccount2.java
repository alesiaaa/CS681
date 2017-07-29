package edu.umb.cs.cs681;

import java.util.ArrayList;



import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadSafeBankAccount2{
	private double balance = 0;
	private static ReentrantLock lock; // Solution 1: Static Locking
	private Condition sufficientFundsCondition, belowUpperLimitFundsCondition;
	private ThreadSafeBankAccount2 account;
	
	public ThreadSafeBankAccount2(){
		lock = new ReentrantLock();
		sufficientFundsCondition = lock.newCondition();
		belowUpperLimitFundsCondition = lock.newCondition();
		account =  this;
	}
	
	public void deposit(double amount){
		lock.lock();
		try{
			// Solution 2: Timed Locking
			// Solution 4: Nested Try Lock
			if( !lock.tryLock(3, TimeUnit.SECONDS) ){ 
				// generate an error msg or throw an exception
				System.out.println("Cannot obtain lock. Trying again.");
				
				if( !lock.tryLock(3, TimeUnit.SECONDS) ){ 
					// generate an error msg or throw an exception
					System.out.println("Cannot obtain lock at this time");
				} 
				
			} 

			if (lock.tryLock() == true){
				System.out.println("Lock obtained");
				System.out.println(Thread.currentThread().getId() + 
						" (d): current balance: " + balance);
				while(balance >= 300){
					
							System.out.println(Thread.currentThread().getId() + 
									" (d): await(): Balance exceeds the upper limit.");
							belowUpperLimitFundsCondition.await();
					
					
					
				}
				balance += amount;
				System.out.println(Thread.currentThread().getId() + 
						" (d): new balance: " + balance);
				sufficientFundsCondition.signalAll();
			}
		}
		catch (InterruptedException exception){
			exception.printStackTrace();
		}
		finally{
			lock.unlock();
			
			System.out.println("Lock released");
		}
	}
	
	public void withdraw(double amount){
		lock.lock();
		try{
			 // Solution 2: Timed Locking
			// Solution 4: Try lock
			if( !lock.tryLock(3, TimeUnit.SECONDS) ){ 
				// generate an error msg or throw an exception
				System.out.println("Cannot obtain lock. Trying again.");
				
				if( !lock.tryLock(3, TimeUnit.SECONDS) ){ 
					// generate an error msg or throw an exception
					System.out.println("Cannot obtain lock at this time");
				} 
				
			} 
			
			if (lock.tryLock() == true){
				System.out.println("Lock obtained");
				System.out.println(Thread.currentThread().getId() + 
						" (w): current balance: " + balance);
				while(balance <= 0){
					
							System.out.println(Thread.currentThread().getId() + 
									" (w): await(): Insufficient funds");
							sufficientFundsCondition.await();
							
				
				}
				balance -= amount;
				System.out.println(Thread.currentThread().getId() + 
						" (w): new balance: " + balance);
				belowUpperLimitFundsCondition.signalAll();
			}
		}
		catch (InterruptedException exception){
			exception.printStackTrace();
		}
		finally{
			lock.unlock();
			System.out.println("Lock released");
		}
	}
	
	public static void main(String[] args){
		ThreadSafeBankAccount2 bankAccount = new ThreadSafeBankAccount2();
	
		Thread thread; 
		
		ArrayList <Thread> threads = new ArrayList<>();
		
		for(int i = 0; i < 5; i++){
			thread = new Thread( bankAccount.new DepositRunnable() );
			thread.start();
			threads.add(thread);
		}
		thread = new Thread( bankAccount.new WithdrawRunnable() );
		thread.start();
		threads.add(thread);
		
		
		try {
			
			Thread.sleep(6000);

		} catch (Exception e) {}
	
		try {
			
			for (Thread t: threads){
				t.join();
			}
			

		} catch (InterruptedException e) {e.printStackTrace();}
		
	}

	private class DepositRunnable implements Runnable{
		public void run(){
			
			
				try{
					for(int i = 0; i < 10; i++){
							account.deposit(100);
							Thread.sleep(100);
					
						}
					
				}
				catch(InterruptedException exception){
					Thread.currentThread().interrupt();
		            return;
				}
				
				
				System.exit(0);
				
			
			
		}
	}
	
	private class WithdrawRunnable implements Runnable{
		public void run(){
		
			
				try{
					for(int i = 0; i < 10; i++){
							account.withdraw(100);
							Thread.sleep(100);
							
						}
				
						
				}
				catch(InterruptedException exception){
					Thread.currentThread().interrupt();
		            return;
				}
	
			
		}
	}
}
