package client;
import model.*;
import adapter.*;
import adapter.BuildAuto;
import adapter.Choice;
import java.io.*;
public class SelectCarOptions {
	// constructors
	public SelectCarOptions() {}
	// instance methods 
	// configure auto will received the automobileo object and 
	// insert it into the linkedHashmap of the user!!
	// when inserting, the auto object property of the Proxy will be set 
	// use the Choice interface to configure the car. 
	// NOTE: the configuring options is hard coded. 
	BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
	public void configureAuto(Object obj) {
		
		Automobile car = (Automobile) obj;
		CreateAuto createAuto = new BuildAuto();
		createAuto.insertAutoLinkedHashMap(car);
		Choice carChoice = new BuildAuto();
		String selected = "";
		
		System.out.println("\n ---------- Configure your automobile ----------");
		for(int i = 0; i < car.getOpset().size(); i++) {
			carChoice.displayChoices(i);
			System.out.println("Select Option: ");
			try {
				selected = stdIn.readLine();
			} catch(IOException e) {
				System.out.println("Error Selecting Option");
			}
			carChoice.setOptionChoice(car.getOptionSetByIndexName(i), selected);
			System.out.println();
		}
		
		// print out the choices after the selections.
		System.out.println("----- Printing Color Option -----");
		System.out.println(carChoice.getOptionChoice("Color"));
		System.out.println(carChoice.getOptionChoicePrice("Color"));

		System.out.println("----- Printing Wheel Option -----");
		System.out.println(carChoice.getOptionChoice("Wheel"));
		System.out.println(carChoice.getOptionChoicePrice("Wheel"));

		System.out.println("----- Printing Premium Interior Option -----");
		System.out.println(carChoice.getOptionChoice("Premium Interior"));
		System.out.println(carChoice.getOptionChoicePrice("Premium Interior"));

		System.out.println("----- Printing Autopilot Option -----");
		System.out.println(carChoice.getOptionChoice("Autopilot"));
		System.out.println(carChoice.getOptionChoicePrice("Autopilot"));

		System.out.println("----- Printing Full Self-Driving Capability Option -----");
		System.out.println(carChoice.getOptionChoice("Full Self-Driving Capability"));
		System.out.println(carChoice.getOptionChoicePrice("Full Self-Driving Capability"));

		System.out.println("----- Printing the total cost ----- ");
		System.out.println(carChoice.getTotalPrice());
	}
	// check if the object is automobile. 
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
}
