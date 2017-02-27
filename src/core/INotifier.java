/**
 * 
 */
package core;

/**
 * Specifies the behaviour of a class that will alert others of a specified event.
 * The Publish should maintain a list of listeners and notify each at the required time.
 * 
 * @author Lawrence
 */
public interface INotifier {

	/**
	 * This allows IListeners to register themselves to listen to the object.
	 * 
	 * @param listener - The listener to register.
	 */
	public void registerListener(IListener listener);

	/**
	 * Alerts the listeners that an update has occurred.
	 * 
	 * @param element - Any data necessary to perform the update.
	 */
	public void notifyListeners(Object element);
}
