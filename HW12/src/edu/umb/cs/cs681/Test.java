package edu.umb.cs.cs681;


public class Test {
	
	public static void main(String[] args){
		
		File newFile = new File ("newFile.txt");
	
		Editor editor = new Editor (newFile);
		AutoSaver autoSaver = new AutoSaver (newFile);
		
		Thread t1;
		Thread t2;
		
		t1 = new Thread(editor, "Thread running editor");
		t2 = new Thread(autoSaver, "Thread running autosaver");
		     
		t1.start();
		t2.start();
		 
		 try {
			//main thread goes to sleep
			Thread.sleep(7000);
				 
		} catch (Exception e) {
			
			e.printStackTrace();
		} finally {
			
			editor.setDone();
			autoSaver.setDone();
		}
		 
		 try {
			 t1.join();
			 t2.join();
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
		
	
		
	
	}

}
