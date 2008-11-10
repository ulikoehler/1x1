/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jcrypter.password;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private String charset = null;
    private int charsetLength;
    private SecureRandom randomSource;
    private MersenneTwisterFast mt;
    private static final int mtSeedSize = 2496;

    /**
     * Seeds using the seed generation algorithm of randomSource
     * @param randomSource The random source to be used
     */
    public PasswordGenerator(SecureRandom randomSource)
    {
        this.randomSource = randomSource;
        mt = new MersenneTwisterFast(generateMTSeed(randomSource));
        //Set standard charset
        this.charset = alphanumericCharset;
    }

    public PasswordGenerator()
    {
        this.randomSource = new SecureRandom();
        //Seed; 624(*4) = max number of seed nums
        mt = new MersenneTwisterFast(generateMTSeed(randomSource));
        //Set standard charset
        this.charset = alphanumericCharset;
    }

    public void reseed()
    {
        mt.setSeed(generateMTSeed(randomSource));
    }

    private static byte[] getUrandMTSeed()
    {
        try
        {
            File urandom = new File("/dev/urandom");
            if (!urandom.exists())
            {
                return null;
            }
            byte[] seed = new byte[2496];
            FileInputStream fin = new FileInputStream(urandom);
            fin.read(seed);
            fin.close();
            return seed;
        }
        catch (IOException ex)
        {
            Logger.getLogger(PasswordGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null; //Never occurs
    }

    public static int[] byteToInt(byte[] input)
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

    public static int[] generateMTSeed(SecureRandom randomSource)
    {
        //Seed; 624(*4) = max number of seed bytes
        byte[] seedBytes = null;
        seedBytes = getUrandMTSeed();
        if (seedBytes == null)
        {
            seedBytes = new byte[mtSeedSize];
            randomSource.nextBytes(seedBytes);
        }
        return byteToInt(seedBytes);
    }

    public char[] generatePassword(int length)
    {
        char[] password = new char[length];
        for (int i = 0; i < length; i++)
        {
            int randInt = mt.nextInt(charsetLength);
            password[i] = charset.charAt(randInt % charsetLength);
        }
        return password;
    }

    public List<char[]> generatePasswordList(int length, int num)
    {
        //Generate a password char-by-char
        List<char[]> passwords = new LinkedList<char[]>();
        for (int i = 0; i < num; i++)
        {
            passwords.add(generatePassword(length));
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

    public String getCharset()
    {
        return charset;
    }

    public void setCharset(String charset)
    {
        this.charset = charset;
        charsetLength = charset.length();
    }
}
