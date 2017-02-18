/**
 * 
 */
package plugins;

import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.table.DefaultTableCellRenderer;

import core.plugins.DoublePlugin;

/**
 * @author Lawrence
 *
 */
public class PaymentPlugin<T> extends DoublePlugin<T> implements Serializable {

	/**
	 *  Appease the gods of serialisation.
	 */
	private static final long serialVersionUID = 8967797405022944799L;

	public PaymentPlugin(ArrayList<T> primaryKeySet, String title) {
		super(primaryKeySet, title);
	}
	
	@Override
	public DefaultTableCellRenderer getCellRenderer() {
		return new MoneyDoubleCellRenderer(2);
	}

}
