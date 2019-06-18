package exception;

import java.util.Scanner;

public class Fix101to200 {
	public String fix101() {	
		System.out.println("Fixing missing make, model, year or price (make .model .year.price):");
		Scanner reader = new Scanner(System.in);
		System.out.println("Enter the name and price: ");
		return reader.nextLine();
	}
	public String fix102() {
		System.out.println("fixing missing OptionSet size");
		Scanner reader = new Scanner(System.in);
		System.out.println("Enter the size of Option Set: ");
		return reader.nextLine();
	}
	public String fix103() {
		System.out.println("fixing missing Option size");
		System.out.println("Enter the size of Option: ");
		Scanner reader = new Scanner(System.in);
		return reader.nextLine();
	}


}
