package tripTracker;

import java.util.ArrayList;
import java.util.HashMap;

import core.UIBuilder;
import core.ui.NavigationFrame;
import dialogs.AddClassDialogCreator;
import dialogs.TripTypeDialogCreator;
import transports.NoTransport;
import uiBuilders.DayTripTeacherCreator;
import uiBuilders.StudentClassCreator;
import venueBookings.NoVenue;

/**
*
* @author Lawrence
*/
public class TripTrackerMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
    	ClassManager.getInstance();
    	
    	ArrayList<UIBuilder<Student>> trips = new ArrayList<UIBuilder<Student>>();
    	
    	DayTripTeacherCreator fac = new DayTripTeacherCreator();
    	HashMap<String, Object> entries = new HashMap<String, Object>();
    	entries.put(TripTrackerConstants.TITLE, "test-day-teach");
    	entries.put(TripTrackerConstants.ENTRY_FEE, 230.00);
    	entries.put(TripTrackerConstants.TRANSPORT, new NoTransport());
    	entries.put(TripTrackerConstants.VENUE_BOOKING, new NoVenue());
    	trips.add(fac.getNewInstance(entries));
    	    	
    	entries = new HashMap<String, Object>();
    	entries.put(TripTrackerConstants.NEW_CLASS, "Default");
    	StudentClassCreator factory = new StudentClassCreator();
    	UIBuilder<Student> defaultClass = factory.getNewInstance(entries);
    	
    	for (UIBuilder<Student> trip : trips) {
        	addStudents(trip);
    	}

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            	NavigationFrame frame = new NavigationFrame();
            	frame.setVisible(true);
            	                
                frame.addFolder(TripTrackerConstants.TRIPS, new TripTypeDialogCreator());
                frame.addFolder(TripTrackerConstants.CLASSES, new AddClassDialogCreator());
                
            	for (UIBuilder<Student> trip : trips) {
                    frame.addObject(frame.getFolder(TripTrackerConstants.TRIPS), trip, true);
            	}
            	
            	frame.addObject(frame.getFolder(TripTrackerConstants.CLASSES), defaultClass, true);
            	frame.getJMenuBar().add(new HelpMenu());
            	
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
