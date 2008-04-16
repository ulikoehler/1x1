package com.jrijndael;

import com.jrijndael.crypt.*;



/**
 *
 * @author neil
 */
public class JRijndael {
    
    private Rijndael algorithm;
    
    //The constructor
    public JRijndael() {
        algorithm = new Rijndael();
    }
    
    //Generates an internal Rijndael key from the supplied passphrase
    public void generateKey(String passphrase, int keysize) {
        String pass = "";
        
        if (keysize == 8) {
            pass = "uuuuuuuu";
        } else if (keysize == 16) {
            pass = "uuuuuuuuuuuuuuuu";
        } else if (keysize == 24) {
            pass = "uuuuuuuuuuuuuuuuuuuuuuuu";
        } else if (keysize == 32) {
            pass = "uuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu";
        }
        
        pass = new String(passphrase + pass.substring(passphrase.length()));
        
        algorithm.makeKey(pass.getBytes(), keysize * 8);
    }
    
    //Encrypts a supplied string using the internal key
    public byte[] encryptString(String string) {
        byte[] result = encryptString(string, 0);
        
        return result;
    }
    
    //Encrypts a supplied string from the offset point on using the internal key
    public byte[] encryptString(String string, int offset) {
        byte[] result = null;
        
        try {
            result = algorithm.encryptArray(string.getBytes(), offset);
        } catch (CryptoException ex) {
            ex.printStackTrace();
        }
        return result;
    }
    
    //Decrypts a supplied string using the internal key
    public byte[] decryptString(String string) {
        byte[] result = decryptString(string, 0);
        
        return result;
    }
    
    //Decrypts a supplied string from the offset point on using the internal key
    public byte[] decryptString(String string, int offset) {
        byte[] result = null;
        
        try {
            result = algorithm.decryptArray(string.getBytes(), offset);
        } catch (CryptoException ex) {
            ex.printStackTrace();
        }
        return result;
    }
    
    //Encrypts a supplied byte array using the internal key
    public byte[] encryptByteArray(byte[] bytes) {
        byte[] result = encryptByteArray(bytes, 0);
        
        return result;
    }
    
    //Encrypts a supplied byte array from the offset point on using the internal key
    public byte[] encryptByteArray(byte[] bytes, int offset) {
        byte[] result = null;
        
        try {
            result = algorithm.encryptArray(bytes, offset);
        } catch (CryptoException ex) {
            ex.printStackTrace();
        }
        return result;
    }
    
    //Decrypts a supplied byte array using the internal key
    public byte[] decryptByteArray(byte[] bytes) {
        byte[] result = decryptByteArray(bytes, 0);
        
        return result;
    }
    
    //Decrypts a supplied string from the offset point on using the internal key
    public byte[] decryptByteArray(byte[] bytes, int offset) {
        byte[] result = null;
        
        try {
            result = algorithm.decryptArray(bytes, offset);
        } catch (CryptoException ex) {
            ex.printStackTrace();
        }
        return result;
    }
    
    //Encrypts a supplied block using the internal key
    public byte[] encryptBlock(byte[] block) {
        byte[] result = algorithm.encryptBlock(block, new byte[algorithm.BLOCK_SIZE]);
        
        return result;
    }
    
    //Decrypts a supplied block using the internal key
    public byte[] decryptBlock(byte[] block) {
        byte[] result = algorithm.decryptBlock(block, new byte[algorithm.BLOCK_SIZE]);
        
        return result;
    }
    
    //Compares two byte arrays to determine whether they are equal
    public boolean compareArrays(byte[] a, byte[] b) {
        boolean equal = algorithm.areEqual(a, b);
        
        return equal;
    }
    
    //Returns the Twofish algorithm's block size
    public int getBlockSize() {
        int blocksize = algorithm.BLOCK_SIZE;
        
        return blocksize;
    }
    
    //Clears the internal variables
    public void clear() {
        new JRijndael();
    }
}
