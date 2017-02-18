package core.ui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

import core.IAddDialog;
import core.NotUniqueEntryException;
import core.UIBuilder;

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
            UIBuilderPanel child,
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
}

