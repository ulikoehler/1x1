/*
 * JTimeBombClientFrame.java
 *
 * Created on 30. Juli 2008, 13:53
 */

package jtimebomb;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author  uli
 */
public class JTimeBombClientFrame extends javax.swing.JFrame {
    
    //Internationalization
    private final static ResourceBundle i18n = ResourceBundle.getBundle("jtimebomb/i18n");
    
    /** Creates new form JTimeBombClientFrame */
    public JTimeBombClientFrame() {
        initComponents();
        ipField.grabFocus();
    }
    
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ipLabel = new javax.swing.JLabel();
        ipField = new javax.swing.JTextField();
        connectButton = new javax.swing.JButton();
        hourLabel = new javax.swing.JLabel();
        hourSpinner = new javax.swing.JSpinner();
        minuteSpinner = new javax.swing.JSpinner();
        minutesLabel = new javax.swing.JLabel();
        secondsLabel = new javax.swing.JLabel();
        secondSpinner = new javax.swing.JSpinner();
        activateButton = new javax.swing.JButton();
        defuseButton = new javax.swing.JButton();
        disconnectButton = new javax.swing.JButton();
        setButton = new javax.swing.JButton();
        increaseButton = new javax.swing.JButton();
        decreaseButton = new javax.swing.JButton();
        detonateButton = new javax.swing.JButton();
        timeLeftLabel = new javax.swing.JLabel();
        updateButton = new javax.swing.JButton();

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("jtimebomb/i18n"); // NOI18N
        setTitle(bundle.getString("JTimeBomb_Client")); // NOI18N

        ipLabel.setText(bundle.getString("IP-Adress:")); // NOI18N

        ipField.setText("127.0.0.1");

