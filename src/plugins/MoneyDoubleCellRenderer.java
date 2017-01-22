package plugins;

import java.text.NumberFormat;

import javax.swing.table.DefaultTableCellRenderer;

public class MoneyDoubleCellRenderer extends DefaultTableCellRenderer {

	private static final long serialVersionUID = 1L;
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