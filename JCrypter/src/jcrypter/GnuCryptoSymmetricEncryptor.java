/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jcrypter;

import gnu.crypto.cipher.CipherFactory;
import gnu.crypto.cipher.IBlockCipher;
import gnu.crypto.hash.HashFactory;
import gnu.crypto.hash.IMessageDigest;
import gnu.crypto.mode.IMode;
import gnu.crypto.mode.ModeFactory;
import gnu.crypto.pad.IPad;
import gnu.crypto.pad.PadFactory;
import gnu.crypto.pad.WrongPaddingException;
import gnu.crypto.util.Base64;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author uli
 */
public class GnuCryptoSymmetricEncryptor
{
    
//Members
private IBlockCipher cipher = null;
private SecureRandom rand;
private IPad padding = PadFactory.getInstance("PKCS7");
private String cipherString;
private String modeString;
private int blocksize;
private int keysize;
private byte[] iv;

    /**
     * Getter for the cipher name
     * @return The cipher name currently associated with this object.
     */
    public String getCipherString()
    {
        return cipherString;
    }

    /**
     * Setter for the cipher name.
     * @param cipherString The cipher name to associate to this instance
     */
    public void setCipherString(String cipherString)
    {
        this.cipherString = cipherString;
    }
    
    /**
     * Getter for the mode name
     * @return The mode name currently associated with this object.
     */
    public String getModeString()
    {
        return modeString;
    }
    
