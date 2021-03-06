package edu.umb.cs.cs681;

public class Test {
	
	
	public static void main(String[] args){
		
		Thread t1 = new Thread(()->{Singleton.getInstance();}, "Thread #1");
		t1.start();
		
		Thread t2 = new Thread(()->{Singleton.getInstance();},"Thread #2");
		t2.start();
		
		Thread t3 = new Thread(()->{Singleton.getInstance();},"Thread #3");
		t3.start();
		
		try {
			
			t1.join();
			t2.join();
			t3.join();
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println(Singleton.getInstances());
		
	}

}
