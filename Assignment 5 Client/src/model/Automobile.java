package model;
import java.io.*;
import java.util.*;

public class Automobile implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private float basePrice;

	// new features
	private String make;
	private String model;
	private int year;
	private ArrayList<OptionSet> opset;
	private ArrayList<Option> choices;

	// ---------- constructors ----------
	public Automobile(String name, String make, String model, int year, float basePrice, int size) {
		this.name = name;
		this.make = make;
		this.model = model;
		this.year = year;
		this.basePrice = basePrice;
		this.opset = new ArrayList<OptionSet>(size);
		this.choices = new ArrayList<Option>(size);

	}

	public Automobile(String name, String make, String model, int year, float basePrice) {
		this.name = name;
		this.make = make;
		this.model = model;
		this.year = year;
		this.basePrice = basePrice;
		this.opset = null;
	}

	// ---------- getters ----------

	// get choices ArrayList
	public ArrayList<Option> getChoice() {
		return this.choices;
	}

	// get the name of the automotive
	public String getName() {
		return this.name;
	}

	// get the make of the automotive
	public String getMake() {
		return this.make;
	}

	// get the model of the automotive
	public String getModel() {
		return this.model;
	}
	// get the year of the automotive
	public int getYear(){
		return this.year;
	}

	// get the base price of the automobile
	public float getBasePrice() {
		return this.basePrice;
	}

	// get the Option Set of the automobile
	// public OptionSet [] getOpset() {
	// return this.opset;
	// }
	public ArrayList<OptionSet> getOpset() {
		return this.opset;
	}

	// get the the specific Option Set given an index
	public OptionSet getOptionSetByIndex(int index) {
		return this.opset.get(index);
	}

	// get the name of the specific Option Set given an index
	public String getOptionSetByIndexName(int index) {
		return this.opset.get(index).getName();
	}

	// get the number of options for the specific Option Set given an index
	public int getNumberOfOptionsGivenIndex(int index) {
		return this.opset.get(index).getOpt().size();
	}

	// get Option choice
	public String getOptionChoice(String setName) {
		for(int i = 0; i < this.opset.size(); i++){
			if (this.opset.get(i).getName().contentEquals(setName)){
				if(this.choices.get(i) != null){
					return this.choices.get(i).getName();
				}
				else {
					return "undefined"; // meaning the user haven't or chosen an option for this option set
				}
			}
		}
		return "";
	}
	
	public String getChoiceName(int indexOptionSet) {
		return this.choices.get(indexOptionSet).getName();
	}
	
	public float getChoicePrice(int indexOptionSet) {
		return this.choices.get(indexOptionSet).getPrice();
	}
	

	// get Option Choice price
	public float getOptionChoicePrice(String setName) {
		for(int i = 0; i < this.opset.size(); i++){
			if (this.opset.get(i).getName().contentEquals(setName)){
				if(this.choices.get(i) != null){
					return this.choices.get(i).getPrice();
				}
				else {
					return 0; // meaning the user haven't or chosen an option for this option set
				}
			}
		}
		return 0;
	}

	// get total price
	public float getTotalPrice() {
		float total = 0;
		for (Option option : choices) {
			if(option != null) {
				total += option.getPrice();
			}
		}
		total += basePrice;
		return total;
	}

	// ---------- setters ----------
	public void createOptionSet(int size) {
		ArrayList<OptionSet> temp = new ArrayList<OptionSet>(size);
		this.opset = temp;
		this.choices = new ArrayList<Option>(size);
	}

	public void setMake(String make) {
		this.make = make;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setBasePrice(float basePrice) {
		this.basePrice = basePrice;
	}

	public void setOpset(String name, int index) {
		OptionSet temp = new OptionSet(name);
		this.opset.add(index, temp);
	}

	public void setOptionChoice(String setName, String optionName) {
		for(int i = 0; i < this.opset.size(); i++){	// check set name
			if(opset.get(i).getName().contentEquals(setName)){
				this.opset.get(i).setOptionChoice(optionName);	
				this.choices.add(i, this.opset.get(i).getOptionChoice());
				return;
			}
		}
		// if not found the optionSet name
		System.out.println("Cannot find the " + optionName + " in " + setName);
		this.choices.add(null);

	}

	// this method receive the number of options for each of the option set
	// then it creates an array of options base on that number of options
	// then it sets that newly populated array into the correct OptionSet
	public void setNumberOfOptions(int index, int numOptions) {
		ArrayList<Option> opt = new ArrayList<Option>(numOptions); // allocating an empty array of a fixed size options
		this.opset.get(index).setOpt(opt);
	}

	public void setOption(int indexOptionSet, int indexOption, String name, float price) {
		this.opset.get(indexOptionSet).getOpt().add(indexOption, new Option(name, price));
	}

	// ---------- find ----------
	// find the option set with the given name
	public int findOptionSetWithName(String targetName) { // return the index if found
		for (int i = 0; i < this.opset.size(); i++) {
			if (!isNullOptionSet(i)) {
				if (this.opset.get(i).getName().contentEquals(targetName)) { // if found
					return i;
				}
			}
		}
		return -1;
	}

	// find the option with the given index of optionSet and targetName
	public int findOptionWithName(int indexOptionSet, String targetName) {
		if (!isNullOptionSet(indexOptionSet)) {
			ArrayList<Option> options = this.opset.get(indexOptionSet).getOpt();
			for (int i = 0; i < options.size(); i++) {
				if (options.get(i) != null) {
					if (options.get(i).getName().contentEquals(targetName)) { // if found
						// System.out.println(targetName + " found at index: " + i);
						return i; // return the index
					}
				}
			}
		}
		return -1;// return -1 if not found
	}

	// ---------- delete ----------
	// delete an option with the given Option Set Index and targetOption (name)
	public boolean deleteOption(int optionSetIndex, String targetOption) {
		if (isNullOptionSet(optionSetIndex))
			return false;
		else {
			ArrayList<Option> options = this.opset.get(optionSetIndex).getOpt();
			for (int i = 0; i < options.size(); i++) {
				if (!isNullOption(optionSetIndex, i)) {
					if (options.get(i).getName().contentEquals(targetOption)) {
						options.remove(i);
						return true; // if able to find and delete, return true
					}
				}
			}
		}
		return false; // if the target is not found or has already been deleted, return false
	}

	// delete an option set with the given targetOption (name)
	public boolean deleteOptionSet(String targetOptionSet) {
		for (int i = 0; i < this.opset.size(); i++) {
			if (!isNullOptionSet(i)) {
				if (this.opset.get(i).getName().contentEquals(targetOptionSet)) {
					this.opset.remove(i);
					return true;
				}
			}
		}
		return false;
	}
	// ---------- update ----------

	// update name and price
	public boolean updateOption(int indexOptionSet, int indexOption, String newName, float newPrice) {
		if (!isNullOptionSet(indexOptionSet)) {
			if (!isNullOption(indexOptionSet, indexOption)) {
				// this.opset[indexOptionSet].getOpt()[indexOption].setProperties(newName,
				// newPrice);
				this.opset.get(indexOptionSet).getOpt().get(indexOption).setProperties(newName, newPrice);
				return true;
			}
		}
		return false;
	}

	// update Option's name
	public boolean updateOption(int indexOptionSet, int indexOption, String newName) {
		if (isNullOptionSet(indexOptionSet)) {
			if (isNullOption(indexOptionSet, indexOption)) {
				this.opset.get(indexOptionSet).getOpt().get(indexOption).setName(newName);
				return true;
			}
		}
		return false;
	}

	// update option price
	public boolean updateOption(String modelName, String optionSetName, String optionName, float newPrice) {
		if (!this.getName().contentEquals(modelName))
			return false;
		else {
			for (int i = 0; i < this.opset.size(); i++) {
				if (this.opset.get(i).getName().contentEquals(optionSetName)) {
					for (int j = 0; i < this.opset.get(i).getOpt().size(); i++) {
						if (this.opset.get(i).getOpt().get(j).getName().contentEquals(optionName)) {
							this.opset.get(i).getOpt().get(j).setPrice(newPrice);
							return true;
						}
					}
					return false;
				}
			}
			return false;
		}
	}

	// update Option's price
	public boolean updateOption(int indexOptionSet, int indexOption, float newPrice) {
		if (isNullOptionSet(indexOptionSet)) {
			if (isNullOption(indexOptionSet, indexOption)) {
				this.opset.get(indexOptionSet).getOpt().get(indexOption).setPrice(newPrice);
				return true;
			}
		}
		return false;
	}

	// update optionSetName
	public boolean updateOptionSet(String modelName, String optionSetName, String newName) {
		if (!this.name.contentEquals(modelName))
			return false;
		else {
			for (int i = 0; i < this.opset.size(); i++) {
				if (opset.get(i).getName().contentEquals(optionSetName)) {
					// if able to find the name of optionSet
					opset.get(i).setName(newName);
					return true;
				}
			}
			return false;
		}
	}

	// update OptionSet's name
	public boolean updateOptionSet(int indexOptionSet, String newOptionSetName) {
		if (!isNullOptionSet(indexOptionSet)) {
			this.opset.get(indexOptionSet).setName(newOptionSetName);
			return true;
		}
		return false;
	}

	// check null
	private boolean isNullOptionSet(int indexOptionSet) {
		return (this.opset.get(indexOptionSet) == null) ? true : false;
	}

	private boolean isNullOption(int indexOptionSet, int indexOption) {
		return (this.opset.get(indexOptionSet).getOpt().get(indexOption) == null) ? true : false;
	}

	// ---------- print ----------
	@Override
	public String toString() {
		StringBuffer sbuff = new StringBuffer("Printing Vechical Information");
		sbuff.append('\n');
		sbuff.append("Name: ");
		sbuff.append(this.name);
		sbuff.append("\n");
		sbuff.append("Base price: ");
		sbuff.append(this.basePrice);
		for (OptionSet opset : this.opset) {
			if (opset != null) {
				sbuff.append('\n');
				sbuff.append(opset.printOptionSet());
			}
		}
		return sbuff.toString();
	}
	
	public void printOptionSet(int index) {
		System.out.println(this.opset.get(index).getName());
		System.out.println("-----------------");
		for(Option opt : this.opset.get(index).getOpt()) {
			System.out.print(opt.printOption());
		}
	}
	
	public String getOpsetName(int index) {
		return this.opset.get(index).getName();
	}
	
	public String [] getEachOptionProps(int index) {
		int i = 0;
		String [] arrOption = new String[this.getOpset().get(index).getOpt().size()];
		for(Option opt : this.opset.get(index).getOpt())
		{
			arrOption[i] = new String(opt.getName() + ", $" + opt.getPrice());
			i++;
		}
		return arrOption;
	}
	
}
