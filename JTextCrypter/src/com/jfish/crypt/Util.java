package com.jfish.crypt;

import com.jfish.*;


//Utility class providing useful methods.
public final class Util {
    
    // Stops initialsiation
    private Util() {
    }
    
    //Adds two byte arrays together.
    public static byte[] addByteArrays(byte[] first, byte[] second) {
        
        byte[]result=new byte[first.length+second.length];
        
        System.arraycopy(first,0,result,0,first.length);
        System.arraycopy(second,0,result,first.length,second.length);
        
        return result;
    }
    
}