        connectButton.setMnemonic('c');
        connectButton.setText(bundle.getString("Connect")); // NOI18N
        connectButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                connectButtonMouseClicked(evt);
            }
        });

        hourLabel.setDisplayedMnemonic('u');
        hourLabel.setText(bundle.getString("Hours:")); // NOI18N

        hourSpinner.setModel(new SpinnerNumberModel(0, 0, 99, 1));
        hourSpinner.setToolTipText(bundle.getString("Hours_in_the_delay_timer")); // NOI18N

        minuteSpinner.setModel(new SpinnerNumberModel(0, -1, 60, 1));
        minuteSpinner.setToolTipText(bundle.getString("Minutes_in_the_delay_timer")); // NOI18N

        minutesLabel.setDisplayedMnemonic('m');
        minutesLabel.setText(bundle.getString("Minutes:")); // NOI18N

        secondsLabel.setDisplayedMnemonic('s');
        secondsLabel.setText(bundle.getString("Seconds:")); // NOI18N

        secondSpinner.setModel(new SpinnerNumberModel(0, -1, 60, 1));
        secondSpinner.setToolTipText(bundle.getString("Seconds_in_the_delay_timer")); // NOI18N

        activateButton.setMnemonic('a');
        activateButton.setText(bundle.getString("Activate")); // NOI18N
        activateButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                activateButtonMouseClicked(evt);
            }
        });

        defuseButton.setMnemonic('f');
        defuseButton.setText(bundle.getString("Defuse")); // NOI18N
        defuseButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                defuseButtonMouseClicked(evt);
            }
        });

        disconnectButton.setMnemonic('d');
        disconnectButton.setText(bundle.getString("Disconnect")); // NOI18N
        disconnectButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                disconnectButtonMouseClicked(evt);
            }
        });

        setButton.setMnemonic('s');
        setButton.setText(bundle.getString("Set")); // NOI18N
        setButton.setMaximumSize(new java.awt.Dimension(50, 22));
        setButton.setMinimumSize(new java.awt.Dimension(50, 22));
        setButton.setPreferredSize(new java.awt.Dimension(50, 22));
        setButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                setButtonMouseClicked(evt);
            }
        });

        increaseButton.setMnemonic('i');
        increaseButton.setText(bundle.getString("Increase")); // NOI18N
        increaseButton.setMaximumSize(new java.awt.Dimension(50, 22));
        increaseButton.setMinimumSize(new java.awt.Dimension(50, 22));
        increaseButton.setPreferredSize(new java.awt.Dimension(50, 22));
        increaseButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                increaseButtonMouseClicked(evt);
            }
        });

        decreaseButton.setMnemonic('c');
        decreaseButton.setText(bundle.getString("Decrease")); // NOI18N
        decreaseButton.setMaximumSize(new java.awt.Dimension(50, 22));
        decreaseButton.setMinimumSize(new java.awt.Dimension(50, 22));
        decreaseButton.setPreferredSize(new java.awt.Dimension(50, 22));
        decreaseButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                decreaseButtonMouseClicked(evt);
            }
        });

        detonateButton.setMnemonic('t');
        detonateButton.setText(bundle.getString("Detonate")); // NOI18N
        detonateButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                detonateButtonMouseClicked(evt);
            }
        });

        timeLeftLabel.setBackground(new java.awt.Color(0, 0, 0));
        timeLeftLabel.setFont(new java.awt.Font("Gunplay", 0, 36));
        timeLeftLabel.setForeground(new java.awt.Color(255, 0, 51));
        timeLeftLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        timeLeftLabel.setText("00:00:00"); // NOI18N
        timeLeftLabel.setFocusable(false);

        updateButton.setText(i18n.getString("Update")); // NOI18N
        updateButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                updateButtonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(detonateButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(activateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(defuseButton, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ipLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ipField, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(connectButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(disconnectButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(decreaseButton, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
                                .addGap(6, 6, 6)
                                .addComponent(secondsLabel))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(increaseButton, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
                                .addGap(9, 9, 9)
                                .addComponent(minutesLabel))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(setButton, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(hourLabel)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(minuteSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(hourSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(secondSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(timeLeftLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(updateButton, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ipLabel)
                    .addComponent(connectButton)
                    .addComponent(disconnectButton)
                    .addComponent(ipField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(minutesLabel)
                            .addComponent(minuteSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(increaseButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(secondsLabel)
                            .addComponent(secondSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(decreaseButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(hourSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(hourLabel)
                        .addComponent(setButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(activateButton)
                    .addComponent(defuseButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(detonateButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(updateButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(timeLeftLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void connectButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_connectButtonMouseClicked
        try {
            String ipAdress = ipField.getText();
            socket = new Socket(ipAdress, 12121);
            in = socket.getInputStream();
            outWriter = new PrintWriter(socket.getOutputStream());
            socket.setSoTimeout(500);//GEN-LAST:event_connectButtonMouseClicked
        } catch (UnknownHostException ex) {
            Logger.getLogger(JTimeBombClientFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(JTimeBombClientFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void disconnectButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_disconnectButtonMouseClicked
        try {
                if(socket == null) {connectButtonMouseClicked(null);}
            socket.close();//GEN-LAST:event_disconnectButtonMouseClicked
            }
        catch (IOException ex) {
                              Logger.getLogger(JTimeBombClientFrame.class.getName()).log(Level.SEVERE, null, ex);
                             }
    }

    private void setButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_setButtonMouseClicked
            if(socket == null) {connectButtonMouseClicked(null);}
            SpinnerNumberModel model = (SpinnerNumberModel) hourSpinner.getModel();
                int hoursLeft = model.getNumber().intValue();
            model = (SpinnerNumberModel) minuteSpinner.getModel();
                int minutesLeft = model.getNumber().intValue();
            model = (SpinnerNumberModel) secondSpinner.getModel();
                int secondsLeft = model.getNumber().intValue(); //+1: Start countdown at exactly the selected time and not at one second after
            int totalSeconds = secondsLeft + minutesLeft * 60 + hoursLeft * 3600 + 1;
            //Set total seconds
            String command = "s" + Integer.toString(totalSeconds);
            outWriter.write(command);
            outWriter.flush();
    }//GEN-LAST:event_setButtonMouseClicked

    private void increaseButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_increaseButtonMouseClicked
            if(socket == null) {connectButtonMouseClicked(null);}
            SpinnerNumberModel model = (SpinnerNumberModel) hourSpinner.getModel();
                int hoursLeft = model.getNumber().intValue();
            model = (SpinnerNumberModel) minuteSpinner.getModel();
                int minutesLeft = model.getNumber().intValue();
            model = (SpinnerNumberModel) secondSpinner.getModel();
                int secondsLeft = model.getNumber().intValue(); //+1: Start countdown at exactly the selected time and not at one second after
            int totalSeconds = secondsLeft + minutesLeft * 60 + hoursLeft * 3600 + 1;
            //Set total seconds
            String command = "i" + Integer.toString(totalSeconds);
            outWriter.write(command);
            outWriter.flush();
    }//GEN-LAST:event_increaseButtonMouseClicked

    private void decreaseButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_decreaseButtonMouseClicked
            if(socket == null) {connectButtonMouseClicked(null);}
            SpinnerNumberModel model = (SpinnerNumberModel) hourSpinner.getModel();
                int hoursLeft = model.getNumber().intValue();
            model = (SpinnerNumberModel) minuteSpinner.getModel();
                int minutesLeft = model.getNumber().intValue();
            model = (SpinnerNumberModel) secondSpinner.getModel();
                int secondsLeft = model.getNumber().intValue(); //+1: Start countdown at exactly the selected time and not at one second after
            int totalSeconds = secondsLeft + minutesLeft * 60 + hoursLeft * 3600 + 1;
            //Set total seconds
            String command = "m" + Integer.toString(totalSeconds);
            outWriter.write(command);
            outWriter.flush();
    }//GEN-LAST:event_decreaseButtonMouseClicked

    private void activateButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_activateButtonMouseClicked
                if(socket == null) {connectButtonMouseClicked(null);}
                outWriter.write("a\n");
                outWriter.flush();
    }//GEN-LAST:event_activateButtonMouseClicked

    private void detonateButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_detonateButtonMouseClicked
                if(socket == null) {connectButtonMouseClicked(null);}
                outWriter.write("d\n");
                outWriter.flush();
}//GEN-LAST:event_detonateButtonMouseClicked

    private void updateButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateButtonMouseClicked
        outWriter.write("p\n");
        outWriter.flush();
    }//GEN-LAST:event_updateButtonMouseClicked

    private void defuseButtonMouseClicked(java.awt.event.MouseEvent evt) {                                          
                if(socket == null) {connectButtonMouseClicked(null);}
                outWriter.write("h\n");
                outWriter.flush();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JTimeBombClientFrame().setVisible(true);
            }
        });
    }
    
    private Socket socket;
    private InputStream in;
    private PrintWriter outWriter;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton activateButton;
    private javax.swing.JButton connectButton;
    private javax.swing.JButton decreaseButton;
    private javax.swing.JButton defuseButton;
    private javax.swing.JButton detonateButton;
    private javax.swing.JButton disconnectButton;
    private javax.swing.JLabel hourLabel;
    private javax.swing.JSpinner hourSpinner;
    private javax.swing.JButton increaseButton;
    private javax.swing.JTextField ipField;
    private javax.swing.JLabel ipLabel;
    private javax.swing.JSpinner minuteSpinner;
    private javax.swing.JLabel minutesLabel;
    private javax.swing.JSpinner secondSpinner;
    private javax.swing.JLabel secondsLabel;
    private javax.swing.JButton setButton;
    private javax.swing.JLabel timeLeftLabel;
    private javax.swing.JButton updateButton;
    // End of variables declaration//GEN-END:variables
    
}
