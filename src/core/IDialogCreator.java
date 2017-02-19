/**
 * 
 */
package core;

import core.ui.DialogBuilder;

/**
 * @author Lawrence
 *
 */
public interface IDialogCreator {
	
	public DialogBuilder getNewInstance(IAddDialog addable);

}
