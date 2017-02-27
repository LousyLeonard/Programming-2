package core.util;

import java.io.Serializable;

/**
* A Simple Pair class mapping to objects together.
* 
* @author Lawrence
*/
public class Pair<T1, T2> implements Serializable {

	/**
	 * Appease the gods of serialisation.
	 */
	private static final long serialVersionUID = -1122944190523892457L;
	
	private T1 first;
	private T2 second;
	
	/**
	 * CONSTRUCTOR
	 * 
	 * @param first - First value to map.
	 * @param second - Second value to map.
	 */
	public Pair(T1 first, T2 second) {
		this.first = first;
		this.second = second;
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
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
	    if (obj == null) {
	        return false;
	    }
	    
	    final Pair<T1, T2> other = (Pair<T1, T2>) obj;
	    if ((this.first == null) ? (other.first != null) : !this.first.equals(other.first)) {
	        return false;
	    }
	    if ((this.second == null) ? (other.second != null) : !this.second.equals(other.second)) {
	        return false;
	    }
	    
	    return true;
	}
}
