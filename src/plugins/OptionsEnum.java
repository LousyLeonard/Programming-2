package plugins;

public enum OptionsEnum {
    NOT_YET("Not yet"), 
    YES("Yes");
    
	private String description;
	
	private OptionsEnum(String description)
    {
    	this.description = description;
    }
	
	@Override
	public String toString()
	{
		return this.description;
	}
}
