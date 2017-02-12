package tripTracker;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JPanel;

import core.CustomClassLoader;
import core.IAddDialog;
import core.IEntryPanelProvider;
import core.events.HideWindowEvent;
import core.ui.DialogBuilder;
import core.ui.entrypanels.GenericExclusiveSelectionPanel;
import core.ui.entrypanels.GenericNumberEntryPanel;
import core.ui.entrypanels.GenericStringEntryPanel;
import core.ui.entrypanels.SmartExclusiveSelectionPanel;
import core.util.ArrayCastingUtils;
import events.AddStudentEvent;
import events.AddTripEvent;
import events.GetTripTypeEvent;
import transports.Transportable;
import venueBookings.VenueBookingable;

public abstract class DialogFactory {
	
	public static DialogBuilder parse(IAddDialog addable, TripTypes tripType) throws NotATripTypeException {		
		switch(tripType) {
			case DAY_TRIP_EXTERNAL_PROVIDER : 
				return getTripDayExternalProviderDialog(addable);
			case DAY_TRIP_TEACHER : 
				return getTripDayTeacherOrganisedDialog(addable);
			case RESIDENTIAL_TRIP_TEACHER : 
				return getTripResidentialTeacherOrganisedDialog(addable);
			case RESIDENTIAL_TRIP_EXTERNAL_PROVIDER : 
				return getTripResidentialExternalProviderDialog(addable);
			default :
				throw new NotATripTypeException();
		}
	}

	private static DialogBuilder getTripDayTeacherOrganisedDialog(IAddDialog addable) {
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
		builder.registerYesEvent(new AddTripEvent(addable, TripTypes.DAY_TRIP_TEACHER));
		
		return builder;
	}
	
	private static DialogBuilder getTripDayExternalProviderDialog(IAddDialog addable) {
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
		builder.registerYesEvent(new AddTripEvent(addable, TripTypes.DAY_TRIP_EXTERNAL_PROVIDER));
		
		return builder;
	}
	
	private static DialogBuilder getTripResidentialTeacherOrganisedDialog(IAddDialog addable) {
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
		builder.registerYesEvent(new AddTripEvent(addable, TripTypes.RESIDENTIAL_TRIP_TEACHER));
		
		return builder;
	}
	
	private static DialogBuilder getTripResidentialExternalProviderDialog(IAddDialog addable) {
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
		builder.registerYesEvent(new AddTripEvent(addable, TripTypes.RESIDENTIAL_TRIP_EXTERNAL_PROVIDER));
		
		return builder;
	}

	public static DialogBuilder getAddStudentDialog(IAddDialog addable) {
		GenericStringEntryPanel firstName = new GenericStringEntryPanel(StringConstants.FIRST_NAME);
		GenericStringEntryPanel secondName = new GenericStringEntryPanel(StringConstants.SECOND_NAME);
		GenericStringEntryPanel phoneNumber = new GenericStringEntryPanel(StringConstants.PHONE_NUMBER);

		DialogBuilder builder = new DialogBuilder(StringConstants.NEW_STUDENT);
		
		builder.addPanel(firstName);
		builder.addPanel(secondName);
		builder.addPanel(phoneNumber);
		
		builder.registerNoEvent(new HideWindowEvent());
		builder.registerYesEvent(new AddStudentEvent(addable));
		
		return builder;
	}
	
	public static DialogBuilder getTripTypeDialog(IAddDialog addable) {
		GenericExclusiveSelectionPanel selections = new GenericExclusiveSelectionPanel(StringConstants.TRIP_TYPE, TripTypes.class);

		DialogBuilder builder = new DialogBuilder(StringConstants.NEW_TRIP);		
		
		builder.addPanel(selections);
		
		builder.registerNoEvent(new HideWindowEvent());
		builder.registerYesEvent(new GetTripTypeEvent(addable));

		return builder;
	}
}
