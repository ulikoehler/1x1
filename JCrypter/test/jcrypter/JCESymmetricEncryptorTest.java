/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jcrypter;

import java.security.Key;
import java.security.SecureRandom;
import javax.crypto.KeyGenerator;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author uli
 */
public class JCESymmetricEncryptorTest {

    public JCESymmetricEncryptorTest() {
        System.out.println("->Testing class JCESymmetricEncryptor");
    }

    /**
     * Tests if the decryption of an encrypted String is equal to the original input.
     */
    @Test
    public void encryptDecrypt() throws Exception {
        System.out.println("Testing symmetric encryption/decryption");
        String algorithm = "AES/CBC/PKCS5Padding";
        String plaintext = "This is a test text";
        SecureRandom rand = new SecureRandom();
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(rand);
        Key key = keyGen.generateKey();
        String ciphertext = JCESymmetricEncryptor.encrypt(algorithm, plaintext, key);
        System.out.println("Encryption succeeded");
        String result = JCESymmetricEncryptor.decrypt(algorithm, ciphertext, key);
        System.out.println("Decryption succeeded");
        assertEquals(result, plaintext);
    }
    /**
     * Test of decryptToBytes method, of class JCESymmetricEncryptor.
     */
//    @Test
//    public void decryptToBytes() throws Exception {
//        System.out.println("decryptToBytes");
//        String algorithm = "";
//        String plaintext = "";
//        String password = "";
//        byte[] expResult = null;
//        byte[] result = JCESymmetricEncryptor.decryptToBytes(algorithm, plaintext, password);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

}