package core;

/**
* Specifies the behaviour of a class that will change upon a given event.
* The Listener should register itself to an INotifier class to receive updates 
* from it at specified times.
*
* @author Lawrence
*/
public interface IListener {

	/**
	 * This is called by the registered notifiers when a change has occurred
	 * and should perform the necessary update actions.
	 * 
	 * @param element - The data necessary to perform the update with.
	 */
	public void update(Object element);
}
