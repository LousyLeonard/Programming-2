package plugins;

/**
* Enum representing the options available in the Authorisation Column.
* 
* @author Lawrence
*/
public enum OptionsEnum {
    NOT_YET("Not yet"), 
    YES("Yes");
    
	private String description;
	
	/**
	 * CONSTRUCTOR
	 * 
	 * @param description - The string representation of the object.
	 */
	private OptionsEnum(String description)
    {
    	this.description = description;
    }
	
	/* (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString()
	{
		return this.description;
	}
}
