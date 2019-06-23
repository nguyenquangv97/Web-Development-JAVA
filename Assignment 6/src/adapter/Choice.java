package adapter;

public interface Choice {
	public void displayChoices(int indexOpset);
	public float getOptionChoicePrice(String setName);
	public String getOptionChoice(String setName);
	public void setOptionChoice(String setName, String optionName);
	public float getTotalPrice();
	public String getChoiceName(int indexOptionSet);
	public float getChoicePrice(int indexOptionSet);
}
