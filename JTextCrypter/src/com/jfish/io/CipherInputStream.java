package com.jfish.io;

import java.io.*;
import com.jfish.JFish;


public class CipherInputStream {
    
    //The underlying BufferedInputStream instance
    private BufferedInputStream bis;
    
    //The underlying block cipher instance
    private JFish algorithm;
    
    /** Creates a new instance of CipherInputStream */
    public CipherInputStream(FileInputStream fis, String passphrase, int keylength) {
        //Create a new instance of the BufferedInputStream
        bis = new BufferedInputStream(fis);
        
        //Create a new instance of the block cipher
        algorithm = new JFish();
        
        //Initialize the block cipher
        algorithm.generateKey(passphrase, keylength);
    }
    
    /** Fills the supplied Byte Array with decrypted text from the BufferedInputStream */
    public byte[] read(byte[] bytes) throws IOException {
        //Fill the supplyed array with encrypted bytes
        bis.read(bytes);
        
        //Decrypt the bytes
        byte[] plainText = algorithm.decryptByteArray(bytes);
        
        //Return the decrypted bytes
        return plainText;
    }
    
    /** Returns the amount of available bytes */
    public int available() throws IOException {
        //Return the available byte count
        return bis.available();
    }
    
    /** Sets the BufferedInputStream's mark */
    public void mark(int i) {
        //Set the BufferedInputStream's mark
        bis.mark(i);
    }
    
    /** Returns whether or not the BufferedInputStream supports mark */
    public boolean markSupported() {
        //Return whether or not the BufferedInputStream supports mark
        return bis.markSupported();
    }
    
    /** Resets the BufferedInputStream */
    public void reset() throws IOException {
        //Reset the BufferedInputStream
        bis.reset();
    }
    
    /** Skips bytes on the BufferedInputStream */
    public void skip(long l) throws IOException {
        //Set the BufferedInputStream's skip count
        bis.skip(l);
    }
    
    /** Clears the cipher, then closes the BufferedInputStream */
    public void close() throws IOException {
        //Clear the block cipher
        algorithm.clear();
        
        //Close the BufferedInputStream
        bis.close();
    }
    
}
