package exception;
import java.util.Scanner;
public class Fix1to100 {
	
	public String fix1() {
		System.out.println("fixing file does not exist!");
		Scanner reader = new Scanner(System.in);
		System.out.println("Enter a different name: ");
		return reader.nextLine();
	}
	public String fix2() {
		System.out.println("fixing file is empty!");
		Scanner reader = new Scanner(System.in);
		System.out.println("Enter a different name: ");
		return reader.nextLine();
	}
	

}
