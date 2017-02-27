package core.util;

import java.util.ArrayList;

/**
* Enumeration utilities.
* 
* @author Lawrence
*/
public abstract class EnumUtils {
	
	/**
	 * Returns an ArrayList of values representing the values of the enumeration supplied.
	 * 
	 * @param enumData - The enumeration supplied.
	 * @return a ArrayList of values representing the given enumeration.
	 */
	public static <E extends Enum<E>> ArrayList<Object> enumValues(Class<E> enumData) {
		ArrayList<Object> values = new ArrayList<Object>();
		for (Enum<E> enumVal: enumData.getEnumConstants()) {  
            values.add(enumVal);
        } 
		return values;
    }
}
