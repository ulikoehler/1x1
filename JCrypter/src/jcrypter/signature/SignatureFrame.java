/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * SignatureFrame.java
 *
 * Created on 10.09.2008, 19:57:18
 */
package jcrypter.signature;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import jcrypter.utils.keyfinder.KeyFinder;
import java.io.*;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.Signature;
import java.util.ResourceBundle;
import java.util.logging.*;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import jcrypter.*;
import jcrypter.utils.KeyGeneratorFrame;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Base64;

/**
 *
 * @author uli
 */
public class SignatureFrame extends javax.swing.JFrame
{

    /** Creates new form SignatureFrame */
    public SignatureFrame()
    {
        initComponents();
        //Register Bouncy castle provider
        Security.addProvider(new BouncyCastleProvider());
        //Load the keys
        ecdsaKf.fillComboBox(keyComboBox);
        dsaKf.fillComboBox(keyComboBox);
        rsaKf.fillComboBox(keyComboBox);
        ecgostKf.fillComboBox(keyComboBox);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        messageLabel = new javax.swing.JLabel();
        plaintextScrollPane = new javax.swing.JScrollPane();
        messageField = new javax.swing.JTextArea();
        signVerifyButton = new javax.swing.JButton();
        signatureLabel = new javax.swing.JLabel();
        keyLabel = new javax.swing.JLabel();
        ciphertextScrollPane = new javax.swing.JScrollPane();
        signatureField = new javax.swing.JTextArea();
        keyComboBox = new javax.swing.JComboBox();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        loadFromFileMenuItem = new javax.swing.JMenuItem();
        saveToFileMenuItem = new javax.swing.JMenuItem();
        signatureMenu = new javax.swing.JMenu();
        generateKeyMenuItem = new javax.swing.JMenuItem();
        rsaSigAlgorithmMenuItem = new javax.swing.JMenuItem();
        resarchKeysMenuItem = new javax.swing.JMenuItem();

        setTitle(i18n.getString("SignatureFrame.title")); // NOI18N

        messageLabel.setDisplayedMnemonic('i');
        messageLabel.setText(i18n.getString("SignatureFrame.messageLabel.text")); // NOI18N

        messageField.setColumns(20);
        messageField.setLineWrap(true);
        messageField.setRows(5);
        plaintextScrollPane.setViewportView(messageField);

        signVerifyButton.setMnemonic('o');
        signVerifyButton.setText(i18n.getString("SignatureFrame.signVerifyButton.text")); // NOI18N
        signVerifyButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                signVerifyButtonMouseClicked(evt);
            }
        });

        signatureLabel.setDisplayedMnemonic('o');
        signatureLabel.setText(i18n.getString("SignatureFrame.signatureLabel.text")); // NOI18N

        keyLabel.setText(i18n.getString("SignatureFrame.keyLabel.text")); // NOI18N

        signatureField.setColumns(20);
        signatureField.setLineWrap(true);
        signatureField.setRows(5);
        ciphertextScrollPane.setViewportView(signatureField);

        keyComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "None" }));

        fileMenu.setText(i18n.getString("SignatureFrame.fileMenu.text")); // NOI18N

        loadFromFileMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        loadFromFileMenuItem.setMnemonic('l');
        loadFromFileMenuItem.setText(i18n.getString("SignatureFrame.loadFromFileMenuItem.text")); // NOI18N
        loadFromFileMenuItem.setToolTipText("null");
        loadFromFileMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadFromFileMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(loadFromFileMenuItem);

        saveToFileMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        saveToFileMenuItem.setMnemonic('s');
        saveToFileMenuItem.setText(i18n.getString("SignatureFrame.saveToFileMenuItem.text")); // NOI18N
        saveToFileMenuItem.setToolTipText("null");
        saveToFileMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveToFileMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(saveToFileMenuItem);

        menuBar.add(fileMenu);

        signatureMenu.setText(i18n.getString("SignatureFrame.signatureMenu.text")); // NOI18N

        generateKeyMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_MASK));
        generateKeyMenuItem.setText(i18n.getString("SignatureFrame.generateKeyMenuItem.text")); // NOI18N
        generateKeyMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateKeyMenuItemActionPerformed(evt);
            }
        });
        signatureMenu.add(generateKeyMenuItem);

        rsaSigAlgorithmMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        rsaSigAlgorithmMenuItem.setText(i18n.getString("SignatureFrame.rsaSigAlgorithmMenuItem.text")); // NOI18N
        rsaSigAlgorithmMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rsaSigAlgorithmMenuItemActionPerformed(evt);
            }
        });
        signatureMenu.add(rsaSigAlgorithmMenuItem);

        resarchKeysMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_MASK));
        resarchKeysMenuItem.setText(i18n.getString("SignatureFrame.resarchKeysMenuItem.text")); // NOI18N
        resarchKeysMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resarchKeysMenuItemActionPerformed(evt);
            }
        });
        signatureMenu.add(resarchKeysMenuItem);

        menuBar.add(signatureMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addComponent(ciphertextScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(messageLabel)
                            .addComponent(signatureLabel)
                            .addComponent(keyLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(signVerifyButton, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                            .addComponent(keyComboBox, 0, 260, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addComponent(plaintextScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(messageLabel)
                    .addComponent(plaintextScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(signVerifyButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(keyLabel)
                    .addComponent(keyComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(signatureLabel)
                    .addComponent(ciphertextScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void signECGOST()
    {
        try
        {
            //Determinate which algorithm to use
            String selection = (String) keyComboBox.getSelectedItem();
            //Get the plaintext,
            byte[] message = messageField.getText().getBytes();
            PrivateKey privkey = ecgostKf.getPrivateKey(selection);
            //Generate the signature
            Signature sig = Signature.getInstance("ECGOST3410", "BC");
            sig.initSign(privkey);
            sig.update(message);
            signatureField.setText(new String(Base64.encode(sig.sign())));
        }
        catch (InvalidKeyException ex)
        {
            Logger.getLogger(SignatureFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (SignatureException ex)
        {
            Logger.getLogger(SignatureFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (NoSuchAlgorithmException ex)
        {
            Logger.getLogger(SignatureFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (NoSuchProviderException ex)
        {
            Logger.getLogger(SignatureFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void signVerifyButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_signVerifyButtonMouseClicked
        String selection = (String) keyComboBox.getSelectedItem();
        if (((String) keyComboBox.getSelectedItem()).endsWith("p"))
        {
            if (selection.endsWith(".dsp"))
            {
                verifyDSA();
            }
            else if (selection.endsWith(".ecp"))
            {
                verifyECDSA();
            }
            else if (selection.endsWith(".egp"))
            {
                verifyECGOST();
            }
            else if (selection.endsWith(".rsp"))
            {
                verifyRSA();
            }
        }
        else
        {
            if (selection.endsWith(".dss"))
            {
                signDSA();
            }
            else if (selection.endsWith(".ecs"))
            {
                signECDSA();
            }
            else if (selection.endsWith(".egs"))
            {
                signECGOST();
            }
            else if (selection.endsWith(".rss"))
            {
                signRSA();
            }
        }
}//GEN-LAST:event_signVerifyButtonMouseClicked

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
            messageField.setText(new String(buffer));
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
            byte[] buffer = signatureField.getText().getBytes();
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

private void generateKeyMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateKeyMenuItemActionPerformed
    new KeyGeneratorFrame().setVisible(true);
}//GEN-LAST:event_generateKeyMenuItemActionPerformed

private void rsaSigAlgorithmMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rsaSigAlgorithmMenuItemActionPerformed
    rsaSigSelDialog.setVisible(true);
}//GEN-LAST:event_rsaSigAlgorithmMenuItemActionPerformed

private void resarchKeysMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resarchKeysMenuItemActionPerformed
    ecdsaKf.findKeys();
    dsaKf.findKeys();
    rsaKf.findKeys();
    ecgostKf.findKeys();
}//GEN-LAST:event_resarchKeysMenuItemActionPerformed

    private void signDSA() //Encrypt using elliptic curve cryptography
    {
        try
        {
            //Determinate which algorithm to use
            String selection = (String) keyComboBox.getSelectedItem();
            //Get the plaintext,
            byte[] message = messageField.getText().getBytes();
            PrivateKey privkey = dsaKf.getPrivateKey(selection);
            //Generate the signature
            Signature sig = Signature.getInstance("DSA", "BC");
            sig.initSign(privkey);
            sig.update(message);
            signatureField.setText(new String(Base64.encode(sig.sign())));
        }
        catch (InvalidKeyException ex)
        {
            Logger.getLogger(SignatureFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (SignatureException ex)
        {
            Logger.getLogger(SignatureFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (NoSuchAlgorithmException ex)
        {
            Logger.getLogger(SignatureFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (NoSuchProviderException ex)
        {
            Logger.getLogger(SignatureFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void verifyDSA()
    {
        try
        {
            //Determinate which algorithm to use
            String selection = (String) keyComboBox.getSelectedItem();
            //Get the plaintext,
            byte[] message = messageField.getText().getBytes();
            byte[] signature = Base64.decode(signatureField.getText());
            PublicKey pubkey = dsaKf.getPublicKey(selection);
            //Gnerate the signature
            Signature sig = Signature.getInstance("DSA", "BC");
            sig.initVerify(pubkey);
            sig.update(message);
            if (sig.verify(signature))
            {
                displaySuccessMessage();
            }
            else
            {
                displayErrorMessage();
            }
        }
        catch (InvalidKeyException ex)
        {
            Logger.getLogger(SignatureFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (SignatureException ex)
        {
            Logger.getLogger(SignatureFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (NoSuchAlgorithmException ex)
        {
            Logger.getLogger(SignatureFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (NoSuchProviderException ex)
        {
            Logger.getLogger(SignatureFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void signECDSA() //Encrypt using elliptic curve cryptography
    {
        try
        {
            //Determinate which algorithm to use
            String selection = (String) keyComboBox.getSelectedItem();
            //Get the plaintext,
            byte[] message = messageField.getText().getBytes();
            PrivateKey privkey = ecdsaKf.getPrivateKey(selection);
            //Generate the signature
            Signature sig = Signature.getInstance("ECDSA", "BC");
            sig.initSign(privkey);
            sig.update(message);
            signatureField.setText(new String(Base64.encode(sig.sign())));
        }
        catch (InvalidKeyException ex)
        {
            Logger.getLogger(SignatureFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (SignatureException ex)
        {
            Logger.getLogger(SignatureFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (NoSuchAlgorithmException ex)
        {
            Logger.getLogger(SignatureFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (NoSuchProviderException ex)
        {
            Logger.getLogger(SignatureFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void verifyECDSA()
    {
        try
        {
            //Determinate which algorithm to use
            String selection = (String) keyComboBox.getSelectedItem();
            //Get the plaintext,
            byte[] message = messageField.getText().getBytes();
            byte[] signature = Base64.decode(signatureField.getText());
            PublicKey pubkey = ecdsaKf.getPublicKey(selection);
            //Gnerate the signature
            Signature sig = Signature.getInstance("ECDSA", "BC");
            sig.initVerify(pubkey);
            sig.update(message);
            if (sig.verify(signature))
            {
                displaySuccessMessage();
            }
            else
            {
                displayErrorMessage();
            }
        }
        catch (InvalidKeyException ex)
        {
            Logger.getLogger(SignatureFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (SignatureException ex)
        {
            Logger.getLogger(SignatureFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (NoSuchAlgorithmException ex)
        {
            Logger.getLogger(SignatureFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (NoSuchProviderException ex)
        {
            Logger.getLogger(SignatureFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void signRSA() //Encrypt using elliptic curve cryptography
    {
        String selection = null;
        try
        {
            //Determinate which algorithm to use
            selection = (String) keyComboBox.getSelectedItem();
            //Get the plaintext,
            byte[] message = messageField.getText().getBytes();
            PrivateKey privkey = rsaKf.getPrivateKey(selection);
            //Generate the signature
            String algorithm = rsaSigSelDialog.getAlgorithm();
            Signature sig = Signature.getInstance(algorithm, "BC");
            sig.initSign(privkey);
            sig.update(message);
            signatureField.setText(new String(Base64.encode(sig.sign())));
        }
        catch (InvalidKeyException ex)
        {
            //The selected file does not contain a valid key
            JOptionPane.showMessageDialog(this, "Invalid key", "The file " + selection + " does not contain a valid key!", JOptionPane.ERROR_MESSAGE);
        }
        catch (SignatureException ex)
        {
            Logger.getLogger(SignatureFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (NoSuchAlgorithmException ex)
        {
            Logger.getLogger(SignatureFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (NoSuchProviderException ex)
        {
            Logger.getLogger(SignatureFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void verifyECGOST()
    {
        try
        {
            //Determinate which algorithm to use
            String selection = (String) keyComboBox.getSelectedItem();
            //Get the plaintext,
            byte[] message = messageField.getText().getBytes();
            byte[] signature = Base64.decode(signatureField.getText());
            PublicKey pubkey = ecgostKf.getPublicKey(selection);
            //Gnerate the signature
            Signature sig = Signature.getInstance("ECGOST3410", "BC");
            sig.initVerify(pubkey);
            sig.update(message);
            if (sig.verify(signature))
            {
                displaySuccessMessage();
            }
            else
            {
                displayErrorMessage();
            }
        }
        catch (InvalidKeyException ex)
        {
            Logger.getLogger(SignatureFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (SignatureException ex)
        {
            Logger.getLogger(SignatureFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (NoSuchAlgorithmException ex)
        {
            Logger.getLogger(SignatureFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (NoSuchProviderException ex)
        {
            Logger.getLogger(SignatureFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void verifyRSA()
    {
        try
        {
            //Determinate which algorithm to use
            String selection = (String) keyComboBox.getSelectedItem();
            //Get the plaintext,
            byte[] message = messageField.getText().getBytes();
            byte[] signature = Base64.decode(signatureField.getText());
            PublicKey pubkey = rsaKf.getPublicKey(selection);
            //Gnerate the signature
            String algorithm = rsaSigSelDialog.getAlgorithm();
            Signature sig = Signature.getInstance(algorithm, "BC");
            sig.initVerify(pubkey);
            sig.update(message);
            if (sig.verify(signature))
            {
                displaySuccessMessage();
            }
            else
            {
                displayErrorMessage();
            }
        }
        catch (InvalidKeyException ex)
        {
            Logger.getLogger(SignatureFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (SignatureException ex)
        {
            Logger.getLogger(SignatureFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (NoSuchAlgorithmException ex)
        {
            Logger.getLogger(SignatureFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (NoSuchProviderException ex)
        {
            Logger.getLogger(SignatureFrame.class.getName()).log(Level.SEVERE, null, ex);
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
                new SignatureFrame().setVisible(true);
            }
        });
    }

    private void displaySuccessMessage()
    {
        JOptionPane.showMessageDialog(this,
                "The signature has been verified successfully!",
                "Signature verified",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void displayErrorMessage()
    {
        JOptionPane.showMessageDialog(this,
                "This is not a valid signature!",
                "Signature verification error",
                JOptionPane.ERROR_MESSAGE);
    }
    //Cryptography members

    //Dialog members
    JFileChooser fileChooser = JCrypterFrame.mainFrame.fileChooser;
    RSASignatureAlgorithmSelectorDialog rsaSigSelDialog = new RSASignatureAlgorithmSelectorDialog(this, true);
    KeyFinder ecdsaKf = new KeyFinder(".ecp", ".ecs", "ECDSA");
    KeyFinder dsaKf = new KeyFinder(".dsp", ".dss", "DSA");
    KeyFinder rsaKf = new KeyFinder(".rsp", ".rss", "RSA");
    KeyFinder ecgostKf = new KeyFinder(".egp", ".egs", "RSA");
    ResourceBundle i18n = ResourceBundle.getBundle("jcrypter/signature/Bundle");
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane ciphertextScrollPane;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenuItem generateKeyMenuItem;
    private javax.swing.JComboBox keyComboBox;
    private javax.swing.JLabel keyLabel;
    private javax.swing.JMenuItem loadFromFileMenuItem;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JTextArea messageField;
    private javax.swing.JLabel messageLabel;
    private javax.swing.JScrollPane plaintextScrollPane;
    private javax.swing.JMenuItem resarchKeysMenuItem;
    private javax.swing.JMenuItem rsaSigAlgorithmMenuItem;
    private javax.swing.JMenuItem saveToFileMenuItem;
    private javax.swing.JButton signVerifyButton;
    private javax.swing.JTextArea signatureField;
    private javax.swing.JLabel signatureLabel;
    private javax.swing.JMenu signatureMenu;
    // End of variables declaration//GEN-END:variables
}
