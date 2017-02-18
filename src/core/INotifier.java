/**
 * 
 */
package core;

/**
 * @author Lawrence
 *
 */
public interface INotifier {

	public void registerListener(IListener listener);

	public void notifyListeners(Object element);
}
