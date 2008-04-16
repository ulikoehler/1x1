package com.jfish;

import com.jfish.io.*;
import java.io.*;

/**
 *
 * @author neil
 */
public class TestTwofishECB {
    
    String inText = "This is a test of JFish. This text will be encrypted, then decrypted using ECB mode.";
    byte[] cipherText = null;
    byte[] decText = null;
    String passphrase = "testkey";
    int keysize = 32;
    
    /** Creates a new instance of TestTwofishECB */
    public TestTwofishECB() {
        System.out.println("JFish ECB Test\n");
        singleBlockCheck();
        
        encrypt();
        System.out.println("");
        decrypt();
        
        System.out.println("");
        System.out.println("--------------------");
        System.out.println("");
        
        JFish jfish = new JFish();
        
        System.out.println("String Verification Success: " + jfish.compareArrays(inText.getBytes(), decText));
        
        System.out.println("");
        System.out.println("--------------------");
        System.out.println("");
        
        checkOutputStream();
        System.out.println("");
        checkInputStream();
    }
    
    private void encrypt() {
        JFish jfish = new JFish();
        
        System.out.println("Input Text: " + inText);
        System.out.println("Passphrase: " + passphrase + "\n");
        
        System.out.println("Generating Key...");
        
        jfish.generateKey(passphrase, 32);
        
        System.out.println("Encrypting Input Text...");
        
        cipherText = jfish.encryptString(inText);
        
        System.out.println("");
        System.out.println("Encrypted Text: " + new String(cipherText));
        
        jfish.clear();
    }
    
    private void decrypt() {
        JFish jfish = new JFish();
        
        System.out.println("Passphrase: " + passphrase + "\n");
        
        System.out.println("Generating Key...");
        
        jfish.generateKey(passphrase, 32);
        
        System.out.println("Decrypting Cipher Text...");
        
        decText = jfish.decryptByteArray(cipherText);
        
        System.out.println("");
        System.out.println("Decrypted Text: " + new String(decText));
        
        jfish.clear();
    }
    
    public void singleBlockCheck() {
        System.out.println("Performing Single Block Test...");
        
        JFish jfish = new JFish();
        
        jfish.generateKey(passphrase, 32);
        
        byte[] inputBlock = new byte[16];
        
        System.out.println("Input Block: " + new String(inputBlock));
        
        byte[] encryptedBlock = jfish.encryptBlock(inputBlock);
        
        System.out.println("Encrypted Block: " + new String(encryptedBlock));
        
        jfish.clear();
        
        //Decryption phase
        
        jfish.generateKey(passphrase, 32);
        
        byte[] outputBlock = jfish.decryptBlock(encryptedBlock);
        
        System.out.println("Decrypted Block: " + new String(outputBlock));
        
        System.out.println("");
        System.out.println("--------------------");
        System.out.println("");
        
        System.out.println("Single Block Success: " + jfish.compareArrays(inputBlock, outputBlock));
        
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
        new TestTwofishECB();
    }
}
