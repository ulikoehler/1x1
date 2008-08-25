/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jcrypter;

import gnu.crypto.util.Base64;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.MessageDigest;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author uli
 */
public class JCESymmetricEncryptor
{
    public static String encrypt(String algorithm, String plaintext, Key key) throws GeneralSecurityException
    {
        return Base64.encode(encryptToBytes(algorithm, plaintext, key));
    }
    
    public static String decrypt(String algorithm, String ciphertext, Key key) throws GeneralSecurityException, UnsupportedEncodingException
    {
        return new String(decryptToBytes(algorithm, Base64.decode(ciphertext), key));
    }
    
    public static byte[] decryptToBytes(String algorithm, byte[] ciphertext, Key key) throws GeneralSecurityException
    {
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.DECRYPT_MODE, key);
        //Do the actual encryption
        return cipher.doFinal(ciphertext);
    }
            
    private static String deriveAlgorithmOnlyName(String algorithmString)
    {
        return algorithmString.substring(0, algorithmString.indexOf('/'));
    }
    
    private static void derive256Key(String password, Cipher cipher, int mode) throws GeneralSecurityException
    {
            //Hash the password
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] keyBytes = digest.digest(password.getBytes());
            //Generate the key and initialize the cipher
            String algorithm = deriveAlgorithmOnlyName(cipher.getAlgorithm());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(algorithm);
            SecretKeySpec keySpec = new SecretKeySpec(keyBytes, algorithm);
            cipher.init(mode, keyFactory.generateSecret(keySpec));
    }
    
    public static byte[] encryptToBytes(String algorithm, String plaintext, Key key) throws GeneralSecurityException
    {
            Cipher cipher = Cipher.getInstance(algorithm);
            //Do the actual encryption
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] plaintextBytes = plaintext.getBytes();
            return cipher.doFinal(plaintextBytes);
    }
}
