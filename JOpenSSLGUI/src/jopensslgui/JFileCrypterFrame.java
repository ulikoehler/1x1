/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * JFileCrypterFrame.java
 *
 * Created on 29.01.2009, 15:22:29
 */
package jopensslgui;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author uli
 */
public class JFileCrypterFrame extends javax.swing.JFrame
{

    /** Creates new form JFileCrypterFrame */
    public JFileCrypterFrame()
    {
        initComponents();
    //Force RNG seeding
    }

    private void cryptSymmetric(boolean encrypt)
    {
        try
        {
            //Init the cipher
            Runtime r = Runtime.getRuntime();
            StringBuilder cmdBuilder = new StringBuilder("openssl aes-256-cbc");//NOI18N
            //I/O filenames
            cmdBuilder.append(" -in \"" + encryptionInputField.getText() + "\""); //NOI18N
            cmdBuilder.append(" -out \"" + outputFileField.getText() + "\""); //NOI18N
            //Encrypt/decrypt
            if (decryptCheckbox.isSelected())
            {
                cmdBuilder.append(" -d"); //NOI18N
            }
            else
            {
                cmdBuilder.append(" -e"); //NOI18N
            }
            //Password
            cmdBuilder.append(" -k \"" + new String(passwordField.getPassword()) + "\""); //NOI18N

            /**
             * Execute the command
             */
            r.exec(cmdBuilder.toString());
            System.out.println("\"" + cmdBuilder.toString() + "\""); //NOI18N

            //Display a success message
            displaySuccessMessage();
            return; //Don't show the error dialog
        }
        catch (Exception ex)
        {
            Logger.getLogger(JFileCrypterFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        displayErrorMessage();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainTabbedPane = new javax.swing.JTabbedPane();
        fileEncryptionPanel = new javax.swing.JPanel();
        inputFileLabel = new javax.swing.JLabel();
        encryptionInputField = new javax.swing.JTextField();
        outputFileLabel = new javax.swing.JLabel();
        outputFileField = new javax.swing.JTextField();
        decryptCheckbox = new javax.swing.JCheckBox();
        fileEncryptionOKButton = new javax.swing.JButton();
        passwordField = new javax.swing.JPasswordField();
        passwordLabel = new javax.swing.JLabel();
        selectInputFileButton = new javax.swing.JButton();
        selectOutputFileButton = new javax.swing.JButton();
        randomFilePanel = new javax.swing.JPanel();
        selectOutputFileButton1 = new javax.swing.JButton();
        outputFileLabel1 = new javax.swing.JLabel();
        randomOutputFileField = new javax.swing.JTextField();
        sizeLabel = new javax.swing.JLabel();
        randomSizeSpinner = new javax.swing.JSpinner();
        randomSizeSuffixComboBox = new javax.swing.JComboBox();
        randomFileOKButton = new javax.swing.JButton();
        generateKeyPanel = new javax.swing.JPanel();
        statusPanel = new javax.swing.JPanel();
        statusLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle( i18n.getString("JFileCrypterFrame.title")); // NOI18N

        inputFileLabel.setText( i18n.getString("JFileCrypterFrame.inputFileLabel.text")); // NOI18N

        encryptionInputField.setText( i18n.getString("JFileCrypterFrame.encryptionInputField.text")); // NOI18N

        outputFileLabel.setText( i18n.getString("JFileCrypterFrame.outputFileLabel.text")); // NOI18N

        outputFileField.setText( i18n.getString("JFileCrypterFrame.outputFileField.text")); // NOI18N

        decryptCheckbox.setMnemonic('d');
        decryptCheckbox.setText(i18n.getString("JFileCrypterFrame.decryptCheckbox.text")); // NOI18N

        fileEncryptionOKButton.setMnemonic('o');
        fileEncryptionOKButton.setText(i18n.getString("JFileCrypterFrame.fileEncryptionOKButton.text")); // NOI18N
        fileEncryptionOKButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fileEncryptionOKButtonMouseClicked(evt);
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

        javax.swing.GroupLayout fileEncryptionPanelLayout = new javax.swing.GroupLayout(fileEncryptionPanel);
        fileEncryptionPanel.setLayout(fileEncryptionPanelLayout);
        fileEncryptionPanelLayout.setHorizontalGroup(
            fileEncryptionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fileEncryptionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(fileEncryptionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(fileEncryptionPanelLayout.createSequentialGroup()
                        .addGroup(fileEncryptionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(inputFileLabel)
                            .addComponent(outputFileLabel))
                        .addGap(7, 7, 7)
                        .addGroup(fileEncryptionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(outputFileField, javax.swing.GroupLayout.DEFAULT_SIZE, 531, Short.MAX_VALUE)
                            .addComponent(encryptionInputField, javax.swing.GroupLayout.DEFAULT_SIZE, 531, Short.MAX_VALUE))
                        .addGroup(fileEncryptionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(fileEncryptionPanelLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(selectInputFileButton))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, fileEncryptionPanelLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(selectOutputFileButton))))
                    .addGroup(fileEncryptionPanelLayout.createSequentialGroup()
                        .addComponent(passwordLabel)
                        .addGap(15, 15, 15)
                        .addGroup(fileEncryptionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(fileEncryptionPanelLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(decryptCheckbox)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fileEncryptionOKButton, javax.swing.GroupLayout.DEFAULT_SIZE, 532, Short.MAX_VALUE))
                            .addComponent(passwordField, javax.swing.GroupLayout.DEFAULT_SIZE, 607, Short.MAX_VALUE))))
                .addContainerGap())
        );
        fileEncryptionPanelLayout.setVerticalGroup(
            fileEncryptionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fileEncryptionPanelLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(fileEncryptionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inputFileLabel)
                    .addComponent(encryptionInputField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(selectInputFileButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(fileEncryptionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(outputFileLabel)
                    .addComponent(outputFileField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(selectOutputFileButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(fileEncryptionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passwordLabel)
                    .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(fileEncryptionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fileEncryptionOKButton)
                    .addComponent(decryptCheckbox))
                .addContainerGap(52, Short.MAX_VALUE))
        );

        mainTabbedPane.addTab( i18n.getString("JFileCrypterFrame.fileEncryptionPanel.TabConstraints.tabTitle"), fileEncryptionPanel); // NOI18N

        selectOutputFileButton1.setText(i18n.getString("JFileCrypterFrame.selectOutputFileButton1.text")); // NOI18N
        selectOutputFileButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectRandomOutputFileButtonActionPerformed(evt);
            }
        });

