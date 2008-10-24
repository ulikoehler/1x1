package jcrypter.rand;

import jcrypter.utils.*;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.SecureRandom;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;

/*
 * RandomFileCreatorFrame.java
 *
 * Created on 19. Oktober 2008, 16:29
 */
import javax.swing.JOptionPane;
import javax.swing.SpinnerNumberModel;
import jcrypter.JCrypterFrame;

/**
 *
 * @author  uli
 */
public class RandomFileCreatorFrame extends javax.swing.JFrame
{

    /** Creates new form RandomFileCreatorFrame */
    public RandomFileCreatorFrame()
    {
        initComponents();
        //Test if /dev/urandom is available
        File urandom = new File("/dev/urandom");
        if (!urandom.exists())
        {
            urandomRadioButton.setEnabled(false);
            mt19937RadioButton.setSelected(true);
        }
    }

    private void genRandFileMT(int size, File file)
    {
        FileOutputStream fout = null;
        try
        {
            //Init the required objects
            fout = new FileOutputStream(file);
            MersenneTwisterFast mt =
                    new MersenneTwisterFast(Utils.MTSeed(JCrypterFrame.rand));
            //Main random data write loop
            final int blocksize = 1024;
            for (int i = 0; i < size; i += blocksize)
            {
                byte[] bytes;
                if (i + blocksize <= size)
                {
                    bytes = new byte[blocksize];
                }
                else
                {
                    bytes = new byte[size - i];
                }

                mt.nextBytes(bytes);
                fout.write(bytes);
            }
            displaySucessMessage();
        }
        catch (FileNotFoundException ex)
        {
            Logger.getLogger(RandomFileCreatorFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IOException ex)
        {
            JOptionPane.showMessageDialog(this, ex, "IO Error", JOptionPane.ERROR_MESSAGE);
        }
        finally
        {
            try
            {
                fout.close();
            }
            catch (IOException ex)
            {
                JOptionPane.showMessageDialog(this, ex, "IO Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void genRandFileSec(int size, File file, SecureRandom rand)
    {
        OutputStream fout = null;
        try
        {
            //Init the required objects
            fout = new BufferedOutputStream(new FileOutputStream(file));
            //Main random data write loop
            final int blocksize = 1024;
            for (int i = 0; i < size; i += blocksize)
            {
                byte[] bytes;
                if (i + blocksize <= size)
                {
                    bytes = new byte[blocksize];
                }
                else
                {
                    bytes = new byte[size - i];
                }

                rand.nextBytes(bytes);
                fout.write(bytes);
            }
            displaySucessMessage();
        }
        catch (FileNotFoundException ex)
        {
            Logger.getLogger(RandomFileCreatorFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IOException ex)
        {
            JOptionPane.showMessageDialog(this, ex, "IO Error", JOptionPane.ERROR_MESSAGE);
        }
        finally
        {
            try
            {
                fout.close();
            }
            catch (IOException ex)
            {
                JOptionPane.showMessageDialog(this, ex, "IO Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void genRandFileUrandom(int size, File file)
    {
        OutputStream fout = null;
        InputStream fin = null;
        try
        {
            //Init the required objects
            fout = new BufferedOutputStream(new FileOutputStream(file));
            MersenneTwisterFast mt =
                    new MersenneTwisterFast(Utils.MTSeed(JCrypterFrame.rand));
            fin = new FileInputStream("/dev/urandom");
            //Main random data write loop
            final int blocksize = 4096;
            for (int i = 0; i < size; i += blocksize)
            {
                byte[] bytes;
                if (i + blocksize <= size)
                {
                    bytes = new byte[blocksize];
                }
                else
                {
                    bytes = new byte[size - i];
                }

                fin.read(bytes);
                fout.write(bytes);
            }
            displaySucessMessage();
        }
        catch (FileNotFoundException ex)
        {
            Logger.getLogger(RandomFileCreatorFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IOException ex)
        {
            JOptionPane.showMessageDialog(this, ex, "IO Error", JOptionPane.ERROR_MESSAGE);
        }
        finally
        {
            try
            {
                fout.close();
                fin.close();
            }
            catch (IOException ex)
            {
                JOptionPane.showMessageDialog(this, ex, "IO Error", JOptionPane.ERROR_MESSAGE);
            }
            catch(NullPointerException ex)
            {
                
            }
        }
    }

    private void displaySucessMessage()
    {
        //Show a success message
        JOptionPane.showMessageDialog(this, "The entropy file has been created successfully!", "File created", JOptionPane.INFORMATION_MESSAGE);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sizeButtonGroup = new javax.swing.ButtonGroup();
        algorithmButtonGroup = new javax.swing.ButtonGroup();
        okButton = new javax.swing.JButton();
        sizePanel = new javax.swing.JPanel();
        sizeSpinner = new javax.swing.JSpinner();
        byteRadioButton = new javax.swing.JRadioButton();
        kbRadioButton = new javax.swing.JRadioButton();
        mbRadioButton = new javax.swing.JRadioButton();
        gbRadioButton = new javax.swing.JRadioButton();
        algorithmPanel = new javax.swing.JPanel();
        secureRandomRadioButton = new javax.swing.JRadioButton();
        mt19937RadioButton = new javax.swing.JRadioButton();
        urandomRadioButton = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(i18n.getString("RandomFileCreatorFrame.title")); // NOI18N

        okButton.setText(i18n.getString("RandomFileCreatorFrame.okButton.text")); // NOI18N
        okButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                okButtonMouseClicked(evt);
            }
        });

        sizePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(i18n.getString("RandomFileCreatorFrame.sizePanel.border.title"))); // NOI18N

        sizeSpinner.setModel(new SpinnerNumberModel(1,1,1000000,1));

        sizeButtonGroup.add(byteRadioButton);
        byteRadioButton.setText(i18n.getString("RandomFileCreatorFrame.byteRadioButton.text")); // NOI18N

        sizeButtonGroup.add(kbRadioButton);
        kbRadioButton.setText(i18n.getString("RandomFileCreatorFrame.kbRadioButton.text")); // NOI18N

        sizeButtonGroup.add(mbRadioButton);
        mbRadioButton.setSelected(true);
        mbRadioButton.setText(i18n.getString("RandomFileCreatorFrame.mbRadioButton.text")); // NOI18N

        sizeButtonGroup.add(gbRadioButton);
        gbRadioButton.setText(i18n.getString("RandomFileCreatorFrame.gbRadioButton.text")); // NOI18N

        javax.swing.GroupLayout sizePanelLayout = new javax.swing.GroupLayout(sizePanel);
        sizePanel.setLayout(sizePanelLayout);
        sizePanelLayout.setHorizontalGroup(
            sizePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sizePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sizeSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(byteRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(kbRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(mbRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(gbRadioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );
        sizePanelLayout.setVerticalGroup(
            sizePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sizePanelLayout.createSequentialGroup()
                .addGroup(sizePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(byteRadioButton)
                    .addComponent(kbRadioButton)
                    .addComponent(mbRadioButton)
                    .addComponent(gbRadioButton)
                    .addComponent(sizeSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        algorithmPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(i18n.getString("RandomFileCreatorFrame.algorithmPanel.border.title"))); // NOI18N

        algorithmButtonGroup.add(secureRandomRadioButton);
        secureRandomRadioButton.setText(i18n.getString("RandomFileCreatorFrame.secureRandomRadioButton.text")); // NOI18N

        algorithmButtonGroup.add(mt19937RadioButton);
        mt19937RadioButton.setText(i18n.getString("RandomFileCreatorFrame.mt19937RadioButton.text")); // NOI18N
        mt19937RadioButton.setToolTipText(i18n.getString("RandomFileCreatorFrame.mt19937RadioButton.toolTipText")); // NOI18N

        algorithmButtonGroup.add(urandomRadioButton);
        urandomRadioButton.setSelected(true);
        urandomRadioButton.setText("/dev/urandom"); // NOI18N

        javax.swing.GroupLayout algorithmPanelLayout = new javax.swing.GroupLayout(algorithmPanel);
        algorithmPanel.setLayout(algorithmPanelLayout);
        algorithmPanelLayout.setHorizontalGroup(
            algorithmPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(algorithmPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(secureRandomRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mt19937RadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(urandomRadioButton)
                .addContainerGap(16, Short.MAX_VALUE))
        );
        algorithmPanelLayout.setVerticalGroup(
            algorithmPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(algorithmPanelLayout.createSequentialGroup()
                .addGroup(algorithmPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(secureRandomRadioButton)
                    .addComponent(mt19937RadioButton)
                    .addComponent(urandomRadioButton))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sizePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(algorithmPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(okButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sizePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(algorithmPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(okButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void okButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_okButtonMouseClicked
    //Determinate the file size
    SpinnerNumberModel sizeModel = (SpinnerNumberModel) sizeSpinner.getModel();
    size = sizeModel.getNumber().intValue();

    if (kbRadioButton.isSelected())
    {
        size *= 1024;
    }
    if (mbRadioButton.isSelected())
    {
        size *= 1024 * 1024;
    }
    if (gbRadioButton.isSelected())
    {
        size *= 1024 * 1024 * 1024;
    }
    //Ask the user for a file to fill with random data
    fileChooser.showSaveDialog(this);
    file = fileChooser.getSelectedFile();
    //Start a new thread tom fill the file with random data
    if (mt19937RadioButton.isSelected())
    {
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                genRandFileMT(size, file);
            }
        });

    }
    else if (secureRandomRadioButton.isSelected())
    {
        new Thread(new Runnable()
        {

            @Override
            public void run()
            {
                genRandFileMT(size, file);
            }
        });

        genRandFileSec(size, file, JCrypterFrame.rand);
    }
    else if (urandomRadioButton.isSelected())
    {
        new Thread(new Runnable()
        {

            @Override
            public void run()
            {
                genRandFileMT(size, file);
            }
        });

        genRandFileSec(size, file, JCrypterFrame.rand);
    }
}//GEN-LAST:event_okButtonMouseClicked
    JFileChooser fileChooser = JCrypterFrame.mainFrame.fileChooser;
    private ResourceBundle i18n =
            ResourceBundle.getBundle("jcrypter/rand/Bundle");
    int size;
    File file;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup algorithmButtonGroup;
    private javax.swing.JPanel algorithmPanel;
    private javax.swing.JRadioButton byteRadioButton;
    private javax.swing.JRadioButton gbRadioButton;
    private javax.swing.JRadioButton kbRadioButton;
    private javax.swing.JRadioButton mbRadioButton;
    private javax.swing.JRadioButton mt19937RadioButton;
    private javax.swing.JButton okButton;
    private javax.swing.JRadioButton secureRandomRadioButton;
    private javax.swing.ButtonGroup sizeButtonGroup;
    private javax.swing.JPanel sizePanel;
    private javax.swing.JSpinner sizeSpinner;
    private javax.swing.JRadioButton urandomRadioButton;
    // End of variables declaration//GEN-END:variables
}
