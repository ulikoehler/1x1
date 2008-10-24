/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jcrypter.rand;

import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * @author uli
 */
public class ResultsFrameOutputStream extends OutputStream
{

    public ResultsFrameOutputStream(ResultsFrame frame)
    {
        this.rf = frame;
    }

    @Override
    public void write(int c) throws IOException
    {
        rf.append(Character.toString((char) c));
        content = true;
    }

    @Override
    public void close()
    {
        if (content)
        {
            rf.setVisible(true);
        }
    }
    private ResultsFrame rf = null;
    private boolean content = false; //True if content has been written to the stream
}
