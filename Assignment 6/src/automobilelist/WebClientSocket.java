package automobilelist;
import java.io.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import model.Automobile;

class WebClientSocket implements adapter.Debuggable {
    Socket sock = null;
	private ObjectOutputStream out; 
	private ObjectInputStream in; 
    Object fromServer;
    Object toServer;
    InputHelper helper;


    WebClientSocket(InputHelper helper){
    	this.helper = helper;
    }
	public void establishConnection() {
		try {
			if(DEBUG)
				System.out.println("Connecting to host ... ");
			this.sock = new Socket("10.44.3.26", 4500);
			
			if(DEBUG)
				System.out.println("Connected to host, creating object streams ... ");
			out = new ObjectOutputStream(sock.getOutputStream());
			in = new ObjectInputStream(sock.getInputStream());
		
		} catch(IOException e) {
			System.err.println("Error obtaining I/O for connection to host ... ");
			System.exit(1);
		}
	}
	
	public Object getListFromServer() {
		Object fromServer, toServer = null;
		
		try {
			if(DEBUG)
				System.out.println("Communicating with host ... ");
			// while there is still data from the server.
			while((fromServer = in.readObject()) != null) {
				if(DEBUG)
					System.out.println("Received server response ... ");
				
				if(toServer == helper.getToServer())	// if toServer = 2 meaning from the last execution, we sent to server a "2" for the menu
					// during this execution, we're not going to response to the Server right away
					// we break to get the list of Auto.
					return fromServer.toString();
				
				
				if(this.isAutomobile(fromServer)) {
					return (Automobile) fromServer;
				}
				
				System.out.println("Response to server: ");
		
				toServer = helper.getToServer();	
				
				sendOutput(toServer);
				System.out.println("sent!");
								
				System.out.println(fromServer.toString());

				if(DEBUG) 
					System.out.println("Sending " + toServer + " to server ... ");
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
		return null;
	}
	public boolean isAutomobile(Object obj) {
		boolean isAutomobile = false;
		
		try {
			Automobile a1 = (Automobile) obj;
			isAutomobile = true;
		}
		catch(ClassCastException e) {
			isAutomobile = false;
		}
		return isAutomobile;
	}
	
	public void sendOutput(Object obj) {
		try {
			out.writeObject(obj);
		} catch(IOException e) {
			System.err.println("Error in I/O stream while sending object to host ... ");
			System.exit(1);
		}
	}
}

// maybe in its own file?
