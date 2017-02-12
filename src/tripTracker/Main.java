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
    	
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UIBuilderPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UIBuilderPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UIBuilderPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UIBuilderPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            	NavigationFrame frame = new NavigationFrame(trips);
            	frame.setVisible(true);
                DialogBuilder addDialog = DialogFactory.getTripTypeDialog(frame.getTreeNavigator());
                frame.getTreeNavigator().registerAddDialog(addDialog);
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
