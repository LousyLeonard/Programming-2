package core.ui;

import java.awt.Component;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;

public class ComboBoxEditor extends DefaultCellEditor implements Serializable {

	/**
	 *  Appease the gods of serialisation.
	 */
	@SuppressWarnings("unused")
	private static final long serialVersionUID = -479956743050219449L;

	// Declare a model that is used for adding the elements to the `ComboBox`
    private DefaultComboBoxModel model;

    private List<String> obtainedList;

    public ComboBoxEditor(ArrayList<String> values) {
        super(new JComboBox());
        this.model = (DefaultComboBoxModel)((JComboBox)getComponent()).getModel();
        obtainedList = new ArrayList<String>();

        for (String value : values) {
            obtainedList.add(value);
        }
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {


       if(column == 0) {
           model.removeAllElements();
           for(int i = 0; i < obtainedList.size(); i++) {
               model.addElement(obtainedList.get(i));
            } 
        } else {

             model.removeAllElements();
             String selectedItem = (String) table.getValueAt(row, 0);
             for(int i = 0; i < obtainedList.size(); i++) {
                    if(!selectedItem.equals(obtainedList.get(i)))
                    model.addElement(obtainedList.get(i));
             } 
         } // Close else

        return super.getTableCellEditorComponent(table, value, isSelected, row, column);
     }
    }