/**
 * 
 */
package core.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Lawrence
 *
 */
public abstract class ArrayCastingUtils {
	
	public static <T1, T2> ArrayList<T1> convertArray(Class<T1> t1, List<T2> array) {
		ArrayList<T1> result = new ArrayList<T1>();
		
		for (T2 entry : array) {
			result.add((T1)entry);
		}
		
		return result;
	}
}
