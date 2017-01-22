package core.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import core.IColumnPlugin;
import core.ILabelPlugin;
import core.NoDialogRegisteredException;
import core.UIBuilder;
import core.util.GraphicUtils;
import tripTracker.StringConstants;
import tripTracker.ToolTipConstants;

public class UIBuilderPanel<T> extends javax.swing.JPanel {
		
    private javax.swing.JPanel labelContainer;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JTable table;
    
    private javax.swing.JButton addButton;
    private javax.swing.JButton removeButton;
        
    private UIBuilder<T> builder;
    
    /**
     * Creates new panel UIBuilderPanel
     */
    public UIBuilderPanel(UIBuilder<T> builder) {
    	this.builder = builder;
    	
        initComponents(builder);
    }

    /**
     * This method is called from within the constructor to initialise the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     * @param <T>
     */
    @SuppressWarnings("unchecked")
    private void initComponents(UIBuilder<T> builder) {

        scrollPane = new javax.swing.JScrollPane();
        table = generateTable();
        labelContainer = generatePanels(builder);
        
        addButton = new javax.swing.JButton();
        removeButton = new javax.swing.JButton();
        
        ImageIcon addIcon = GraphicUtils.createImageIcon(
        		StringConstants.ADD_BUTTON_IMAGE_PATH,
        		StringConstants.ADD_BUTTON_DESCRIPTION);
        addButton.setIcon(addIcon);
        addButton.setToolTipText(ToolTipConstants.ADD_ROW_TOOLTIP);
        
        ImageIcon removeIcon = GraphicUtils.createImageIcon(
        		StringConstants.REMOVE_BUTTON_IMAGE_PATH,
        		StringConstants.REMOVE_BUTTON_DESCRIPTION);
        removeButton.setIcon(removeIcon);
        removeButton.setToolTipText(ToolTipConstants.REMOVE_ROW_TOOLTIP);


        scrollPane.setViewportView(table);
        
//        table.getModel().addTableModelListener(new TableModelListener() {
//
//            public void tableChanged(TableModelEvent e) {
//               //System.out.println(e);
//            }
//          });
        
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
    
    private void remove(ActionEvent evt) {
		builder.removeEntry(table.getSelectedRow());
		refreshModel();
	}

	private void add(java.awt.event.ActionEvent evt) {    
    	try {
	    	DialogBuilder addDialog = builder.getAddDialog();
	    	addDialog.setVisible(true);
	    	refreshModel();
    	} catch (NoDialogRegisteredException e) {
    		System.out.println(e);
    	}
    }

	/**
     * @param <T>
     * @param trip: the trip object to display
     */
    private JTable generateTable() {
    	JTable table = new javax.swing.JTable();
    	table.setModel(generateModel());
    
    	generatePluginEditors(table);
    	generatePluginRenderers(table);
    	
    	return table;
    }
    
    private void generatePluginEditors(JTable table) {
    	for(int i = 1; i < table.getColumnCount(); ++i) {
    		table.getColumnModel().getColumn(i).setCellEditor(builder.getPlugins().get(i-1).getCellEditor());
    	}
      }
    
    private void generatePluginRenderers(JTable table) {
    	for(int i = 1; i < table.getColumnCount(); ++i) {
    		table.getColumnModel().getColumn(i).setCellRenderer(builder.getPlugins().get(i-1).getCellRenderer());
    	}  
    }
    
    /**
     * @param <T>
     * @param trip: the trip object to display
     */
    private javax.swing.table.DefaultTableModel generateModel() {
    	javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel();
    	
    	model.addColumn(builder.getPrimaryKeyListTitle(), builder.getPrimaryKeyStringList().toArray());
    	
    	for(IColumnPlugin<T> plugin : builder.getPlugins()) {
    		model.addColumn(plugin.getTitle(), plugin.getTableEntries(builder.getPrimaryKeyList()).toArray());
    	}

    	return model;
    }
    
    private JPanel generatePanels(UIBuilder<T> builder) {
    	javax.swing.JPanel panel = new javax.swing.JPanel();
    	panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
    	panel.setBorder(BorderFactory.createLineBorder(Color.black));
    	
    	for( ILabelPlugin plugin : builder.getLabelPlugins()) {
    		panel.add(new InfoLabel(plugin.getTitle(), plugin.getInfo()));
    		panel.add(Box.createRigidArea(new Dimension(0,5)));
    	}
    	
    	return panel;
	}
    
    @Override
    public String toString() {
    	return builder.getTitle();
    }
    
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
