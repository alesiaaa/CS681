package edu.umb.cs.cs681;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.IOException;

public class BankServer{
	private static final int BANKPORT = 8888;
	private BankAccount account;
	
	public BankServer(){
		account = new BankAccount();
	}
	
	public void init(){
		try{
			try(ServerSocket serverSocket = new ServerSocket(BANKPORT)) {
				
				System.out.println("Socket created.");
			
				while(true){
					
					System.out.println( "Listening for a connection on the local port " +
										serverSocket.getLocalPort() + "..." );
					
					Socket socket = serverSocket.accept();
					
					System.out.println( "\nA connection established with the remote port " + 
										socket.getPort() + " at " +
										socket.getInetAddress().toString() );
					
					
					// custom runnable class
					new Thread(new BankRunnable(socket)).start();
				}
			}
		}
		catch(IOException exception){}
	}


	protected void accessAccount( String command, Scanner in, PrintWriter out ){
		double amount;
		if( command.equals("DEPOSIT") ){
			amount = in.nextDouble();
			account.deposit( amount );
			System.out.println( "DEPOSIT: Current balance: " + account.getBalance() );
			out.println( "DEPOSIT Done. Current balance: " + account.getBalance() );
		}
		else if( command.equals("WITHDRAW") ){
			amount = in.nextDouble();
			account.withdraw( amount );
			System.out.println( "WITHDRAW: Current balance: " + account.getBalance() );
			out.println( "WITHDRAW Done. Current balance: " + account.getBalance() );
		}
		else if( command.equals("BALANCE") )
		{
			System.out.println( "BALANCE: Current balance: " + account.getBalance() );
			out.println( "BALANCE accepted. Current balance: " + account.getBalance() );
		}
		else{
			System.out.println( "Invalid Command" );
			out.println( "Invalid Command. Try another command." );
		}
		out.flush();
	}
	
	public static void main(String[] args){
		BankServer server = new BankServer();
		server.init();
	}
	
}
