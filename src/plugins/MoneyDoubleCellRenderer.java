package plugins;

import java.io.Serializable;
import java.text.NumberFormat;

import javax.swing.table.DefaultTableCellRenderer;

public class MoneyDoubleCellRenderer extends DefaultTableCellRenderer implements Serializable {

	/**
	 *  Appease the gods of serialisation.
	 */
	@SuppressWarnings("unused")
	private static final long serialVersionUID = -2329509437115052270L;
	
	int precision = 0;
	Number numberValue;
	NumberFormat nf;

	public MoneyDoubleCellRenderer(int p_precision) {
	            super();
	            setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
	            precision = p_precision;
	            nf = NumberFormat.getNumberInstance();
	            nf.setMinimumFractionDigits(p_precision);
	            nf.setMaximumFractionDigits(p_precision);
	        }

	@Override
	public void setValue(Object value) {
		if ((value != null) && (value instanceof Number)) {
			numberValue = (Number) value;
			value = nf.format(numberValue.doubleValue());
		}
		super.setValue(value);
	}
}