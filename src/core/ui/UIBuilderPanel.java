package core.ui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import core.IColumnPlugin;
import core.ILabelPlugin;
import core.NoDialogRegisteredException;
import core.CoreConstants;
import core.UIBuilder;
import core.util.GraphicUtils;

/**
* The generated GUI panel created from a UIBuilder instance. Displays the 
* UIBuilder table and the InfoLabels.
* 
* @author Lawrence
*/
public class UIBuilderPanel<T> extends javax.swing.JPanel {
		
    private javax.swing.JPanel labelContainer;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JTable table;
    
    private javax.swing.JButton addButton;
    private javax.swing.JButton removeButton;
        
    /**
     * The UIBuilder instance to generate from.
     */
    private UIBuilder<T> builder;
    
    /**
     * Creates new panel UIBuilderPanel
     */
    public UIBuilderPanel(UIBuilder<T> builder) {
    	this.builder = builder;
    	
        initComponents();
    }

    /**
     * Setup the GUI components.
     */
    private void initComponents() {

        scrollPane = new javax.swing.JScrollPane();
        table = generateTable();
        labelContainer = generatePanels();
        
        addButton = new javax.swing.JButton();
        removeButton = new javax.swing.JButton();
        
        ImageIcon addIcon = GraphicUtils.createImageIcon(
        		CoreConstants.ADD_BUTTON_IMAGE_PATH,
        		CoreConstants.ADD_BUTTON_DESCRIPTION);
        addButton.setIcon(addIcon);
        addButton.setToolTipText(CoreConstants.ADD_ROW_TOOLTIP);
        
        ImageIcon removeIcon = GraphicUtils.createImageIcon(
        		CoreConstants.REMOVE_BUTTON_IMAGE_PATH,
        		CoreConstants.REMOVE_BUTTON_DESCRIPTION);
        removeButton.setIcon(removeIcon);
        removeButton.setToolTipText(CoreConstants.REMOVE_ROW_TOOLTIP);

        scrollPane.setViewportView(table);
        
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add(evt);
            }
        });
        
        removeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                remove(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(removeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelContainer, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(72, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(removeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(labelContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)))
                .addContainerGap(58, Short.MAX_VALUE))
        );

    }           
    
    /**
     * Remove an element from the table.
     * 
     * @param evt - The event information.
     */
    private void remove(ActionEvent evt) {
    	if (table.getSelectedRow() != -1) {
    		builder.removeEntry(table.getSelectedRow());
    		refreshModel();	
    	}
	}

	/**
	 * Add an element to the table.
	 * 
	 * @param evt - The event information.
	 */
	private void add(ActionEvent evt) {    
    	try {
	    	DialogBuilder addDialog = builder.getAddDialog();
	    	addDialog.setVisible(true);
	    	refreshModel();
    	} catch (NoDialogRegisteredException e) {
			 JOptionPane.showMessageDialog(this, "Something has gone wrong with the set up"
					 + " I don't know how to add to the table!");
    	}
    }

	/**
	 * Generate the table based of the given UIBuilder.
	 * 
     * @param <T> - The primary key type.
     * @param trip: the trip object to display
     */
    private JTable generateTable() {
    	JTable table = new javax.swing.JTable();
    	table.setModel(generateModel());
    
    	generatePluginEditors(table);
    	generatePluginRenderers(table);
    	
    	return table;
    }
    
    /**
     * Add the plugin editors to their table rows.
     * 
     * @param table - The table to add to.
     */
    private void generatePluginEditors(JTable table) {
    	for(int i = 1; i < table.getColumnCount(); ++i) {
    		table.getColumnModel().getColumn(i).setCellEditor(builder.getColumnPlugins().get(i-1).getCellEditor());
    	}
      }
    
    /**
     * Add the plugin renderers to their table rows.
     * 
     * @param table - The table to add to.
     */
    private void generatePluginRenderers(JTable table) {
    	for(int i = 1; i < table.getColumnCount(); ++i) {
    		table.getColumnModel().getColumn(i).setCellRenderer(builder.getColumnPlugins().get(i-1).getCellRenderer());
    	}  
    }
    
    /**
     * Generate the table values based of the given UIBuilder.
     */
    private DefaultTableModel generateModel() {
    	DefaultTableModel model = new DefaultTableModel();
    	
    	model.addColumn(builder.getPrimaryKeyListTitle(), builder.getPrimaryKeyStringList().toArray());
    	
    	for(IColumnPlugin<T> plugin : builder.getColumnPlugins()) {
    		model.addColumn(plugin.getTitle(), plugin.getTableEntries(builder.getPrimaryKeyList()).toArray());
    	}

    	return model;
    }
    
    /**
     * Generate the infoPanels to display.
     * 
     * @return The container panel with all the InfoPanels add to it.
     */
    private JPanel generatePanels() {
    	javax.swing.JPanel panel = new javax.swing.JPanel();
    	panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
    	
    	for( ILabelPlugin plugin : builder.getLabelPlugins()) {
    		panel.add(new InfoLabel(plugin.getTitle(), plugin.getInfo()));
    		panel.add(Box.createRigidArea(new Dimension(0,5)));
    	}
    	
    	return panel;
	}
    
    /* (non-Javadoc)
     * @see java.awt.Component#toString()
     */
    @Override
    public String toString() {
    	return builder.getTitle();
    }
    
    /**
     * Update the table model.
     */
    public void refreshModel() {
    	if (table != null) {
        	table.setModel(generateModel());
            
        	generatePluginEditors(table);
        	generatePluginRenderers(table);
    	} else {
    		System.out.println("Refresh called on empty model");
    	}
    }
 
}
