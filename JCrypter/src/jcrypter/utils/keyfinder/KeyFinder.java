/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jcrypter.utils.keyfinder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;

/**
 * A class which searches directories for keys.
 * The model related to this class uses object composition instead of inheritation (-> final attribute)!
 * @author uli
 */
public final class KeyFinder
{

    Map<String, PublicKey> pubkeys = null;
    Map<String, PrivateKey> privkeys = null;
    Vector<String> keys = new Vector<String>();
    
    private KeyReader kr;

    /**
     * Loads public and secret key files from this directory and append each filename to the combobox
     * @param directory The directory to search in (not recursive)
     * @param pubSuffix The suffix of the public key files
     * @param privSuffix The suffix of the private key files
     */
    public void findKeys(String directory, final String pubSuffix, final String privSuffix)
    {
        //(Re)initialize the maps
        pubkeys = new HashMap<String, PublicKey>();
        privkeys = new HashMap<String, PrivateKey>();
        //Load public keys
        try
        {
            File thisDir = new File(directory);
            File[] ecp = thisDir.listFiles(new FilenameFilter()
            {

                @Override
                public boolean accept(File arg0, String arg1)
                {
                    if (arg1.endsWith(pubSuffix))
                    {
                        return true;
                    }
                    return false;
                }
            });
            for (File f : ecp)
            {
                             
                
            }
            //Load private keys
            File[] ecs = thisDir.listFiles(new FilenameFilter()
            {
                @Override
                public boolean accept(File arg0, String arg1)
                {
                    if (arg1.endsWith(privSuffix))
                    {
                        return true;
                    }
                    return false;
                }
            });
            for (File f : ecs)
            {
                FileInputStream in = new FileInputStream(f);
                byte[] keyBytes = new byte[in.available()];
                in.read(keyBytes);
                in.close();
                
                getKr().readPrivateKey(f, this);
            }
        }
        catch (Exception ex)
        {
           Logger.getLogger(KeyFinder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public KeyFinder(final String pubSuffix, final String privSuffix, KeyReader kr)
    {
        this.kr = kr;
        findKeys(".", pubSuffix, privSuffix);
    }
    
    public KeyFinder(String directory, final String pubSuffix, final String privSuffix, KeyReader kr)
    {
        this.kr = kr;
        findKeys(directory, pubSuffix, privSuffix);
    }

    public KeyFinder(String directory, final String pubSuffix, final String privSuffix, String algorithm)
    {
        this.kr = new DefaultKeyReader(algorithm);
        findKeys(directory, pubSuffix, privSuffix);
    }
    
    public KeyFinder(final String pubSuffix, final String privSuffix, String algorithm)
    {
        this.kr = new DefaultKeyReader(algorithm);
        findKeys(".", pubSuffix, privSuffix);
    }

    public Vector<String> getNames()
    {
        return keys;
    }

    public PublicKey getPublicKey(String name)
    {
        return pubkeys.get(name);
    }

    public PrivateKey getPrivateKey(String name)
    {
        return privkeys.get(name);
    }
    
    public void fillComboBox(JComboBox cb)
    {
        for(String s : this.getNames())
        {
            cb.addItem(s);
        }
    }
    
    public void addPublicKey(String name, PublicKey key)
    {
        pubkeys.put(name, key);
        keys.add(name);
    }
    
    public void addPrivateKey(String name, PrivateKey key)
    {
        privkeys.put(name, key);
        keys.add(name);
    }

    public KeyReader getKr()
    {
        return kr;
    }
}
