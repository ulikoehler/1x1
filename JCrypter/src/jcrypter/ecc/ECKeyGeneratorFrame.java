/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ECKeyGeneratorFrame.java
 *
 * Created on 10.09.2008, 23:25:49
 */
package jcrypter.signature;

import java.awt.EventQueue;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.Security;
import java.security.spec.ECGenParameterSpec;
import java.util.Enumeration;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import jcrypter.JCrypterFrame;
import jcrypter.utils.KeyGeneratorFrame;
import org.bouncycastle.jce.ECGOST3410NamedCurveTable;
import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

/**
 *
 * @author uli
 */
public class ECKeyGeneratorFrame extends javax.swing.JFrame
{

    /** Creates new form ECKeyGeneratorFrame
     * @param parent The calling KeyGeneratorFrame
     */
    public ECKeyGeneratorFrame(KeyGeneratorFrame parent)
    {
        initComponents();
        //Register Bouncy castle provider
        Security.addProvider(new BouncyCastleProvider());
        //Populate the named curve selection combo box
        loadEcdsaCurves();
        //Set the parent reference
        this.parent = parent;
        //Add the items to the key type combo box
        for (String s : KeyGeneratorFrame.keyTypes)
        {
            keyTypeComboBox.addItem(s);
        }
        //Set "ECC" as the selected item
        keyTypeComboBox.setSelectedItem("ECC"); //NOI18N
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        algorithmButtonGroup = new javax.swing.ButtonGroup();
        curveComboBox = new javax.swing.JComboBox();
        curveLabel = new javax.swing.JLabel();
        okButton = new javax.swing.JButton();
        pubkeyFileLabel = new javax.swing.JLabel();
        privkeyFileLabel = new javax.swing.JLabel();
        pubFileField = new javax.swing.JTextField();
        privFileField = new javax.swing.JTextField();
        keyTypeLabel = new javax.swing.JLabel();
        keyTypeComboBox = new javax.swing.JComboBox();
        selectPubFileButton = new javax.swing.JButton();
        selectPrivFileButton = new javax.swing.JButton();
        curveTypePanel = new javax.swing.JPanel();
        ecdsaRadioButton = new javax.swing.JRadioButton();
        ecgostRadioButton = new javax.swing.JRadioButton();

        setTitle(i18n.getString("ECKeyGeneratorFrame.title")); // NOI18N

        curveLabel.setText(i18n.getString("ECKeyGeneratorFrame.curveLabel.text")); // NOI18N

        okButton.setText(i18n.getString("ECKeyGeneratorFrame.okButton.text")); // NOI18N
        okButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                okButtonMouseClicked(evt);
            }
        });

        pubkeyFileLabel.setText(i18n.getString("ECKeyGeneratorFrame.pubkeyFileLabel.text")); // NOI18N

        privkeyFileLabel.setText(i18n.getString("ECKeyGeneratorFrame.privkeyFileLabel.text")); // NOI18N

        pubFileField.setText("pub.ecp");
        pubFileField.setToolTipText("null");

        privFileField.setText("sec.ecs"); // NOI18N
        privFileField.setToolTipText("null");

        keyTypeLabel.setText(i18n.getString("ECKeyGeneratorFrame.keyTypeLabel.text")); // NOI18N

        keyTypeComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                keyTypeComboBoxActionPerformed(evt);
            }
        });

        selectPubFileButton.setText(i18n.getString("ECKeyGeneratorFrame.selectPubFileButton.text")); // NOI18N
        selectPubFileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectPubFileButtonActionPerformed(evt);
            }
        });

        selectPrivFileButton.setText(i18n.getString("ECKeyGeneratorFrame.selectPrivFileButton.text")); // NOI18N
        selectPrivFileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectPrivFileButtonActionPerformed(evt);
            }
        });

        curveTypePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(i18n.getString("ECKeyGeneratorFrame.curveTypePanel.border.title"))); // NOI18N

        algorithmButtonGroup.add(ecdsaRadioButton);
        ecdsaRadioButton.setSelected(true);
        ecdsaRadioButton.setText("ECDSA"); // NOI18N
        ecdsaRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ecdsaRadioButtonActionPerformed(evt);
            }
        });

        algorithmButtonGroup.add(ecgostRadioButton);
        ecgostRadioButton.setText("ECGOST-3410"); // NOI18N
        ecgostRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ecgostRadioButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout curveTypePanelLayout = new javax.swing.GroupLayout(curveTypePanel);
        curveTypePanel.setLayout(curveTypePanelLayout);
        curveTypePanelLayout.setHorizontalGroup(
            curveTypePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(curveTypePanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ecdsaRadioButton)
                .addGap(18, 18, 18)
                .addComponent(ecgostRadioButton))
        );
        curveTypePanelLayout.setVerticalGroup(
            curveTypePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(curveTypePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(ecdsaRadioButton)
                .addComponent(ecgostRadioButton))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(okButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(pubkeyFileLabel)
                                .addComponent(privkeyFileLabel))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(privFileField)
                                .addComponent(pubFileField, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(selectPrivFileButton)
                                .addComponent(selectPubFileButton))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(curveLabel)
                        .addGap(68, 68, 68)
                        .addComponent(curveComboBox, 0, 216, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(keyTypeLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(keyTypeComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(curveTypePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(keyTypeLabel)
                    .addComponent(keyTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(curveTypePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(curveComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(curveLabel))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pubkeyFileLabel)
                    .addComponent(pubFileField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(selectPubFileButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(privkeyFileLabel)
                    .addComponent(selectPrivFileButton)
                    .addComponent(privFileField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(okButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ecdsaRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ecdsaRadioButtonActionPerformed
        EventQueue.invokeLater(new Runnable()
        {

            @Override
            public void run()
            {
                if (ecdsaRadioButton.isSelected())
                {
                    loadEcdsaCurves();
                    pubFileField.setText("pub.ecp"); //NOI18N
                    privFileField.setText("sec.ecs"); //NOI18N
                }
            }
        });
    }//GEN-LAST:event_ecdsaRadioButtonActionPerformed

    private void ecgostRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ecgostRadioButtonActionPerformed
        EventQueue.invokeLater(new Runnable()
        {

            @Override
            public void run()
            {
                if (ecgostRadioButton.isSelected())
                {
                    loadEcgostCurves();
                    pubFileField.setText("pub.egp"); //NOI18N
                    privFileField.setText("sec.egs"); //NOI18N
                }
            }
        });
    }//GEN-LAST:event_ecgostRadioButtonActionPerformed

private void okButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_okButtonMouseClicked
    {

        BufferedOutputStream pubOut = null;
        BufferedOutputStream privOut = null;
        try
        {
            ECGenParameterSpec ecGenSpec =
                    new ECGenParameterSpec((String) curveComboBox.getSelectedItem());
            KeyPairGenerator g = null;
            if (ecgostRadioButton.isSelected())
            {
                g = KeyPairGenerator.getInstance("ECGOST3410", "BC"); //NOI18N
            }
            else if (ecdsaRadioButton.isSelected())
            {
                g = KeyPairGenerator.getInstance("ECDSA", "BC"); //NOI18N
            }
            g.initialize(ecGenSpec, new SecureRandom());
            KeyPair pair = g.generateKeyPair();

            pubOut =
                    new BufferedOutputStream(new FileOutputStream(pubFileField.getText()));
            privOut =
                    new BufferedOutputStream(new FileOutputStream(privFileField.getText()));

            pubOut.write(pair.getPublic().getEncoded());
            privOut.write(pair.getPrivate().getEncoded());

            pubOut.close();
            privOut.close();
            //Display a success message
            parent.displaySuccessMessage(this);
        }
        catch (IOException ex)
        {
            JOptionPane.showMessageDialog(this, ex.getLocalizedMessage(), i18n.getString("IO_Error"), JOptionPane.ERROR_MESSAGE);
        }
        catch (InvalidAlgorithmParameterException ex)
        {
            Logger.getLogger(ECKeyGeneratorFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (NoSuchAlgorithmException ex)
        {
            Logger.getLogger(ECKeyGeneratorFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (NoSuchProviderException ex)
        {
            Logger.getLogger(ECKeyGeneratorFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try
            {
                pubOut.close();
                privOut.close();
            }
            catch (IOException ex)
            {
                Logger.getLogger(ECKeyGeneratorFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }


}//GEN-LAST:event_okButtonMouseClicked

private void keyTypeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_keyTypeComboBoxActionPerformed
    String sel = (String) keyTypeComboBox.getSelectedItem();
    if (!sel.equals("ECC")) //NOI18N
    {
        parent.setTypeSelection(sel);
        this.setVisible(false);
        parent.setVisible(true);
    }
}//GEN-LAST:event_keyTypeComboBoxActionPerformed

private void selectPubFileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectPubFileButtonActionPerformed
    fileChooser.setSelectedFile(new File(pubFileField.getText()));
    fileChooser.showSaveDialog(this);
    pubFileField.setText(fileChooser.getSelectedFile().getAbsolutePath());
}//GEN-LAST:event_selectPubFileButtonActionPerformed

private void selectPrivFileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectPrivFileButtonActionPerformed
    fileChooser.setSelectedFile(new File(privFileField.getText()));
    fileChooser.showSaveDialog(this);
    privFileField.setText(fileChooser.getSelectedFile().getAbsolutePath());
}//GEN-LAST:event_selectPrivFileButtonActionPerformed

    private void loadEcdsaCurves()
    {
        curveComboBox.removeAllItems();
        Enumeration<String> curves = ECNamedCurveTable.getNames();
        while (curves.hasMoreElements())
        {
            curveComboBox.addItem(curves.nextElement());
        }
        curveComboBox.setSelectedItem("prime192v1"); //NOI18N
    }

    private void loadEcgostCurves()
    {
        curveComboBox.removeAllItems();
        Enumeration<String> curves = ECGOST3410NamedCurveTable.getNames();
        while (curves.hasMoreElements())
        {
            curveComboBox.addItem(curves.nextElement());
        }
        curveComboBox.setSelectedItem("GOSTR3410-2001-CryptoProA"); //NOI18N
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        java.awt.EventQueue.invokeLater(new Runnable()
        {

            @Override
            public void run()
            {
                new ECKeyGeneratorFrame(null).setVisible(true);
            }
        });

    }    //Resource bundle
    ResourceBundle i18n = ResourceBundle.getBundle("jcrypter/ecc/Bundle");
    KeyGeneratorFrame parent = null; //Stores a reference 
    JFileChooser fileChooser = JCrypterFrame.mainFrame.fileChooser;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup algorithmButtonGroup;
    private javax.swing.JComboBox curveComboBox;
    private javax.swing.JLabel curveLabel;
    private javax.swing.JPanel curveTypePanel;
    private javax.swing.JRadioButton ecdsaRadioButton;
    private javax.swing.JRadioButton ecgostRadioButton;
    private javax.swing.JComboBox keyTypeComboBox;
    private javax.swing.JLabel keyTypeLabel;
    private javax.swing.JButton okButton;
    private javax.swing.JTextField privFileField;
    private javax.swing.JLabel privkeyFileLabel;
    private javax.swing.JTextField pubFileField;
    private javax.swing.JLabel pubkeyFileLabel;
    private javax.swing.JButton selectPrivFileButton;
    private javax.swing.JButton selectPubFileButton;
    // End of variables declaration//GEN-END:variables
}
