package driver;
import client.*;
import adapter.*;
public class ClientDriver {

	public static void main(String[] args) {
		// this driver is to run the client
		// creating a client interface 
		AutoClientable client = new BuildAuto();
		// start the client 
		client.startClient("192.168.1.78", 4500);
	}
}
