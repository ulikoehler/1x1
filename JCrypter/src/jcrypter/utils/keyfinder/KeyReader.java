/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jcrypter.utils.keyfinder;

import java.io.File;
import java.util.List;

/**
 *
 * @author uli
 */
public interface KeyReader {
    public void readPublicKey(File file, KeyFinder instance);
    public void readPrivateKey(File file, KeyFinder instance);
    public List<String> getUnreadableFiles();
}
