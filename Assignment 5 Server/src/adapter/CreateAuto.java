package adapter;
import model.Automobile;

public interface CreateAuto {
	/*
	 * Given a text file name a method called BuildAuto can be written to build and
	 * instance of Automobile. This method does not have to return the Auto
	 * instance.
	 */
	public void buildAuto(String fileName);
	
	public void buildAuto(Object obj, int type);


	// this function searches and prints the properties of a given Automodel
	public void printAuto(String modelName);

	public void displayAutoLinkedHashMap();

	public void deleteAutoLinkedHashMap(String key);

	public void insertAutoLinkedHashMap(Automobile newCar);

    public void printValueGivenKey(String key);
    
    public Automobile getValueGivenKey(String Key);
}
