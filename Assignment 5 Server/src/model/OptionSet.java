package model;

import java.io.*;
import java.util.*;

public class OptionSet implements Serializable {
	private ArrayList<Option> opt;
	private Option choice;
	private String name;

	// new features
	protected Option getOptionChoice() {
		return this.choice;
	}

	public void setOptionChoice(String optionName) {
		for (int i = 0; i < this.opt.size(); i++) {
			if (optionName.contentEquals(this.opt.get(i).getName())) {
				this.choice = this.opt.get(i);
				return;
			}
		}
		System.out.println("Cannot find " + optionName);
	}

	// constructor
	protected OptionSet() {
	}

	protected OptionSet(String name) {
		this.name = name;
	}

	protected OptionSet(String name, int size) {
		this.name = name;
		this.opt = new ArrayList<Option>(size);
		for (int i = 0; i < size; i++) {
			this.opt.add(i, new Option());
		}
	}

	// getters
	protected String getName() {
		return this.name;
	}

	protected ArrayList<Option> getOpt() {
		return this.opt;
	}

	// setters
	protected void setName(String name) {
		this.name = name;
	}

	protected void setOpt(ArrayList<Option> opt) {
		this.opt = opt;
	}

	protected String printOptionSet() {
		StringBuffer sbuff = new StringBuffer("---------- ");
		sbuff.append(this.name);
		sbuff.append(" ----------\n");
		for (Option option : this.opt) {
			if (option != null) {
				sbuff.append(option.printOption());
			} else {
				// sbuff.append("NULL");
			}
		}
		return sbuff.toString();
	}
}
