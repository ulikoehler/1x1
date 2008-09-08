package jcrypter;


import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;
import org.bouncycastle.openpgp.*;

/**
 *
 * @author uli
 */
public class PGPKeyRingReader
{
    private HashMap<String, PGPPublicKey> pubKeys = new HashMap<String, PGPPublicKey>();
    private HashMap<String, PGPSecretKey> secKeys = new HashMap<String, PGPSecretKey>();
    private HashMap<Long, PGPSecretKey> secKeysID = new HashMap<Long, PGPSecretKey>();
    private HashMap<Long, PGPPublicKey> pubKeysID = new HashMap<Long, PGPPublicKey>();
    private Vector<String> pubKeyNames = new Vector<String>();
    private Vector<String> secKeyNames = new Vector<String>();

    public PGPKeyRingReader()
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
                    secKeys.put(idString, k);
                    secKeyNames.add(idString);
                    //Put into container associating by key ID
                    secKeysID.put(k.getKeyID(), k);
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
}