        outputFileLabel1.setText( i18n.getString("JFileCrypterFrame.outputFileLabel1.text")); // NOI18N

        randomOutputFileField.setText( i18n.getString("JFileCrypterFrame.randomOutputFileField.text")); // NOI18N

        sizeLabel.setText( i18n.getString("JFileCrypterFrame.sizeLabel.text")); // NOI18N

        randomSizeSpinner.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(100), Integer.valueOf(0), null, Integer.valueOf(1)));

        randomSizeSuffixComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Bytes", "Kilobytes", "Megabytes", "Gigabytes" }));
        randomSizeSuffixComboBox.setSelectedIndex(1);

        randomFileOKButton.setText( i18n.getString("JFileCrypterFrame.randomFileOKButton.text")); // NOI18N
        randomFileOKButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                randomFileOKButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout randomFilePanelLayout = new javax.swing.GroupLayout(randomFilePanel);
        randomFilePanel.setLayout(randomFilePanelLayout);
        randomFilePanelLayout.setHorizontalGroup(
            randomFilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(randomFilePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(randomFilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(outputFileLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sizeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(randomFilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(randomFilePanelLayout.createSequentialGroup()
                        .addComponent(randomOutputFileField, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(selectOutputFileButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(randomFileOKButton, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, randomFilePanelLayout.createSequentialGroup()
                        .addComponent(randomSizeSpinner, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(randomSizeSuffixComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(369, 369, 369))
        );
        randomFilePanelLayout.setVerticalGroup(
            randomFilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(randomFilePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(randomFilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(outputFileLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(randomOutputFileField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(selectOutputFileButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(randomFilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(randomSizeSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sizeLabel)
                    .addComponent(randomSizeSuffixComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(randomFileOKButton)
                .addContainerGap(81, Short.MAX_VALUE))
        );

        mainTabbedPane.addTab( i18n.getString("JFileCrypterFrame.randomFilePanel.TabConstraints.tabTitle"), randomFilePanel); // NOI18N

        javax.swing.GroupLayout generateKeyPanelLayout = new javax.swing.GroupLayout(generateKeyPanel);
        generateKeyPanel.setLayout(generateKeyPanelLayout);
        generateKeyPanelLayout.setHorizontalGroup(
            generateKeyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 709, Short.MAX_VALUE)
        );
        generateKeyPanelLayout.setVerticalGroup(
            generateKeyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 179, Short.MAX_VALUE)
        );

        mainTabbedPane.addTab( i18n.getString("JFileCrypterFrame.generateKeyPanel.TabConstraints.tabTitle"), generateKeyPanel); // NOI18N

        getContentPane().add(mainTabbedPane, java.awt.BorderLayout.CENTER);

        statusPanel.setLayout(new javax.swing.BoxLayout(statusPanel, javax.swing.BoxLayout.LINE_AXIS));

        statusLabel.setText( i18n.getString("JFileCrypterFrame.statusLabel.text")); // NOI18N
        statusPanel.add(statusLabel);

        getContentPane().add(statusPanel, java.awt.BorderLayout.PAGE_START);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fileEncryptionOKButtonMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_fileEncryptionOKButtonMouseClicked
    {//GEN-HEADEREND:event_fileEncryptionOKButtonMouseClicked
        if (decryptCheckbox.isSelected())
        {
            cryptSymmetric(false);
        }
        else
        {
            cryptSymmetric(true);
        }
}//GEN-LAST:event_fileEncryptionOKButtonMouseClicked

    private void selectInputFileButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_selectInputFileButtonActionPerformed
    {//GEN-HEADEREND:event_selectInputFileButtonActionPerformed
        fileChooser.setSelectedFile(new File(encryptionInputField.getText()));
        fileChooser.showSaveDialog(this);
        encryptionInputField.setText(fileChooser.getSelectedFile().getAbsolutePath());
}//GEN-LAST:event_selectInputFileButtonActionPerformed

    private void selectOutputFileButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_selectOutputFileButtonActionPerformed
    {//GEN-HEADEREND:event_selectOutputFileButtonActionPerformed
        fileChooser.setSelectedFile(new File(outputFileField.getText()));
        fileChooser.showSaveDialog(this);
        outputFileField.setText(fileChooser.getSelectedFile().getAbsolutePath());
}//GEN-LAST:event_selectOutputFileButtonActionPerformed

    private void selectRandomOutputFileButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_selectRandomOutputFileButtonActionPerformed
    {//GEN-HEADEREND:event_selectRandomOutputFileButtonActionPerformed
        fileChooser.setSelectedFile(new File(randomOutputFileField.getText()));
        fileChooser.showSaveDialog(this);
        randomOutputFileField.setText(fileChooser.getSelectedFile().getAbsolutePath());
}//GEN-LAST:event_selectRandomOutputFileButtonActionPerformed

    private void displaySuccessMessage()
    {
        JOptionPane.showMessageDialog(this, i18n.getString("Operation_successful!"), i18n.getString("Success"), JOptionPane.INFORMATION_MESSAGE);
    }

    private void displayErrorMessage(String message)
    {
        JOptionPane.showMessageDialog(this, i18n.getString("Operation_error:") + message, i18n.
                getString("Error"), JOptionPane.ERROR_MESSAGE);
    }
    private void displayErrorMessage()
    {
        displayErrorMessage("");
    }

    private void randomFileOKButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_randomFileOKButtonActionPerformed
    {//GEN-HEADEREND:event_randomFileOKButtonActionPerformed
        try
        {
            /**
             * First step: Check which size the file should have
             */
            SpinnerNumberModel sizeSpinnerModel =
                    (SpinnerNumberModel) randomSizeSpinner.getModel();
            long size = sizeSpinnerModel.getNumber().longValue();
            switch (randomSizeSuffixComboBox.getSelectedIndex())
            {
                case 1:
                //Kilobytes
                {
                    size *= 1024;
                    break;
                }
                case 2:
                //Megabytes
                {
                    size *= 1024 * 1024;
                    break;
                }
                case 3:
                //Gigabytes
                {
                    size *= 1024 * 1024 * 1024;
                    break;
                }
                default:
                    break; //case 0 --> bytes
            }
            /**
             * Second step: Generate the file
             */
            String outfileName = randomOutputFileField.getText();
            Runtime r = Runtime.getRuntime();
            Process p = r.exec("openssl rand -out " + outfileName + " " + Long.toString(size));
            p.waitFor();
            int exitValue = p.exitValue();
            if (exitValue == 0)
            {
                displaySuccessMessage();
            }
            else
            {
                displayErrorMessage(Integer.toString(exitValue));
            }
        }
        catch (InterruptedException ex)
        {
            Logger.getLogger(JFileCrypterFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IOException ex)
        {
            Logger.getLogger(JFileCrypterFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_randomFileOKButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        java.awt.EventQueue.invokeLater(new Runnable()
        {

            public void run()
            {
                new JFileCrypterFrame().setVisible(true);
            }
        });
    }
    private JFileChooser fileChooser = new JFileChooser();
    private ResourceBundle i18n = ResourceBundle.getBundle("jopensslgui/Bundle"); //NOI18N
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox decryptCheckbox;
    private javax.swing.JTextField encryptionInputField;
    private javax.swing.JButton fileEncryptionOKButton;
    private javax.swing.JPanel fileEncryptionPanel;
    private javax.swing.JPanel generateKeyPanel;
    private javax.swing.JLabel inputFileLabel;
    private javax.swing.JTabbedPane mainTabbedPane;
    private javax.swing.JTextField outputFileField;
    private javax.swing.JLabel outputFileLabel;
    private javax.swing.JLabel outputFileLabel1;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JButton randomFileOKButton;
    private javax.swing.JPanel randomFilePanel;
    private javax.swing.JTextField randomOutputFileField;
    private javax.swing.JSpinner randomSizeSpinner;
    private javax.swing.JComboBox randomSizeSuffixComboBox;
    private javax.swing.JButton selectInputFileButton;
    private javax.swing.JButton selectOutputFileButton;
    private javax.swing.JButton selectOutputFileButton1;
    private javax.swing.JLabel sizeLabel;
    private javax.swing.JLabel statusLabel;
    private javax.swing.JPanel statusPanel;
    // End of variables declaration//GEN-END:variables
}