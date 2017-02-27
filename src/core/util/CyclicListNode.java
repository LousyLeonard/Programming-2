/**
 * 
 */
package core.util;

/**
 * A simple Cyclic List Node structure to creating circular lists.
 * 
 * @author Lawrence
 */
public class CyclicListNode<T> {
	
	private T element;
	private CyclicListNode<T> next;
	private CyclicListNode<T> prev;

	/**
	 * CONSTRUCTOR
	 * 
	 * @param element - The element at this node.
	 */
	public CyclicListNode(T element) {
		this.element = element;
	}
	
	/**
	 * Sets the next node in the sequence.
	 * 
	 * @param next - The next node.
	 */
	public void setNext(CyclicListNode<T> next) {
		this.next = next;
		next.prev = this;
	}

	/**
	 * Gets the next node in the sequence.
	 * 
	 * @return CyclicLinkNode the next node.
	 */
	public CyclicListNode<T> getNext() {
		return this.next;
	}
	
	/**
	 * Gets the previous node in the sequence.
	 * 
	 * @return CyclicLinkNode the previous node.
	 */
	public CyclicListNode<T> getPrev() {
		return this.prev;
	}
	
	/**
	 * Gets the value at this node.
	 * 
	 * @return the value at this node.
	 */
	public T getElement() {
		return element;
	}
}
