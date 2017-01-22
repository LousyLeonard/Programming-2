package core.ui;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeSelectionModel;

import core.IAddDialog;
import core.NoDialogRegisteredException;
import core.NotUniqueEntryException;
import core.events.HideWindowEvent;
import core.util.GraphicUtils;
import tripTracker.StringConstants;
import tripTracker.ToolTipConstants;

public class TreeNavigator extends JPanel implements IAddDialog {

	private JPanel displayPanel;
	private List<UIBuilderPanel> panels;

    private JScrollPane scrollPane;
	
	private JTree tree;
    private JButton addButton;
    private JButton removeButton;
    
    private DialogBuilder addDialog;
    
	private DefaultMutableTreeNode root;
	
	public TreeNavigator(JPanel displayPanel, List<UIBuilderPanel> panels) {
		this.displayPanel = displayPanel;
		this.panels = panels;
		
		init();
		createTree();
	}
	
	private void createTree() {	
		root = new DefaultMutableTreeNode("Trips");

		tree.setModel(null); //this removes all nodes
	    
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

		//Listen for when the selection changes.
		tree.addTreeSelectionListener(new TreeNavigatorSelectionListener(tree, displayPanel));
					
	    DefaultMutableTreeNode tempChild = null;
	    
	    for(JPanel entry : panels) {
		    tempChild = new DefaultMutableTreeNode(entry);
		    root.add(tempChild);
		    displayPanel.add(entry, entry.toString());
	    }
	    
		TreeModel model = new DefaultTreeModel(root);
		tree.setModel(model);

	}
	
	private void init() {
        scrollPane = new javax.swing.JScrollPane();
        tree = new JTree();
        addButton = new javax.swing.JButton();
        removeButton = new javax.swing.JButton();
        
        ImageIcon addIcon = GraphicUtils.createImageIcon(
        		StringConstants.ADD_BUTTON_IMAGE_PATH,
        		StringConstants.ADD_BUTTON_DESCRIPTION);
        addButton.setIcon(addIcon);
        addButton.setToolTipText(ToolTipConstants.ADD_ELEMENT_TOOLTIP);
        
        ImageIcon removeIcon = GraphicUtils.createImageIcon(
        		StringConstants.REMOVE_BUTTON_IMAGE_PATH,
        		StringConstants.REMOVE_BUTTON_DESCRIPTION);
        removeButton.setIcon(removeIcon);
        removeButton.setToolTipText(ToolTipConstants.REMOVE_ELEMENT_TOOLTIP);
        
        scrollPane.setViewportView(tree);
        
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	try {
            		addButtonActionPerformed(evt);
            	} catch (Exception e) {
            		System.out.println(e);
            	}
            }
        });
        
        removeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	removeButtonActionPerformed(evt);
            }
        });
        		
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 180, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(removeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(removeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 275, Short.MAX_VALUE)))
                .addContainerGap(14, Short.MAX_VALUE))
        );
	}
	
	@Override
	public void addEntry(Object entry) throws NotUniqueEntryException {
		UIBuilderPanel newPanel = (UIBuilderPanel)entry;
		for(UIBuilderPanel panel : panels) {
			if(panel.toString().equals(newPanel.toString())) {
				throw new NotUniqueEntryException();
			}
		}
		
		DefaultMutableTreeNode tempChild = new DefaultMutableTreeNode(newPanel);
		root.add(tempChild);
		panels.add(newPanel);
	    displayPanel.add(newPanel, newPanel.toString());
		createTree();
	}

	public void removeEntry(DefaultMutableTreeNode node) {
		node.setParent(null);
		panels.remove((JPanel)node.getUserObject());
		displayPanel.remove((JPanel)node.getUserObject());
		createTree();
	}
	
    private void removeButtonActionPerformed(ActionEvent evt) {
        //Returns the last path element of the selection.
        //This method is useful only when the selection model allows a single selection.
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();

        if (node == null)
        //Nothing is selected.  
        return;
    	
    	removeEntry(node);
	}
    
	private void addButtonActionPerformed(ActionEvent evt) throws NoDialogRegisteredException {
		getAddDialog().setVisible(true);	
	}
	
	public void registerAddDialog(DialogBuilder addDialog) {
		this.addDialog = addDialog;
	}

	public DialogBuilder getAddDialog() throws NoDialogRegisteredException {
		if(addDialog == null) {
			throw new NoDialogRegisteredException();
		}
		return addDialog;
	}
}
