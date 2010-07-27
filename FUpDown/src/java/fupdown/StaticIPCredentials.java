/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fupdown;

/**
 *
 * @author uli
 */
public class StaticIPCredentials
{

    public static boolean isAllowed(String ip)
    {
        if (ip.equals("0:0:0:0:0:0:0:1")) //IPv6
        {
            return true;
        }
        if (ip.equals("127.0.0.1")) //IPv4
        {
            return true;
        }
        return false;
    }
}
