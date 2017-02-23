package uiBuilders;

import java.util.Map;

import core.IAddTreeDialog;
import core.IUIBuilderCreator;
import core.UIBuilder;
import core.plugins.EnumComboPlugin;
import core.ui.DialogBuilder;
import dialogs.ClassSelectorDialogCreator;
import dialogs.TripResidentialExternalProviderDialogCreator;
import plugins.OptionsEnum;
import plugins.PaymentPlugin;
import plugins.PhoneNumberPlugin;
import plugins.TransportPlugin;
import plugins.TripFeePlugin;
import transports.ITransport;
import tripTracker.TripTrackerConstants;
import tripTracker.Student;

/**
*
* @author Lawrence
*/
public class ResidentialTripExtCreator implements IUIBuilderCreator, Trip {

	private static final String TYPE = "Residential Trip by External Provider";
	
	@Override
	public UIBuilder getNewInstance(Map<String, Object> entries) {
		return getTripResidentialExternalProviderObject(
				(String)entries.get(TripTrackerConstants.TITLE), 
				(Double)entries.get(TripTrackerConstants.ENTRY_FEE), 
				(ITransport)entries.get(TripTrackerConstants.TRANSPORT));
	}

	private static UIBuilder<Student> getTripResidentialExternalProviderObject(String title, Double entryFee, ITransport transport) {
		UIBuilder<Student> trip = new UIBuilder<Student>(title, TripTrackerConstants.STUDENTS);
		
    	trip.addPlugin(new PhoneNumberPlugin<Student>(trip.getPrimaryKeyList()));
    	trip.addPlugin(new PaymentPlugin<Student>(trip.getPrimaryKeyList(), TripTrackerConstants.PAYMENTS));
    	trip.addPlugin(new EnumComboPlugin<OptionsEnum, Student>(
    			OptionsEnum.class, OptionsEnum.NOT_YET, trip.getPrimaryKeyList(), TripTrackerConstants.AUTHORISATION));
    	
    	trip.addLabelPlugin(new TripFeePlugin(entryFee));
    	trip.addLabelPlugin(new TransportPlugin(transport));
    	
    	trip.registerAddDialog(new ClassSelectorDialogCreator());

		return trip; 
	}
	
	@Override
	public DialogBuilder getAddDialog(IAddTreeDialog addable) {
		return new TripResidentialExternalProviderDialogCreator().getNewInstance(addable);
	}
	
	@Override
	public String toString() {
		return TYPE;
	}

}
