package com.jrijndael;

import com.jrijndael.io.*;
import java.io.*;

/**
 *
 * @author neil
 */
public class TestRijndaelECB {
    
    String inText = "This is a test of JRijndael. This text will be encrypted, then decrypted using ECB mode.";
    byte[] cipherText = null;
    byte[] decText = null;
    String passphrase = "testkey";
    
    /** Creates a new instance of TestRijndaelECB */
    public TestRijndaelECB() {
        System.out.println("JRijndael ECB Test\n");
        singleBlockCheck();
        
        encrypt();
        System.out.println("");
        decrypt();
        
        System.out.println("");
        System.out.println("--------------------");
        System.out.println("");
        
        JRijndael jserpent = new JRijndael();
        
        System.out.println("String Verification Success: " + jserpent.compareArrays(inText.getBytes(), decText));
        
        System.out.println("");
        System.out.println("--------------------");
        System.out.println("");
        
        checkOutputStream();
        System.out.println("");
        checkInputStream();
    }
    
    private void encrypt() {
        JRijndael jserpent = new JRijndael();
        
        System.out.println("Input Text: " + inText);
        System.out.println("Passphrase: " + passphrase + "\n");
        
        System.out.println("Generating Key...");
        
        jserpent.generateKey(passphrase, 32);
        
        System.out.println("Encrypting Input Text...");
        
        cipherText = jserpent.encryptString(inText);
        
        System.out.println("");
        System.out.println("Encrypted Text: " + new String(cipherText));
        
        jserpent.clear();
    }
    
    private void decrypt() {
        JRijndael jserpent = new JRijndael();
        
        System.out.println("Passphrase: " + passphrase + "\n");
        
        System.out.println("Generating Key...");
        
        jserpent.generateKey(passphrase, 32);
        
        System.out.println("Decrypting Cipher Text...");
        
        decText = jserpent.decryptByteArray(cipherText);
        
        System.out.println("");
        System.out.println("Decrypted Text: " + new String(decText));
        
        jserpent.clear();
    }
    
    public void singleBlockCheck() {
        System.out.println("Performing Single Block Test...");
        
        JRijndael jserpent = new JRijndael();
        
        jserpent.generateKey(passphrase, 32);
        
        byte[] inputBlock = new byte[16];
        
        System.out.println("Input Block: " + new String(inputBlock));
        
        byte[] encryptedBlock = jserpent.encryptBlock(inputBlock);
        
        System.out.println("Encrypted Block: " + new String(encryptedBlock));
        
        jserpent.clear();
        
        //Decryption phase
        
        jserpent.generateKey(passphrase, 32);
        
        byte[] outputBlock = jserpent.decryptBlock(encryptedBlock);
        
        System.out.println("Decrypted Block: " + new String(outputBlock));
        
        System.out.println("");
        System.out.println("--------------------");
        System.out.println("");
        
        System.out.println("Single Block Success: " + jserpent.compareArrays(inputBlock, outputBlock));
        
        System.out.println("");
        System.out.println("--------------------");
        System.out.println("");
        
    }
    
    public void checkOutputStream() {
        System.out.println("Performing Output Stream Test...\n");
        
        System.out.println("Opening File Streams...");
        File testFile = new File("test.txt");
        FileOutputStream fos = null;
        try {
            testFile.createNewFile();
            fos = new FileOutputStream(testFile);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        System.out.println("Opening Cipher Stream...");
        CipherOutputStream cos = new CipherOutputStream(fos, passphrase, 32);
        System.out.println("");
        System.out.println("Writing Text...");
        try {
            cos.write(inText.getBytes());
            System.out.println("");
            System.out.println("Flushing Cipher Stream...");
            cos.flush();
            
            System.out.println("Closing Cipher Stream...");
            cos.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public void checkInputStream() {
        System.out.println("");
        System.out.println("Performing Input Stream Test...\n");
        
        FileInputStream fis = null;
        
        System.out.println("Opening File Streams...");
        File testFile = new File("test.txt");
        try {
            fis = new FileInputStream(testFile);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        
        System.out.println("Opening Cipher Stream...");
        CipherInputStream cis = new CipherInputStream(fis, passphrase, 32);
        System.out.println("");
        System.out.println("Reading Text...");
        byte[] output = null;
        
        try {
            output = cis.read(new byte[(int)testFile.length()]);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        String outText = new String(output);
        
        System.out.println("\nDecrypted Text: " + outText);
        
        
        System.out.println("");
        System.out.println("--------------------");
        System.out.println("");
        
        System.out.println("Cipher Stream Check Success: " + outText.equals(inText));
        
        System.out.println("");
        System.out.println("--------------------");
        System.out.println("");
    }
    
    public static void main(String[] args) {
        new TestRijndaelECB();
    }
}
