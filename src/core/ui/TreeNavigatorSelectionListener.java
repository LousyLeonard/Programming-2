package core.ui;

import java.awt.CardLayout;
import java.io.Serializable;

import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

/**
* A Selection Listener for a tree to display the current selections
* UIBuilderPanel in the display panel.
* 
* @author Lawrence
*/
public class TreeNavigatorSelectionListener implements TreeSelectionListener, Serializable {
	
	private JTree parentTree;
	private JPanel displayPanel;	
    
    /**
     * CONSTRUCTOR
     * 
     * @param parentTree - The tree to listen to.
     * @param displayPanel - The panel to update.
     */
    public TreeNavigatorSelectionListener(JTree parentTree, JPanel displayPanel) {
    	this.parentTree = parentTree;
    	this.displayPanel = displayPanel;
    }

	/* (non-Javadoc)
	 * @see javax.swing.event.TreeSelectionListener#valueChanged(javax.swing.event.TreeSelectionEvent)
	 */
	@Override
    public void valueChanged(TreeSelectionEvent e) {
    		//Returns the last path element of the selection.
    	    DefaultMutableTreeNode node = (DefaultMutableTreeNode)
    	    		parentTree.getLastSelectedPathComponent();
    	    
    	    if (node == null) {
	    	    //Nothing is selected.     
	    	    return;
    	    }
    	        	    
    	    CardLayout layout = (CardLayout)(displayPanel.getLayout());
    	    layout.show(displayPanel, node.toString());
    	    
    	}

}
