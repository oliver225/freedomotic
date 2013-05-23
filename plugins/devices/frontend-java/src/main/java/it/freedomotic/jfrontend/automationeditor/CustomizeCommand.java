/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * CustomizeEvent.java
 *
 * Created on 4-set-2010, 9.57.19
 */
package it.freedomotic.jfrontend.automationeditor;

import com.google.inject.Inject;

import it.freedomotic.api.Client;
import it.freedomotic.api.Plugin;

import it.freedomotic.app.Freedomotic;

import it.freedomotic.plugins.ClientStorage;

import it.freedomotic.reactions.Command;
import it.freedomotic.reactions.CommandPersistence;

import it.freedomotic.util.i18n;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author enrico
 */
public class CustomizeCommand
        extends javax.swing.JFrame {

    ReactionList main;
    Command original;
    DefaultTableModel model = new DefaultTableModel();
    JTable table;
    @Inject
    private ClientStorage clients;

    /**
     * Creates new form CustomizeEvent
     */
    public CustomizeCommand(ReactionList main, Command original) {
        initComponents();
        this.main = main;
        this.original = original;
        txtName.setText(original.getName());
        txtDescription.setText(original.getDescription());

        model.addColumn(i18n.msg("property"));
        model.addColumn(i18n.msg("value"));
        table = new JTable(model);
        pnlParam.add(table);

        int row = 0;

        for (Entry e : original.getProperties().entrySet()) {
            List list = new ArrayList();
            list.add(e.getKey().toString());
            list.add(e.getValue().toString());
            model.insertRow(row,
                    list.toArray());
            row++;
        }

        this.toFront();
    }

    private void enqueueReceivers() {
        DefaultComboBoxModel receiversModel = new DefaultComboBoxModel();

        for (Client c : clients.getClients()) {
            receiversModel.addElement(c.getName());
        }

        cmbReceiver.setModel(receiversModel);
    }

    private Command fillWithFormData() {
        try {
            table.getCellEditor().stopCellEditing();
        } catch (Exception e) {
        }

        Command c = new Command();
        c.setName(txtName.getText());
        c.setDescription(txtDescription.getText());

        if (cmbReceiver.isEnabled()) {
            Plugin plugin = (Plugin) clients.get((String) cmbReceiver.getSelectedItem());
            c.setReceiver(plugin.getReadQueue());
        } else {
            c.setReceiver(original.getReceiver());
        }

        System.out.println("receiver for  " + c.getName() + " is: " + c.getReceiver());

        for (int r = 0; r < model.getRowCount(); r++) {
            c.setProperty(model.getValueAt(r, 0).toString(),
                    model.getValueAt(r, 1).toString());
        }

        System.out.println(c.getProperties().toString());

        return c;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings( "unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents(  )
    {
        btnSave = new javax.swing.JButton(  );
        jLabel1 = new javax.swing.JLabel(  );
        txtName = new javax.swing.JTextField(  );
        jLabel2 = new javax.swing.JLabel(  );
        txtDescription = new javax.swing.JTextField(  );
        jLabel3 = new javax.swing.JLabel(  );
        btnEdit = new javax.swing.JButton(  );
        cmbReceiver = new javax.swing.JComboBox(  );
        txtReceiver = new javax.swing.JLabel(  );
        btnChangeReceiver = new javax.swing.JButton(  );
        btnDelete = new javax.swing.JButton(  );
        jScrollPane1 = new javax.swing.JScrollPane(  );
        pnlParam = new javax.swing.JPanel(  );
        txtAddRow = new javax.swing.JButton(  );

        setDefaultCloseOperation( javax.swing.WindowConstants.DISPOSE_ON_CLOSE );

        btnSave.setText( i18n.msg( "save_as_new" ) );
        btnSave.addActionListener( new java.awt.event.ActionListener(  )
            {
                public void actionPerformed( java.awt.event.ActionEvent evt )
                {
                    btnSaveActionPerformed( evt );
                }
            } );

        jLabel1.setText( i18n.msg( "name" ) + ":" );

        jLabel2.setText( i18n.msg( "description" ) + ":" );

        jLabel3.setText( i18n.msg( "parameters" ) + ":" );

        btnEdit.setText( i18n.msg( "save_changes" ) );
        btnEdit.addActionListener( new java.awt.event.ActionListener(  )
            {
                public void actionPerformed( java.awt.event.ActionEvent evt )
                {
                    btnEditActionPerformed( evt );
                }
            } );

        cmbReceiver.setEnabled( false );

        txtReceiver.setText( i18n.msg( this, "performed_by" ) );
        txtReceiver.setEnabled( false );

        btnChangeReceiver.setText( i18n.msg( "change" ) );
        btnChangeReceiver.addActionListener( new java.awt.event.ActionListener(  )
            {
                public void actionPerformed( java.awt.event.ActionEvent evt )
                {
                    btnChangeReceiverActionPerformed( evt );
                }
            } );

        btnDelete.setText( i18n.msg( "delete_X",
                                     new Object[] { i18n.msg( "command" ) } ) );
        btnDelete.addActionListener( new java.awt.event.ActionListener(  )
            {
                public void actionPerformed( java.awt.event.ActionEvent evt )
                {
                    btnDeleteActionPerformed( evt );
                }
            } );

        jScrollPane1.setVerticalScrollBarPolicy( javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );

        pnlParam.setLayout( new java.awt.BorderLayout(  ) );
        jScrollPane1.setViewportView( pnlParam );

        txtAddRow.setText( i18n.msg( "add_X",
                                     new Object[] { i18n.msg( "parameter" ) } ) );
        txtAddRow.addActionListener( new java.awt.event.ActionListener(  )
            {
                public void actionPerformed( java.awt.event.ActionEvent evt )
                {
                    txtAddRowActionPerformed( evt );
                }
            } );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout( getContentPane(  ) );
        getContentPane(  ).setLayout( layout );
        layout.setHorizontalGroup( layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING )
                                         .addGroup( layout.createSequentialGroup(  ).addContainerGap(  )
                                                          .addGroup( layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING )
                                                                           .addComponent( jScrollPane1,
                                                                                          javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                          590, Short.MAX_VALUE )
                                                                           .addGroup( layout.createSequentialGroup(  )
                                                                                            .addGroup( layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING )
                                                                                                             .addComponent( jLabel1,
                                                                                                                            javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                            103,
                                                                                                                            javax.swing.GroupLayout.PREFERRED_SIZE )
                                                                                                             .addComponent( jLabel2 )
                                                                                                             .addComponent( txtReceiver ) )
                                                                                            .addPreferredGap( javax.swing.LayoutStyle.ComponentPlacement.RELATED )
                                                                                            .addGroup( layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING )
                                                                                                             .addComponent( txtDescription,
                                                                                                                            javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                            483,
                                                                                                                            Short.MAX_VALUE )
                                                                                                             .addGroup( layout.createSequentialGroup(  )
                                                                                                                              .addComponent( cmbReceiver,
                                                                                                                                             0,
                                                                                                                                             385,
                                                                                                                                             Short.MAX_VALUE )
                                                                                                                              .addPreferredGap( javax.swing.LayoutStyle.ComponentPlacement.RELATED )
                                                                                                                              .addComponent( btnChangeReceiver ) )
                                                                                                             .addComponent( txtName,
                                                                                                                            javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                            483,
                                                                                                                            Short.MAX_VALUE ) ) )
                                                                           .addGroup( layout.createSequentialGroup(  )
                                                                                            .addComponent( jLabel3 )
                                                                                            .addPreferredGap( javax.swing.LayoutStyle.ComponentPlacement.RELATED )
                                                                                            .addComponent( txtAddRow ) )
                                                                           .addGroup( layout.createSequentialGroup(  )
                                                                                            .addComponent( btnEdit )
                                                                                            .addPreferredGap( javax.swing.LayoutStyle.ComponentPlacement.UNRELATED )
                                                                                            .addComponent( btnSave,
                                                                                                           javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                           165,
                                                                                                           javax.swing.GroupLayout.PREFERRED_SIZE )
                                                                                            .addGap( 10, 10, 10 )
                                                                                            .addComponent( btnDelete ) ) )
                                                          .addContainerGap(  ) ) );
        layout.setVerticalGroup( layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING )
                                       .addGroup( layout.createSequentialGroup(  ).addContainerGap(  )
                                                        .addGroup( layout.createParallelGroup( javax.swing.GroupLayout.Alignment.BASELINE )
                                                                         .addComponent( jLabel1 )
                                                                         .addComponent( txtName,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE ) )
                                                        .addPreferredGap( javax.swing.LayoutStyle.ComponentPlacement.RELATED )
                                                        .addGroup( layout.createParallelGroup( javax.swing.GroupLayout.Alignment.BASELINE )
                                                                         .addComponent( jLabel2,
                                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                        Short.MAX_VALUE )
                                                                         .addComponent( txtDescription,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                        41,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE ) )
                                                        .addPreferredGap( javax.swing.LayoutStyle.ComponentPlacement.UNRELATED )
                                                        .addGroup( layout.createParallelGroup( javax.swing.GroupLayout.Alignment.BASELINE )
                                                                         .addComponent( txtReceiver )
                                                                         .addComponent( cmbReceiver,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE )
                                                                         .addComponent( btnChangeReceiver ) )
                                                        .addGap( 18, 18, 18 )
                                                        .addGroup( layout.createParallelGroup( javax.swing.GroupLayout.Alignment.BASELINE )
                                                                         .addComponent( jLabel3 ).addComponent( txtAddRow ) )
                                                        .addPreferredGap( javax.swing.LayoutStyle.ComponentPlacement.RELATED )
                                                        .addComponent( jScrollPane1,
                                                                       javax.swing.GroupLayout.PREFERRED_SIZE, 226,
                                                                       javax.swing.GroupLayout.PREFERRED_SIZE )
                                                        .addPreferredGap( javax.swing.LayoutStyle.ComponentPlacement.UNRELATED )
                                                        .addGroup( layout.createParallelGroup( javax.swing.GroupLayout.Alignment.BASELINE )
                                                                         .addComponent( btnDelete ).addComponent( btnEdit )
                                                                         .addComponent( btnSave ) ).addContainerGap(  ) ) );

        jLabel3.getAccessibleContext(  ).setAccessibleName( "" );

        pack(  );
    } // </editor-fold>//GEN-END:initComponents

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt)    {//GEN-FIRST:event_btnSaveActionPerformed

        Command c = fillWithFormData();
        c.setEditable(true); //sets it needs to be saved on disk

        int preSize = CommandPersistence.size();
        CommandPersistence.add(c);

        int postSize = CommandPersistence.size();

        if (preSize < postSize) {
            Freedomotic.logger.info("Command addedd correctly [" + postSize + " commands]");
        } else {
            Freedomotic.logger.severe("Error while adding a command");
        }

        main.setTargetCommand(c);
        this.dispose();
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnChangeReceiverActionPerformed(java.awt.event.ActionEvent evt)    {//GEN-FIRST:event_btnChangeReceiverActionPerformed
        enqueueReceivers();
        txtReceiver.setEnabled(true);
        cmbReceiver.setEnabled(true);
    }//GEN-LAST:event_btnChangeReceiverActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt)    {//GEN-FIRST:event_btnEditActionPerformed

        Command newCommand = fillWithFormData();
        newCommand.setEditable(true);

        int preSize = CommandPersistence.size();
        CommandPersistence.remove(original);
        CommandPersistence.add(newCommand);

        int postSize = CommandPersistence.size();

        if (preSize == postSize) {
            Freedomotic.logger.info("Command edited correctly [" + postSize + " commands]");
        } else {
            Freedomotic.logger.severe("Error while edit a command");
        }

        main.setTargetCommand(newCommand);
        this.dispose();
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt)    {//GEN-FIRST:event_btnDeleteActionPerformed
        Freedomotic.logger.info("Trying to remove a commend from the list");
        CommandPersistence.remove(original);
        main.updateData();
        this.dispose();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void txtAddRowActionPerformed(java.awt.event.ActionEvent evt)    {//GEN-FIRST:event_txtAddRowActionPerformed
        model.addRow(new Object[]{"", "", "", ""});
    }//GEN-LAST:event_txtAddRowActionPerformed
      // Variables declaration - do not modify//GEN-BEGIN:variables

    private javax.swing.JButton btnChangeReceiver;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox cmbReceiver;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnlParam;
    private javax.swing.JButton txtAddRow;
    private javax.swing.JTextField txtDescription;
    private javax.swing.JTextField txtName;
    private javax.swing.JLabel txtReceiver;

    // End of variables declaration//GEN-END:variables
}
