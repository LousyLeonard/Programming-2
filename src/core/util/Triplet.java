package core.util;

/**
*
* @author Lawrence
*/
public class Triplet<T1, T2, T3> {

	private T1 first;
	private T2 second;
	private T3 third;
	
	public Triplet(T1 first, T2 second, T3 third) {
		this.first = first;
		this.second = second;
		this.third = third;
	}
	
	public T1 getFirst() {
		return first;
	}

	public T2 getSecond() {
		return second;
	}
	
	public T3 getThird() {
		return third;
	}
	
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
