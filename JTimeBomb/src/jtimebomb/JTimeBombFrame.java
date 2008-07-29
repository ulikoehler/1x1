package jtimebomb;

/*
 * JTImeBombFrame.java
 *
 * Created on 28. Juli 2008, 12:22
 */
import java.awt.Color;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.SpinnerNumberModel;



/**
 *
 * @author  uli
 */
public class JTimeBombFrame extends javax.swing.JFrame {
    
    //Custom components
    int totalSecondsLeft; //In seconds, overall
    int hoursLeft;
    int minutesLeft; //Not overall
    int secondsLeft; //Not overall
    Timer timer;
    final int delay = 1000;
    
    /** Creates new form JTImeBombFrame */
    public JTimeBombFrame() {
        initComponents();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        timeLeftLabel = new javax.swing.JLabel();
        statusLabel = new javax.swing.JLabel();
        hourSpinner = new javax.swing.JSpinner();
        minuteSpinner = new javax.swing.JSpinner();
        secondSpinner = new javax.swing.JSpinner();
        hourLabel = new javax.swing.JLabel();
        minutesLabel = new javax.swing.JLabel();
        secondsLabel = new javax.swing.JLabel();
        statusBar = new javax.swing.JProgressBar();
        activateToggleButton = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Time Bomb");
        setAlwaysOnTop(true);
        setBackground(new java.awt.Color(0, 0, 0));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setFocusableWindowState(false);
        setForeground(java.awt.Color.black);

        timeLeftLabel.setBackground(new java.awt.Color(0, 0, 0));
        timeLeftLabel.setFont(new java.awt.Font("Gunplay", 0, 36));
        timeLeftLabel.setForeground(new java.awt.Color(255, 0, 51));
        timeLeftLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        timeLeftLabel.setText("00:00:00");

        statusLabel.setFont(new java.awt.Font("Gunplay", 0, 18));
        statusLabel.setForeground(new java.awt.Color(0, 255, 0));
        statusLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        statusLabel.setText("Deactivated");

        hourSpinner.setModel(new SpinnerNumberModel(0, 0, 59, 1));

        minuteSpinner.setModel(new SpinnerNumberModel(0, 0, 59, 1));

        secondSpinner.setModel(new SpinnerNumberModel(0, 0, 59, 1));

        hourLabel.setText("Hours:");

        minutesLabel.setText("Minutes:");

        secondsLabel.setText("Seconds:");

        activateToggleButton.setText("Activate");
        activateToggleButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                activateToggleButtonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(timeLeftLabel)
                            .addComponent(statusBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(statusLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(secondsLabel)
                                    .addComponent(minutesLabel)
                                    .addComponent(hourLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(minuteSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(hourSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(secondSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE))))
                    .addComponent(activateToggleButton, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(statusLabel)
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(minutesLabel)
                            .addComponent(minuteSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(secondsLabel)
                            .addComponent(secondSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(timeLeftLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(hourSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(hourLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusBar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(activateToggleButton)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void activateToggleButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_activateToggleButtonMouseClicked
        // TODO add your handling code here:
        //Check whether to activate or to deactivate the bomb
        if(activateToggleButton.isSelected()) //Try to deactivate the bomb
            {
                //Get data
                SpinnerNumberModel model = (SpinnerNumberModel) hourSpinner.getModel();
                    hoursLeft = model.getNumber().intValue();
                model = (SpinnerNumberModel) minuteSpinner.getModel();
                    minutesLeft = model.getNumber().intValue();
                model = (SpinnerNumberModel) secondSpinner.getModel();
                    secondsLeft = model.getNumber().intValue();
                totalSecondsLeft = secondsLeft + minutesLeft * 60 + hoursLeft * 3600;
                //Initialize status bar
                 statusBar.setMaximum(totalSecondsLeft);
                 statusBar.setValue(totalSecondsLeft);
                //Initialize main (big) timer Label
                 String timerString = Integer.toString(hoursLeft) + ":" + Integer.toString(minutesLeft) + ":" + Integer.toString(secondsLeft);
                 timeLeftLabel.setText(timerString);
                //Set state label value and color
                statusLabel.setText("Activated");
                statusLabel.setForeground(new Color(255,0,0));
                //Activate timer
                timer = new Timer();
                timer.schedule(new TimerTask() {
                                                    public void run()
                                                        {
                                                            timerTick();
                                                        }
                                                }, 0, 1000);
            }
        else //Try to activate the bomb
            {
                timer.cancel();
                statusLabel.setText("Defused");
                statusLabel.setForeground(new Color(0,255,0));
            }
    }//GEN-LAST:event_activateToggleButtonMouseClicked
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JTimeBombFrame().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton activateToggleButton;
    private javax.swing.JLabel hourLabel;
    private javax.swing.JSpinner hourSpinner;
    private javax.swing.JSpinner minuteSpinner;
    private javax.swing.JLabel minutesLabel;
    private javax.swing.JSpinner secondSpinner;
    private javax.swing.JLabel secondsLabel;
    private javax.swing.JProgressBar statusBar;
    private javax.swing.JLabel statusLabel;
    private javax.swing.JLabel timeLeftLabel;
    // End of variables declaration//GEN-END:variables

    public void timerTick()
    {
        totalSecondsLeft--;
        //Calculate time left
        secondsLeft = totalSecondsLeft%60;
        hoursLeft = (int) Math.floor(totalSecondsLeft/3600);
        minutesLeft = ((totalSecondsLeft%3600)-secondsLeft)/60;
        statusBar.setValue(totalSecondsLeft);
        //Initialize main (big) timer Label
        String timerString = Integer.toString(hoursLeft) + ":" + Integer.toString(minutesLeft) + ":" + Integer.toString(secondsLeft);
        timeLeftLabel.setText(timerString);
        if(totalSecondsLeft==0) //If bomb has detonated
            {
                timer.cancel();
                statusLabel.setText("Detonated");
                activateToggleButton.setSelected(false);
            }
    }
    
}
