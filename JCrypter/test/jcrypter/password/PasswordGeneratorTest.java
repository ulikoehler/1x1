/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jcrypter.password;

import java.security.SecureRandom;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author uli
 */
public class PasswordGeneratorTest {

    public PasswordGeneratorTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception
    {
    }

    @AfterClass
    public static void tearDownClass() throws Exception
    {
    }

    /**
     * Test of reseed method, of class PasswordGenerator.
     * Just checks for exceptions
     */
    @Test
    public void testReseed()
    {
        System.out.println("reseed");
        PasswordGenerator instance = new PasswordGenerator();
        instance.reseed();
    }

    /**
     * Test of byteToInt method, of class PasswordGenerator.
     * Test if correct results are produced
     */
    @Test
    public void testByteToInt()
    {
        System.out.println("byteToInt");
        //Test with expected results: 1; 1+(2<<8); 35+(115<<8)+(31<<16)+(127<<24)
        byte[] input = {1,0,0,0, 1,2,0,0, 35,115,31,127};
        PasswordGenerator instance = new PasswordGenerator();
        int[] expResult = {1, 1+(2<<8),35+(115<<8)+(31<<16)+(127<<24)};
        int[] result = instance.byteToInt(input);
        assertEquals(expResult[0], result[0]);
        assertEquals(expResult[1], result[1]);
    }

}