package core.ui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import core.IAddTreeDialog;
import core.IDialogCreator;
import core.NoDialogRegisteredException;
import core.UIBuilder;
import core.dialogs.ElementTypeDialogCreator;
import core.CoreConstants;
import core.events.GetElementTypeEvent;
import core.events.HideWindowEvent;
import core.ui.entrypanels.GenericExclusiveSelectionPanel;
import core.util.GraphicUtils;
import core.util.Pair;

/**
*
* @author Lawrence
*/
public class TreeNavigator extends JPanel implements IAddTreeDialog {
	
	private List<UIBuilderPanel> panels;

    private JScrollPane scrollPane;
	
	private JTree tree;
    private JButton addButton;
    private JButton removeButton;
    
    private JPanel displayPanel;
    
    private IDialogCreator addDialogCreator;
    
	private DefaultMutableTreeNode root;
	private DefaultTreeModel model;
	
	private ArrayList<Pair<String, IDialogCreator>> topLevelElements;
	
	public TreeNavigator() {
		this.topLevelElements = new ArrayList<Pair<String, IDialogCreator>>();
		this.panels = new ArrayList<UIBuilderPanel>();
		
		this.root = new DefaultMutableTreeNode("rootTreeNode");
		this.model = new DefaultTreeModel(root);
				
		this.addDialogCreator = new ElementTypeDialogCreator();
		
		init();
		createTree();
	}
	
	private void createTree() {	
		tree.expandRow(0);
		tree.setRootVisible(false);
	    
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
					
		tree.setModel(model);

	}
	
	private void init() {
        scrollPane = new javax.swing.JScrollPane();
        tree = new JTree();
        addButton = new javax.swing.JButton();
        removeButton = new javax.swing.JButton();
        
        ImageIcon addIcon = GraphicUtils.createImageIcon(
        		CoreConstants.ADD_BUTTON_IMAGE_PATH,
        		CoreConstants.ADD_BUTTON_DESCRIPTION);
        addButton.setIcon(addIcon);
        addButton.setToolTipText(CoreConstants.ADD_ELEMENT_TOOLTIP);
        
        ImageIcon removeIcon = GraphicUtils.createImageIcon(
        		CoreConstants.REMOVE_BUTTON_IMAGE_PATH,
        		CoreConstants.REMOVE_BUTTON_DESCRIPTION);
        removeButton.setIcon(removeIcon);
        removeButton.setToolTipText(CoreConstants.REMOVE_ELEMENT_TOOLTIP);
        
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
	
	public void setDisplayPanel(JPanel displayPanel) {
		this.displayPanel = displayPanel;
		//Listen for when the selection changes.
		tree.addTreeSelectionListener(new TreeNavigatorSelectionListener(tree, displayPanel));
	}
	
	@Override
	public void addEntry(Object entry) {
		UIBuilder newPanel = (UIBuilder)entry;
		
		DefaultMutableTreeNode parentNode = null;
	    TreePath parentPath = tree.getSelectionPath();

	    if (parentPath == null) {
	        //There is no selection. Default to the root node.
	        parentNode = root;
	    } else {
	        parentNode = (DefaultMutableTreeNode)
	                     (parentPath.getLastPathComponent());
	    }

	    addEntry(parentNode, newPanel, true);
	}

	@Override
	public void addEntry(DefaultMutableTreeNode parent,
	                                        Object entry,
	                                        boolean shouldBeVisible) {
		UIBuilder newPanel = (UIBuilder)entry;

	    DefaultMutableTreeNode childNode =
	            new DefaultMutableTreeNode(newPanel);

	    model.insertNodeInto(childNode, parent, parent.getChildCount());
		
	    panels.add(newPanel.getPanel());
	    displayPanel.add(newPanel.getPanel(), newPanel.toString());

	    //Make sure the user can see the lovely new node.
	    if (shouldBeVisible) {
	        tree.scrollPathToVisible(new TreePath(childNode.getPath()));
	    }
	}

	public void addFolderEntry(String folderName, IDialogCreator dialog) {
		DefaultMutableTreeNode folder = new DefaultMutableTreeNode(folderName);
	    model.insertNodeInto(folder, root, root.getChildCount());
	    topLevelElements.add(new Pair<String, IDialogCreator>(folderName, dialog));
	}

	public void removeEntry(DefaultMutableTreeNode node) {
		panels.remove(((UIBuilder)node.getUserObject()).getPanel());
		displayPanel.remove(((UIBuilder)node.getUserObject()).getPanel());
		model.removeNodeFromParent(node);
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
	
	public void registerAddDialog(IDialogCreator addDialogCreator) {
		this.addDialogCreator = addDialogCreator;
	}

	public DialogBuilder getAddDialog() throws NoDialogRegisteredException {
		if(addDialogCreator == null) {
			throw new NoDialogRegisteredException();
		}
		return addDialogCreator.getNewInstance(this);
	}
	
	public DialogBuilder getAddDialogForString(String folder) {		
		for(Pair<String, IDialogCreator> element : topLevelElements) {
			if(element.getFirst().equals(folder)) {
				return element.getSecond().getNewInstance(this);
			}
		}
		return null;
	}
	
	public DefaultMutableTreeNode getFolder(String folderName) {
		DefaultMutableTreeNode result = null;
		
		for (int i = 0; i < root.getChildCount(); ++i) {
			if(((DefaultMutableTreeNode)root.getChildAt(i)).getUserObject().equals(folderName)) {
				result = (DefaultMutableTreeNode) root.getChildAt(i);
			}
		}
		
		return result;
	}
	
	public ArrayList<String> getTopLevelElements() {
		ArrayList<String> elements = new ArrayList<String>();
		
		for (Pair<String, IDialogCreator> element : topLevelElements) {
			elements.add(element.getFirst());
		}
		
		return elements;
	}
	
	public DefaultTreeModel getModel() {
		return model;
	}
	
	public void importData(ArrayList<Object> data) {
		this.model = (DefaultTreeModel) data.get(1);
		this.topLevelElements = (ArrayList<Pair<String, IDialogCreator>>) data.get(0);
		
		this.root = (DefaultMutableTreeNode) model.getRoot();
		
		this.panels = new ArrayList<UIBuilderPanel>();
		
		for (int i = 0; i < root.getChildCount(); ++i) {
			for (int j = 0; j < root.getChildAt(i).getChildCount(); ++j) {
				this.panels.add(((UIBuilder)(
									(DefaultMutableTreeNode)(
											root.getChildAt(i).getChildAt(j)))
									.getUserObject())
								.getPanel());
			}
		}
		
		tree.setModel(model);
		expandAllNodes(0, tree.getRowCount());	
		
		displayPanel.removeAll();
		for (UIBuilderPanel panel : this.panels) {
			displayPanel.add(panel, panel.toString());
		}
	}
	
	public ArrayList<Object> getExportData(){
		ArrayList<Object> data = new ArrayList<Object>();
		
		data.add(topLevelElements);
		data.add(model);
		
		return data;
	}
	
	private void expandAllNodes(int startingIndex, int rowCount){
	    for(int i=startingIndex;i<rowCount;++i){
	        tree.expandRow(i);
	    }

	    if(tree.getRowCount()!=rowCount){
	        expandAllNodes(rowCount, tree.getRowCount());
	    }
	}
}
