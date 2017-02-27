package plugins;

import java.io.Serializable;
import java.text.NumberFormat;

import javax.swing.table.DefaultTableCellRenderer;

/**
* CellRenderer for displaying monetary values. i.e. £0.##
* 
* @author Lawrence
*/
public class MoneyDoubleCellRenderer extends DefaultTableCellRenderer implements Serializable {

	/**
	 *  Appease the gods of serialisation.
	 */
	private static final long serialVersionUID = -2329509437115052270L;
	
	private Number numberValue;
	private NumberFormat nf;

	/**
	 * CONSTRUCTOR
	 * 
	 * @param precision - The number of decimal places to show.
	 */
	public MoneyDoubleCellRenderer(int precision) {
	            super();
	            setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
	            nf = NumberFormat.getNumberInstance();
	            nf.setMinimumFractionDigits(precision);
	            nf.setMaximumFractionDigits(precision);
	        }

	/* (non-Javadoc)
	 * @see javax.swing.table.DefaultTableCellRenderer#setValue(java.lang.Object)
	 */
	@Override
	public void setValue(Object value) {
		if ((value != null) && (value instanceof Number)) {
			numberValue = (Number) value;
			value = nf.format(numberValue.doubleValue());
		}
		super.setValue(value);
	}
}