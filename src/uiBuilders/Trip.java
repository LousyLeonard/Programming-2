package uiBuilders;

import core.IAddTreeDialog;
import core.IUIBuilderCreator;
import core.ui.DialogBuilder;

/**
 * Customer class loader enabler
 * Also ensures an add dialog is present
 * 
 * @author Lawrence
 */
public interface Trip extends IUIBuilderCreator{

	public DialogBuilder getAddDialog(IAddTreeDialog addable);

}
