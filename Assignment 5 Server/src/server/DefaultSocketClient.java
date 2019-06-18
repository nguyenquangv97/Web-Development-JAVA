package server;

import java.net.*;
import java.io.*;
import adapter.Debuggable; 

// the socket acts as a helper to the socket received by the client in the server
public class DefaultSocketClient extends Thread implements Debuggable {
	// properties
	private ObjectOutputStream out; 	
	private ObjectInputStream in;
	private Socket clientSocket;
	private BuildCarModelOptions protocol;
	
	// constructor; 
	public DefaultSocketClient(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}
	
	// instance methods
	// handle connection
	// create the streams
	public void handleConnection(Socket sock) {
		if(DEBUG) 
			System.out.println("Creating server object streams ... ");
		try {
			out = new ObjectOutputStream(sock.getOutputStream());
			in = new ObjectInputStream(sock.getInputStream());
		} catch (IOException e) {
			System.err.println("Error creating server object streams ... ");
			System.exit(1);
		}
		// send the menu to the client;
		protocol = new BuildCarModelOptions();
		String menu = 
				"\nEnter 1 to upload a new Automobile\n"
				+ "Enter 2 to configure an Automobile\n"
				+ "Enter 0 to terminate connection\n";
		try {
			do {
				if(DEBUG)
					System.out.println("Sending client interaction choices ... ");
				sendOutput(menu);
				if(DEBUG) 
					System.out.println("Reading client request ... ");
				// received an integer from the client for the choice for the menu
				int request = Integer.parseInt(in.readObject().toString());
				if(DEBUG) 
					System.out.println(request);
				if(request == 0)
					break;
				if(DEBUG)
					System.out.println("Sending client request follow-up ... ");
				// inside BuildCarModelOptions - setRequest returns the prompt to the user 
				// and use it to prompt the client.
				sendOutput(protocol.setRequest(request));
				// if the choice is 1 or 2, handle the client's request.
				if(request >= 1 && request <= 2)
					handleInput(request);
			} while(in.readObject() != null);
			
			if(DEBUG)
				System.out.println("Closing server input stream for client " + sock.getInetAddress() + " ... ");
			// don't want to terminate the server, just close the input from client
			in.close();
		}
		catch(IOException e) {
			System.err.println("Error handling client connection ... ");
			System.exit(1);
		}
		catch(ClassNotFoundException e) {
			System.err.println("Error in uploaded object, object may be corrupted ... ");
			System.exit(1);
		}
	}
	// write the object to the user. 
	public void sendOutput(Object obj) {
		try {
			out.writeObject(obj);
		} catch(IOException e) {
			System.err.println("Error returning output to client ... ");
			System.exit(1);
		}
	}
	
	// if the user selected 1 or 2
	// handle their request.
	public void handleInput(int request) {
		Object fromClient = null;
		Object toClient = null; 
		
		try {
			switch(request) {
				case 1: // Client request to build Automobile
					if(DEBUG)
						System.out.println("Waiting for client to upload file ... ");
					fromClient = in.readObject();
					if(DEBUG) {
						System.out.println(fromClient);
						System.out.println("Adding new Automobile to database ... ");
					}
					toClient = protocol.processRequest(fromClient);
					sendOutput(toClient);
					break;
				case 2: // Client request to configure Automobile
					if(DEBUG)
						System.out.println("Waiting for client to select Automobile ... ");
					fromClient = in.readObject().toString();
					if(DEBUG)
						System.out.println("Sending Automobile Object to client ... ");
					toClient = protocol.processRequest(fromClient);
					sendOutput(toClient);
					break;
				default: // Invalid requests
					;
					
			}
		} catch(ClassNotFoundException e) {
			System.err.println("Error in uploaded object, file may be corrupted ... ");
			System.exit(1);
		} catch(IOException e) {
			System.err.println("Error in retrieving object from client ... ");
			System.exit(1);
		}
	}
	
	
	@Override
	// we want to have each thead to handle each each client
	// this part in the server is multithreaded.
	public void run() {
		handleConnection(this.clientSocket);

		//Close client output stream
		if (DEBUG)
			System.out.println("Closing server output stream for client " + this.clientSocket.getInetAddress() + " ... ");
		try {
			out.close();
		}
		catch (IOException e) {
			System.err.println("Error closing server output stream for client " + this.clientSocket.getInetAddress() + " ... ");
		}

		//Close client socket
		if (DEBUG)
			System.out.println("Closing client socket " + this.clientSocket.getInetAddress() + " ... ");
		try {
			this.clientSocket.close();
		}
		catch (IOException e) {
			System.err.println("Error closing client socket " + this.clientSocket.getInetAddress() + " ... ");
		}
	}
}
