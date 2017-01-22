/**
 * 
 */
package plugins;

import java.util.ArrayList;

import javax.swing.table.DefaultTableCellRenderer;

import core.plugins.DoublePlugin;

/**
 * @author Lawrence
 *
 */
public class PaymentPlugin<T> extends DoublePlugin<T> {

	public PaymentPlugin(ArrayList<T> primaryKeySet, String title) {
		super(primaryKeySet, title);
	}
	
	@Override
	public DefaultTableCellRenderer getCellRenderer() {
		return new MoneyDoubleCellRenderer(2);
	}

}
