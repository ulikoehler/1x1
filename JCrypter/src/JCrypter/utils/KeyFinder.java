/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package JCrypter.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 *
 * @author uli
 */
public class KeyFinder {
    
    Map<String, RSAPublicKey> pubkeys = new HashMap<String, RSAPublicKey>();
    Map<String, RSAPrivateKey> privkeys = new HashMap<String, RSAPrivateKey>();
    
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
            File[] ecp = thisDir.listFiles(new FilenameFilter() {
                        @Override
                        public boolean accept(File arg0, String arg1) {
                            if(arg1.endsWith(pubSuffix)) {return true;}
                            return false;
                        }
                    });
            for(File f : ecp)
            {
                FileInputStream in = new FileInputStream(f);
                byte[] keyBytes = new byte[in.available()];
                in.read(keyBytes);
                in.close();

                RSAPublicKey pubKey = (RSAPublicKey) fact.generatePublic(new X509EncodedKeySpec(keyBytes));
                pubkeys.put(f.getName(), pubKey);
                
                keys.add(f.getName());
            }
            //Load private keys
            File[] ecs = thisDir.listFiles(new FilenameFilter() {
                        @Override
                        public boolean accept(File arg0, String arg1) {
                            if(arg1.endsWith(privSuffix)) {return true;}
                            return false;
                        }
                    });
            for(File f : ecs)
            {
                FileInputStream in = new FileInputStream(f);
                byte[] keyBytes = new byte[in.available()];
                in.read(keyBytes);
                in.close();
                
                RSAPrivateKey privKey = (RSAPrivateKey) fact.generatePrivate(new PKCS8EncodedKeySpec(keyBytes));
                privkeys.put(f.getName(), privKey);
                
                keys.add(f.getName());
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
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
    
    public RSAPublicKey getPublicKey(String name)
    {
        return pubkeys.get(name);
    }
    
    public RSAPrivateKey getPrivateKey(String name)
    {
        return privkeys.get(name);
    }

}
