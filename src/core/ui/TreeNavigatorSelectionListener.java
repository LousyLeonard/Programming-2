package core.ui;

import java.awt.CardLayout;

import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

public class TreeNavigatorSelectionListener implements TreeSelectionListener {

    private JTree parentTree;
	private JPanel displayPanel;	
    
    public TreeNavigatorSelectionListener(JTree parentTree, JPanel displayPanel) {
    	this.parentTree = parentTree;
    	this.displayPanel = displayPanel;
    }

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
