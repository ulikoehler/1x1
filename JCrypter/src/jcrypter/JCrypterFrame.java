/*
 * JCrypterFrame.java
 *
 * Created on 26. Juli 2008, 17:47
 * http://1x1.googlecode.com
 * GNUCrypto version: Revision 122
 * Released under Apache License
 */
package jcrypter;

import jcrypter.password.PasswordGeneratorFrame;
import java.io.ByteArrayInputStream;
import jcrypter.utils.CipherModePaddingSelectorDialog;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import jcrypter.signature.SignatureFrame;
import jcrypter.asymmetric.AsymmetricCrypterFrame;
import jcrypter.digest.DigestFrame;
import jcrypter.hmac.HMACFrame;
import jcrypter.utils.Base64UtilFrame;
import jcrypter.utils.KeyGeneratorFrame;
import jcrypter.utils.RandomFileCreatorFrame;
import org.bouncycastle.crypto.*;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Base64;

/**
 *
 * @author  uli
 */
public class JCrypterFrame extends javax.swing.JFrame
{

    public Set<String> getCiphers()
    {
        if (mainFrame.showOIDsMenuItem.isSelected())
        {
            return ciphers;
        }
        //else implied 
        return ciphersNoOIDs;
    }

    public Set<String> getKeyAgreements()
    {
        if (mainFrame.showOIDsMenuItem.isSelected())
        {
            return keyAgreements;
        }
        //else implied 
        return keyAgreementsNoOIDs;
    }

    public Set<String> getMacs()
    {
        if (mainFrame.showOIDsMenuItem.isSelected())
        {
            return macs;
        }
        //else implied 
        return macsNoOIDs;
    }

    public Set<String> getMessageDigests()
    {
        if (mainFrame.showOIDsMenuItem.isSelected())
        {
            return messageDigests;
        }
        //else implied 
        return messageDigestsNoOIDs;
    }

    public Set<String> getSignatures()
    {
        if (mainFrame.showOIDsMenuItem.isSelected())
        {
            return signatures;
        }
        //else implied (return)
        return signaturesNoOIDs;
    }  
    
    /**
     * @return the cipherName
     */
    public Cipher getCipher()
    {
        return cipher;
    }
    
