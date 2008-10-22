/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jcrypter.password;

import java.util.List;
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
     * Test of generatePasswordList method, of class PasswordGenerator.
     * Also checks generatePassword implicitly because it is called by generatePasswordList
     */
    @Test
    public void testGeneratePasswordList()
    {
        System.out.println("generatePasswordList");
        String charset = PasswordGenerator.completeCharset;
        int num = 10;
        int length = 5;
        PasswordGenerator instance = new PasswordGenerator();
        instance.setCharset(charset);
        List<char[]> result = instance.generatePasswordList(length, num);
        
        int count = 0;
        for(char[] password : result)
        {
            count++;
            assertTrue(password.length == length); //Check password length
        }
        assertTrue(count == num); //Check the password count
    }

}