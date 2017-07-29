package edu.umb.cs.cs681;

public class HelloWorldTest2{

	
	public static void main(String[] args){
		GreetingRunnable runnable1 = new GreetingRunnable("Hello World");
		GreetingRunnable runnable2 = new GreetingRunnable("Goodbye World");
		
		
		//Thread thread1 = new Thread(runnable1);	
		//Thread thread2 = new Thread(runnable2);	
		//thread1.start();
		//thread2.start();
		
		runnable1.run();
		runnable2.run();
	}
	
	
}
