/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jcrypter.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;

/**
 *
 * @author uli
 */
public class KeyFinder
{

    Map<String, PublicKey> pubkeys = new HashMap<String, PublicKey>();
    Map<String, PrivateKey> privkeys = new HashMap<String, PrivateKey>();
    Vector<String> keys = new Vector<String>();

    public void findKeys(String directory, final String pubSuffix, final String privSuffix, String algorithm)
    {
        //////////////
        //Load public and secret key files from this directory and append each filename to the combobox
        /////////////

        //Load public keys
        try
        {
            KeyFactory fact = KeyFactory.getInstance(algorithm, "BC");
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
                FileInputStream in = new FileInputStream(f);
                byte[] keyBytes = new byte[in.available()];
                in.read(keyBytes);
                in.close();
                
                try
                {
                    PublicKey pubKey = fact.generatePublic(new X509EncodedKeySpec(keyBytes));
                    pubkeys.put(f.getName(), pubKey);

                    keys.add(f.getName());
                }
                catch (InvalidKeySpecException ex) //Non-valid key in file
                {
                    continue;
                }
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
                
                try
                {
                    PrivateKey privKey = fact.generatePrivate(new PKCS8EncodedKeySpec(keyBytes));
                    privkeys.put(f.getName(), privKey);

                    keys.add(f.getName());
                }
                catch (InvalidKeySpecException ex) //Non-valid key in file
                {
                    continue;
                }
            }
        }
        catch (Exception ex)
        {
           Logger.getLogger(KeyFinder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public KeyFinder(final String pubSuffix, final String privSuffix, String algorithm)
    {
        findKeys(".", pubSuffix, privSuffix, algorithm);
    }

    public KeyFinder(String directory, final String pubSuffix, final String privSuffix, String algorithm)
    {
        findKeys(directory, pubSuffix, privSuffix, algorithm);
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
}
