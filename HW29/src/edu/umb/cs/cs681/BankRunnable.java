package edu.umb.cs.cs681;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class BankRunnable extends BankServer implements Runnable {
	private Socket socket;
	private Scanner in;
	private PrintWriter out;
	

	public BankRunnable(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {

		try {
			try {
				in = new Scanner( this.socket.getInputStream() );
				out = new PrintWriter( this.socket.getOutputStream() );
				System.out.println( "I/O setup done." );
			
				while(true){
					if( in.hasNext() ){
						String command = in.next();
						if( command.equals("QUIT") ){
							System.out.println( "QUIT: Connection being closed." );
							out.println( "QUIT accepted. Connection being closed." );
							out.close();
							return;
						}
						accessAccount( command, in, out );
					}
				}
				
			}	finally{
				socket.close();
				System.out.println( "A connection is closed." );
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	

}
