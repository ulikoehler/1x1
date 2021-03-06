/*
 * RSACrypterFrame.java
 *
 * Created on 20. September 2008, 15:58
 */
package jcrypter.rsa;

import jcrypter.utils.KeyFinder;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import jcrypter.JCrypterFrame;
import jcrypter.utils.CipherModePaddingSelectorDialog;
import jcrypter.utils.KeyGeneratorFrame;
import org.bouncycastle.util.encoders.Base64;

/**
 *
 * @author  uli
 */
public class RSACrypterFrame extends javax.swing.JFrame
{

    /** Creates new form RSACrypterFrame */
    public RSACrypterFrame()
    {
        initComponents();
        //Add public keys
        for (String s : kf.getNames())
        {
            keyComboBox.addItem(s);
        }
    }

    private void decryptRSA()
    {
        try
        {
            byte[] ciphertext = Base64.decode(inputField.getText());
            String selection = (String) keyComboBox.getSelectedItem();
            if (!selection.endsWith(".rss"))
            {
                JOptionPane.showMessageDialog(this,
                        "Select a private key before decrypting!",
                        "No valid key selected", JOptionPane.WARNING_MESSAGE);
                return;
            }
            RSAPrivateKey privkey = (RSAPrivateKey) kf.getPrivateKey(selection);

            Cipher cipher = Cipher.getInstance("RSA/None/" + cmpDialog.getPadding(), "BC");
            cipher.init(Cipher.DECRYPT_MODE, privkey, JCrypterFrame.rand);
            outputField.setText(new String(cipher.doFinal(ciphertext)));
        }
        catch (IllegalBlockSizeException ex)
        {
            Logger.getLogger(RSACrypterFrame.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
        catch (BadPaddingException ex)
        {
            Logger.getLogger(RSACrypterFrame.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
        catch (NoSuchAlgorithmException ex)
        {
            Logger.getLogger(RSACrypterFrame.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
        catch (NoSuchProviderException ex)
        {
            Logger.getLogger(RSACrypterFrame.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
        catch (NoSuchPaddingException ex)
        {
            Logger.getLogger(RSACrypterFrame.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
        catch (InvalidKeyException ex)
        {
            Logger.getLogger(RSACrypterFrame.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
    }

    private void encryptRSA()
    {
        try
        {
            byte[] plaintext = inputField.getText().getBytes();
            //If no key is selected, sho a warning message and abort encryption
            String selection = (String) keyComboBox.getSelectedItem();
            if (!selection.endsWith(".rsp"))
            {
                JOptionPane.showMessageDialog(this,
                        "Select a public key before encrypting!",
                        "No valid key selected", JOptionPane.WARNING_MESSAGE);
                return;
            }
            RSAPublicKey pubkey = (RSAPublicKey) kf.getPublicKey(selection);

            Cipher rsaCipher = Cipher.getInstance("RSA/None/" + cmpDialog.getPadding(), "BC");
            rsaCipher.init(Cipher.ENCRYPT_MODE, pubkey, JCrypterFrame.rand);
            outputField.setText(new String(Base64.encode(rsaCipher.doFinal(
                    plaintext))));
        }
        catch (IllegalBlockSizeException ex)
        {
            Logger.getLogger(RSACrypterFrame.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
        catch (BadPaddingException ex)
        {
            Logger.getLogger(RSACrypterFrame.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
        catch (NoSuchAlgorithmException ex)
        {
            Logger.getLogger(RSACrypterFrame.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
        catch (NoSuchProviderException ex)
        {
            Logger.getLogger(RSACrypterFrame.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
        catch (NoSuchPaddingException ex)
        {
            Logger.getLogger(RSACrypterFrame.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
        catch (InvalidKeyException ex)
        {
            Logger.getLogger(RSACrypterFrame.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        inputLabel = new javax.swing.JLabel();
        plaintextScrollPane = new javax.swing.JScrollPane();
        inputField = new javax.swing.JTextArea();
        okButton = new javax.swing.JButton();
        ciphertextLabel = new javax.swing.JLabel();
        keyLabel = new javax.swing.JLabel();
        ciphertextScrollPane = new javax.swing.JScrollPane();
        outputField = new javax.swing.JTextArea();
        keyComboBox = new javax.swing.JComboBox();
        decryptCheckbox = new javax.swing.JCheckBox();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        loadFromFileMenuItem = new javax.swing.JMenuItem();
        saveToFileMenuItem = new javax.swing.JMenuItem();
        rsaMenu = new javax.swing.JMenu();
        generateKeyMenuItem = new javax.swing.JMenuItem();
        selectPaddingMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(i18n.getString("RSACrypterFrame.title")); // NOI18N

        inputLabel.setDisplayedMnemonic('i');
        inputLabel.setText(i18n.getString("RSACrypterFrame.inputLabel.text")); // NOI18N

        inputField.setColumns(20);
        inputField.setLineWrap(true);
        inputField.setRows(5);
        plaintextScrollPane.setViewportView(inputField);

        okButton.setMnemonic('o');
        okButton.setText(i18n.getString("RSACrypterFrame.okButton.text")); // NOI18N
        okButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                okButtonMouseClicked(evt);
            }
        });

        ciphertextLabel.setDisplayedMnemonic('o');
        ciphertextLabel.setText(i18n.getString("RSACrypterFrame.ciphertextLabel.text")); // NOI18N

        keyLabel.setText(i18n.getString("RSACrypterFrame.keyLabel.text")); // NOI18N

        outputField.setColumns(20);
        outputField.setEditable(false);
        outputField.setLineWrap(true);
        outputField.setRows(5);
        ciphertextScrollPane.setViewportView(outputField);

        keyComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "None" }));

        decryptCheckbox.setMnemonic('d');
        decryptCheckbox.setText(i18n.getString("decryptCheckbox.text")); // NOI18N

        fileMenu.setText(i18n.getString("RSACrypterFrame.fileMenu.text")); // NOI18N

        loadFromFileMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        loadFromFileMenuItem.setMnemonic('l');
        loadFromFileMenuItem.setText(i18n.getString("RSACrypterFrame.loadFromFileMenuItem.text")); // NOI18N
        loadFromFileMenuItem.setToolTipText(i18n.getString("RSACrypterFrame.loadFromFileMenuItem.toolTipText")); // NOI18N
        loadFromFileMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadFromFileMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(loadFromFileMenuItem);

        saveToFileMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        saveToFileMenuItem.setMnemonic('s');
        saveToFileMenuItem.setText(i18n.getString("RSACrypterFrame.saveToFileMenuItem.text")); // NOI18N
        saveToFileMenuItem.setToolTipText(i18n.getString("RSACrypterFrame.saveToFileMenuItem.toolTipText")); // NOI18N
        saveToFileMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveToFileMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(saveToFileMenuItem);

        menuBar.add(fileMenu);

        rsaMenu.setText(i18n.getString("RSACrypterFrame.rsaMenu.text")); // NOI18N

        generateKeyMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_MASK));
        generateKeyMenuItem.setText(i18n.getString("RSACrypterFrame.generateKeyMenuItem.text")); // NOI18N
        generateKeyMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateKeyMenuItemActionPerformed(evt);
            }
        });
        rsaMenu.add(generateKeyMenuItem);

        selectPaddingMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        selectPaddingMenuItem.setText("Padding");
        rsaMenu.add(selectPaddingMenuItem);

        menuBar.add(rsaMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ciphertextLabel)
                    .addComponent(keyLabel)
                    .addComponent(inputLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(plaintextScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE)
                    .addComponent(ciphertextScrollPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE)
                    .addComponent(keyComboBox, javax.swing.GroupLayout.Alignment.TRAILING, 0, 317, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(decryptCheckbox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(okButton, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(inputLabel)
                    .addComponent(plaintextScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(decryptCheckbox)
                    .addComponent(okButton))
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(keyComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(keyLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ciphertextLabel)
                    .addComponent(ciphertextScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void okButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_okButtonMouseClicked
    if (decryptCheckbox.isSelected())
    {
        decryptRSA();
    }
    else
    {
        encryptRSA();
    }
}//GEN-LAST:event_okButtonMouseClicked

private void loadFromFileMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadFromFileMenuItemActionPerformed
    FileInputStream fin = null;
    try
    {
        fileChooser.showOpenDialog(this);
        File file = fileChooser.getSelectedFile();
        byte[] buffer = new byte[(int) file.length()];
        fin = new FileInputStream(file);
        fin.read(buffer);
        fin.close();
        inputField.setText(new String(buffer));
    }
    catch (IOException ex)
    {
        Logger.getLogger(JCrypterFrame.class.getName()).log(Level.SEVERE, null,
                ex);
        ex.printStackTrace();
    }
    finally
    {
        try
        {
            fin.close();
        }
        catch (IOException ex)
        {
            Logger.getLogger(JCrypterFrame.class.getName()).log(Level.SEVERE,
                    null, ex);
            ex.printStackTrace();
        }
    }
}//GEN-LAST:event_loadFromFileMenuItemActionPerformed

private void saveToFileMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveToFileMenuItemActionPerformed
    FileOutputStream fout = null;
    try
    {
        fileChooser.showSaveDialog(this);
        File file = fileChooser.getSelectedFile();
        byte[] buffer = outputField.getText().getBytes();
        fout = new FileOutputStream(file);
        fout.write(buffer);
        fout.close();
    }
    catch (IOException ex)
    {
        Logger.getLogger(JCrypterFrame.class.getName()).log(Level.SEVERE, null,
                ex);
    }
    finally
    {
        try
        {
            fout.close();
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }

    }
}//GEN-LAST:event_saveToFileMenuItemActionPerformed

private void generateKeyMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateKeyMenuItemActionPerformed
    new KeyGeneratorFrame().setVisible(true);
}//GEN-LAST:event_generateKeyMenuItemActionPerformed

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
                new RSACrypterFrame().setVisible(true);
            }
        });
    }    //Dialog members
    JFileChooser fileChooser = JCrypterFrame.mainFrame.fileChooser;
    CipherModePaddingSelectorDialog cmpDialog = new CipherModePaddingSelectorDialog(
                                                        this, true, JCrypterFrame.ciphers, JCrypterFrame.modes,
                                                        JCrypterFrame.paddings);
    //Cryptography members
    KeyFinder kf = new KeyFinder(".rsp", ".rss", "RSA");
    ResourceBundle i18n = ResourceBundle.getBundle("jcrypter/rsa/Bundle");
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ciphertextLabel;
    private javax.swing.JScrollPane ciphertextScrollPane;
    private javax.swing.JCheckBox decryptCheckbox;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenuItem generateKeyMenuItem;
    private javax.swing.JTextArea inputField;
    private javax.swing.JLabel inputLabel;
    private javax.swing.JComboBox keyComboBox;
    private javax.swing.JLabel keyLabel;
    private javax.swing.JMenuItem loadFromFileMenuItem;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JButton okButton;
    private javax.swing.JTextArea outputField;
    private javax.swing.JScrollPane plaintextScrollPane;
    private javax.swing.JMenu rsaMenu;
    private javax.swing.JMenuItem saveToFileMenuItem;
    private javax.swing.JMenuItem selectPaddingMenuItem;
    // End of variables declaration//GEN-END:variables
}
