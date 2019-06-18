package client;

import java.net.*;
import java.io.*;
import adapter.Debuggable;
public class DefaultSocketClient extends Thread implements Debuggable {
	// properties 
	
	private ObjectOutputStream out; 
	private ObjectInputStream in; 
	private BufferedReader stdIn;
	private Socket sock;
	private String strHost;
	private int iPort;
	private CarModelOptionsIO clientFTP;
	private SelectCarOptions clientProtocol;
	
	
	// constructor
	
	public DefaultSocketClient(String strHost, int iPort) {
		this.strHost = strHost;
		this.iPort = iPort;
	}
	
	// instance method
	// establish the connection 
	// creating input and output streams
	// instantiate the file trasfer protocol and client protocol 
	public void establishConnection() {
		try {
			if(DEBUG)
				System.out.println("Connecting to host ... ");
			this.sock = new Socket(strHost, iPort);
			
			if(DEBUG)
				System.out.println("Connected to host, creating object streams ... ");
			out = new ObjectOutputStream(sock.getOutputStream());
			in = new ObjectInputStream(sock.getInputStream());
			stdIn = new BufferedReader(new InputStreamReader(System.in));
			
			clientFTP  = new CarModelOptionsIO();
			clientProtocol = new SelectCarOptions();
		} catch(IOException e) {
			System.err.println("Error obtaining I/O for connection to host ... ");
			System.exit(1);
		}
	}
	// handle the connection
	// print out whatever object received from the server.
	// if the object from the server is automobile, modify it on the client side. 
	// the method also send files to the server.
	public void handleConnection() {
		Object fromServer, toServer = null;
		
		try {
			if(DEBUG)
				System.out.println("Communicating with host ... ");
			// while there is still data from the server.
			while((fromServer = in.readObject()) != null) {
				if(DEBUG)
					System.out.println("Received server response ... ");
				// print out whatever received from the server
				System.out.println(fromServer.toString());
				
				// if the object from the server is an automobile
				// then configure that automobile object
				if(clientProtocol.isAutomobile(fromServer))
					clientProtocol.configureAuto(fromServer);
				
				// now we are responding to the server
				System.out.println("Response to server: ");
				toServer = stdIn.readLine();
				
				if(toServer.toString().contains(".prop")) {
					// if the string has .prop, then it is the properties file
					toServer = clientFTP.loadPropsFile(toServer.toString());
				}
				if(toServer.toString().contains(".txt")) {
					toServer = clientFTP.loadTextFile(toServer.toString());
				}
				if(DEBUG) 
					System.out.println("Sending " + toServer + " to server ... ");
				sendOutput(toServer);
				System.out.println("sent!");
				
				if(toServer.equals("0")) {
					break;
				}
			}
			if(DEBUG)
				System.out.println("Closing client input stream ... ");
			in.close();
		} 
		catch(ClassNotFoundException e) {
			System.err.println("Error in downloaded object, object may be corrupted ... ");
			System.exit(1);
		}
		catch(IOException e) {
			System.err.println("Error in I/O stream ... ");
			System.exit(1);
		}
	}
	
	public void sendOutput(Object obj) {
		try {
			out.writeObject(obj);
		} catch(IOException e) {
			System.err.println("Error in I/O stream while sending object to host ... ");
			System.exit(1);
		}
	}
	// the method will execute establish adn handle connection. 
	@Override
	public void run() {
		establishConnection();
		handleConnection();
		try {
			if (DEBUG)
				System.out.println("Closing client output stream ... ");
			out.close();

			if (DEBUG)
				System.out.println("Closing client console input stream ... ");
			stdIn.close();

			if (DEBUG)
				System.out.println("Closing client socket ... ");
			sock.close();
		}
		catch (IOException e) {
			System.err.println("Error ending client connection ... ");
			System.exit(1);
		}
	}
}

