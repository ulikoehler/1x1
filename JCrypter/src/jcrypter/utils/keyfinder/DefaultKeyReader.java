/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jcrypter.utils.keyfinder;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Key finder read requests are delegated to this class per default
 * Public keys are read using X.509 and private keys using PKCS8
 * @author uli
 */
public class DefaultKeyReader implements KeyReader
{

    String algorithm;
    KeyFactory fact = null;
    List<String> unreadableFiles = new LinkedList<String>();

    public DefaultKeyReader(String algorithm)
    {
        try
        {
            this.algorithm = algorithm;
            fact = KeyFactory.getInstance(algorithm, "BC");
        }
        catch (NoSuchAlgorithmException ex)
        {
            Logger.getLogger(DefaultKeyReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (NoSuchProviderException ex)
        {
            Logger.getLogger(DefaultKeyReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void readPublicKey(File f, KeyFinder instance)
    {
        {
            FileInputStream in = null;
            try
            {
                in = new FileInputStream(f);
                byte[] keyBytes = new byte[in.available()];
                in.read(keyBytes);
                in.close();
                PublicKey pubKey =
                        fact.generatePublic(new X509EncodedKeySpec(keyBytes));
                instance.addPublicKey(algorithm, pubKey);
            }
            catch (IOException ex)
            {
                Logger.getLogger(DefaultKeyReader.class.getName()).log(Level.SEVERE, null, ex);
            }
            catch (InvalidKeySpecException ex)
            {
            }
            finally
            {
                try
                {
                    in.close();
                }
                catch (IOException ex)
                {
                    Logger.getLogger(DefaultKeyReader.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public void readPrivateKey(File f, KeyFinder instance)
    {
        {
            FileInputStream in = null;
            try
            {
                in = new FileInputStream(f);
                byte[] keyBytes = new byte[in.available()];
                in.read(keyBytes);
                in.close();
                PrivateKey privKey =
                        fact.generatePrivate(new PKCS8EncodedKeySpec(keyBytes));
                instance.addPrivateKey(f.getName(), privKey);
            }
            catch (IOException ex)
            {
                Logger.getLogger(DefaultKeyReader.class.getName()).log(Level.SEVERE, null, ex);
            }
            catch (InvalidKeySpecException ex)
            {
                unreadableFiles.add(f.getName());
            }
            finally
            {
                try
                {
                    in.close();
                }
                catch (IOException ex)
                {
                    Logger.getLogger(DefaultKeyReader.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public List<String> getUnreadableFiles()
    {
        return unreadableFiles;
    }
}
