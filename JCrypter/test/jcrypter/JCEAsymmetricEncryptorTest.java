/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jcrypter;

import java.security.SecureRandom;
import java.security.interfaces.RSAPublicKey;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import sun.security.rsa.RSAKeyPairGenerator;
import static org.junit.Assert.*;

/**
 *
 * @author uli
 */
public class JCEAsymmetricEncryptorTest {

    public JCEAsymmetricEncryptorTest() {
    }

    /**
     * Test of encrypt method, of class JCEAsymmetricEncryptor.
     */
    @Test
    public void encrypt() {
        System.out.println("encrypt");
        RSAKeyPairGenerator kpgen = new RSAKeyPairGenerator();
        kpgen.initialize(1024, new SecureRandom());
        RSAPublicKey key = null;
        String algorithm = "";
        String plaintext = "";
        boolean b64encode = false;
        String expResult = "";
        String result = JCEAsymmetricEncryptor.encrypt(key, algorithm, plaintext, b64encode);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}