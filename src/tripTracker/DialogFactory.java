package tripTracker;

import core.IAddDialog;
import core.events.HideWindowEvent;
import core.ui.DialogBuilder;
import core.ui.entrypanels.GenericExclusiveSelectionPanel;
import core.ui.entrypanels.GenericNumberEntryPanel;
import core.ui.entrypanels.GenericStringEntryPanel;
import events.AddStudentEvent;
import events.AddTripEvent;
import events.GetTripTypeEvent;
import transports.TransportManager;

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
		GenericExclusiveSelectionPanel transport = 
				new GenericExclusiveSelectionPanel(StringConstants.TRANSPORT, TransportManager.getInstance().getTransportTypes());
		VenueBookingEntryPanel venueBooking = new VenueBookingEntryPanel(StringConstants.VENUE_BOOKING);
		
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
		TransportEntryPanel transport = new TransportEntryPanel(StringConstants.TRANSPORT);
		
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
		TransportEntryPanel transport = new TransportEntryPanel(StringConstants.TRANSPORT);
		VenueBookingEntryPanel venueBooking = new VenueBookingEntryPanel(StringConstants.VENUE_BOOKING);
		
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
		TransportEntryPanel transport = new TransportEntryPanel(StringConstants.TRANSPORT);
		
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
