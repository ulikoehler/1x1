/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package filecat;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author uli
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        //Ask the user which files to open
        JFileChooser openFileChooser = new JFileChooser();
            openFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            openFileChooser.setMultiSelectionEnabled(true);
        openFileChooser.showOpenDialog(null);
        File[] inputFiles = openFileChooser.getSelectedFiles();

        //Ask the user where to save the files to
        JFileChooser saveFileChooser = new JFileChooser();
            saveFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            saveFileChooser.setMultiSelectionEnabled(false);
        saveFileChooser.showSaveDialog(null);
        File outputFile = saveFileChooser.getSelectedFile();
        
        try
        {
            OutputStream fout = new BufferedOutputStream(new FileOutputStream(outputFile));
            
            InputStream fin;
            int read;
            byte[] buffer = new byte[4096];
            
            for(File inputFile : inputFiles)
            {
                fin = new BufferedInputStream(new FileInputStream(inputFile));
              
                while(fin.available() > 0)
                {
                    //Read max. 4096 bytes and write them to the output stream
                    read = fin.read(buffer);
                    fout.write(buffer, 0, read);
                }
                fin.close();
            }
            fout.close();
            JOptionPane.showMessageDialog(null, "The operation has been completed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
        catch (IOException ex)
        {
            JOptionPane.showMessageDialog(null, "An IO error occured while copying the file data", "IO Error", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
