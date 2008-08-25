
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.String;
import java.util.Iterator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.SortedMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ListModel;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.swing.event.ListDataListener;
import org.bouncycastle.openpgp.*;

/**
 *
 * @author uli
 */
public class PGPFileListModel implements ListModel
{
    private String file;
    private Map<String, PGPPublicKey> keys = new NavigableMap<String, PGPPublicKey>();
    
    public PGPFileListModel(String file)
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
                            while(ids.hasNext())
                                {
                                    keys.put((String)ids.next(), k);
                                }
                        }
                }
            }
        }
        catch (PGPException ex)
        {
            Logger.getLogger(PGPFileListModel.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        catch (IOException ex)
        {
            Logger.getLogger(PGPFileListModel.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(PGPFileListModel.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            }
        }
        
    }

    public int getSize()
    {
        return keys.size();
    }

    public Object getElementAt(int index)
    {
        Iterator it = keys.keySet().iterator();
        for(int i = 0; i < index; i++)
        {
            it.next();
        }
        return it.next();
    }

    public void addListDataListener(ListDataListener l) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void removeListDataListener(ListDataListener l) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
