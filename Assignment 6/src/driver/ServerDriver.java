package driver;
import server.*;
import adapter.*;
public class ServerDriver {

	public static void main(String[] args) {
		// create the server using the interface.
		// this driver starts the server. 
		AutoServable server = new BuildAuto();
		server.serve(4500);
	}
}