    /** Creates new form JCrypterFrame */
    public JCrypterFrame()
    {
        //Register Bouncy castle provider
        Security.addProvider(new BouncyCastleProvider());
        //Set the static reference (see the field javadoc)
        JCrypterFrame.mainFrame = this;
        //Fill the algorithm containers
        fillAlgorithms();
        //Init the GUI components
        initComponents();
        //Init the cipher parameters selector
        cmpDialog =
                new CipherModePaddingSelectorDialog(this, true, getCiphers(), modes, paddings); //Modal
        //Set the selected cipher, mode and padding
        cmpDialog.setCipher("TWOFISH");
        cmpDialog.setMode("OFB");
        cmpDialog.setPadding("PKCS7");
        //Force seeding of the random generator by requesting a random number
        rand.nextLong();
        //Init the cipher field
        cmpDialog.updateCipher();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        inputLabel = new javax.swing.JLabel();
        plaintextScrollPane = new javax.swing.JScrollPane();
        inputField = new javax.swing.JTextArea();
        passwordLabel = new javax.swing.JLabel();
        passwordField = new javax.swing.JPasswordField();
        ciphertextLabel = new javax.swing.JLabel();
        ciphertextScrollPane = new javax.swing.JScrollPane();
        outputField = new javax.swing.JTextArea();
        okButton = new javax.swing.JButton();
        decryptCheckbox = new javax.swing.JCheckBox();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        loadFromFileMenuItem = new javax.swing.JMenuItem();
        saveToFileMenuItem = new javax.swing.JMenuItem();
        extrasMenu = new javax.swing.JMenu();
        cmpMenuItem = new javax.swing.JMenuItem();
        signatureMenuItem = new javax.swing.JMenuItem();
        rsaMenuItem = new javax.swing.JMenuItem();
        genKeysMenuItem = new javax.swing.JMenuItem();
        passwordGeneratorMenuItem = new javax.swing.JMenuItem();
        digestMenuItem = new javax.swing.JMenuItem();
        hmacMenuItem = new javax.swing.JMenuItem();
        showOIDsMenuItem = new javax.swing.JCheckBoxMenuItem();
        utilsMenu = new javax.swing.JMenu();
        base64MenuItem = new javax.swing.JMenuItem();
        generateRandomFileMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle(i18n.getString("JCrypter")); // NOI18N

        inputLabel.setDisplayedMnemonic('i');
        inputLabel.setLabelFor(inputField);
        inputLabel.setText(i18n.getString("Input:")); // NOI18N

        inputField.setColumns(20);
        inputField.setLineWrap(true);
        inputField.setRows(5);
        plaintextScrollPane.setViewportView(inputField);

        passwordLabel.setDisplayedMnemonic('p');
        passwordLabel.setLabelFor(passwordField);
        passwordLabel.setText(i18n.getString("Password:")); // NOI18N

        ciphertextLabel.setDisplayedMnemonic('o');
        ciphertextLabel.setLabelFor(outputField);
        ciphertextLabel.setText(i18n.getString("Output:")); // NOI18N

        outputField.setColumns(20);
        outputField.setEditable(false);
        outputField.setLineWrap(true);
        outputField.setRows(5);
        ciphertextScrollPane.setViewportView(outputField);

        okButton.setMnemonic('o');
        okButton.setText(i18n.getString("OK")); // NOI18N
        okButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                okButtonMouseClicked(evt);
            }
        });

        decryptCheckbox.setMnemonic('d');
        decryptCheckbox.setText(i18n.getString("Decrypt")); // NOI18N

        fileMenu.setMnemonic('f');
        fileMenu.setText(i18n.getString("File")); // NOI18N

        loadFromFileMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        loadFromFileMenuItem.setMnemonic('l');
        loadFromFileMenuItem.setText(i18n.getString("Load_from_file")); // NOI18N
        loadFromFileMenuItem.setToolTipText(i18n.getString("Load_data_from_a_file_into_the_input_field")); // NOI18N
        loadFromFileMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadFromFileMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(loadFromFileMenuItem);

        saveToFileMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        saveToFileMenuItem.setMnemonic('s');
        saveToFileMenuItem.setText(i18n.getString("Save_to_file")); // NOI18N
        saveToFileMenuItem.setToolTipText(i18n.getString("Save_the_data_from_the_output_field_to_a_file")); // NOI18N
        saveToFileMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveToFileMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(saveToFileMenuItem);

        menuBar.add(fileMenu);

        extrasMenu.setMnemonic('e');
        extrasMenu.setText(i18n.getString("Extras")); // NOI18N
        extrasMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                extrasMenuActionPerformed(evt);
            }
        });

        cmpMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.CTRL_MASK));
        cmpMenuItem.setText(i18n.getString("Cipher_settings")); // NOI18N
        cmpMenuItem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmpMenuItemMouseClicked(evt);
            }
        });
        cmpMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmpMenuItemActionPerformed(evt);
            }
        });
        extrasMenu.add(cmpMenuItem);

        signatureMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        signatureMenuItem.setText(i18n.getString("Signatures")); // NOI18N
        signatureMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signatureMenuItemActionPerformed(evt);
            }
        });
        extrasMenu.add(signatureMenuItem);

        rsaMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        rsaMenuItem.setText(i18n.getString("RSA")); // NOI18N
        rsaMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rsaMenuItemActionPerformed(evt);
            }
        });
        extrasMenu.add(rsaMenuItem);

        genKeysMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_MASK));
        genKeysMenuItem.setText(i18n.getString("Generate_keys")); // NOI18N
        genKeysMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                genKeysMenuItemActionPerformed(evt);
            }
        });
        extrasMenu.add(genKeysMenuItem);

        passwordGeneratorMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        passwordGeneratorMenuItem.setText(i18n.getString("Password_generator")); // NOI18N
        passwordGeneratorMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordGeneratorMenuItemActionPerformed(evt);
            }
        });
        extrasMenu.add(passwordGeneratorMenuItem);

        digestMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.CTRL_MASK));
        digestMenuItem.setText(i18n.getString("Digests")); // NOI18N
        digestMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                digestMenuItemActionPerformed(evt);
            }
        });
        extrasMenu.add(digestMenuItem);

        hmacMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        hmacMenuItem.setText(i18n.getString("HMAC")); // NOI18N
        hmacMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hmacMenuItemActionPerformed(evt);
            }
        });
        extrasMenu.add(hmacMenuItem);

        showOIDsMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        showOIDsMenuItem.setText(i18n.getString("Show_OID_Algorithms")); // NOI18N
        extrasMenu.add(showOIDsMenuItem);

        menuBar.add(extrasMenu);

        utilsMenu.setMnemonic('u');
        utilsMenu.setText(i18n.getString("Utils")); // NOI18N

        base64MenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.CTRL_MASK));
        base64MenuItem.setText(i18n.getString("Base64")); // NOI18N
        base64MenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                base64MenuItemActionPerformed(evt);
            }
        });
        utilsMenu.add(base64MenuItem);

        generateRandomFileMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        generateRandomFileMenuItem.setText(i18n.getString("Generate_entropy_file")); // NOI18N
        generateRandomFileMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateRandomFileMenuItemActionPerformed(evt);
            }
        });
        utilsMenu.add(generateRandomFileMenuItem);

        menuBar.add(utilsMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(passwordLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(passwordField, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(inputLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(plaintextScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ciphertextLabel)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(decryptCheckbox)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(okButton, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ciphertextScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)))))
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
                    .addComponent(passwordLabel)
                    .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(decryptCheckbox)
                    .addComponent(okButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ciphertextLabel)
                    .addComponent(ciphertextScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    @SuppressWarnings("empty-statement")
    private void okButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_okButtonMouseClicked
        if (decryptCheckbox.isSelected())
        {
            decryptSymmetric();
        }
        else
        {
            encryptSymmetric();
        }
    }//GEN-LAST:event_okButtonMouseClicked

    private void cmpMenuItemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmpMenuItemMouseClicked
        cmpDialog.setVisible(true);
}//GEN-LAST:event_cmpMenuItemMouseClicked

    private void cmpMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmpMenuItemActionPerformed
        cmpDialog.setVisible(true);
}//GEN-LAST:event_cmpMenuItemActionPerformed

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
                Logger.getLogger(JCrypterFrame.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            }
            catch (NullPointerException ex)
            {
            }
        }
    }//GEN-LAST:event_saveToFileMenuItemActionPerformed

    private void signatureMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signatureMenuItemActionPerformed
        new SignatureFrame().setVisible(true);
}//GEN-LAST:event_signatureMenuItemActionPerformed

