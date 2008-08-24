/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jcrypter;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.interfaces.RSAPublicKey;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;

/**
 *
 * @author uli
 */
public class JCEAsymmetricEncryptor
{
    /**
     * Encrypts a text using a non-hybrid method. May be much slower than the hybrid method.
     * @param key The public key to use for encryption
     * //param algorithm The symmetric algorithm name to encrypt the plaintext with.
     *                  A suitable provider must be available. Has to be a JCE algorithm
     *                  String like "AES/CBC/PKCS5Padding".
     *                  If the string is null, "AES/CBC/PKCS7Padding is used."
     * @param plaintext The plaintext to encrypt as bytes (use String.getBytes()).
     * @param b64encode Whether to Base64-encode the output.
     *                  Should only be false if the output is processed further.
     * @return the ciphertext
     */
    public static String encrypt(RSAPublicKey key,
                                       byte[] plaintext,
                                       boolean b64encode)
    {
        try
        {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, key);
        }
        catch (InvalidKeyException ex)
            {
                Logger.getLogger(JCEAsymmetricEncryptor.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            }
        catch (NoSuchAlgorithmException ex)
            {
                Logger.getLogger(JCEAsymmetricEncryptor.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            }
        catch (NoSuchPaddingException ex)
            {
                Logger.getLogger(JCEAsymmetricEncryptor.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            }
        finally{return null;}
    }
}
