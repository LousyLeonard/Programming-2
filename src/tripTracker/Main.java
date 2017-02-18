package tripTracker;

import java.util.ArrayList;
import java.util.HashMap;

import core.UIBuilder;
import core.ui.NavigationFrame;
import factories.ClassFactory;
import factories.DayTripTeacherFactory;
import transports.NoTransport;
import venueBookings.NoVenue;

/**
*
* @author Lawrence
*/
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
    	ArrayList<UIBuilder<Student>> trips = new ArrayList<UIBuilder<Student>>();
    	
    	DayTripTeacherFactory fac = new DayTripTeacherFactory();
    	HashMap<String, Object> entries = new HashMap<String, Object>();
    	entries.put(StringConstants.TITLE, "test-day-teach");
    	entries.put(StringConstants.ENTRY_FEE, 230.00);
    	entries.put(StringConstants.TRANSPORT, new NoTransport());
    	entries.put(StringConstants.VENUE_BOOKING, new NoVenue());
    	trips.add(fac.getNewInstance(entries));
    	    	
    	entries = new HashMap<String, Object>();
    	entries.put(StringConstants.NEW_CLASS, "Default");
    	ClassFactory factory = new ClassFactory();
    	UIBuilder<Student> defaultClass = factory.getNewInstance(entries);
    	
    	for (UIBuilder<Student> trip : trips) {
        	addStudents(trip);
    	}

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            	NavigationFrame frame = new NavigationFrame();
            	frame.setVisible(true);
                
                frame.addFolder(StringConstants.TRIPS, DialogFactory.getTripTypeDialog(frame.getTreeNavigator()));
                frame.addFolder(StringConstants.CLASSES, DialogFactory.getAddClassDialog(frame.getTreeNavigator()));
                
            	for (UIBuilder<Student> trip : trips) {
                    frame.addObject(frame.getFolder(StringConstants.TRIPS), trip, true);
            	}
            	
            	frame.addObject(frame.getFolder(StringConstants.CLASSES), defaultClass, true);
            	
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
