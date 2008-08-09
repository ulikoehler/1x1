/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jtrafficgenerator;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import javax.swing.JLabel;

/**
 *
 * @author uli
 */
public class JScientificNumberLabel extends JLabel
{
    public enum Notation
    {
        SCIENTIFIC,
        SCIENTIFIC_SUFFIX
    }
    
    //SI Unit suffices. Offset:9
    private final char[] siSuffices = new char[]{'y','z','a','f','p','n','\u005b','m',' ','k','M','G','T','P','E','Z','Y'};
    
    private DecimalFormat format = new DecimalFormat("0.##E00", new DecimalFormatSymbols(Locale.ENGLISH));
    private Notation notation = Notation.SCIENTIFIC_SUFFIX;
    private int decimals = 2;
    /**
     * Sets the format parameters.
     * @param notationParam The notation type to use.
     * @param decimalsParam Sets the numbers of decimals to display
     */
    public void setNotation(Notation notationParam, int decimalsParam)
    {
        //Check variables
        //Set variables
        notation = notationParam;
        decimals = decimalsParam;
        //Generate format string
        StringBuilder formatString  = new StringBuilder("#0");
        if(decimalsParam != 0)
        {
            formatString.append('.');
            for(int i = 0; i < decimalsParam;i++) formatString.append("#"); //Append a optional decimal digit to the format string
        }
        formatString.append("E00");
        format .applyPattern(formatString.toString());
    }
    
    /**
     * Formats a number
     * @param input The number to format
     * @returns The formatted Number as String
     * @see setNotation
     */
    protected String formatNumber(double input)
    {
        String formattedInput = format.format(input);
        switch(notation)
        {
            case SCIENTIFIC: //Just return the formatted String
                {
                    return formattedInput;
                }
            case SCIENTIFIC_SUFFIX: //Use algorithms to fit the input number
                {
                    //Init result string builder
                    StringBuilder resultSB = new StringBuilder(); 
                    //If the default localization want a comma instead of a point, replace it (in fact, don't check, just replace)
                    formattedInput.replaceAll(",", "."); 
                    int exponentBeginIndex = formattedInput.lastIndexOf("E") + 1;
                    //Calculate the exponent from a substring
                    int exponent = new Integer(formattedInput.substring(exponentBeginIndex, formattedInput.length()));
                    double mantissa = new Double(formattedInput.substring(0, exponentBeginIndex-2));
                    int modulus = exponent % 3;
                    int arrayIndex = (int) Math.floor(input);
                    if(arrayIndex > siSuffices.length) {return formattedInput;}
                    switch(modulus)
                    {
                        case 0:
                            {
                                resultSB.append(mantissa);
                                resultSB.append(siSuffices[arrayIndex]);
                            }
                        case 1:
                            {
                                mantissa *= 10;
                                resultSB.append(mantissa);
                                resultSB.append(siSuffices[arrayIndex]);
                            }
                        case 2:
                            {
                                mantissa /= 10;
                                resultSB.append(mantissa);
                                resultSB.append(siSuffices[arrayIndex]);
                            }
                    }
                    return resultSB.toString();
                }
        }
        return null;
    }
    
    public void setNumber(double number)
    {
        super.setText(formatNumber(number));
    }    
}
