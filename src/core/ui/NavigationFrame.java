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
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;

import core.CoreConstants;
import core.IDialogCreator;
import core.NotUniqueEntryException;
import core.UIBFileFilter;
import core.UIBuilder;
import core.util.FileUtils;

/**
*
* @author Lawrence
*/
public class NavigationFrame extends javax.swing.JFrame {
	
	private javax.swing.JPanel displayPanel;
    private TreeNavigator treeNavigator;
    
    private JMenuBar menuBar;
    private JMenu fileMenu;
    
    private JMenuItem importMenuItem, exportMenuItem;
    
    private JFileChooser fileChooser;

    /**
     * Creates new form NavigationFrame
     * @param panels
     */
    public NavigationFrame() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialise the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     * @param <T>
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
			            
			            if (FileUtils.getExtension(file.getName()).equalsIgnoreCase(FileUtils.uib)) {
			                // filename is OK as-is
			            } else {
			                file = new File(file.toString() + FileUtils.dot + FileUtils.uib); 
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
			            
			            if (FileUtils.getExtension(file.getName()).equalsIgnoreCase(FileUtils.uib)) {
			                // filename is OK as-is
			            } else {
			            	 // append .xml if "foo.jpg.uib" is OK
			                file = new File(file.toString() + FileUtils.dot + FileUtils.uib); 
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
	
	public void addEntry(Object panel) throws NotUniqueEntryException {
		treeNavigator.addEntry(panel);
	}
	
	public void addObject(DefaultMutableTreeNode parent,
            UIBuilder child,
            boolean shouldBeVisible) {
		treeNavigator.addEntry(parent, child, shouldBeVisible);
	}
	
	public void addFolder(String folderName, IDialogCreator dialog) {
		treeNavigator.addFolderEntry(folderName, dialog);
	}
	
	public DefaultMutableTreeNode getFolder(String folderName) {
		return treeNavigator.getFolder(folderName);
	}

	public TreeNavigator getTreeNavigator() {
		return treeNavigator;
	}
	
	private void exportState(File file) {
		try {
			FileOutputStream fout = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fout);
			oos.writeObject(treeNavigator.getExportData());
			oos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
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
	
	public JMenuBar getJMenuBar() {
		return menuBar;
	}
}

