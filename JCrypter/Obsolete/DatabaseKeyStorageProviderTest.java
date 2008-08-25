/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jcrypter;

import java.io.*;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.util.List;
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
public class DatabaseKeyStorageProviderTest {
    
    private String connectionURL = "jdbc:derby://localhost/Test";
    private String table = "Keys";
    private String username = "Test";
    private String password = "Test";
    
    private String identifier = "TestIdentifier";
    
    private static KeyPair expResult;

    public DatabaseKeyStorageProviderTest() throws ClassNotFoundException {
    }
    
    /**
     * Test of setup method, of class DatabaseKeyStorageProvider.
     */
    @Test
    public void setup() throws Exception
    {
        System.out.println("Testing setup()");
        //Test constructor, automatically calls setup
        DatabaseKeyStorageProvider instance = new DatabaseKeyStorageProvider(connectionURL, table, username, password);
        //instance.setup();
        instance.close();
    }

    /**
     * Test of close method, of class DatabaseKeyStorageProvider.
     * Just tests if a recently opened Connection can be closed.
     */
    @Test
    public void close() throws Exception {
        System.out.println("Testing close()");
        DatabaseKeyStorageProvider instance = new DatabaseKeyStorageProvider(connectionURL, table, username, password);
        instance.close();
    }
    
    private byte[] generateIV(int size)
    {
        //
        ////Try to read 128 bytes seed from /dev/urandom
        //
        FileInputStream urandomReader = null;
        byte[] iv = new byte[size];
        File urandom = new File("/dev/urandom");
        if (!urandom.exists())
            {
                //Non-POSIX system, use the standard Java SecureRandom generator for seeding
                new SecureRandom().nextBytes(iv);
                return iv;
            }
        try     
            {
                urandomReader = new FileInputStream(urandom);
                urandomReader.read(iv);
                urandomReader.close();
                return iv;
            }
        catch (FileNotFoundException ex) {} //Unreachable because of existence check before
        catch (IOException ex)
            {
                ex.printStackTrace();
            }
        finally
            {
                try{urandomReader.close();}
                catch (IOException ex)
                {
                    ex.printStackTrace();
                }
            }

        //Non-POSIX system, use the standard Java SecureRandom generator for seeding
        new SecureRandom().nextBytes(iv);
        return iv;
    }

    /**
     * Test of storeKeyPair method, of class DatabaseKeyStorageProvider.
     */
    @Test
    public void storeKeyPair() throws Exception {
        System.out.println("Testing storeKeyPair(...)");
        int keysize = 2048;
        //Get a key pair generator (for RSA)
        KeyPairGenerator kpGen = KeyPairGenerator.getInstance("RSA");
        //Gener
        FileInputStream r = new FileInputStream("/dev/urandom");
        byte[] seed = new byte[128];
        r.read(seed);
        SecureRandom random = new SecureRandom(seed);
        //Generate a key pair
        kpGen.initialize(keysize, random);
        expResult = kpGen.generateKeyPair();
        DatabaseKeyStorageProvider instance = new DatabaseKeyStorageProvider(connectionURL, table, username, password);
        instance.storeKeyPair(expResult, identifier);
    }

    /**
     * Test of getIdentifiers method, of class DatabaseKeyStorageProvider.
     */
    @Test
    public void getIdentifiers() throws Exception {
        System.out.println("Testing getIdentifiers()");
        DatabaseKeyStorageProvider instance = new DatabaseKeyStorageProvider(connectionURL, table, username, password);
        List<String> result = instance.getIdentifiers();
        assertEquals(result.get(0), identifier);
    }

    /**
     * Test of retrieveKeyPair method, of class DatabaseKeyStorageProvider.
     */
    @Test
    public void retrieveKeyPair() throws Exception {
        System.out.println("retrieveKeyPair");
        DatabaseKeyStorageProvider instance = new DatabaseKeyStorageProvider(connectionURL, table, username, password);
        KeyPair result = instance.retrieveKeyPair(identifier);
        assertNotNull(result); //Maybe too weak
    }

}