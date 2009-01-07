/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * JRocketStartSimulatorFrame.java
 *
 * Created on 07.01.2009, 18:55:06
 */

package jrocketstartsimulator;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author uli
 */
public class JRocketStartSimulatorFrame extends javax.swing.JFrame {

    /** Creates new form JRocketStartSimulatorFrame */
    public JRocketStartSimulatorFrame() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rocketWeightLabel = new javax.swing.JLabel();
        fuelWeightLabel = new javax.swing.JLabel();
        fuelEmissionLabel = new javax.swing.JLabel();
        fuelVelocityLabel = new javax.swing.JLabel();
        gravityLabel = new javax.swing.JLabel();
        okButton = new javax.swing.JButton();
        resolutionLabel = new javax.swing.JLabel();
        resolutionField = new javax.swing.JTextField();
        gravityField = new javax.swing.JTextField();
        fuelVelocityField = new javax.swing.JTextField();
        fuelEmissionField = new javax.swing.JTextField();
        fuelWeightField = new javax.swing.JTextField();
        rocketWeightField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        outputTextArea = new javax.swing.JTextArea();
        precisionLabel = new javax.swing.JLabel();
        precisionField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle( i18n.getString("JRocketStartSimulatorFrame.title")); // NOI18N

        rocketWeightLabel.setText( i18n.getString("JRocketStartSimulatorFrame.rocketWeightLabel.text")); // NOI18N

        fuelWeightLabel.setText( i18n.getString("JRocketStartSimulatorFrame.fuelWeightLabel.text")); // NOI18N

        fuelEmissionLabel.setText( i18n.getString("JRocketStartSimulatorFrame.fuelEmissionLabel.text")); // NOI18N

        fuelVelocityLabel.setText( i18n.getString("JRocketStartSimulatorFrame.fuelVelocityLabel.text")); // NOI18N

        gravityLabel.setText( i18n.getString("JRocketStartSimulatorFrame.gravityLabel.text")); // NOI18N