    /**
     * Setter for the mode name
     * @param modeString The mode name to associate to this instance
     */
    public void setModeString(String modeString)
    {
        this.modeString = modeString;
    }

enum BlocksizeBehaviour{BS_DEFAULT, BS_MINIMUM, BS_MAXIMUM};
enum KeysizeBehaviour{KS_DEFAULT, KS_MINIMUM, KS_MAXIMUM};
        
public static final String[] modes = {"CBC", "CTR", "ECB", "ICM", "OFB"};
public static final String[] ciphers = {"AES", "Anubis", "Blowfish", "DES", "NULL", "Khazad", "Rijndael", "Serpent", "Square", "TripleDES", "Twofish"};

//Methods
//Constructors
private GnuCryptoSymmetricEncryptor(){} //Forbidden
/**
 * Constructs a new GnuCryptoSymmetricEncryptor object.
 * @param cipherParam
 * @param modeParam
 */
public GnuCryptoSymmetricEncryptor(String cipherParam, String modeParam)
{
    //Get default blocksize (initalize "raw" ecb cipher and call member method
    cipher = CipherFactory.getInstance(cipherParam);
    //Determinate blocksize
    blocksize = cipher.defaultBlockSize();
    //Determinate keysize
    keysize = cipher.defaultKeySize() * 8;
    //Init the main cipher object
    cipherString = cipherParam;
    modeString = modeParam;
}
/**
 * Default constructor with additional keysize parameter.
 * Using this constructor is not recommended if you don't know what you're doing
 * @param cipherParam The cipher name as String
 * @param modeParam The mode name as String
 * @param keysizeParam The keysize to use
 */
public GnuCryptoSymmetricEncryptor(String cipherParam, String modeParam, int keysizeParam)
{
    //Get default blocksize (initalize "raw" ecb cipher and call member method
    cipher = CipherFactory.getInstance(cipherParam);
    //Determinate blocksize
    blocksize = cipher.defaultBlockSize();
    //Determinate keysize
    keysize = keysizeParam;
    //Set member variables for later use (cipher is inited in ecnrypt/decrypt functions
    cipherString = cipherParam;
    modeString = modeParam;
}
//Member methods

private Map getInitMap(boolean encrypt, String password)
{
    Map attributes = new HashMap();
    //Put state (encryption/decryption)
    if (encrypt) {
        attributes.put(IMode.STATE, new Integer(IMode.ENCRYPTION));
    } else {
        attributes.put(IMode.STATE, new Integer(IMode.DECRYPTION));
    }

    IMessageDigest md;
    byte[] hashedPassword;
    switch (keysize)
    {
        case 128:
            //Use RIPEMD-128
            {
                md = HashFactory.getInstance("RIPEMD-128");
                md.update(new Integer(keysize).byteValue());
                hashedPassword = md.digest();
                break;
            }
        case 256:

            {
                md = HashFactory.getInstance("SHA-256");
                md.update(new Integer(keysize).byteValue());
                hashedPassword = md.digest();
                break;
            }
        default:
            {
                throw new UnsupportedOperationException("Keysize " + keysize + " bits is not supported by this class");
            }
    }
    attributes.put(IMode.KEY_MATERIAL, hashedPassword);
    //Generate random IV
    if(encrypt)
    {
        iv = generateIV(keysize);
        attributes.put(IMode.IV, iv);
    }
    //Put the block size into the map
    attributes.put(IMode.CIPHER_BLOCK_SIZE, new Integer(blocksize));
    return attributes;
}

/**
 * Tries to read a IV from /dev/urandom (on POSIX systems) or else uses a SecureRandom
 * instance to generate one with less entropy.
 */
private static byte[] generateIV(int size)
{
    //
    ////Try to read 128 bytes seed from /dev/urandom
    //
    FileInputStream urandomReader = null;
    byte[] iv = new byte[size];
    File urandom = new File("/dev/urandom");
    if (!urandom.exists())
        {
            //Non-POSIX system, use the standard Java SecureRandom generator for seeding
            new SecureRandom().nextBytes(iv);
            return iv;
        }
    try     
        {
            urandomReader = new FileInputStream(urandom);
            urandomReader.read(iv);
            urandomReader.close();
            return iv;
        }
    catch (FileNotFoundException ex) {} //Unreachable because of existence check before
    catch (IOException ex)
        {
            Logger.getLogger(GnuCryptoSymmetricEncryptor.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    finally
        {
            try{urandomReader.close();}
            catch (IOException ex)
            {
                Logger.getLogger(GnuCryptoSymmetricEncryptor.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            }
        }
    
    //Non-POSIX system, use the standard Java SecureRandom generator for seeding
    new SecureRandom().nextBytes(iv);
    return iv;
}

private byte[] padTextPKCS7(String text)
{
    padding.init(blocksize);
    byte[] textBytes = text.getBytes();
    int tbLen = textBytes.length;
    byte[] appendBytes = padding.pad(textBytes, 0, tbLen);
    byte[] resultBytes  = new byte[tbLen + appendBytes.length];
    System.arraycopy(textBytes, 0, resultBytes, 0, tbLen);
    System.arraycopy(appendBytes, 0, resultBytes, tbLen, appendBytes.length);
    return resultBytes;
}

private String unpadTextPKCS7(byte[] textBytes) throws WrongPaddingException
{
    padding = PadFactory.getInstance("PKCS7");
    padding.init(blocksize);
    int tbLen = textBytes.length;
    int cutoffLen = padding.unpad(textBytes, 0, tbLen);
    return new String(textBytes, 0, tbLen-cutoffLen);    
}

public String encryptString(String plaintext, String password)
{
    return encryptString(plaintext, password, true);
}


public String encryptString(String plaintext, String password, boolean b64encode)
{
        try
        {
            if(cipher != null) {cipher.reset();}
            //Init the cipher
            cipher = ModeFactory.getInstance(getModeString(),getCipherString(), blocksize);
            //Init the cipher
            cipher.init(getInitMap(true, password));
            //Pad the ciphertext
            byte[] paddedPlaintext = padTextPKCS7(plaintext);
            //Do the actual encryption
            byte[] output = new byte[paddedPlaintext.length];
            for(int i = -blocksize;i + blocksize < paddedPlaintext.length; i += blocksize)
                  {
                    cipher.encryptBlock(paddedPlaintext, i+blocksize, output, i+blocksize);
                  }
            //Return the IV concatenated with the ciphertext
            byte[] outputBuffer = new byte[blocksize + paddedPlaintext.length];
            System.arraycopy(iv, 0, outputBuffer, 0, blocksize);
            System.arraycopy(output, 0, outputBuffer, blocksize, output.length);
            //Base64-encode the StringBuilder's content and return
            if(b64encode){return Base64.encode(outputBuffer);}
            else{return new String(outputBuffer);}
        }
        catch (InvalidKeyException ex)
            {
                Logger.getLogger(GnuCryptoSymmetricEncryptor.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            }        catch (IllegalStateException ex)
            {
                Logger.getLogger(GnuCryptoSymmetricEncryptor.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            }
        return null;
}

public static SecureRandom getSeededSecureRandom()
{
    return new SecureRandom(generateIV(128));
}

public SecureRandom getGenerator()
{
    return rand;
}

public String decryptString(String ciphertext, String password)
{
    return decryptString(ciphertext, password, true);
}

public String decryptString(String ciphertext, String password, boolean b64decode) 
{
        try
        {
            //Reset the cipher if it is not null
            if(cipher != null) {cipher.reset();}
            //Init the cipher
            cipher = ModeFactory.getInstance(getModeString(),getCipherString(), blocksize);
            Map initMap = getInitMap(false, password);
            //Do the actual encryption
            byte[] ciphertextBytes;
            if(b64decode) {ciphertextBytes = Base64.decode(ciphertext);}
            else {ciphertextBytes = ciphertext.getBytes();}
            int ctLen = ciphertextBytes.length;
            //Get the IV from the ciphertext array
            iv = new byte[blocksize];
            System.arraycopy(ciphertextBytes, 0, iv, 0, blocksize);
            initMap.put(IMode.IV, iv);
            //Init the cipher object
            cipher.init(initMap);
            //Do the actual decryption process
            byte[] output = new byte[ctLen-blocksize];
            for(int i = 0;i + blocksize < ctLen; i += blocksize)
                  {
                    cipher.decryptBlock(ciphertextBytes, i+blocksize, output, i);
                  }
            //Unpad the output and return
            return unpadTextPKCS7(output);
        }
        catch (UnsupportedEncodingException ex)
        {
            Logger.getLogger(GnuCryptoSymmetricEncryptor.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        catch (WrongPaddingException ex)
        {
            Logger.getLogger(GnuCryptoSymmetricEncryptor.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        catch (InvalidKeyException ex)
        {
            Logger.getLogger(GnuCryptoSymmetricEncryptor.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        catch (IllegalStateException ex)
        {
            Logger.getLogger(GnuCryptoSymmetricEncryptor.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        return null;
}



public String cryptString(String plaintext, String password, boolean decrypt)
{
    if(decrypt)
        {return decryptString(plaintext, password);}
    else
        {return encryptString(plaintext, password);}
}

public static String encryptString(String cipher, String mode, String plaintext, String password)
{
    return new GnuCryptoSymmetricEncryptor(cipher,
                                  mode).encryptString(plaintext, password);
}


public static String decryptString(String cipher, String mode, String plaintext, String password)
{
    return new GnuCryptoSymmetricEncryptor(cipher,
                                  mode)
                                  .decryptString(plaintext, password);
}

public static String cryptString(String cipher, String mode, String plaintext, String password, boolean decrypt)
{
    if(decrypt)
        {return decryptString(cipher, mode, plaintext, password);}
    else
        {return encryptString(cipher, mode, plaintext, password);}
}

/**
 * Getter for a list of supported ciphers
 * @return A String array of the names of the supported ciphers
 */
public String[] getSupportedCiphers() {
    return ciphers;
}

/**
 * Getter for a list of supported modes
 * @return A String array of the names of the supported modes
 */
public String[] getSupportedModes() {
    return modes;
}
}
