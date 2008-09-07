/*
 * JCrypterFrame.java
 *
 * Created on 26. Juli 2008, 17:47
 * http://1x1.googlecode.com
 * GNUCrypto version: Revision 122
 * Released under Apache License
 */

package jcrypter;

import java.io.*;
import java.security.*;
import java.util.*;
import javax.swing.JOptionPane;
import org.bouncycastle.bcpg.ArmoredOutputStream;
import org.bouncycastle.crypto.*;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.engines.TwofishEngine;
import org.bouncycastle.crypto.modes.OpenPGPCFBBlockCipher;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openpgp.*;
import org.bouncycastle.util.encoders.Base64;

/**
 *
 * @author  uli
 */
public class JCrypterFrame extends javax.swing.JFrame {
    
    /** Creates new form JCrypterFrame */
    public JCrypterFrame() {
        //Register Bouncy castle provider
        Security.addProvider(new BouncyCastleProvider());  
        //Init the GUI components
        initComponents();
        //Load PGP keyrings and update the key selection combo box
        pkr = new PGPKeyRingReader();
        for(String s : pkr.getPubKeyNames())
        {
            keyComboBox.addItem(s);
        }
    }

    private void encryptAsymmetric()
    {
        try
        {
            if(decryptCheckbox.isSelected())
            {
                //Get text
                InputStream in = new ByteArrayInputStream(inputField.getText().getBytes());
                char[] password = passwordField.getPassword();

                ByteArrayOutputStream out = new ByteArrayOutputStream();

                PGPPublicKeyEncryptedData pbe = null;
                in = PGPUtil.getDecoderStream(in);

                PGPObjectFactory pgpF = new PGPObjectFactory(in);
                PGPEncryptedDataList enc;
                Object o = pgpF.nextObject();

                if(o == null) throw new PGPException("Cannot recognize input data format");
                //
                // the first object might be a PGP marker packet.
                //
                if (o instanceof PGPEncryptedDataList)
                {
                    enc = (PGPEncryptedDataList) o;
                }
                else
                {
                    enc = (PGPEncryptedDataList) pgpF.nextObject();
                }

                //
                // find the secret key
                //
                Iterator encObjects = enc.getEncryptedDataObjects();
                if(!encObjects.hasNext()) throw new PGPException("No encrypted data");
                pbe=(PGPPublicKeyEncryptedData) encObjects.next();

                PGPPrivateKey sKey = null;
                System.out.println(Long.toHexString(pbe.getKeyID()));
                PGPSecretKey secretKey = pkr.getSecKeysID().get(pbe.getKeyID());
                if (secretKey == null) throw new IllegalArgumentException("Secret key for message not found.");

                sKey = secretKey.extractPrivateKey(password, "BC");
                //sKey = findSecretKey(it, passwd);

                InputStream clear = pbe.getDataStream(sKey, "BC");

                PGPObjectFactory plainFact = new PGPObjectFactory(clear);

                Object message = plainFact.nextObject();
                Object sigLiteralData = null;
                PGPObjectFactory pgpFact = null;

                if (message instanceof PGPCompressedData)
                {
                    PGPCompressedData cData = (PGPCompressedData) message;
                    pgpFact = new PGPObjectFactory(cData.getDataStream());
                    message = pgpFact.nextObject();
                    if(message instanceof PGPOnePassSignatureList)
                    {
                        sigLiteralData =  pgpFact.nextObject();
                    }
                }
                
                if (message instanceof PGPLiteralData)
                    {
                        //Message is just encrypted
                        processLiteralData((PGPLiteralData) message,out,null);
                    }
                
                else if (message instanceof PGPOnePassSignatureList)
                {
                    PGPOnePassSignature ops = ((PGPOnePassSignatureList)message).get(0);
                    PGPPublicKey pubKey = pkr.getPubKeysID().get(ops.getKeyID());
                    if(pubKey == null)
                        {
                            System.out.println("Cannot find the public key 0x"+Integer.toHexString((int)ops.getKeyID()).toUpperCase());
                            //... decrypt without checking signature
                            processLiteralData((PGPLiteralData) sigLiteralData,out,null);
                        }
                    else
                        {
                            System.out.println((String)pubKey.getUserIDs().next());
                            ops.initVerify(pubKey, "BC");
                            processLiteralData((PGPLiteralData) sigLiteralData,out,ops);
                            PGPSignatureList sigList =  (PGPSignatureList) pgpFact.nextObject();
                        }
                }
                else {throw new PGPException("message is not a simple encrypted file - type unknown.");}

                if (pbe.isIntegrityProtected())
                    {
                        if (!pbe.verify())
                            {throw new PGPException("Message failed integrity check!");}
                    }

                outputField.setText(new String(out.toByteArray()));

            }
            else //Encrypt
            {
                //Get the selected public key
                PGPPublicKey pubkey = pkr.getPubKeys().get(keyComboBox.getSelectedItem());
                ByteArrayOutputStream finalOut = new ByteArrayOutputStream();
                OutputStream aOut = new ArmoredOutputStream(finalOut);
                        
                ByteArrayOutputStream bOut = new ByteArrayOutputStream();
                PGPCompressedDataGenerator comData = new PGPCompressedDataGenerator(PGPCompressedData.BZIP2);
                OutputStreamWriter comWriter = new OutputStreamWriter(comData.open(bOut));
                comWriter.write(inputField.getText());
                comWriter.close();
                comData.close();
                //Do the encryption
                PGPEncryptedDataGenerator cPk = new PGPEncryptedDataGenerator(PGPEncryptedData.CAST5, true, new SecureRandom(), "BC");
                cPk.addMethod(pubkey);
                byte[] bytes = bOut.toByteArray();
                OutputStream cOut = cPk.open(aOut, bytes.length);
                cOut.write(bytes);
                cOut.close();
                aOut.close();
                //Print the encrypted text into the GUI
                outputField.setText(new String(finalOut.toByteArray()));
            }
        }
        catch (NoSuchProviderException ex)
        {
            ex.printStackTrace();
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        catch(PGPException ex) //Data not decryptible
        {
            JOptionPane.showMessageDialog(this, ex, "PGP Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
            ex.getUnderlyingException().printStackTrace();
        }
        catch(IllegalArgumentException ex)
        {
            JOptionPane.showMessageDialog(this, ex, "Argument Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
        catch(SecurityException ex)
        {
            JOptionPane.showMessageDialog(this, ex, "Argument Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
        catch(SignatureException ex)
        {
            JOptionPane.showMessageDialog(this, ex, "Argument Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
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
        keyLabel = new javax.swing.JLabel();
        keyComboBox = new javax.swing.JComboBox();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        loadFromFileMenuItem = new javax.swing.JMenuItem();
        saveToFileMenuItem = new javax.swing.JMenuItem();
        extrasMenu = new javax.swing.JMenu();

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

        keyLabel.setText("Key:");

        keyComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "None" }));

        fileMenu.setMnemonic('f');
        fileMenu.setText("File");

        loadFromFileMenuItem.setMnemonic('l');
        loadFromFileMenuItem.setText("Load from file");
        loadFromFileMenuItem.setToolTipText("Load data from a file into the input field");
        fileMenu.add(loadFromFileMenuItem);

        saveToFileMenuItem.setMnemonic('s');
        saveToFileMenuItem.setText("Save to file");
        saveToFileMenuItem.setToolTipText("Save the data from the output field to a file");
        fileMenu.add(saveToFileMenuItem);

        menuBar.add(fileMenu);

        extrasMenu.setMnemonic('e');
        extrasMenu.setText("Extras");
        menuBar.add(extrasMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(passwordLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(passwordField, javax.swing.GroupLayout.DEFAULT_SIZE, 383, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(inputLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(plaintextScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(decryptCheckbox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(okButton, javax.swing.GroupLayout.DEFAULT_SIZE, 335, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ciphertextLabel)
                            .addComponent(keyLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(ciphertextScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 399, Short.MAX_VALUE)
                            .addComponent(keyComboBox, 0, 399, Short.MAX_VALUE))))
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
                    .addComponent(passwordLabel)
                    .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(decryptCheckbox)
                    .addComponent(okButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(keyComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(keyLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ciphertextLabel)
                    .addComponent(ciphertextScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    @SuppressWarnings("empty-statement")
    private void okButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_okButtonMouseClicked
        if(keyComboBox.getSelectedIndex() == 0){encryptSymmetric();}
        else{encryptAsymmetric();}
    }//GEN-LAST:event_okButtonMouseClicked

    private static void processLiteralData(PGPLiteralData ld, OutputStream out, PGPOnePassSignature ops) throws IOException, SignatureException{
        InputStream unc = ld.getInputStream();
        int ch;
        if(ops==null){
            while ((ch = unc.read()) >= 0) 
                out.write(ch);
        }else{
            while ((ch = unc.read()) >= 0) {
                out.write(ch);
                ops.update((byte)ch);
            }
        }
    }
    
    private void encryptSymmetric()
    {
        try
        {
            //Bouncy castle inline using lightweight API
            BlockCipher engine = new TwofishEngine();
            BufferedBlockCipher cipher = new PaddedBufferedBlockCipher(new OpenPGPCFBBlockCipher(engine));
            //Get data
            byte[] passwordBytes = new String(passwordField.getPassword()).getBytes();
            byte[] input;
            //Base64-decode the ciphertext
            if(decryptCheckbox.isSelected()) {input = Base64.decode(inputField.getText().getBytes());}
            else {input = inputField.getText().getBytes();}
            
            //Hash the password to fit it into the right size
            Digest digest = new SHA256Digest();
            digest.update(passwordBytes, 0, passwordBytes.length);
            byte[] keyBytes = new byte[32];
            digest.doFinal(keyBytes, 0);
            
            cipher.init(!decryptCheckbox.isSelected(), new KeyParameter(keyBytes));
            
            byte[] output = new byte[cipher.getOutputSize(input.length)];
            int outputLen = cipher.processBytes(input, 0, input.length, output, 0);
            cipher.doFinal(output, outputLen);
            //Print the output into outputField and Base64-encode if we have to encrypt
            if(decryptCheckbox.isSelected()) {outputField.setText(new String(output).trim());}
            else {outputField.setText(new String(Base64.encode(output)));}
        }
        catch(CryptoException ex) //Data not decryptible
        {
            JOptionPane.showMessageDialog(this, "Data not encrypted!", "Decryption impossible", JOptionPane.ERROR_MESSAGE);
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
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ciphertextLabel;
    private javax.swing.JScrollPane ciphertextScrollPane;
    private javax.swing.JCheckBox decryptCheckbox;
    private javax.swing.JMenu extrasMenu;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JTextArea inputField;
    private javax.swing.JLabel inputLabel;
    private javax.swing.JComboBox keyComboBox;
    private javax.swing.JLabel keyLabel;
    private javax.swing.JMenuItem loadFromFileMenuItem;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JButton okButton;
    private javax.swing.JTextArea outputField;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JScrollPane plaintextScrollPane;
    private javax.swing.JMenuItem saveToFileMenuItem;
    // End of variables declaration//GEN-END:variables
    
}
