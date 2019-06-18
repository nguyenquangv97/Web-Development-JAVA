package adapter;

public interface UpdateAuto {
	
	public void updateOptionSetName(String modelName, String optionSetname, String newName);
	
	
	public void updateOptionPrice(String modelName, String optionSetName, String optionName, float newPrice);
}
