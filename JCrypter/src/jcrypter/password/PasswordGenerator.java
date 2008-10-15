/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jcrypter.password;

import java.security.SecureRandom;
import jcrypter.utils.MersenneTwisterFast;

/**
 *
 * @author uli
 */
public class PasswordGenerator
{
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
    
    public int[] byteToInt(byte[] input)
    {
        int[] array = new int[input.length/4]; //sizeof(int) = 4 bytes
        for(int i = 0;i < array.length; i++)
        {   
            array[i] = (int) input[4*i];
            array[i] += (int) input[4*i+1] << 8;
            array[i] += (int) input[4*i+2] << 16;
            array[i] += (int) input[4*i+3] << 24;
        }
        return array;
    }
}
