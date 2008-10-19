/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jcrypter.utils;

import java.security.SecureRandom;

/**
 *
 * @author uli
 */
public class Utils
{

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
    
    public static int[] MTSeed(SecureRandom randomSource)
    {
        //Seed; 624(*4) = max number of seed nums
        byte[] seedBytes = new byte[2496];
        randomSource.nextBytes(seedBytes);
        return byteToInt(seedBytes);
    }
}
