/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jcrypter.password;

import java.security.SecureRandom;
import java.util.LinkedList;
import java.util.List;
import jcrypter.utils.MersenneTwisterFast;

/**
 *
 * @author uli
 */
public class PasswordGenerator
{

    public static final String completeCharset =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789^°!\"§$%&/()=?`'\\}][{³²@äöüÄÖÜ#'+*~,;.:<>| -_";
    public static final String alphanumericCharset =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    SecureRandom randomSource;
    MersenneTwisterFast mt;

    /**
     * Seeds using the seed generation algorithm of randomSource
     * @param randomSource The random source to be used
     */
    public PasswordGenerator(SecureRandom randomSource)
    {
        this.randomSource = randomSource;
        //Seed; 624(*4) = max number of seed nums
        byte[] seedBytes = new byte[2496];
        randomSource.nextBytes(seedBytes);
        mt = new MersenneTwisterFast(byteToInt(seedBytes));
    }

    public PasswordGenerator()
    {
        this.randomSource = new SecureRandom();
        //Seed; 624(*4) = max number of seed nums
        byte[] seedBytes = new byte[2496];
        randomSource.nextBytes(seedBytes);
        mt = new MersenneTwisterFast(byteToInt(seedBytes));
    }

    public void reseed()
    {
        byte[] seedBytes = new byte[2496];
        randomSource.nextBytes(seedBytes);
        mt.setSeed(byteToInt(seedBytes));
    }

    public char[] generatePassword(String charset, int length)
    {
        int charsetLength = charset.length();
        char[] password = new char[length];
        for (int i = 0; i < length; i++)
        {
            int randInt = mt.nextInt(charsetLength);
            password[i] = charset.charAt(randInt % charsetLength);
        }
        return password;
    }
    
    public List<char[]> generatePasswordList(String charset, int length, int num)
    {
        //Generate a password char-by-char
        List<char[]> passwords = new LinkedList<char[]>();
        for (int i = 0; i < num; i++)
        {
            passwords.add(generatePassword(charset, length));
        }
        return passwords;
    }

    public static String generateCharset(boolean ul, boolean ll, boolean nums,
            boolean special, boolean white, boolean minus, boolean underl)
    {
        StringBuilder charsetBuilder = new StringBuilder();
        if (ul)
        {
            charsetBuilder.append("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        }
        if (ll)
        {
            charsetBuilder.append("abcdefghijklmnopqrstuvwxyz");
        }
        if (nums)
        {
            charsetBuilder.append("0123456789");
        }
        if (special)
        {
            charsetBuilder.append("^°!\"§$%&/()=?`'\\}][{³²@äöüÄÖÜ#'+*~,;.:<>|");
        }
        if (white)
        {
            charsetBuilder.append(" ");
        }
        if (minus)
        {
            charsetBuilder.append("-");
        }
        if (underl)
        {
            charsetBuilder.append("_");
        }
        //Check if we don't have an empty string
        if (charsetBuilder.length() == 0)
        {
            return null;
        }
        return charsetBuilder.toString();
    }

    public int[] byteToInt(byte[] input)
    {
        int[] array = new int[input.length / 4]; //sizeof(int) = 4 bytes
        for (int i = 0; i < array.length; i++)
        {
            array[i] = (int) input[4 * i];
            array[i] += (int) input[4 * i + 1] << 8;
            array[i] += (int) input[4 * i + 2] << 16;
            array[i] += (int) input[4 * i + 3] << 24;
        }
        return array;
    }
}
