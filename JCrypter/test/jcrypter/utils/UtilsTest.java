/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jcrypter.utils;

import java.security.SecureRandom;
import jcrypter.password.PasswordGenerator;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author uli
 */
public class UtilsTest {

    public UtilsTest() {
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
     * Test of byteToInt method, of class Utils.
     * Test if correct results are produced
     */
    @Test
    public void testByteToInt()
    {
        System.out.println("byteToInt");
        //Test with expected results: 1; 1+(2<<8); 35+(115<<8)+(31<<16)+(127<<24)
        byte[] input = {1,0,0,0, 1,2,0,0, 35,115,31,127};
        int[] expResult = {1, 1+(2<<8),35+(115<<8)+(31<<16)+(127<<24)};
        int[] result = Utils.byteToInt(input);
        assertEquals(expResult[0], result[0]);
        assertEquals(expResult[1], result[1]);
        //Test with input with an additional array
        byte[] input2 = {1,0,0,0,2};
        int[] expResult2 = {1};
        result = Utils.byteToInt(input2);
        assertEquals(expResult[0], result[0]);
    }

    /**
     * Test of MTSeed method, of class Utils.
     */
    @Test
    public void testMTSeed()
    {
        System.out.println("MTSeed");
        SecureRandom randomSource = null;
        int[] expResult = null;
        int[] result = Utils.MTSeed(randomSource);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}