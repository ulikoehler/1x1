/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jcrypter;

import java.security.KeyPair;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author uli
 */
public interface KeyStorageProvider {
    public void setup() throws SQLException;
    public void storeKeyPair(KeyPair keyPair, String identifier) throws Exception;
    public List<String> getIdentifiers() throws Exception;
    public KeyPair retrieveKeyPair(String identifier) throws Exception;
    public void close() throws SQLException;
}
