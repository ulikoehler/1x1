/*
 * JCrypterFrame.java
 *
 * Created on 26. Juli 2008, 17:47
 * http://1x1.googlecode.com
 * GNUCrypto version: Revision 122
 * Released under Apache License
 */
package jcrypter;

import java.io.ByteArrayInputStream;
import jcrypter.utils.CipherModePaddingSelectorDialog;
import jcrypter.pgp.PGPKeyRingReader;
import jcrypter.pgp.PGPCrypterFrame;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JFileChooser;
import jcrypter.signature.SignatureFrame;
import jcrypter.asymmetric.RSACrypterFrame;
import jcrypter.digest.DigestFrame;
import jcrypter.hmac.HMACFrame;
import jcrypter.utils.KeyGeneratorFrame;
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

    /** Creates new form JCrypterFrame */
    public JCrypterFrame()
    {
        //Register Bouncy castle provider
        Security.addProvider(new BouncyCastleProvider());
        //Init the GUI components
        initComponents();
        //Set the selected cipher, mode and padding
        cmpDialog.setCipher("Twofish");
        cmpDialog.setMode("CBC");
        cmpDialog.setPadding("Twofish");
        //Set the static reference (see javadoc of the field)
        JCrypterFrame.mainFrame = this;
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
        pgpMenuItem = new javax.swing.JMenuItem();
        cmpMenuItem = new javax.swing.JMenuItem();
        signatureMenuItem = new javax.swing.JMenuItem();
        rsaMenuItem = new javax.swing.JMenuItem();
        genKeysMenuItem = new javax.swing.JMenuItem();
        passwordGeneratorMenuItem = new javax.swing.JMenuItem();
        digestMenuItem = new javax.swing.JMenuItem();
        hmacMenuItem = new javax.swing.JMenuItem();
        utilsMenu = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("JCrypter");

        inputLabel.setDisplayedMnemonic('i');
        inputLabel.setLabelFor(inputField);
        inputLabel.setText("Input:");

        inputField.setColumns(20);
        inputField.setLineWrap(true);
        inputField.setRows(5);
        plaintextScrollPane.setViewportView(inputField);

        passwordLabel.setDisplayedMnemonic('p');
        passwordLabel.setLabelFor(passwordField);
        passwordLabel.setText("Password:");

        ciphertextLabel.setDisplayedMnemonic('o');
        ciphertextLabel.setLabelFor(outputField);
        ciphertextLabel.setText("Output:");

        outputField.setColumns(20);
        outputField.setEditable(false);
        outputField.setLineWrap(true);
        outputField.setRows(5);
        ciphertextScrollPane.setViewportView(outputField);

        okButton.setMnemonic('o');
        okButton.setText("OK");
        okButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                okButtonMouseClicked(evt);
            }
        });

        decryptCheckbox.setMnemonic('d');
        decryptCheckbox.setText("Decrypt");

        fileMenu.setMnemonic('f');
        fileMenu.setText("File");

        loadFromFileMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        loadFromFileMenuItem.setMnemonic('l');
        loadFromFileMenuItem.setText("Load from file");
        loadFromFileMenuItem.setToolTipText("Load data from a file into the input field");
        loadFromFileMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadFromFileMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(loadFromFileMenuItem);

        saveToFileMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        saveToFileMenuItem.setMnemonic('s');
        saveToFileMenuItem.setText("Save to file");
        saveToFileMenuItem.setToolTipText("Save the data from the output field to a file");
        saveToFileMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveToFileMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(saveToFileMenuItem);

        menuBar.add(fileMenu);

        extrasMenu.setMnemonic('e');
        extrasMenu.setText("Extras");
        extrasMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                extrasMenuActionPerformed(evt);
            }
        });

        pgpMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        pgpMenuItem.setText("PGP");
        pgpMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pgpMenuItemActionPerformed(evt);
            }
        });
        extrasMenu.add(pgpMenuItem);

        cmpMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.CTRL_MASK));
        cmpMenuItem.setText("Cipher and Mode");
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
        signatureMenuItem.setText("Signatures");
        signatureMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signatureMenuItemActionPerformed(evt);
            }
        });
        extrasMenu.add(signatureMenuItem);

        rsaMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        rsaMenuItem.setText("RSA");
        rsaMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rsaMenuItemActionPerformed(evt);
            }
        });
        extrasMenu.add(rsaMenuItem);

        genKeysMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_MASK));
        genKeysMenuItem.setText("Generate keys");
        genKeysMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                genKeysMenuItemActionPerformed(evt);
            }
        });
        extrasMenu.add(genKeysMenuItem);

        passwordGeneratorMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, java.awt.event.InputEvent.CTRL_MASK));
        passwordGeneratorMenuItem.setText("Password generator");
        passwordGeneratorMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordGeneratorMenuItemActionPerformed(evt);
            }
        });
        extrasMenu.add(passwordGeneratorMenuItem);

        digestMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.CTRL_MASK));
        digestMenuItem.setText("Digests");
        digestMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                digestMenuItemActionPerformed(evt);
            }
        });
        extrasMenu.add(digestMenuItem);

        hmacMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        hmacMenuItem.setText("HMAC");
        hmacMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hmacMenuItemActionPerformed(evt);
            }
        });
        extrasMenu.add(hmacMenuItem);

        menuBar.add(extrasMenu);

        utilsMenu.setMnemonic('u');
        utilsMenu.setText("Utils");
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

    private void pgpMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pgpMenuItemActionPerformed
        new PGPCrypterFrame().setVisible(true);
    }//GEN-LAST:event_pgpMenuItemActionPerformed

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
        }
    }//GEN-LAST:event_saveToFileMenuItemActionPerformed

    private void signatureMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signatureMenuItemActionPerformed
        new SignatureFrame().setVisible(true);
}//GEN-LAST:event_signatureMenuItemActionPerformed

