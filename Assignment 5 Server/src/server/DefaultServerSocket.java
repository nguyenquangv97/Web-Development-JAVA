package server;

import adapter.*;
import java.io.*;
import java.net.*;

public class DefaultServerSocket extends Thread implements Debuggable{

	// Properties
	private int port; 
	private ServerSocket server; 
	
	// constructor 
	// create a new server socket on the given port
	public DefaultServerSocket(int port) {
		this.port = port;
		try {
			this.server = new ServerSocket(this.port);
			System.out.println("Server started on port: " + this.port);
		} catch(IOException e) {
			System.err.println("Could not listen on port " + this.port + " ... ");
			System.exit(1);
		}
	}
	// instance methods
	
	// the server socket will be on its own thread. but we are not 
	// starting multiple threads of the server. 
	@Override 
	public void run() {
		Socket clientSocket = null;
		while(true) {
			// Accept client
			try {
				System.out.println("Waiting for connection ... ");
				// wait until there is a connection from the client
				clientSocket = server.accept();
			} catch(IOException e) {
				System.err.println("Error establishing client connection ... ");
				System.exit(1);
			}
			
			// Handle client - server interaction
			if(DEBUG) 
				System.out.println(clientSocket.getLocalAddress());
			new DefaultSocketClient(clientSocket).start();
		}
	}
}
