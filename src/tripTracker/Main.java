package tripTracker;

import java.util.ArrayList;
import java.util.Date;

import core.UIBuilder;
import core.ui.DialogBuilder;
import core.ui.NavigationFrame;
import core.ui.UIBuilderPanel;
import transports.BusTransport;
import transports.NoTransport;
import venueBookings.NoVenue;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
    	ArrayList<UIBuilder<Student>> trips = new ArrayList<UIBuilder<Student>>();
    	
    	trips.add(TripFactory.getTripDayExternalProviderObject("test-day-EP", 230.0, new NoTransport()));
//    	trips.add(TripFactory.getTripResidentialExternalProviderObject("test-res-EP", 230.0, new BusTransport(new Date(), new Date())));
    	trips.add(TripFactory.getTripResidentialExternalProviderObject("test-res-EP", 230.0, new BusTransport("default", "default")));
    	trips.add(TripFactory.getTripDayTeacherOrganisedObject("test-day-teach", 230.0, new NoTransport(), new NoVenue()));
    	trips.add(TripFactory.getTripResidentialTeacherOrganisedObject("test-res-teach", 230.0, new NoTransport(), new NoVenue()));
    	
    	for (UIBuilder<Student> trip : trips) {
        	addStudents(trip);
    	}

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            	NavigationFrame frame = new NavigationFrame();
            	frame.setVisible(true);
                
                frame.addFolder("Trips", DialogFactory.getTripTypeDialog(frame.getTreeNavigator()));
                frame.addFolder("Classes", DialogFactory.getTripTypeDialog(frame.getTreeNavigator()));
                
            	for (UIBuilder<Student> trip : trips) {
                    frame.addObject(frame.getFolder("Trips"), trip.getPanel(), true);
            	}

            	frame.pack();
            }
        });
    }    
    
    private static void addStudents(UIBuilder<Student> trip) {
    	try {
	    	Student student = new Student("Lawrence", "Coles");
	    	trip.addEntry(student);
	    	Student student2 = new Student("Ella", "Love");
	    	trip.addEntry(student2);
	    	Student student3 = new Student("Joe", "Bloggs");
	    	trip.addEntry(student3); 
    	} catch (Exception e) {
    		System.out.println(e);;
    	}
    }

}
