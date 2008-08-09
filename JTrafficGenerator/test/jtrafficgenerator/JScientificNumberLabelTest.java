/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jtrafficgenerator;

import jtrafficgenerator.JScientificNumberLabel.Notation;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author uli
 */
public class JScientificNumberLabelTest {

    public JScientificNumberLabelTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of setNotation method, of class JScientificNumberLabel.
     */
    @Test
    public void setNotation() {
        System.out.println("setNotation");
        //Test default parameters
        Notation notationParam = Notation.SCIENTIFIC_SUFFIX;
        int decimalsParam = 2;
        JScientificNumberLabel instance = new JScientificNumberLabel();
        instance.setNotation(notationParam, decimalsParam);
        //Test null case for parameters
        notationParam = null;
        decimalsParam = 0;
        instance.setNotation(notationParam, decimalsParam);
    }

    /**
     * Test of formatNumber method, of class JScientificNumberLabel.
     */
    @Test
    public void formatNumber() {
        System.out.println("formatNumber");
        double input = 0.0;
        JScientificNumberLabel instance = new JScientificNumberLabel();
        //s instance.setNotation(Notation.SCIENTIFIC_SUFFIX, 2);
        //Test with modulus 0   
        input = 1234.0;
        String expResult = "1.23k";
        String result = instance.formatNumber(input);
        assertEquals(expResult, result);
        //Test case with modulus 1
        input = 12345.0;
        expResult = "10.2k";
        result = instance.formatNumber(input);
        assertEquals(expResult, result);
        //Test case with modulus 2
        input = 12345.0;
        expResult = "10.2k";
        result = instance.formatNumber(input);
        assertEquals(expResult, result);
        //Test case with Output too large to have a prefix
        input = 1.0 * ((int)10^26);
        expResult = "1E26";
        result = instance.formatNumber(input);
        assertEquals(expResult, result);
    }

    /**
     * Test of setNumber method, of class JScientificNumberLabel.
     * Tests only if the result is set right in the text field, not
     */
    @Test
    public void setNumber() {
        System.out.println("setNumber");
        double number = 0.0;
        JScientificNumberLabel instance = new JScientificNumberLabel();
        instance.setNumber(number);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}