private void rsaMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rsaMenuItemActionPerformed
    new AsymmetricCrypterFrame().setVisible(true);
}//GEN-LAST:event_rsaMenuItemActionPerformed

private void extrasMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_extrasMenuActionPerformed
    new KeyGeneratorFrame().setVisible(true);
}//GEN-LAST:event_extrasMenuActionPerformed

private void passwordGeneratorMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordGeneratorMenuItemActionPerformed
    new PasswordGeneratorFrame().setVisible(true);
}//GEN-LAST:event_passwordGeneratorMenuItemActionPerformed

private void digestMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_digestMenuItemActionPerformed
    new DigestFrame().setVisible(true);
}//GEN-LAST:event_digestMenuItemActionPerformed

private void hmacMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hmacMenuItemActionPerformed
    new HMACFrame().setVisible(true);
}//GEN-LAST:event_hmacMenuItemActionPerformed

private void genKeysMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genKeysMenuItemActionPerformed
    new KeyGeneratorFrame().setVisible(true);
}//GEN-LAST:event_genKeysMenuItemActionPerformed

private void base64MenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_base64MenuItemActionPerformed
    new Base64UtilFrame().setVisible(true);
}//GEN-LAST:event_base64MenuItemActionPerformed

private void generateRandomFileMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateRandomFileMenuItemActionPerformed
    new RandomFileCreatorFrame().setVisible(true);
}//GEN-LAST:event_generateRandomFileMenuItemActionPerformed

    private void decryptSymmetric()
    {
        try
        {
            //Cipher field is automatically initialized by the CMP Dialog
            int bs = cipher.getBlockSize(); //Blocksize
            //Get data
            byte[] passwordBytes =
                    new String(passwordField.getPassword()).getBytes();
            byte[] input;
            //Base64-decode the ciphertext
            input = Base64.decode(inputField.getText().getBytes());

            //All data will be read from this stream
            ByteArrayInputStream bin = new ByteArrayInputStream(input);

            //Hash the password to fit it into the right size (with salt)
            byte[] salt = new byte[8];
            byte[] keyBytes = new byte[32];
            bin.read(salt); //Get the salt from the input stream 

            Digest digest = new SHA256Digest();
            digest.update(salt, 0, salt.length); //Add the salt...
            digest.update(passwordBytes, 0, passwordBytes.length); //...and the password to the generator
            digest.doFinal(keyBytes, 0); //Do the final hashing

            //IV generation/retrievement
            byte[] iv = new byte[cipher.getBlockSize()]; //Using iv array only with offset
            bin.read(iv);
            IvParameterSpec ivSpec = new IvParameterSpec(iv, 0, bs);

            //Generate the secret key spec
            SecretKeySpec keySpec = new SecretKeySpec(keyBytes, cmpDialog.getCipher());
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);

            CipherInputStream cin = new CipherInputStream(bin, cipher);

            //Print the input (minus the IV, if decrypting) into cout

            byte[] plaintext = new byte[bin.available()];
            cin.read(plaintext); //Decrypts the rest data in bin
            //Close the cipher stream
            cin.close();
            //Print the output into outputField and Base64-encode if we have to encrypt
            outputField.setText(new String(plaintext).trim());
        }
        catch (InvalidAlgorithmParameterException ex)
        {
            Logger.getLogger(JCrypterFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IOException ex) //Must not occure
        {
            Logger.getLogger(JCrypterFrame.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        catch (InvalidKeyException ex)
        {
            Logger.getLogger(JCrypterFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void encryptSymmetric()
    {
        try
        {
            //cipher field is initalized by the cmp dialog and at the beginning
            
            //Using BouncyCastle JCEs
            int bs = cipher.getBlockSize(); //Blocksize
            //Get data
            byte[] passwordBytes =
                    new String(passwordField.getPassword()).getBytes();
            byte[] input;
            //Base64-decode the ciphertext
            input = inputField.getText().getBytes();

            //All data is written to this stream
            ByteArrayOutputStream bout = new ByteArrayOutputStream();

            //Hash the password to fit it into the right size (with salt)
            byte[] salt = new byte[8];
            byte[] keyBytes = new byte[32]; //Assume a 256-bit key
            rand.nextBytes(salt);
            bout.write(salt); //Write the salt to the stream

            Digest digest = new SHA256Digest(); //Assume a 256-bit key
            digest.update(salt, 0, salt.length); //Add the salt...
            digest.update(passwordBytes, 0, passwordBytes.length); //...and the password to the generator
            digest.doFinal(keyBytes, 0); //Do the final hashing

            //Generate the iv and the IvParameter spec
            byte[] iv = new byte[cipher.getBlockSize()];
            rand.nextBytes(iv);
            IvParameterSpec ivSpec = new IvParameterSpec(iv, 0, bs);

            //Generate the secret key spec
            SecretKeySpec keySpec = new SecretKeySpec(keyBytes, cmpDialog.getCipher());
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);

            CipherOutputStream cout = new CipherOutputStream(bout, cipher);

            //If print the IV into bout
            bout.write(iv);
            cout.write(input);
            //All data has been written so close the streams
            cout.close();
            bout.close();
            //Print the output into outputField and Base64-encode if we have to encrypt
            outputField.setText(new String(Base64.encode(bout.toByteArray())));
        }
        catch (InvalidAlgorithmParameterException ex)
        {
            Logger.getLogger(JCrypterFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IOException ex) //Must not occure
        {
            Logger.getLogger(JCrypterFrame.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        catch (InvalidKeyException ex)
        {
            Logger.getLogger(JCrypterFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
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
                new JCrypterFrame().setVisible(true);
            }
        });
    }    //Encryption variables
    public static SecureRandom rand = new SecureRandom();
    public static final String[] modesArray =
    {
        "ECB", "CBC", "CCM", "CFB", "CTS", "EAX", "GCM", "GOF", "OFB", "SIC"
    };
    public static final String[] paddingsArray =
    {
        "PKCS7", "TBC", "X923", "None", "ZeroByte", "ISO10126d2", "ISO7816d4"
    };
    //Dialog members
    public CipherModePaddingSelectorDialog cmpDialog = null;
    //The file chooser is used by all frames to save the current directory
    public JFileChooser fileChooser = new JFileChooser();
    /**
     * This implements singleton design pattern:
     * Every time a new JCrypterFrame is created (has to happen only once!)
     * it is saved in this static field so other apps have access to all
     * commonly used fields etc.
     */
    private Cipher cipher = null;
    
    public static JCrypterFrame mainFrame;
    private ResourceBundle i18n = ResourceBundle.getBundle("jcrypter/Bundle");
    //Collections to save algorithm names
    private static Set<String> ciphers =
            new TreeSet(String.CASE_INSENSITIVE_ORDER);
    private static Set<String> keyAgreements =
            new TreeSet(String.CASE_INSENSITIVE_ORDER);
    private static Set<String> macs =
            new TreeSet(String.CASE_INSENSITIVE_ORDER);
    private static Set<String> messageDigests =
            new TreeSet(String.CASE_INSENSITIVE_ORDER);
    private static Set<String> signatures =
            new TreeSet(String.CASE_INSENSITIVE_ORDER);
    //The same without OIDs
    private static Set<String> ciphersNoOIDs =
            new TreeSet(String.CASE_INSENSITIVE_ORDER);
    private static Set<String> keyAgreementsNoOIDs =
            new TreeSet(String.CASE_INSENSITIVE_ORDER);
    private static Set<String> macsNoOIDs =
            new TreeSet(String.CASE_INSENSITIVE_ORDER);
    private static Set<String> messageDigestsNoOIDs =
            new TreeSet(String.CASE_INSENSITIVE_ORDER);
    private static Set<String> signaturesNoOIDs =
            new TreeSet(String.CASE_INSENSITIVE_ORDER);
    private static List<String> modes = Arrays.asList(modesArray);
    private static List<String> paddings = Arrays.asList(paddingsArray);
    //Regex patterns
    private static Pattern oidPattern = //Used to filter OIDs from algorithms
            Pattern.compile("(OID\\.)?(\\d+\\.)+\\d+");
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem base64MenuItem;
    private javax.swing.JLabel ciphertextLabel;
    private javax.swing.JScrollPane ciphertextScrollPane;
    private javax.swing.JMenuItem cmpMenuItem;
    private javax.swing.JCheckBox decryptCheckbox;
    private javax.swing.JMenuItem digestMenuItem;
    private javax.swing.JMenu extrasMenu;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenuItem genKeysMenuItem;
    private javax.swing.JMenuItem generateRandomFileMenuItem;
    private javax.swing.JMenuItem hmacMenuItem;
    private javax.swing.JTextArea inputField;
    private javax.swing.JLabel inputLabel;
    private javax.swing.JMenuItem loadFromFileMenuItem;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JButton okButton;
    private javax.swing.JTextArea outputField;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JMenuItem passwordGeneratorMenuItem;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JScrollPane plaintextScrollPane;
    private javax.swing.JMenuItem rsaMenuItem;
    private javax.swing.JMenuItem saveToFileMenuItem;
    private javax.swing.JCheckBoxMenuItem showOIDsMenuItem;
    private javax.swing.JMenuItem signatureMenuItem;
    private javax.swing.JMenu utilsMenu;
    // End of variables declaration//GEN-END:variables
    /**
     * @return the cipherName
     */
    public String getCipherName()
    {
        return cmpDialog.getCipher();
    }

    /**
     * Test if the unlimited strength policy files are installed
     */
    private void testUnlimitedPolicy()
    {
        try
        {
            byte[] data =
            {
                0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07
            };

            // create a 64 bit secret key from raw bytes

            SecretKey key64 = new SecretKeySpec(new byte[]
                    {
                        0x00, 0x01, 0x02,
                        0x03, 0x04, 0x05, 0x06, 0x07
                    }, "Blowfish");

            // create a cipher and attempt to encrypt the data block with our key

            Cipher c = Cipher.getInstance("Blowfish/ECB/NoPadding");

            c.init(Cipher.ENCRYPT_MODE, key64);
            c.doFinal(data);
            System.out.println("64 bit test: passed");

            // create a 192 bit secret key from raw bytes

            SecretKey key192 = new SecretKeySpec(new byte[]
                    {
                        0x00, 0x01, 0x02,
                        0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0a, 0x0b, 0x0c,
                        0x0d, 0x0e, 0x0f, 0x10, 0x11, 0x12, 0x13, 0x14, 0x15, 0x16,
                        0x17
                    }, "Blowfish");

            // now try encrypting with the larger key

            c.init(Cipher.ENCRYPT_MODE, key192);
            c.doFinal(data);
        }
        catch (InvalidKeyException ex)
        {
            JOptionPane.showMessageDialog(this, "The Unrestricted Policy Files are not installed in your JRE." +
                    "Please install them to enable strong cryptography!", "Restricted policy files",
                    JOptionPane.ERROR_MESSAGE);
        }
        catch (Exception ex)
        {
        }
    }

    /**
     * Fills the algorithms which determinate the
     */
    private static void fillAlgorithms()
    {
        Provider[] providers = Security.getProviders();
        for (int i = 0; i != providers.length; i++)
        {
            Iterator it = providers[i].keySet().iterator();

            while (it.hasNext())
            {
                String entry = (String) it.next();

                if (entry.startsWith("Alg.Alias."))
                {
                    entry = entry.substring("Alg.Alias.".length());
                }
                //Filter PBE, asymmetric and invalid algorithms (not case-sensitive
                if (entry.contains("PBE") ||
                        entry.toLowerCase().contains("supported") ||
                        entry.toLowerCase().contains("wrap") ||
                        entry.toLowerCase().contains("padding") ||
                        entry.toLowerCase().contains("rsa") ||
                        entry.toLowerCase().contains("elgamal"))
                {
                    continue;
                }

                if (entry.startsWith("Cipher."))
                {
                    String algorithm = entry.substring("Cipher.".length());
                    ciphers.add(algorithm);

                    Matcher m = oidPattern.matcher(algorithm);
                    if (!m.matches())
                    {
                        ciphersNoOIDs.add(algorithm);
                    }
                }
                else if (entry.startsWith("KeyAgreement."))
                {
                    String algorithm = entry.substring("KeyAgreement.".length());
                    keyAgreements.add(algorithm);

                    Matcher m = oidPattern.matcher(algorithm);
                    if (!m.matches())
                    {
                        keyAgreementsNoOIDs.add(algorithm);
                    }
                }
                else if (entry.startsWith("Mac."))
                {
                    //4 = "HMAC(-|/)".
                    String algorithm = entry.substring("Mac.".length());
                    //Filter out algorithms not beginning with "HMAC"
                    if (!algorithm.startsWith("HMAC"))
                    {
                        continue;
                    }
                    else
                    {
                        algorithm = algorithm.substring(4);
                    }

                    //Remove the first character of the algorithm string if it is not a letter
                    if (algorithm.charAt(0) == '-' || algorithm.charAt(0) == '/')
                    {
                        algorithm = algorithm.substring(1);
                    }

                    //Check if the algorithm is not already in the set
                    //Also applies to macsNoOIDs
                    if (macs.contains(algorithm))
                    {
                        continue;
                    }

                    macs.add(algorithm);

                    Matcher m = oidPattern.matcher(algorithm);
                    if (!m.matches())
                    {
                        macsNoOIDs.add(algorithm);
                    }
                }
                else if (entry.startsWith("MessageDigest."))
                {
                    String algorithm =
                            entry.substring("MessageDigest.".length());
                    messageDigests.add(algorithm);
                    
                    if(algorithm.endsWith("ImplementedIn")) {continue;}

                    Matcher m = oidPattern.matcher(algorithm);
                    if (!m.matches())
                    {
                        messageDigestsNoOIDs.add(algorithm);
                    }
                }
                else if (entry.startsWith("Signature."))
                {
                    String algorithm = entry.substring("Signature.".length());
                    signatures.add(algorithm);

                    Matcher m = oidPattern.matcher(algorithm);
                    if (!m.matches())
                    {
                        signaturesNoOIDs.add(algorithm);
                    }
                }

            }
        }
    }

    public void setCipher(Cipher cipher)
    {
        this.cipher = cipher;
    }
}
