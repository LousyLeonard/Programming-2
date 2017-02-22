/**
 * 
 */
package core.util;

/**
 * @author Lawrence
 *
 */
public class CyclicListNode<T> {
	
	private T element;
	private CyclicListNode<T> next;
	private CyclicListNode<T> prev;

	public CyclicListNode(T element) {
		this.element = element;
	}
	
	public void setNext(CyclicListNode next) {
		this.next = next;
		next.prev = this;
	}

	public CyclicListNode getNext() {
		return this.next;
	}
	
	public CyclicListNode getPrev() {
		return this.prev;
	}
	
	public T getElement() {
		return element;
	}
}
