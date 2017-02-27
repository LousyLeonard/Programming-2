/**
 * 
 */
package plugins;

import java.io.Serializable;
import java.util.ArrayList;

import core.plugins.DoublePlugin;

/**
 * Payment column for tracking payments of Students.
 * 
 * @author Lawrence
 */
public class PaymentPlugin<T> extends DoublePlugin<T> implements Serializable {

	/**
	 *  Appease the gods of serialisation.
	 */
	private static final long serialVersionUID = 8967797405022944799L;

	/**
	 * CONSTRUCTOR
	 * 
	 * @param primaryKeySet - The initial primary key list to populate with values.
	 * @param title - The Column heading.
	 */
	public PaymentPlugin(ArrayList<T> primaryKeySet, String title) {
		super(primaryKeySet, title);
	}
	
	/* (non-Javadoc)
	 * @see core.plugins.DoublePlugin#getCellRenderer()
	 */
	@Override
	public MoneyDoubleCellRenderer getCellRenderer() {
		return new MoneyDoubleCellRenderer(2);
	}

}
