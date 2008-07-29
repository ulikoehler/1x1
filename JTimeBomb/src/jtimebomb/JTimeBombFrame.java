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
   
    /** Creates new form JTImeBombFrame */
    public JTimeBombFrame() {
        initComponents();
        minuteSpinner.grabFocus();
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
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("jtimebomb/Internationalization"); // NOI18N
        setTitle(bundle.getString("Time_Bomb")); // NOI18N
        setAlwaysOnTop(true);
        setBackground(new java.awt.Color(0, 0, 0));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setFocusableWindowState(false);
        setForeground(java.awt.Color.black);
        setResizable(false);

        timeLeftLabel.setBackground(new java.awt.Color(0, 0, 0));
        timeLeftLabel.setFont(new java.awt.Font("Gunplay", 0, 36));
        timeLeftLabel.setForeground(new java.awt.Color(255, 0, 51));
        timeLeftLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        timeLeftLabel.setText("00:00:00");
        timeLeftLabel.setFocusable(false);

        statusLabel.setFont(new java.awt.Font("Gunplay", 0, 18));
        statusLabel.setForeground(new java.awt.Color(0, 255, 0));
        statusLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        statusLabel.setText(bundle.getString("Deactivated")); // NOI18N

        hourSpinner.setModel(new SpinnerNumberModel(0, 0, 100, 1));
        hourSpinner.setNextFocusableComponent(minuteSpinner);

        minuteSpinner.setModel(new SpinnerNumberModel(0, -1, 60, 1));
        minuteSpinner.setNextFocusableComponent(secondSpinner);
        minuteSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                minuteSpinnerStateChanged(evt);
            }
        });

        secondSpinner.setModel(new SpinnerNumberModel(0, -1, 60, 1));
        secondSpinner.setNextFocusableComponent(hourSpinner);
        secondSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                secondSpinnerStateChanged(evt);
            }
        });

        hourLabel.setText(bundle.getString("Hours:")); // NOI18N

        minutesLabel.setText(bundle.getString("Minutes:")); // NOI18N

        secondsLabel.setText(bundle.getString("Seconds:")); // NOI18N

        statusBar.setForeground(new java.awt.Color(255, 0, 51));

        activateToggleButton.setText(bundle.getString("Activate")); // NOI18N
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
                            .addComponent(statusLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
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
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 3, Short.MAX_VALUE))))
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                statusLabel.setText(java.util.ResourceBundle.getBundle("jtimebomb/Internationalization").getString("Activated"));
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
                statusLabel.setText(java.util.ResourceBundle.getBundle("jtimebomb/Internationalization").getString("Defused"));
                statusLabel.setForeground(new Color(0,255,0));
            }
    }//GEN-LAST:event_activateToggleButtonMouseClicked

    private void secondSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_secondSpinnerStateChanged
        //If seconds are 60, set seconds to 0 and increase minutes
        SpinnerNumberModel secondModel = (SpinnerNumberModel) secondSpinner.getModel();
        SpinnerNumberModel minuteModel = (SpinnerNumberModel) minuteSpinner.getModel();
        SpinnerNumberModel hourModel = (SpinnerNumberModel) hourSpinner.getModel();
        if(secondModel.getNumber().intValue() >= 60)
            {
                secondModel.setValue(0);
                minuteModel.setValue(minuteModel.getNextValue());
                return; //Value cannot be greater than 60 and less than 0 at one time
            }
        //If seconds are less than 0 set seconds to 0 and increase minutes
        if(secondModel.getNumber().intValue() <= -1)
            {
                //Iv the overall time is 0 (no minute to split into seconds), set seconds to 0 and return
                if(minuteModel.getNumber().intValue() == 0 && hourModel.getNumber().intValue() == 0)
                    {
                        secondModel.setValue(0);
                        return;
                    }
                secondModel.setValue(59);
                minuteModel.setValue(minuteModel.getPreviousValue());
            }
    }//GEN-LAST:event_secondSpinnerStateChanged

    private void minuteSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_minuteSpinnerStateChanged
        //If seconds are 60, set seconds to 0 and increase minutes
        SpinnerNumberModel minuteModel = (SpinnerNumberModel) minuteSpinner.getModel();
        SpinnerNumberModel hourModel = (SpinnerNumberModel) hourSpinner.getModel();
        if(minuteModel.getNumber().intValue() >= 60)
            {
                minuteModel.setValue(0);
                hourModel.setValue(hourModel.getNextValue());
                return; //Value cannot be greater than 60 and less than 0 at one time
            }
        //If minutes are -1, set minutes to 0 and increase hours
        if(minuteModel.getNumber().intValue() <= -1)
            {
                minuteModel.setValue(59);
                hourModel.setValue(hourModel.getPreviousValue());
            }
    }//GEN-LAST:event_minuteSpinnerStateChanged
    
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
                statusLabel.setText(java.util.ResourceBundle.getBundle("jtimebomb/Internationalization").getString("Detonated"));
                activateToggleButton.setSelected(false);
            }
    }
    
}
