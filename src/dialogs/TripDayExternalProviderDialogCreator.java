/**
 * 
 */
package dialogs;

import java.io.Serializable;
import java.util.ArrayList;

import core.CustomClassLoader;
import core.IAddDialog;
import core.IAddTreeDialog;
import core.IDialogCreator;
import core.IEntryPanelProvider;
import core.events.AddTreeEvent;
import core.events.HideWindowEvent;
import core.ui.DialogBuilder;
import core.ui.entrypanels.GenericNumberEntryPanel;
import core.ui.entrypanels.GenericStringEntryPanel;
import core.ui.entrypanels.SmartExclusiveSelectionPanel;
import core.util.ArrayCastingUtils;
import transports.Transportable;
import tripTracker.StringConstants;
import uiBuilders.DayTripExtCreator;

/**
 * @author Lawrence
 *
 */
public class TripDayExternalProviderDialogCreator implements IDialogCreator, Serializable {

	/**
	 * Appease the gods of serialisation.
	 */
	private static final long serialVersionUID = -3204299114982115631L;
	
	/* (non-Javadoc)
	 * @see core.IDialogCreator#getNewInstance(core.IAddDialog)
	 */
	@Override
	public DialogBuilder getNewInstance(IAddDialog addable) {
		return getTripDayExternalProviderDialog((IAddTreeDialog)addable);
	}
	
	private static DialogBuilder getTripDayExternalProviderDialog(IAddTreeDialog addable) {
		GenericStringEntryPanel title = new GenericStringEntryPanel(StringConstants.TITLE);
		GenericNumberEntryPanel entryFee = new GenericNumberEntryPanel(StringConstants.ENTRY_FEE);

		CustomClassLoader<Transportable> transportLoader = new CustomClassLoader<Transportable>(Transportable.class);
		ArrayList<Transportable> transports = transportLoader.getElements();		
		ArrayList<IEntryPanelProvider> transportPanels = ArrayCastingUtils.convertArray(IEntryPanelProvider.class, transports);	
		SmartExclusiveSelectionPanel transport = new SmartExclusiveSelectionPanel(StringConstants.TRANSPORT, transportPanels);
	
		DialogBuilder builder = new DialogBuilder(StringConstants.NEW_TRIP);

		builder.addPanel(title);
		builder.addPanel(entryFee);
		builder.addPanel(transport);
		
		builder.registerNoEvent(new HideWindowEvent());
		builder.registerYesEvent(new AddTreeEvent(addable, new DayTripExtCreator(), StringConstants.TRIPS));
		
		return builder;
	}

}
