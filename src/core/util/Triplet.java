package core.util;

import java.io.Serializable;

/**
* Simple Triplet class mapping together three values.
* 
* @author Lawrence
*/
public class Triplet<T1, T2, T3> implements Serializable {

	/**
	 * Appease the gods of serialisation.
	 */
	private static final long serialVersionUID = -6567190356943785679L;
	
	private T1 first;
	private T2 second;
	private T3 third;
	
	/**
	 * CONSTRUCTOR
	 * 
	 * @param first - The first value to map.
	 * @param second - The second value to map.
	 * @param third - The third value to map.
	 */
	public Triplet(T1 first, T2 second, T3 third) {
		this.first = first;
		this.second = second;
		this.third = third;
	}
	
	/**
	 * Get the first mapped value.
	 * 
	 * @return the first value.
	 */
	public T1 getFirst() {
		return first;
	}
	
	/**
	 * Get the second mapped value.
	 * 
	 * @return the second value.
	 */
	public T2 getSecond() {
		return second;
	}
	
	/**
	 * Get the third mapped value.
	 * 
	 * @return the third value.
	 */
	public T3 getThird() {
		return third;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
	    if (obj == null) {
	        return false;
	    }
	    
	    final Triplet<T1, T2, T3> other = (Triplet<T1, T2, T3>) obj;
	    if ((this.first == null) ? (other.first != null) : !this.first.equals(other.first)) {
	        return false;
	    }
	    if ((this.second == null) ? (other.second != null) : !this.second.equals(other.second)) {
	        return false;
	    }
	    if ((this.third == null) ? (other.third != null) : !this.third.equals(other.third)) {
	        return false;
	    }
	    
	    return true;
	}
}
