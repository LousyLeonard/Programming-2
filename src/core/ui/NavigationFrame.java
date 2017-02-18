package core.ui;

import java.awt.CardLayout;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import core.NotUniqueEntryException;
import core.UIBuilder;
import tripTracker.StringConstants;

/**
*
* @author Lawrence
*/
public class NavigationFrame extends javax.swing.JFrame {
	
	private javax.swing.JPanel displayPanel;
    private TreeNavigator treeNavigator;

    /**
     * Creates new form NavigationFrame
     * @param panels
     */
    public <T> NavigationFrame() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialise the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     * @param <T>
     */
    @SuppressWarnings("unchecked")
    private <T> void initComponents() {

        displayPanel = new javax.swing.JPanel(new CardLayout());
        treeNavigator = new TreeNavigator();
        treeNavigator.setDisplayPanel(displayPanel);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.LINE_AXIS));

        getContentPane().add(treeNavigator);
        getContentPane().add(displayPanel);

        pack();
    }                     
     
	private <T> ArrayList<UIBuilderPanel> getPanelsfromList(List<UIBuilder<T>> builders) {
    	ArrayList<UIBuilderPanel> panels = new ArrayList<UIBuilderPanel>();
    	for(UIBuilder<T> builder : builders) {
    		panels.add(builder.getPanel());
    	}
    	return panels;
    }
	
	public void addEntry(Object panel) throws NotUniqueEntryException {
		treeNavigator.addEntry(panel);
	}
	
	public void addObject(DefaultMutableTreeNode parent,
            UIBuilder child,
            boolean shouldBeVisible) {
		treeNavigator.addEntry(parent, child, shouldBeVisible);
	}
	
	public void addFolder(String folderName, DialogBuilder dialog) {
		treeNavigator.addFolderEntry(folderName, dialog);
	}
	
	public DefaultMutableTreeNode getFolder(String folderName) {
		return treeNavigator.getFolder(folderName);
	}

	public TreeNavigator getTreeNavigator() {
		return treeNavigator;
	}
	
	public void exportState() {
		try {
			FileOutputStream fout = new FileOutputStream("C:\\temp\\test.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fout);
			oos.writeObject(treeNavigator.getModel());

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void importState() {
		try {
			FileInputStream streamIn = new FileInputStream("C:\\temp\\test.ser");
		    ObjectInputStream objectinputstream = new ObjectInputStream(streamIn);
		    DefaultTreeModel readCase = (DefaultTreeModel) objectinputstream.readObject();
		    treeNavigator.setModel(readCase);
		 } catch (Exception e) {
		     e.printStackTrace();
		 }
	}
	
	public void wipeModel() {
		treeNavigator.setModel(new DefaultTreeModel(treeNavigator.getFolder(StringConstants.CLASSES)));
	}
}

