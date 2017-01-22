package tripTracker;

import java.lang.reflect.Constructor;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import core.UIBuilder;
import core.plugins.EnumComboPlugin;
import plugins.OptionsEnum;
import plugins.PaymentPlugin;
import plugins.PhoneNumberPlugin;
import plugins.TransportPlugin;
import plugins.TripFeePlugin;
import plugins.VenueBookingPlugin;
import transports.NoTransport;
import transports.Transportable;

public abstract class TripFactory {
		
	public static UIBuilder<Student> parse(TripTypes type, Map<String, Object> args) {
		switch(type) {
			case DAY_TRIP_EXTERNAL_PROVIDER : 
				return getTripDayExternalProviderObject(
						(String)args.get(StringConstants.TITLE), 
						(Double)args.get(StringConstants.ENTRY_FEE), 
						(Transportable)args.get(StringConstants.TRANSPORT));
			case DAY_TRIP_TEACHER : 
				return getTripDayTeacherOrganisedObject(
						(String)args.get(StringConstants.TITLE), 
						(Double)args.get(StringConstants.ENTRY_FEE), 
						fromClass((Class)args.get(StringConstants.TRANSPORT)),
						(VenueBookingable)args.get(StringConstants.VENUE_BOOKING));
			case RESIDENTIAL_TRIP_TEACHER : 
				return getTripResidentialTeacherOrganisedObject(
						(String)args.get(StringConstants.TITLE), 
						(Double)args.get(StringConstants.ENTRY_FEE), 
						(Transportable)args.get(StringConstants.TRANSPORT),
						(VenueBookingable)args.get(StringConstants.VENUE_BOOKING));
			case RESIDENTIAL_TRIP_EXTERNAL_PROVIDER : 
				return getTripResidentialExternalProviderObject(
						(String)args.get(StringConstants.TITLE), 
						(Double)args.get(StringConstants.ENTRY_FEE), 
						(Transportable)args.get(StringConstants.TRANSPORT));
			default : 
				return null;
		}
	}
	
	public static UIBuilder<Student> getTripDayExternalProviderObject(String title, Double entryFee, Transportable transport) {
    	UIBuilder<Student> trip = new UIBuilder<Student>(title, "Students");
    	
    	trip.addPlugin(new PhoneNumberPlugin<Student>(trip.getPrimaryKeyList()));
    	trip.addPlugin(new PaymentPlugin<Student>(trip.getPrimaryKeyList(), StringConstants.PAYMENTS));
    	
    	trip.addLabelPlugin(new TripFeePlugin(entryFee));
    	trip.addLabelPlugin(new TransportPlugin(transport));
    	
    	trip.registerAddDialog(DialogFactory.getAddStudentDialog(trip));
    	
		return trip; 
	}
	
	public static UIBuilder<Student> getTripResidentialExternalProviderObject(String title, Double entryFee, Transportable transport) {
		UIBuilder<Student> trip = new UIBuilder<Student>(title, "Students");
		
    	trip.addPlugin(new PhoneNumberPlugin<Student>(trip.getPrimaryKeyList()));
    	trip.addPlugin(new PaymentPlugin<Student>(trip.getPrimaryKeyList(), StringConstants.PAYMENTS));
    	trip.addPlugin(new EnumComboPlugin<OptionsEnum, Student>(
    			OptionsEnum.class, OptionsEnum.NOT_YET, trip.getPrimaryKeyList(), StringConstants.AUTHORISATION));
    	
    	trip.addLabelPlugin(new TripFeePlugin(entryFee));
    	trip.addLabelPlugin(new TransportPlugin(transport));
    	
    	trip.registerAddDialog(DialogFactory.getAddStudentDialog(trip));

		return trip; 
	}
	
	public static UIBuilder<Student> getTripDayTeacherOrganisedObject(String title, Double entryFee, Transportable transport, VenueBookingable booking) {
    	UIBuilder<Student> trip = new UIBuilder<Student>(title, "Students");
    	
    	trip.addPlugin(new PhoneNumberPlugin<Student>(trip.getPrimaryKeyList()));
    	trip.addPlugin(new PaymentPlugin<Student>(trip.getPrimaryKeyList(), StringConstants.PAYMENTS));
    	
    	trip.addLabelPlugin(new TripFeePlugin(entryFee));
    	trip.addLabelPlugin(new TransportPlugin(transport));
    	trip.addLabelPlugin(new VenueBookingPlugin(booking));
    	
    	trip.registerAddDialog(DialogFactory.getAddStudentDialog(trip));

		return trip; 
	}
	
	public static UIBuilder<Student> getTripResidentialTeacherOrganisedObject(String title, Double entryFee, Transportable transport, VenueBookingable booking) {
    	UIBuilder<Student> trip = new UIBuilder<Student>(title, "Students");
    	
    	trip.addPlugin(new PhoneNumberPlugin<Student>(trip.getPrimaryKeyList()));
    	trip.addPlugin(new PaymentPlugin<Student>(trip.getPrimaryKeyList(), StringConstants.PAYMENTS));
    	trip.addPlugin(new EnumComboPlugin<OptionsEnum, Student>(
    			OptionsEnum.class, OptionsEnum.NOT_YET, trip.getPrimaryKeyList(), StringConstants.AUTHORISATION));
    	
    	trip.addLabelPlugin(new TripFeePlugin(entryFee));
    	trip.addLabelPlugin(new TransportPlugin(transport));
    	trip.addLabelPlugin(new VenueBookingPlugin(booking));
    	
    	trip.registerAddDialog(DialogFactory.getAddStudentDialog(trip));

		return trip; 
	}
	
	private static Transportable fromClass(Class transportClass) {
		try {
			Transportable transport = (Transportable) transportClass.newInstance();
			final CountDownLatch latch = new CountDownLatch(1);
			transport.setup(latch);
			try {
				latch.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return transport;
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		Constructor constructor = transportClass.getConstructor(null);
		return new NoTransport();
	}

}
