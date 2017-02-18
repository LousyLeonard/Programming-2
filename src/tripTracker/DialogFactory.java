package tripTracker;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JPanel;
import javax.swing.tree.DefaultMutableTreeNode;

import com.sun.xml.internal.ws.api.streaming.XMLStreamReaderFactory.Default;

import core.CustomClassLoader;
import core.IAddDialog;
import core.IAddTreeDialog;
import core.IEntryPanelProvider;
import core.UIBuilder;
import core.events.AddTreeEvent;
import core.events.HideWindowEvent;
import core.ui.DialogBuilder;
import core.ui.entrypanels.GenericExclusiveSelectionPanel;
import core.ui.entrypanels.GenericNumberEntryPanel;
import core.ui.entrypanels.GenericStringEntryPanel;
import core.ui.entrypanels.SmartExclusiveSelectionPanel;
import core.util.ArrayCastingUtils;
import events.AddStudentEvent;
import events.AddClassEvent;
import events.GetTripTypeEvent;
import factories.ClassFactory;
import factories.DayTripExtFactory;
import factories.DayTripTeacherFactory;
import factories.ResidentialTripExtFactory;
import factories.ResidentialTripTeacherFactory;
import factories.Trip;
import transports.Transportable;
import venueBookings.VenueBookingable;

public abstract class DialogFactory {

	public static DialogBuilder getTripDayTeacherOrganisedDialog(IAddTreeDialog addable) {
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
		builder.registerYesEvent(new AddTreeEvent(addable, new DayTripTeacherFactory(), StringConstants.TRIPS));
		
		return builder;
	}
	
	public static DialogBuilder getTripDayExternalProviderDialog(IAddTreeDialog addable) {
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
		builder.registerYesEvent(new AddTreeEvent(addable, new DayTripExtFactory(), StringConstants.TRIPS));
		
		return builder;
	}
	
	public static DialogBuilder getTripResidentialTeacherOrganisedDialog(IAddTreeDialog addable) {
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
		builder.registerYesEvent(new AddTreeEvent(addable, new ResidentialTripTeacherFactory(), StringConstants.TRIPS));
		
		return builder;
	}
	
	public static DialogBuilder getTripResidentialExternalProviderDialog(IAddTreeDialog addable) {
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
		builder.registerYesEvent(new AddTreeEvent(addable, 
				new ResidentialTripExtFactory(), StringConstants.TRIPS));
		
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
	
	public static DialogBuilder getTripTypeDialog(IAddTreeDialog addable) {
		CustomClassLoader<Trip> tripLoader = new CustomClassLoader<Trip>(Trip.class);
		ArrayList<Trip> trips = tripLoader.getElements();		
		GenericExclusiveSelectionPanel selections = new GenericExclusiveSelectionPanel(StringConstants.TRIP_TYPE, trips);

		DialogBuilder builder = new DialogBuilder(StringConstants.NEW_TRIP);		
		
		builder.addPanel(selections);
		
		builder.registerNoEvent(new HideWindowEvent());
		builder.registerYesEvent(new GetTripTypeEvent(addable));

		return builder;
	}
	
	public static DialogBuilder getAddClassDialog(IAddTreeDialog addable) {
		GenericStringEntryPanel selections = new GenericStringEntryPanel(StringConstants.NEW_CLASS);

		DialogBuilder builder = new DialogBuilder(StringConstants.NEW_CLASS);		
		
		builder.addPanel(selections);
		
		builder.registerNoEvent(new HideWindowEvent());
		builder.registerYesEvent(new AddTreeEvent(addable, new ClassFactory(), StringConstants.CLASSES));

		return builder;
	}

	public static DialogBuilder getClassSelectorDialog(IAddDialog addable) {
		GenericExclusiveSelectionPanel selections = ClassManager.getInstance().getSelectionPanel();
		
		DialogBuilder builder = new DialogBuilder(StringConstants.ADD_CLASS);	
		
		builder.addPanel(selections);
		
		builder.registerNoEvent(new HideWindowEvent());
		builder.registerYesEvent(new AddClassEvent(addable));
		
		ClassManager.getInstance().registerListener(builder);
		
		return builder;
	}
}
