package server;

import adapter.*;
import util.FileIO;
import model.*;
import java.util.*;

public class BuildCarModelOptions extends ProxyAutomobile {
	// properties

	private static final int WAITING = 0;
	private static final int REQUEST_BUILD_AUTO = 1;
	private static final int REQUEST_CONFIGURE_AUTO = 2;

	private int state = WAITING;

	// constructor

	public BuildCarModelOptions() {

	}
	// instance methods
	// process the request of the client according to the state
	public Object processRequest(Object obj) {
		Object toClient = null;
		CreateAuto createAuto = new BuildAuto();
	
		boolean fromTextFile = false;
		boolean fromPropsFile = false;

		if(state == REQUEST_BUILD_AUTO) {
			try {
				StringBuffer sbuff = (StringBuffer) obj;
				fromTextFile = true;
			} catch(ClassCastException e) {
				fromPropsFile = true; 
			}
			if(fromTextFile) 
				createAuto.buildAuto(obj, 1);
			if(fromPropsFile) 
				createAuto.buildAuto(obj, 2);

			toClient = "Automobile object successfully added to database\n"
					+ "Press any key to return to main menu";
		}
		else if(state == REQUEST_CONFIGURE_AUTO) {
			// configure auto
			String key = obj.toString();
			// display the choices of car to the client
			toClient = super.getValueGivenKey(key);
		}
		else { 

		}
		this.state = WAITING; 

		return toClient;
	}
	// set the state based on the client choice
	public String setRequest(int i) {
		String output = null;
		if (i == 1) {
			this.state = REQUEST_BUILD_AUTO;
			output = "Upload a file to create an Automobile";
		} else if (i == 2) {
			this.state = REQUEST_CONFIGURE_AUTO;
			output = /*"Select an Automobile from the following list to configure: \n" + */super.getAllModels();
		} else {
			output = "Invalid request";
		}
		return output;
	}
}
