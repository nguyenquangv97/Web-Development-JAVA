package util;

import java.util.*;
import exception.*;
import java.io.*;
import model.*;

public class FileIO {
	public Automobile buildAutoObject(String fileName) {
		Automobile a1 = null;
		boolean fixed = false;
		while (fixed == false) {
			try {
				BufferedReader buff = createFile(fileName);
				fixed = true; // if get to here, able to recover filenotfoundException
				a1 = populateObject(buff, a1);
				fixed = true;
			} catch (AutoException ex) {
				fixed = false;
				fileName = ex.fixError();
			}
		}
		return a1;
	}

	public Automobile populateObject(BufferedReader buff, Automobile a1) throws AutoException {
		String line;
		int counter = 0;
		int indexOptionSet = 0; // this is the index for the OptionSet array everytime we detect a '#' in the
		// text file, we know we're at a new OptionSet
		int indexOption = 0;
		int numOptions = 0;
		boolean fixed = false;
		try {
			line = buff.readLine();
			counter++;

			if (line == null) {
				// empty file
				buff.close();
				System.out.println("The file is empty!");
				return null;
			} else {
				// parse the line
				while (fixed == false) {
					try {
						a1 = buildVechical(line);
						fixed = true;
					} catch (AutoException ex) {
						line = ex.fixError();
					}
				}

				boolean eof = false;
				do {
					line = buff.readLine();
					counter++;
					if (line == null)
						eof = true;
					else {
						if (counter == 2) {
							fixed = false;
							while (fixed == false) {
								try {
									if (line.length() == 0)
										throw new AutoException(102, "missing option set size");
									fixed = true;
								} catch (AutoException ex) {
									line = ex.fixError();
								}
							}
							a1.createOptionSet(Integer.parseInt(line));
						}
						if (counter >= 3) {
							// reading line 2 forward
							if (line.charAt(0) == '#') { // if there is # then we know its a OptionSet
								a1.setOpset(line.substring(1), indexOptionSet);
								// the next line will always be number of options
								line = buff.readLine();
								fixed = false;
								while (fixed == false) {
									try {
										if (line.length() == 0) {
											throw new AutoException(103, "missing option size");
										}
										numOptions = Integer.parseInt(line);
										fixed = true;
									} catch (AutoException ex) {
										line = ex.fixError();
									}
								}
								counter++;
								a1.setNumberOfOptions(indexOptionSet, numOptions);
								indexOptionSet++;
								// reset indexOption
								indexOption = 0;
							} else {
								populateOption(line, a1, indexOptionSet - 1, indexOption);
								indexOption++;
							}
						}
					}
				} while (!eof);
				buff.close();
			}
			return a1;
		} catch (IOException ex) {
			throw new AutoException(1, "IOException");
		}

	}

	public Automobile buildVechical(String line) throws AutoException {
		StringTokenizer stk = new StringTokenizer(line, ".");
		try {
			String make = stk.nextToken();
			String model = stk.nextToken();
			int year = Integer.parseInt(stk.nextToken());
			StringBuffer buff = new StringBuffer();
			buff.append(make).append(model);
			String name = buff.toString();
			float price = Float.parseFloat(stk.nextToken());
			return new Automobile(name, make, model, year, price);
		} catch (NoSuchElementException ex) {
			throw new AutoException(101, "make, model, year or price not found");
		}
	}

	public void populateOption(String line, Automobile a1, int indexOptionSet, int indexOption) {
		StringTokenizer stk = new StringTokenizer(line, ".");
		while (stk.hasMoreTokens()) {
			String name = stk.nextToken();
			float price = Float.parseFloat(stk.nextToken());
			a1.setOption(indexOptionSet, indexOption, name, price);
		}
	}

	public void serializeObject(Automobile car) {
		try {
			FileOutputStream fos = new FileOutputStream("SerializedAutomotive.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(car);
			oos.close();
		} catch (IOException ex) {
			System.out.println("ERROR: " + ex);
		}
	}

	// deserialize
	public Automobile deSerializeObject(String dataFile) { // can throw another autoexception here
		Automobile car = null;
		try {
			FileInputStream fis = new FileInputStream(dataFile);
			ObjectInputStream ois = new ObjectInputStream(fis);
			car = (Automobile) ois.readObject();
			ois.close();
		} catch (IOException | ClassNotFoundException ex) {
			System.out.println("ERROR: " + ex);
		}
		return car;
	}

	public BufferedReader createFile(String fileName) throws AutoException {
		try {
			FileReader fr = new FileReader(fileName);
			BufferedReader buff = new BufferedReader(fr);
			return buff;
		} catch (IOException ex) {
			throw new AutoException(1, "IOException");
		}
	}
	// build Automobile from properties file given the object ( properties ).
	public Automobile buildAutoFromPropertiesFile(Object obj) {
		// opening the properties file 
		Properties props = (Properties) obj;
		Automobile car = null;
		String make = props.getProperty("make");
		String model = props.getProperty("model");
		int year = Integer.parseInt(props.getProperty("year"));
		float basePrice = Float.parseFloat(props.getProperty("BasePrice"));
		int numOpSet = Integer.parseInt(props.getProperty("opset"));
		String name = make + model;
		car = new Automobile(name, make, model, year, basePrice);
		car.createOptionSet(numOpSet);
		
		for(int currentSet = 1; currentSet <= numOpSet; currentSet++)
		{
			String optionSetName = props.getProperty("set" + currentSet);
			int optionSize = Integer.parseInt(props.getProperty("size" + currentSet));
			car.setOpset(optionSetName, currentSet - 1);
			car.setNumberOfOptions(currentSet - 1, optionSize);
			for(int currentOption = 1; currentOption <= optionSize; currentOption++) {
				String optionName = props.getProperty("name" + currentSet + currentOption);
				float optionPrice = Float.parseFloat(props.getProperty("price" + currentSet + currentOption));
				car.setOption(currentSet - 1, currentOption - 1, optionName, optionPrice);
			}
		}
		return car;
	}
	// build automobile from the text file by passing in the string buffer. 
	public Automobile buildAutoFromTextFile(Object obj) {
		Automobile car = null;
		try {
			FileIO helper = new FileIO();

			String line = "";
			int indexOption = 0;
			int currentOptionSet = -1;
			StringBuffer sbuff = (StringBuffer) obj;
			// split the array, each element represent each line in the
			// text file
			String[] arr = sbuff.toString().split("\n");
			// use the method of FileIO to help build the vechical
			// using reusable code
			car = helper.buildVechical(arr[0]);
			car.createOptionSet(Integer.parseInt(arr[1]));

			for (int i = 2; i < arr.length; i++) {
				line = arr[i];
				if (line.charAt(0) == '#') { // we're at the option set
					currentOptionSet++;
					car.setOpset(line.substring(1), currentOptionSet);
					car.setNumberOfOptions(currentOptionSet, Integer.parseInt(arr[i + 1]));
					i++;
					indexOption = 0;
				} else {
					helper.populateOption(line, car, currentOptionSet, indexOption);
					indexOption++;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Error in processing the request");
		}
		return car;
	}
}
