/**
 * 
 */
package core.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Array Casting Utilities.
 * 
 * @author Lawrence
 *
 */
public abstract class ArrayCastingUtils {
	
	/**
	 * Cast an array of one type to an array of another.
	 * 
	 * @param t1 - The resultant array type.
	 * @param array - The requested to be cast array type.
	 * @return An of of element that have be converted to the requested type.
	 */
	public static <T1, T2> ArrayList<T1> convertArray(Class<T1> t1, List<T2> array) {
		ArrayList<T1> result = new ArrayList<T1>();
		
		try {
			for (T2 entry : array) {
				result.add((T1)entry);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
