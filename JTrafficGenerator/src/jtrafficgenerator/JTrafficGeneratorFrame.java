/*
 * JTrafficGeneratorFrame.java
 *
 * Created on 9. August 2008, 17:39
 */

package jtrafficgenerator;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author  uli
 */
public class JTrafficGeneratorFrame extends javax.swing.JFrame {
    
    /** Creates new form JTrafficGeneratorFrame */
    public JTrafficGeneratorFrame() {
        initComponents();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        urlLabel = new javax.swing.JLabel();
        urlField = new javax.swing.JTextField();
        countLabel = new javax.swing.JLabel();
        threadsLabel = new javax.swing.JLabel();
        countSpinner = new javax.swing.JSpinner();
        threadSpinner = new javax.swing.JSpinner();
        okButton = new javax.swing.JButton();
        overallLabel = new javax.swing.JLabel();
        overallProgressBar = new javax.swing.JProgressBar();
        overallTrafficLabel = new javax.swing.JLabel();
        trafficLabel = new jtrafficgenerator.JScientificNumberLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        urlLabel.setText("URL:");

        countLabel.setText("Count:");

        threadsLabel.setText("Threads:");

        countSpinner.setModel(new SpinnerNumberModel(10,0,25000000,1));

        threadSpinner.setModel(new SpinnerNumberModel(1,1,50,1));

        okButton.setText("OK");
        okButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                okButtonMouseClicked(evt);
            }
        });

        overallLabel.setText("Overall:");

        overallTrafficLabel.setText("Traffic:");

        trafficLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        trafficLabel.setText("0.00M");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(okButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(urlLabel)
                            .addComponent(countLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(urlField, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(countSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(threadsLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(threadSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(overallLabel)
                        .addGap(27, 27, 27)
                        .addComponent(overallProgressBar, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(overallTrafficLabel)
                        .addGap(30, 30, 30)
                        .addComponent(trafficLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(urlLabel)
                    .addComponent(urlField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(countLabel)
                    .addComponent(threadsLabel)
                    .addComponent(countSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(threadSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(okButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(overallLabel)
                    .addComponent(overallProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(overallTrafficLabel)
                    .addComponent(trafficLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void okButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_okButtonMouseClicked
        try
        {
            int threads = ((SpinnerNumberModel) threadSpinner.getModel()).getNumber().intValue();
            downloadSlots = ((SpinnerNumberModel) countSpinner.getModel()).getNumber().intValue();
            URL url = new URL(urlField.getText());
            InputStream downloadStream = url.openStream();
        }
        catch (MalformedURLException ex)
        {
           JOptionPane.showMessageDialog(this, ex, "Malformed URL", JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_okButtonMouseClicked
    
    //Threading subclass
    class nullDownloadThread implements Runnable
    {
        InputStream downloadStream;
        
        //Configuration constants
        final int blockSize = 4096; //Bytes to download at once

        public nullDownloadThread(InputStream input) {
        }

        public void run() {
            byte[] buffer = new byte[blockSize];
            //BufferedReader reader = new BufferedReader(new InputStreamReader(downloadStream));
            int count;
            try
            {
                //All downloads
                while(getDownloadSlot())
                {
                //One download
                while(true)
                    {
                        count = downloadStream.read(buffer);
                        addToTrafficCounter(count);
                        if(count == -1){break;}
                    }
                }
            } catch(IOException ex){}
        }
        
    }
    
    private synchronized void addToTrafficCounter(int bytes)
    {
        byteTraffic += bytes;
        trafficLabel.setNumber(new Double(byteTraffic));
    }
    
    private synchronized boolean getDownloadSlot()
    {
       if(downloadSlots-- >= 0) {return true;} //If downloadSlots >= 0, decrement downloadSlots and return true
       return false;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JTrafficGeneratorFrame().setVisible(true);
            }
        });
    }

    //Threading variables
    long byteTraffic;
    int downloadSlots;
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel countLabel;
    private javax.swing.JSpinner countSpinner;
    private javax.swing.JButton okButton;
    private javax.swing.JLabel overallLabel;
    private javax.swing.JProgressBar overallProgressBar;
    private javax.swing.JLabel overallTrafficLabel;
    private javax.swing.JSpinner threadSpinner;
    private javax.swing.JLabel threadsLabel;
    private jtrafficgenerator.JScientificNumberLabel trafficLabel;
    private javax.swing.JTextField urlField;
    private javax.swing.JLabel urlLabel;
    // End of variables declaration//GEN-END:variables
    
}
