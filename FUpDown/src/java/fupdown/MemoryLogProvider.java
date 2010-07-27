/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fupdown;

/**
 *
 * @author uli
 */
public class MemoryLogProvider {
 private static StringBuilder logBuilder = new StringBuilder();

 public static void log(String msg)
 {
     logBuilder.append(msg);
     logBuilder.append('\n');
 }

 public static String getLog()
 {
     return logBuilder.toString();
 }

 public static void reset()
 {
     logBuilder.setLength(0);
 }
}
