/*
 * RSACrypterFrame.java
 *
 * Created on 20. September 2008, 15:58
 */

package jcrypter.asymmetric;

import jcrypter.utils.KeyGeneratorFrame;
import java.security.InvalidAlgorithmParameterException;
import jcrypter.utils.keyfinder.KeyFinder;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.Key;
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
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import jcrypter.JCrypterFrame;
import jcrypter.utils.CipherModePaddingSelectorDialog;
import org.bouncycastle.util.encoders.Base64;


//TODO remove decrypt checkbox and determinate by key selection
/**
 *
 * @author  uli
 */
public class RSACrypterFrame extends javax.swing.JFrame {

    /** Creates new form RSACrypterFrame */
    public RSACrypterFrame() {
        initComponents();
        //Add keys
        kf.fillComboBox(keyComboBox);
        //Set the selected cipher, mode and padding
        cmpDialog.setCipher("Twofish");
        cmpDialog.setMode("CBC");
        cmpDialog.setPadding("PKCS5Padding");
    }

    private void decryptRSA()
    {
        try
        {
            //Get the selected cipher mode and padding
            String cipher = cmpDialog.getCipher();
            String mode = cmpDialog.getMode();
            String padding = cmpDialog.getPadding();
            
            //Construct a ByteArrayInputStream from which to read 
            ByteArrayInputStream bin = new ByteArrayInputStream(Base64.decode(inputField.getText()));
            
            //Check if a public key is selected, otherwise display a warning message
            String selection = (String) keyComboBox.getSelectedItem();
            if(!selection.endsWith(".rss"))
                {
                    JOptionPane.showMessageDialog(this, i18n.getString("Select_a_private_key_before_decrypting!"), i18n.getString("No_valid_key_selected"), JOptionPane.WARNING_MESSAGE);
                    return;
                }
            
            //Initialize the RSA cipher object
            RSAPrivateKey privkey = (RSAPrivateKey) kf.getPrivateKey(selection);
            Cipher rsaCipher = Cipher.getInstance("RSA/None/OAEPWithSHA1AndMGF1Padding", "BC");
            rsaCipher.init(Cipher.DECRYPT_MODE, privkey, JCrypterFrame.rand);
            
            //Get the symmetric cipher object
            Cipher symCipher = Cipher.getInstance(cipher + "/" + mode + "/" + padding, "BC");
            //Get the IV if we are not using ECB#
            byte[] iv = null;
            if(!mode.equals("ECB"))
            {
                iv = new byte[symCipher.getBlockSize()];
                bin.read(iv); //Read the IV from the input stream
                iv = rsaCipher.doFinal(iv);
            }
            
            //Read the key data from the stream and decrypt it
            byte[] encryptedKey = new byte[256];
            bin.read(encryptedKey);
            byte[] encodedKey = rsaCipher.doFinal(encryptedKey);
            SecretKeySpec keySpec = new SecretKeySpec(encodedKey, cipher);
            
            //Init the cipher
            if(mode.equals("ECB"))
            {
                symCipher.init(Cipher.DECRYPT_MODE, keySpec);
            }
            else //No ECB -> Use an IV
            {
                symCipher.init(Cipher.DECRYPT_MODE, keySpec, new IvParameterSpec(iv));
            }
            
            //Decrypt the ciphertext and write the result into the output field
            byte[] ciphertext = new byte[bin.available()];
            bin.read(ciphertext);
            outputField.setText(new String(symCipher.doFinal(ciphertext)));
        }
        catch (InvalidAlgorithmParameterException ex)
        {
            Logger.getLogger(RSACrypterFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IOException ex)
        {
            Logger.getLogger(RSACrypterFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IllegalBlockSizeException ex)
        {
            Logger.getLogger(RSACrypterFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (BadPaddingException ex)
        {
            Logger.getLogger(RSACrypterFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (NoSuchAlgorithmException ex)
        {
            Logger.getLogger(RSACrypterFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (NoSuchProviderException ex)
        {
            Logger.getLogger(RSACrypterFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (NoSuchPaddingException ex)
        {
            Logger.getLogger(RSACrypterFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (InvalidKeyException ex)
        {
            Logger.getLogger(RSACrypterFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void encryptRSA()
    {
        String selection = null;
        try
        {
            //Get the selected cipher mode and padding
            String cipher = cmpDialog.getCipher();
            String mode = cmpDialog.getMode();
            String padding = cmpDialog.getPadding();
            //Get the plaintext
            byte[] plaintext = inputField.getText().getBytes();
            //If no key is selected, sho a warning message and abort encryption
            selection = (String) keyComboBox.getSelectedItem();
            if(!selection.endsWith(".rsp"))
                {
                    JOptionPane.showMessageDialog(this, i18n.getString("Select_a_public_key_before_encrypting!"), i18n.getString("No_valid_key_selected"), JOptionPane.WARNING_MESSAGE);
                    return;
                }
            
            //Generate the (random symmetric key
            KeyGenerator symKeyGenerator = KeyGenerator.getInstance(cipher, "BC");
            symKeyGenerator.init(256, JCrypterFrame.rand);
            Key symKey = symKeyGenerator.generateKey();
            
            Cipher symCipher = Cipher.getInstance(cipher + "/" + mode + "/" + padding, "BC");
            
            ByteArrayOutputStream outStream = new ByteArrayOutputStream(); //Everything is written into this stream
            
            //Init the asymmetric cipher
            RSAPublicKey pubkey = (RSAPublicKey) kf.getPublicKey(selection); //Retrieve the public key
            Cipher rsaCipher = Cipher.getInstance(i18n.getString("RSA/None/OAEPWithSHA1AndMGF1Padding"), "BC");
            rsaCipher.init(Cipher.ENCRYPT_MODE, pubkey, JCrypterFrame.rand);
            
            //Generate a random (encrypted) IV if we are not using ECC
            if(mode.equals("ECB"))
            {
                symCipher.init(Cipher.ENCRYPT_MODE, symKey);
            }
            else //Mode != ECC
            {
                byte[] iv = new byte[symCipher.getBlockSize()];
                JCrypterFrame.rand.nextBytes(iv);
                iv = rsaCipher.doFinal(iv); //Encrypt the IV
                symCipher.init(Cipher.ENCRYPT_MODE, symKey, new IvParameterSpec(iv));
                outStream.write(iv); //Print the IV into the output stream
            }
            
            
            //Encrypt the encoded key with RSA
            byte[] encodedKey = rsaCipher.doFinal(symKey.getEncoded());
            
            outStream.write(encodedKey);
            
            //Encrypt the plaintext with the symmetric cipher
            outStream.write(symCipher.doFinal(plaintext));
            
            outStream.close();
            
            outputField.setText(new String(Base64.encode(outStream.toByteArray())));
        }
        catch (InvalidKeyException ex)
        {
            //The selected file does not contain a valid key
            JOptionPane.showMessageDialog(this, i18n.getString("Invalid_key"), i18n.getString("The_file_") + selection + i18n.getString("_does_not_contain_a_valid_key!"), JOptionPane.ERROR_MESSAGE);
        }
        catch (InvalidAlgorithmParameterException ex)
        {
            Logger.getLogger(RSACrypterFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IOException ex)
        {
            Logger.getLogger(RSACrypterFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IllegalBlockSizeException ex)
        {
            Logger.getLogger(RSACrypterFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (BadPaddingException ex)
        {
            Logger.getLogger(RSACrypterFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (NoSuchAlgorithmException ex)
        {
            Logger.getLogger(RSACrypterFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (NoSuchProviderException ex)
        {
            Logger.getLogger(RSACrypterFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (NoSuchPaddingException ex)
        {
            Logger.getLogger(RSACrypterFrame.class.getName()).log(Level.SEVERE, null, ex);
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
        selectCmpMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(i18n.getString("RSACrypterFrame.title_1")); // NOI18N

        inputLabel.setDisplayedMnemonic('i');
        inputLabel.setText(i18n.getString("RSACrypterFrame.inputLabel.text")); // NOI18N

        inputField.setColumns(20);
        inputField.setLineWrap(true);
        inputField.setRows(5);
        plaintextScrollPane.setViewportView(inputField);

        okButton.setMnemonic('o');
        okButton.setText(i18n.getString("RSACrypterFrame.okButton.text_1")); // NOI18N
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
        decryptCheckbox.setText(i18n.getString("RSACrypterFrame.decryptCheckbox.text")); // NOI18N

        fileMenu.setText(i18n.getString("RSACrypterFrame.fileMenu.text_1")); // NOI18N

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

        selectCmpMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        selectCmpMenuItem.setText(i18n.getString("Cipher_parameters")); // NOI18N
        selectCmpMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectCmpMenuItemActionPerformed(evt);
            }
        });
        rsaMenu.add(selectCmpMenuItem);

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
                    .addComponent(plaintextScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                    .addComponent(ciphertextScrollPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                    .addComponent(keyComboBox, javax.swing.GroupLayout.Alignment.TRAILING, 0, 250, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(decryptCheckbox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(okButton, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(inputLabel)
                    .addComponent(plaintextScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE))
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
                    .addComponent(ciphertextScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void okButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_okButtonMouseClicked
    String selection = (String) keyComboBox.getSelectedItem();
    if(selection.endsWith("s")){decryptRSA();}
    else{encryptRSA();}
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
        Logger.getLogger(JCrypterFrame.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(JCrypterFrame.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(JCrypterFrame.class.getName()).log(Level.SEVERE, null, ex);
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

private void selectCmpMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectCmpMenuItemActionPerformed
    cmpDialog.setVisible(true);
}//GEN-LAST:event_selectCmpMenuItemActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new RSACrypterFrame().setVisible(true);
            }
        });
    }
    
    //Dialog members
    JFileChooser fileChooser = new JFileChooser();
    CipherModePaddingSelectorDialog cmpDialog =
                                new CipherModePaddingSelectorDialog(this,
                                                                    true,
                                                                    JCrypterFrame.ciphers,
                                                                    JCrypterFrame.modes,
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
    private javax.swing.JMenuItem selectCmpMenuItem;
    // End of variables declaration//GEN-END:variables

}
