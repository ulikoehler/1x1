/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jcrypter;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.Key;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author uli
 */
public class DatabaseKeyStorageProvider implements KeyStorageProvider
{
    //JDBC properties: Examples for Apache Derby ('Java DB') localhost connection
    private String connectionUrl;//Example: "jdbc:derby://localhost:1527/PKIKeys" (concatenated with database
    private String table; // Example: "Keys";
    private String username; // Example: "pki";
    private String password; // Example: "";
    //Database should be contained in the connection URL
    //JDBC holders
    private Connection connection;
    //Prepared statements
    private boolean prepared = false; //true if the prepared statements have already been prepared
    PreparedStatement insertKeyStatement;
    PreparedStatement getIdentifiersStatement;
    PreparedStatement retrieveKeysStatement;
    
    public DatabaseKeyStorageProvider(String connectionUrl, String table, String username, String password) throws SQLException
    {
        //Set fields
        this.connectionUrl = connectionUrl;
        this.table = table;
        this.username = username;
        this.password = password;
        //Connect
        connection = DriverManager.getConnection(connectionUrl, username, password);
    }
    
    public void close() throws SQLException
    {
        connection.close();
    }

    public void setup() throws SQLException
    {
        Statement stmt = connection.createStatement();
        DatabaseMetaData md = connection.getMetaData();
        //Create the table
        stmt.executeUpdate("CREATE TABLE " + table +
                           "(Identifier VARCHAR(200)," +
                           "PublicKey BLOB DEFAULT NULL," +
                           "PrivateKey BLOB DEFAULT NULL)");
    }
    
    private void prepare() throws SQLException
    {
        //Prepare statements
        insertKeyStatement = connection.prepareStatement("INSERT INTO "+table+" VALUES (?,?,?)");
        getIdentifiersStatement = connection.prepareStatement("SELECT Identifier FROM "+table);
        retrieveKeysStatement = connection.prepareStatement("SELECT PublicKey, PrivateKey FROM "+table+" WHERE Identifier LIKE ?");
        //Number of parameters:
         //insertKeyStatement: 3: STRING BLOB BLOB
         //getIdentifiersStatement: 0
         //retrieveKeysStatement: 1: STRING
        prepared = true;
    }


    public void storeKeyPair(KeyPair keyPair, String identifier) throws SQLException
    {
        if(!prepared) {prepare();}
        ObjectOutputStream oout = null;
        Blob blob = null;
        try
        {
            insertKeyStatement.setString(1, identifier);
            //Set public key
            Key key = keyPair.getPublic();
            if(key != null)
                {
                    blob = connection.createBlob();
                    oout = new ObjectOutputStream(blob.setBinaryStream(1));
                    oout.writeObject(key);
                    oout.close();
                    insertKeyStatement.setBlob(2, blob);
                }
            else //Public key is null <-- Not possible
                {
                    throw new NullPointerException("Public key must not be NULL");
                }
            //Set private key
            key = keyPair.getPrivate();
            if(key != null)
                {
                    blob = connection.createBlob();
                    oout = new ObjectOutputStream(blob.setBinaryStream(1));
                    oout.writeObject(key);
                    oout.close();
                    insertKeyStatement.setBlob(3, blob);
                }
            else //Private key is null.
                {
                    insertKeyStatement.setNull(3, Types.BLOB);
                }
            insertKeyStatement.execute();
        }
        catch (IOException ex) 
            {
                Logger.getLogger(DatabaseKeyStorageProvider.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            }
        finally
            {
                try
                    {
                        if(oout != null) {oout.close();}
                    }
                catch (IOException ex)
                    {
                        Logger.getLogger(DatabaseKeyStorageProvider.class.getName()).log(Level.SEVERE, null, ex);
                        ex.printStackTrace();
                    }
            }
    }

    public List<String> getIdentifiers() throws SQLException
    {
        if(!prepared) {prepare();}
        ResultSet idResultSet = getIdentifiersStatement.executeQuery();
        LinkedList<String> idList = new LinkedList<String>();
        while(idResultSet.next())
        {
            idList.add(idResultSet.getString(1));
        }
        return idList;
    }

    public KeyPair retrieveKeyPair(String identifier) throws SQLException
    {        
        if(!prepared) {prepare();}
        ObjectInputStream oin = null;
        try
            {
                retrieveKeysStatement.setString(1, identifier);
                ResultSet keysResultSet = retrieveKeysStatement.executeQuery();
                keysResultSet.next();
                //Retrieve the public key
                oin = new ObjectInputStream(keysResultSet.getBlob(1).getBinaryStream());
                PublicKey publicKey = (PublicKey) oin.readObject();
                oin.close();
                //Retrieve the private key
                oin = new ObjectInputStream(keysResultSet.getBlob(2).getBinaryStream());
                PrivateKey privateKey = (PrivateKey) oin.readObject();
                return new KeyPair(publicKey, privateKey);
            }
        catch (IOException ex)
            {
                Logger.getLogger(DatabaseKeyStorageProvider.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            }
        catch (ClassNotFoundException ex)
            {
                Logger.getLogger(DatabaseKeyStorageProvider.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            }
        finally
            {
                try
                    {
                        oin.close();
                    }
                catch (IOException ex)
                    {
                        Logger.getLogger(DatabaseKeyStorageProvider.class.getName()).log(Level.SEVERE, null, ex);
                        ex.printStackTrace();
                    }
            }
        return null;
    }

   
}