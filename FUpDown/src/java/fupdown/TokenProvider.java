/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fupdown;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author uli
 */
public class TokenProvider
{
    private static Map<String, File> tokens = new HashMap<String, File>();

    public static void addToken(String token, File file)
    {
        tokens.put(token, file);
    }
    
    public static File getFile(String token)
    {
        if(!tokens.containsKey(token))
        {
            return null;
        }
        File f = tokens.get(token);
        tokens.remove(token);
        return f;
    }

    public static void removeAll()
    {
        tokens.clear();
    }

    public static void remove(String token)
    {
        tokens.remove(token);
    }
}
