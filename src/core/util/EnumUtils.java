package core.util;

import java.util.ArrayList;

/**
*
* @author Lawrence
*/
public abstract class EnumUtils {
	
	public static <E extends Enum<E>> ArrayList<Object> enumValues(Class<E> enumData) {
		ArrayList<Object> values = new ArrayList<Object>();
		for (Enum<E> enumVal: enumData.getEnumConstants()) {  
            values.add(enumVal);
        } 
		return values;
    }
}
