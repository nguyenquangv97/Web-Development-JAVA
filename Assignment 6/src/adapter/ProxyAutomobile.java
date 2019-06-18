package adapter;

import model.*;
import java.util.*;
import client.DefaultSocketClient;
import server.*;
import scale.EditOptions;
import util.*;
import exception.*;

public abstract class ProxyAutomobile implements FixInterface {
	// this class will contain all the implementation
	// of any method declared in the interface.
	private static Automobile car;
	private int exceptionTicket = 0;
	private static AutoLinkedHashMap<Automobile> autoLinkedHashMap = new AutoLinkedHashMap<Automobile>(); 

	// method for server
	
	public void serve(int port) {
		new DefaultServerSocket(port).start();
	}
	// method for client
	public void startClient(String strHost, int port) {
		new DefaultSocketClient(strHost, port).start();
	}
	
	public void buildAuto(String fileName) {
		FileIO readFile = new FileIO();
		boolean fixed = false;
		while (!fixed) {
			ProxyAutomobile.car = readFile.buildAutoObject(fileName);
			if (ProxyAutomobile.car == null) {
				fixed = false;
				System.out.println("Given File is empty, Error Code # 2");
				this.exceptionTicket = 2;
				fileName = fixError();
			} else {
				fixed = true;
			}
		}
		autoLinkedHashMap.insert(ProxyAutomobile.car);
	}
	
	// Build auto with object and type parameter
	// if the type is 1, the user will pass in text file for the object
	// and if the type is 2, the user will pass in the properties file 
	
	public void buildAuto(Object obj, int type) {
		FileIO readFile = new FileIO();
		switch(type) {
		case 1: // text file
			ProxyAutomobile.car = readFile.buildAutoFromTextFile(obj);	
			break;
		case 2: // properties file
			ProxyAutomobile.car = readFile.buildAutoFromPropertiesFile(obj);
		}
		autoLinkedHashMap.insert(ProxyAutomobile.car);
	}

	public void printAuto(String modelName) { // model name could be the name of the car, first line of the text file
		if (modelName.contentEquals(car.getName())) {
			System.out.println(car.toString());
		}
	}

	// display AutoLinkedHashMap
	public void displayAutoLinkedHashMap(){
		ProxyAutomobile.autoLinkedHashMap.display();
	}

	public void printValueGivenKey(String key){
		ProxyAutomobile.autoLinkedHashMap.printValueGivenKey(key);
	}
	// get all models
	public String getAllModels() {
		return ProxyAutomobile.autoLinkedHashMap.getAllModels();
	}

	// delete AutoLinkedHashMap
	public void deleteAutoLinkedHashMap(String key){
		ProxyAutomobile.autoLinkedHashMap.delete(key);
	}

	public void insertAutoLinkedHashMap(Automobile car){
		ProxyAutomobile.autoLinkedHashMap.insert(car);
		// every time we insert anything to the linkedHashMap we set this car to be
		// the one that gets inserted.
		// this is easier for modification using the Choice interface
		ProxyAutomobile.car = car;
	}

	// get auto given key from LHS
    public Automobile getValueGivenKey(String key) {
    	return ProxyAutomobile.autoLinkedHashMap.getValueGivenKey(key);
    }

	// ---------------- Update ----------------

	public void updateOptionSetName(String modelName, String optionSetName, String newName) {
		ProxyAutomobile.car.updateOptionSet(modelName, optionSetName, newName);
	}

	public void updateOptionPrice(String modelName, String optionSetName, String optionName, float newPrice) {
		ProxyAutomobile.car.updateOption(modelName, optionSetName, optionName, newPrice);
	}

	// ---------- choice interface ----------
	
	public void displayChoices(int indexOpSet) {
		car.printOptionSet(indexOpSet);
	}
	
	public void setOptionChoice(String setName, String optionName) {
		ProxyAutomobile.car.setOptionChoice(setName, optionName);
	}
	public float getOptionChoicePrice(String setName) {
		return ProxyAutomobile.car.getOptionChoicePrice(setName);
	}
	
	public String getOptionChoice(String setName) {
		return ProxyAutomobile.car.getOptionChoice(setName);
	}
	public float getTotalPrice() {
		return ProxyAutomobile.car.getTotalPrice();
	}

	@Override
	public String fixError() {
		Fix1to100 fix1to100 = new Fix1to100();
		switch (this.exceptionTicket) {
		case 2:
			return fix1to100.fix2();
		}
		return "";
	}

	// operations 
	// everytime i want to perform an operation, it spawn a new thread and have that thread do the work 
	public void operation(int operationNumber, int threadNumber, String key, String newData) {
		EditOptions editOption = new EditOptions(operationNumber, threadNumber, key, newData); 
		Thread operation = new Thread(editOption);
		operation.setName(Integer.toString(threadNumber));
		operation.start();
		System.out.println("Thread " + threadNumber + " started!");	
	}

}
