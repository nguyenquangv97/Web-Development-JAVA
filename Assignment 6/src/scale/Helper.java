package scale;
import model.*;

public class Helper {
	// this method updates the vechical's name 
	public void helper1(Automobile car, String newData) {
		car.setName(newData); 
	}
	// this method update the first Option Set's name
	public void helper2(Automobile car, String newData) {
		car.updateOptionSet(0, newData);
	}
	// update first option of the first optionset's price 
	// this method will not be synchronized to show data corruption
	public void helper3(Automobile car, String incrementAmount) {
		car.setBasePrice(car.getBasePrice() + Integer.parseInt(incrementAmount));
	}
}
