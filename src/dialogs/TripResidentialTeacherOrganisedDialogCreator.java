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
import uiBuilders.ResidentialTripTeacherCreator;
import venueBookings.VenueBookingable;

/**
 * @author Lawrence
 *
 */
public class TripResidentialTeacherOrganisedDialogCreator implements IDialogCreator, Serializable {

	/**
	 * Appease the gods of serialisation.
	 */
	private static final long serialVersionUID = -4834014807509090066L;
	
	/* (non-Javadoc)
	 * @see core.IDialogCreator#getNewInstance(core.IAddDialog)
	 */
	@Override
	public DialogBuilder getNewInstance(IAddDialog addable) {
		return getTripResidentialTeacherOrganisedDialog((IAddTreeDialog)addable);
	}
	
	private static DialogBuilder getTripResidentialTeacherOrganisedDialog(IAddTreeDialog addable) {
		GenericStringEntryPanel title = new GenericStringEntryPanel(StringConstants.TITLE);
		GenericNumberEntryPanel entryFee = new GenericNumberEntryPanel(StringConstants.ENTRY_FEE);

		CustomClassLoader<Transportable> transportLoader = new CustomClassLoader<Transportable>(Transportable.class);
		ArrayList<Transportable> transports = transportLoader.getElements();		
		ArrayList<IEntryPanelProvider> transportPanels = ArrayCastingUtils.convertArray(IEntryPanelProvider.class, transports);	
		SmartExclusiveSelectionPanel transport = new SmartExclusiveSelectionPanel(StringConstants.TRANSPORT, transportPanels);

		CustomClassLoader<VenueBookingable> venueLoader = new CustomClassLoader<VenueBookingable>(VenueBookingable.class);
		ArrayList<VenueBookingable> venues = venueLoader.getElements();		
		ArrayList<IEntryPanelProvider> venuePanels = ArrayCastingUtils.convertArray(IEntryPanelProvider.class, venues);	
		SmartExclusiveSelectionPanel venueBooking = new SmartExclusiveSelectionPanel(StringConstants.VENUE_BOOKING, venuePanels);
		
		DialogBuilder builder = new DialogBuilder(StringConstants.NEW_TRIP);

		builder.addPanel(title);
		builder.addPanel(entryFee);
		builder.addPanel(transport);
		builder.addPanel(venueBooking);
		
		builder.registerNoEvent(new HideWindowEvent());
		builder.registerYesEvent(new AddTreeEvent(addable, new ResidentialTripTeacherCreator(), StringConstants.TRIPS));
		
		return builder;
	}
	

}
