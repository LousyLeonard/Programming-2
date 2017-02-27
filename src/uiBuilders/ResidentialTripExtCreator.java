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
* A Trip creator for the Residential Trip provided by an External Provider trip type.
*
* @author Lawrence
*/
public class ResidentialTripExtCreator implements IUIBuilderCreator, ITrip {

	private static final String TYPE = "Residential Trip by External Provider";
	
	/* (non-Javadoc)
	 * @see core.IUIBuilderCreator#getNewInstance(java.util.Map)
	 */
	@Override
	public UIBuilder getNewInstance(Map<String, Object> entries) {
		return getTripResidentialExternalProviderObject(
				(String)entries.get(TripTrackerConstants.TITLE), 
				(Double)entries.get(TripTrackerConstants.ENTRY_FEE), 
				(ITransport)entries.get(TripTrackerConstants.TRANSPORT));
	}

	/**
	 * Create a Residential Trip provided by an External Provider trip instance.
	 * 
	 * @param title - The title of the trip.
	 * @param entryFee - The entry fee.
	 * @param transport - The transport.
	 * @return a new instance of a trip.
	 */
	private static UIBuilder<Student> getTripResidentialExternalProviderObject(String title, Double entryFee, ITransport transport) {
		UIBuilder<Student> trip = new UIBuilder<Student>(title, TripTrackerConstants.STUDENTS);
		
    	trip.addColumnPlugin(new PhoneNumberPlugin<Student>(trip.getPrimaryKeyList()));
    	trip.addColumnPlugin(new PaymentPlugin<Student>(trip.getPrimaryKeyList(), TripTrackerConstants.PAYMENTS));
    	trip.addColumnPlugin(new EnumComboPlugin<OptionsEnum, Student>(
    			OptionsEnum.class, OptionsEnum.NOT_YET, trip.getPrimaryKeyList(), TripTrackerConstants.AUTHORISATION));
    	
    	trip.addLabelPlugin(new TripFeePlugin(entryFee));
    	trip.addLabelPlugin(new TransportPlugin(transport));
    	
    	trip.registerAddDialog(new ClassSelectorDialogCreator());

		return trip; 
	}
	
	/* (non-Javadoc)
	 * @see uiBuilders.ITrip#getAddDialog(core.IAddTreeDialog)
	 */
	@Override
	public DialogBuilder getAddDialog(IAddTreeDialog addable) {
		return new TripResidentialExternalProviderDialogCreator().getNewInstance(addable);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return TYPE;
	}

}