        okButton.setText( i18n.getString("JRocketStartSimulatorFrame.okButton.text")); // NOI18N
        okButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                okButtonMouseClicked(evt);
            }
        });

        resolutionLabel.setText( i18n.getString("JRocketStartSimulatorFrame.resolutionLabel.text")); // NOI18N

        resolutionField.setText( i18n.getString("JRocketStartSimulatorFrame.resolutionField.text")); // NOI18N

        gravityField.setText( i18n.getString("JRocketStartSimulatorFrame.gravityField.text")); // NOI18N

        fuelVelocityField.setText( i18n.getString("JRocketStartSimulatorFrame.fuelVelocityField.text")); // NOI18N

        fuelEmissionField.setText( i18n.getString("JRocketStartSimulatorFrame.fuelEmissionField.text")); // NOI18N

        fuelWeightField.setText( i18n.getString("JRocketStartSimulatorFrame.fuelWeightField.text")); // NOI18N

        rocketWeightField.setText( i18n.getString("JRocketStartSimulatorFrame.rocketWeightField.text")); // NOI18N

        outputTextArea.setColumns(20);
        outputTextArea.setRows(5);
        jScrollPane1.setViewportView(outputTextArea);

        precisionLabel.setText( i18n.getString("JRocketStartSimulatorFrame.precisionLabel.text")); // NOI18N

        precisionField.setText( i18n.getString("JRocketStartSimulatorFrame.precisionField.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(gravityLabel)
                            .addComponent(fuelWeightLabel)
                            .addComponent(rocketWeightLabel)
                            .addComponent(fuelEmissionLabel)
                            .addComponent(resolutionLabel)
                            .addComponent(fuelVelocityLabel)
                            .addComponent(precisionLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fuelVelocityField, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                            .addComponent(fuelWeightField, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                            .addComponent(rocketWeightField, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                            .addComponent(fuelEmissionField, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                            .addComponent(gravityField, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                            .addComponent(resolutionField, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                            .addComponent(precisionField, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)))
                    .addComponent(okButton, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rocketWeightLabel)
                    .addComponent(rocketWeightField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fuelWeightLabel)
                    .addComponent(fuelWeightField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fuelEmissionLabel)
                    .addComponent(fuelEmissionField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fuelVelocityField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fuelVelocityLabel))
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(gravityLabel)
                    .addComponent(gravityField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(resolutionField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(resolutionLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(precisionLabel)
                    .addComponent(precisionField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(okButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void okButtonMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_okButtonMouseClicked
    {//GEN-HEADEREND:event_okButtonMouseClicked
        FileWriter fw = null;
        try
        {
            double fuelWeight =
                    new Double(fuelWeightField.getText());
            double rocketWeight = new Double(rocketWeightField.getText());
            double mass =
                     rocketWeight + fuelWeight;
            double fuelEm = new Double(fuelEmissionField.getText());
            double fuelVel = new Double(fuelVelocityField.getText());
            double gravity = new Double(gravityField.getText());
            double res = new Double(resolutionField.getText());
            double nForce = fuelEm * fuelVel;
            double newtonWeight = mass * gravity;
            double height = 0.0;
            double time = 0.0; //Elapsed time
            double velocity = 0.0; //Entire rocket velocity
            double acceleration;

            ///Build the format string
            String formatstring = "%." + precisionField.getText() + "f";

            fw = new FileWriter("rocket.csv");

            outputTextArea.append("t , m , a , v , h\n");
            
            for(int i = 0; i < 10000;i++)
            {
                time += res;
                acceleration = (nForce - newtonWeight) / mass;
                if(fuelWeight > 0) {fuelWeight -= fuelEm;}
                else {fuelWeight = 0;}
                //Recalculate scalars
                if(fuelWeight > 0) {velocity += acceleration * res;}
                if(velocity < 0) {break;}
                height += velocity;
                mass = rocketWeight + fuelWeight;
                newtonWeight = mass * gravity;
                //Print out the results
                StringBuilder sb = new StringBuilder();
                sb.append(String.format(Locale.US, formatstring, time));
                sb.append(" , ");
                sb.append(String.format(Locale.US, formatstring, mass));
                sb.append(" , ");
                sb.append(String.format(Locale.US, formatstring, acceleration));
                sb.append(" , ");
                sb.append(String.format(Locale.US, formatstring, velocity));
                sb.append(" , ");
                sb.append(String.format(Locale.US, formatstring, height));
                sb.append("\n");
                fw.write(sb.toString());
                outputTextArea.append(sb.toString());
            }
            fw.close();
        }
        catch (IOException ex)
        {
            Logger.getLogger(JRocketStartSimulatorFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try
            {
                fw.close();
            }
            catch(NullPointerException ex){}
            catch (IOException ex)
            {
                Logger.getLogger(JRocketStartSimulatorFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_okButtonMouseClicked

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JRocketStartSimulatorFrame().setVisible(true);
            }
        });
    }


    private ResourceBundle i18n = ResourceBundle.getBundle("jrocketstartsimulator/Bundle"); //NOI18N
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField fuelEmissionField;
    private javax.swing.JLabel fuelEmissionLabel;
    private javax.swing.JTextField fuelVelocityField;
    private javax.swing.JLabel fuelVelocityLabel;
    private javax.swing.JTextField fuelWeightField;
    private javax.swing.JLabel fuelWeightLabel;
    private javax.swing.JTextField gravityField;
    private javax.swing.JLabel gravityLabel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton okButton;
    private javax.swing.JTextArea outputTextArea;
    private javax.swing.JTextField precisionField;
    private javax.swing.JLabel precisionLabel;
    private javax.swing.JTextField resolutionField;
    private javax.swing.JLabel resolutionLabel;
    private javax.swing.JTextField rocketWeightField;
    private javax.swing.JLabel rocketWeightLabel;
    // End of variables declaration//GEN-END:variables

}
