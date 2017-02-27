package core.ui;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.tree.DefaultMutableTreeNode;

import core.CoreConstants;
import core.IDialogCreator;
import core.NotUniqueEntryException;
import core.UIBFileFilter;
import core.UIBuilder;
import core.util.FileUtils;

/**
* A GUI instance of a TreeNavigator to display panels.
* 
* @author Lawrence
*/
public class NavigationFrame extends javax.swing.JFrame {
	
	private JPanel displayPanel;
    private TreeNavigator treeNavigator;
    
    private JMenuBar menuBar;
    private JMenu fileMenu;
    
    private JMenuItem importMenuItem, exportMenuItem;
    
    private JFileChooser fileChooser;

    /**
     * Creates new form NavigationFrame.
     * 
     * @param panels
     */
    public NavigationFrame() {
        initComponents();
    }

	/**
	 * Setup the GUI components.
	 */
    @SuppressWarnings("unchecked")
    private void initComponents() {

        displayPanel = new javax.swing.JPanel(new CardLayout());
        treeNavigator = new TreeNavigator();
        menuBar = new JMenuBar();
        fileMenu = new JMenu(CoreConstants.FILE_MENU);
        importMenuItem = new JMenuItem(CoreConstants.IMPORT_MENU);
        exportMenuItem = new JMenuItem(CoreConstants.EXPORT_MENU);
        fileChooser = new JFileChooser();
        
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(new UIBFileFilter());

        menuBar.add(fileMenu);
        
        fileMenu.add(importMenuItem);
        fileMenu.add(exportMenuItem);
        
        importMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//Handle open button action.
				if (e.getSource() == importMenuItem) {
			        int returnVal = fileChooser.showOpenDialog(NavigationFrame.this);

			        if (returnVal == JFileChooser.APPROVE_OPTION) {
			            File file = fileChooser.getSelectedFile();
			            
			            if (FileUtils.getExtension(file.getName()).equalsIgnoreCase(CoreConstants.FILE_EXTENSION)) {
			                // filename is OK as-is
			            } else {
			            	 // append .uib
			                file = new File(file.toString() + CoreConstants.DOT + CoreConstants.FILE_EXTENSION); 
			            }
			            
					    importState(file);
			        } else {
			        	//Do Nothing
			        }
			    }
			}
        });
        
        exportMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//Handle open button action.
				if (e.getSource() == exportMenuItem) {
			        int returnVal = fileChooser.showSaveDialog(NavigationFrame.this);

			        if (returnVal == JFileChooser.APPROVE_OPTION) {
			            File file = fileChooser.getSelectedFile();
			            
			            if (FileUtils.getExtension(file.getName()).equalsIgnoreCase(CoreConstants.FILE_EXTENSION)) {
			                // filename is OK as-is
			            } else {
			            	 // append .uib
			                file = new File(file.toString() + CoreConstants.DOT + CoreConstants.FILE_EXTENSION); 
			            }
			            
					    exportState(file);
			        } else {
			        	//Do Nothing
			        }
			    }
			}
        });
        
        this.setJMenuBar(menuBar);
        
        treeNavigator.setDisplayPanel(displayPanel);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.LINE_AXIS));

        getContentPane().add(treeNavigator);
        getContentPane().add(displayPanel);

        pack();
    }
	
	/**
	 * Add a Entry.
	 * 
	 * @param panel - The panel to add.
	 * @throws NotUniqueEntryException when the entry already exists.
	 */
	public void addEntry(Object panel) throws NotUniqueEntryException {
		treeNavigator.addEntry(panel);
	}
	
	/**
	 * Add an entry.
	 * 
	 * @param parent - The Node of which to add the entry under.
	 * @param child - The entry to add.
	 * @param shouldBeVisible - True to expand all nodes leading to this.
	 */
	public void addObject(DefaultMutableTreeNode parent,
            UIBuilder child,
            boolean shouldBeVisible) {
		treeNavigator.addEntry(parent, child, shouldBeVisible);
	}
	
	/**
	 * Add a top level element to the tree.
	 * 
	 * @param folderName - The name of the element.
	 * @param dialog - The IDialogCreator to create the DialogBuilder should you want to
	 * add to the folder.
	 */
	public void addFolder(String folderName, IDialogCreator dialog) {
		treeNavigator.addFolderEntry(folderName, dialog);
	}
	
	/**
	 * Get the requested top level element.
	 * @param folderName - The name of the new element.
	 * @return the node the folder is at.
	 */
	public DefaultMutableTreeNode getFolder(String folderName) {
		return treeNavigator.getFolder(folderName);
	}

	/**
	 * Get the held TreeNavigator.
	 * 
	 * @return - The TreeNavigator instance.
	 */
	public TreeNavigator getTreeNavigator() {
		return treeNavigator;
	}
	
	/**
	 * Export the current state of the tree to file.
	 * 
	 * @param file - The file to write to.
	 */
	private void exportState(File file) {
		try {
			FileOutputStream fout = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fout);
			oos.writeObject(treeNavigator.getExportData());
			oos.close();
		} catch (FileNotFoundException e) {
			 JOptionPane.showMessageDialog(this, "Something went wrong with the save!");
		} catch (IOException e) {
			 JOptionPane.showMessageDialog(this, "Something went wrong with the save!");
		}
	}
	
	/**
	 * Import a saved state of the project into the TreeNavigator.
	 * 
	 * @param file - The file to import.
	 */
	private void importState(File file) {
		try {
			FileInputStream streamIn = new FileInputStream(file);
		    ObjectInputStream objectinputstream = new ObjectInputStream(streamIn);
		    ArrayList<Object> readCase = (ArrayList<Object>) objectinputstream.readObject();
		    treeNavigator.importData(readCase);
		    objectinputstream.close();
		 } catch (Exception e) {
			 JOptionPane.showMessageDialog(this, "File is corrupt! cannot load.");
		 }
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.JFrame#getJMenuBar()
	 */
	@Override
	public JMenuBar getJMenuBar() {
		return menuBar;
	}
}

