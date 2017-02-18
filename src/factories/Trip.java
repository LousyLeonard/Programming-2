package factories;

import core.IAddTreeDialog;
import core.IUIBuilderFactory;
import core.ui.DialogBuilder;

/**
 * Customer class loader enabler
 * Also ensures an add dialog is present
 */
public interface Trip extends IUIBuilderFactory{

	public DialogBuilder getAddDialog(IAddTreeDialog addable);

}
