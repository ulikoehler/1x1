package jcrypter.utils.keyfinder;


import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import org.bouncycastle.openpgp.*;

/**
 *
 * @author uli
 */
public class PGPKeyReader implements KeyReader
{

    public PGPKeyReader()
    {
        File thisDir = new File(".");
        File[] asc = thisDir.listFiles(new FilenameFilter() {
                    @Override
                    public boolean accept(File arg0, String arg1) {
                        if(arg1.endsWith(".asc")) {return true;}
                        return false;
                    }
                });
        for(File f : asc)
        {
            readFilePublic(f);
        }
        File[] sec = thisDir.listFiles(new FilenameFilter() {
                    @Override
                    public boolean accept(File arg0, String arg1) {
                        if(arg1.endsWith(".sec")) {return true;}
                        return false;
                    }
                });
        for(File f : sec)
        {
            readFileSecret(f);
        }
    }
    
    public void readFileSecret(File file)
    {
        
    }
    
    private void readFilePublic(File file)
    {
        InputStream in = null;
        try
        {
            in = PGPUtil.getDecoderStream(new FileInputStream(file));

            PGPPublicKeyRingCollection pgpPub = new PGPPublicKeyRingCollection(in);

            Iterator rIt = pgpPub.getKeyRings();

            while (rIt.hasNext())
            {
                PGPPublicKeyRing kRing = (PGPPublicKeyRing) rIt.next();
                Iterator kIt = kRing.getPublicKeys();

                while (kIt.hasNext())
                {
                    PGPPublicKey k = (PGPPublicKey) kIt.next();

                    if(k.isEncryptionKey())
                        {
                            Iterator ids = k.getUserIDs();
                            String idString = (String)ids.next();
                            //Put into containers associating by User ID
                            pubKeys.put(idString, k);
                            getPubKeyNames().add(idString);
                            //Put into container associating by key ID
                            pubKeysID.put(k.getKeyID(), k);
                        }
                }
            }
        }
        catch (PGPException ex)
        {
            ex.printStackTrace();
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            try
            {
                in.close();
            }
            catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }
    }

    /**
     * @return the pubKeys
     */
    public HashMap<String, PGPPublicKey> getPubKeys() {
        return pubKeys;
    }

    /**
     * @return the secKeys
     */
    public HashMap<String, PGPSecretKey> getSecKeys() {
        return secKeys;
    }

    /**
     * @return the pubKeyNames
     */
    public Vector<String> getPubKeyNames() {
        return pubKeyNames;
    }

    /**
     * @return the secKeyNames
     */
    public Vector<String> getSecKeyNames() {
        return secKeyNames;
    }

    /**
     * @return the secKeysID
     */
    public HashMap<Long, PGPSecretKey> getSecKeysID() {
        return secKeysID;
    }

    /**
     * @return the pubKeysID
     */
    public HashMap<Long, PGPPublicKey> getPubKeysID() {
        return pubKeysID;
    }

    @Override
    public void readPublicKey(File file, KeyFinder instance)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void readPrivateKey(File file, KeyFinder instance)
    {
        InputStream in = null;
        try
        {
            in = PGPUtil.getDecoderStream(new FileInputStream(file));

            PGPSecretKeyRingCollection pgpSec = new PGPSecretKeyRingCollection(in);

            Iterator rIt = pgpSec.getKeyRings();

            while (rIt.hasNext())
            {
                PGPSecretKeyRing kRing = (PGPSecretKeyRing) rIt.next();
                Iterator kIt = kRing.getSecretKeys();

                while (kIt.hasNext())
                {
                    PGPSecretKey k = (PGPSecretKey) kIt.next();
                    Iterator ids = k.getUserIDs();
                    String idString = (String)ids.next();
                    //Add to the key finder map
                    instance.addPrivateKey(idString, k);
                }
            }
        }
        catch (PGPException ex)
        {
            ex.printStackTrace();
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            try
            {
                in.close();
            }
            catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public List<String> getUnreadableFiles()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