private void rsaMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rsaMenuItemActionPerformed
    new RSACrypterFrame().setVisible(true);
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

private void decryptSymmetric()
{
    try
    {
        //Using BouncyCastle JCE
        Cipher cipher = Cipher.getInstance(cipherName + "/" + modeName + "/" + paddingName + "Padding", "BC");
        int bs = cipher.getBlockSize(); //Blocksize
        //Get data
        byte[] passwordBytes = new String(passwordField.getPassword()).getBytes();
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
        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, cipherName);
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
    catch (NoSuchAlgorithmException ex)
    {
        Logger.getLogger(JCrypterFrame.class.getName()).log(Level.SEVERE, null, ex);
        ex.printStackTrace();
    }
    catch (NoSuchProviderException ex)
    {
        Logger.getLogger(JCrypterFrame.class.getName()).log(Level.SEVERE, null, ex);
        ex.printStackTrace();
    }
    catch (NoSuchPaddingException ex)
    {
        Logger.getLogger(JCrypterFrame.class.getName()).log(Level.SEVERE, null, ex);
        ex.printStackTrace();
    }
}

private void encryptSymmetric()
{
    try
    {
        //Using BouncyCastle JCE
        Cipher cipher = Cipher.getInstance(cipherName + "/" + modeName + "/" + paddingName + "Padding", "BC");
        int bs = cipher.getBlockSize(); //Blocksize
        //Get data
        byte[] passwordBytes = new String(passwordField.getPassword()).getBytes();
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
        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, cipherName);
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
    catch (NoSuchAlgorithmException ex)
        {
            Logger.getLogger(JCrypterFrame.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    catch (NoSuchProviderException ex)
        {
            Logger.getLogger(JCrypterFrame.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    catch (NoSuchPaddingException ex)
        {
            Logger.getLogger(JCrypterFrame.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
}

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new JCrypterFrame().setVisible(true);
            }
        });
    }
    
    //Encryption variables
    PGPKeyRingReader pkr = null;
    public static SecureRandom rand = new SecureRandom();
    private String cipherName = "Twofish";
    private String modeName = "CBC";
    private String paddingName = "PKCS7";
    public static final String[] ciphers = {"Serpent","Twofish", "AES", "CAST5", "Camellia", "IDEA"};
    public static final String[] modes = {"ECB", "CBC", "CCM", "CFB", "CTS", "EAX", "GCM", "GOF", "OFB", "SIC"};
    public static final String[] paddings = {"PKCS7", "TBC", "X923", "None", "ZeroByte", "ISO10126d2", "ISO 7816d4"};
    public static final String[] digests = {"MD5", "SHA1", "SHA256", "SHA384", "SHA512", "Whirlpool", "RIPEMD160"};

    //Dialog members
    CipherModePaddingSelectorDialog cmpDialog = new CipherModePaddingSelectorDialog(this, true, ciphers, modes, paddings);
    
    //This field is used by all other frames to save the properties over all frames
    public JFileChooser fileChooser = new JFileChooser();
    
    /**
     * This implements singleton design pattern:
     * Every time a new JCrypterFrame is created (has to happen only once!)
     * it is saved in this static field so other apps have access to all
     * commonly used fields etc.
     */
    public static JCrypterFrame mainFrame;
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ciphertextLabel;
    private javax.swing.JScrollPane ciphertextScrollPane;
    private javax.swing.JMenuItem cmpMenuItem;
    private javax.swing.JCheckBox decryptCheckbox;
    private javax.swing.JMenuItem digestMenuItem;
    private javax.swing.JMenu extrasMenu;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenuItem genKeysMenuItem;
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
    private javax.swing.JMenuItem pgpMenuItem;
    private javax.swing.JScrollPane plaintextScrollPane;
    private javax.swing.JMenuItem rsaMenuItem;
    private javax.swing.JMenuItem saveToFileMenuItem;
    private javax.swing.JMenuItem signatureMenuItem;
    private javax.swing.JMenu utilsMenu;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the cipherName
     */
    public String getCipher()
        {
        return cipherName;
        }

    /**
     * @param cipher the cipherName to set
     */
    public void setCipher(String cipher)
        {
        this.cipherName = cipher;
        }

    /**
     * @return the modeName
     */
    public String getMode()
        {
        return modeName;
        }

    /**
     * @param mode the modeName to set
     */
    public void setMode(String mode)
        {
        this.modeName = mode;
        }

    /**
     * @return the paddingName
     */
    public String getPadding()
        {
        return paddingName;
        }

    /**
     * @param padding the paddingName to set
     */
    public void setPadding(String padding)
        {
        this.paddingName = padding;
        }    
}
