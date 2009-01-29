/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * JFileCrypterFrame.java
 *
 * Created on 29.01.2009, 15:22:29
 */

package jfilecrypter;

import java.io.File;
import java.security.SecureRandom;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author uli
 */
public class JFileCrypterFrame extends javax.swing.JFrame {

    /** Creates new form JFileCrypterFrame */
    public JFileCrypterFrame() {
        initComponents();
        //Force RNG seeding
    }


    private void cryptSymmetric(boolean encrypt)
    {
        try
        {
            //Init the cipher
            Runtime r = Runtime.getRuntime();
            StringBuilder cmdBuilder = new StringBuilder("openssl aes-256-cbc");
            //I/O filenames
            cmdBuilder.append(" -in \"" + inputFileField.getText() + "\"");
            cmdBuilder.append(" -out \"" + outputFileField.getText() + "\"");
            //Encrypt/decrypt
            if(decryptCheckbox.isSelected())
            {
                cmdBuilder.append(" -d");
            }
            else
            {
                cmdBuilder.append(" -e");
            }
            //Password
            cmdBuilder.append(" -k \"" + new String(passwordField.getPassword()) + "\"");

            /**
             * Execute the command
             */
            r.exec(cmdBuilder.toString());
            System.out.println("\"" + cmdBuilder.toString() + "\"");
            
            //Display a success message
            JOptionPane.showMessageDialog(this, "Operation successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
            return; //Don't show the error dialog
        }
        catch (Exception ex)
        {
            Logger.getLogger(JFileCrypterFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        //If a exception occured this message is displayed
        JOptionPane.showMessageDialog(this, "Operation error!", "Error", JOptionPane.ERROR_MESSAGE);
    }


    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        inputFileLabel = new javax.swing.JLabel();
        inputFileField = new javax.swing.JTextField();
        outputFileLabel = new javax.swing.JLabel();
        outputFileField = new javax.swing.JTextField();
        decryptCheckbox = new javax.swing.JCheckBox();
        okButton = new javax.swing.JButton();
        passwordField = new javax.swing.JPasswordField();
        passwordLabel = new javax.swing.JLabel();
        selectInputFileButton = new javax.swing.JButton();
        selectOutputFileButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle( i18n.getString("JFileCrypterFrame.title")); // NOI18N

        inputFileLabel.setText( i18n.getString("JFileCrypterFrame.inputFileLabel.text")); // NOI18N

        inputFileField.setText( i18n.getString("JFileCrypterFrame.inputFileField.text")); // NOI18N

        outputFileLabel.setText( i18n.getString("JFileCrypterFrame.outputFileLabel.text")); // NOI18N

        outputFileField.setText( i18n.getString("JFileCrypterFrame.outputFileField.text")); // NOI18N

        decryptCheckbox.setMnemonic('d');
        decryptCheckbox.setText(i18n.getString("JFileCrypterFrame.decryptCheckbox.text")); // NOI18N

        okButton.setMnemonic('o');
        okButton.setText(i18n.getString("JFileCrypterFrame.okButton.text")); // NOI18N
        okButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                okButtonMouseClicked(evt);
            }
        });

        passwordLabel.setDisplayedMnemonic('p');
        passwordLabel.setText(i18n.getString("JFileCrypterFrame.passwordLabel.text")); // NOI18N

        selectInputFileButton.setText(i18n.getString("JFileCrypterFrame.selectInputFileButton.text")); // NOI18N
        selectInputFileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectInputFileButtonActionPerformed(evt);
            }
        });

        selectOutputFileButton.setText(i18n.getString("JFileCrypterFrame.selectOutputFileButton.text")); // NOI18N
        selectOutputFileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectOutputFileButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(inputFileLabel)
                            .addComponent(outputFileLabel))
                        .addGap(7, 7, 7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(outputFileField, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(inputFileField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(selectOutputFileButton)
                                    .addComponent(selectInputFileButton)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(decryptCheckbox)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(okButton, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE))
                            .addComponent(passwordField, javax.swing.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE)))
                    .addComponent(passwordLabel))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inputFileLabel)
                    .addComponent(inputFileField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(selectInputFileButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(outputFileLabel)
                    .addComponent(outputFileField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(selectOutputFileButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(passwordLabel)
                    .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(decryptCheckbox)
                    .addComponent(okButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void okButtonMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_okButtonMouseClicked
    {//GEN-HEADEREND:event_okButtonMouseClicked
        if (decryptCheckbox.isSelected())
        {
            cryptSymmetric(false);
        }
        else
        {
            cryptSymmetric(true);
        }
}//GEN-LAST:event_okButtonMouseClicked

    private void selectInputFileButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_selectInputFileButtonActionPerformed
    {//GEN-HEADEREND:event_selectInputFileButtonActionPerformed
        fileChooser.setSelectedFile(new File(inputFileField.getText()));
        fileChooser.showSaveDialog(this);
        inputFileField.setText(fileChooser.getSelectedFile().getAbsolutePath());
}//GEN-LAST:event_selectInputFileButtonActionPerformed

    private void selectOutputFileButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_selectOutputFileButtonActionPerformed
    {//GEN-HEADEREND:event_selectOutputFileButtonActionPerformed
        fileChooser.setSelectedFile(new File(inputFileField.getText()));
        fileChooser.showSaveDialog(this);
        outputFileField.setText(fileChooser.getSelectedFile().getAbsolutePath());
}//GEN-LAST:event_selectOutputFileButtonActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFileCrypterFrame().setVisible(true);
            }
        });
    }

    private JFileChooser fileChooser = new JFileChooser();
    private SecureRandom rand = new SecureRandom();
    private ResourceBundle i18n = ResourceBundle.getBundle("jfilecrypter/Bundle"); //NOI18N
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox decryptCheckbox;
    private javax.swing.JTextField inputFileField;
    private javax.swing.JLabel inputFileLabel;
    private javax.swing.JButton okButton;
    private javax.swing.JTextField outputFileField;
    private javax.swing.JLabel outputFileLabel;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JButton selectInputFileButton;
    private javax.swing.JButton selectOutputFileButton;
    // End of variables declaration//GEN-END:variables

}
