/*
 * RandomNumberGeneratorFrame.java
 *
 * Created on 23. Oktober 2008, 19:22
 * Preferrably create a single instance to avoid time-consuming reseeding of the RNG
 */
package jcrypter.rand;

import java.awt.EventQueue;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SpinnerNumberModel;
import jcrypter.JCrypterFrame;
import jcrypter.utils.MersenneTwisterFast;
import jcrypter.utils.Utils;

/**
 *
 * @author  uli
 */
public class RandomNumberGeneratorFrame extends javax.swing.JFrame
{

    /** Creates new form RandomNumberGeneratorFrame */
    public RandomNumberGeneratorFrame()
    {
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

        numberTypeButtonGroup = new javax.swing.ButtonGroup();
        outputTypeButtonGroup = new javax.swing.ButtonGroup();
        numberTypePanel = new javax.swing.JPanel();
        booleanRadioButton = new javax.swing.JRadioButton();
        byteRadioButton = new javax.swing.JRadioButton();
        shortRadioButton = new javax.swing.JRadioButton();
        intRadioButton = new javax.swing.JRadioButton();
        longRadioButton = new javax.swing.JRadioButton();
        floatRadioButton = new javax.swing.JRadioButton();
        doubleRadioButton = new javax.swing.JRadioButton();
        gaussianRadioButton = new javax.swing.JRadioButton();
        securityPanel = new javax.swing.JPanel();
        warmupItsLabel = new javax.swing.JLabel();
        warmupItsSpinner = new javax.swing.JSpinner();
        boundsPanel = new javax.swing.JPanel();
        boundsEnabledCheckBox = new javax.swing.JCheckBox();
        lowerBoundLabel = new javax.swing.JLabel();
        upperBoundLabel = new javax.swing.JLabel();
        lowerBoundField = new javax.swing.JTextField();
        upperBoundField = new javax.swing.JTextField();
        okButton = new javax.swing.JButton();
        outputPanel = new javax.swing.JPanel();
        fileRadioButton = new javax.swing.JRadioButton();
        showRadioButton = new javax.swing.JRadioButton();
        filenameRadioButton = new javax.swing.JLabel();
        filenameField = new javax.swing.JTextField();
        selectPubFileButton = new javax.swing.JButton();
        iterationsLabel = new javax.swing.JLabel();
        iterationsSpinner = new javax.swing.JSpinner();

        setTitle(i18n.getString("RandomNumberGeneratorFrame.title")); // NOI18N
        setResizable(false);

        numberTypePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(i18n.getString("RandomNumberGeneratorFrame.numberTypePanel.border.title"))); // NOI18N
        numberTypePanel.setLayout(new javax.swing.BoxLayout(numberTypePanel, javax.swing.BoxLayout.PAGE_AXIS));

        numberTypeButtonGroup.add(booleanRadioButton);
        booleanRadioButton.setText(i18n.getString("RandomNumberGeneratorFrame.booleanRadioButton.text")); // NOI18N
        numberTypePanel.add(booleanRadioButton);

        numberTypeButtonGroup.add(byteRadioButton);
        byteRadioButton.setText(i18n.getString("RandomNumberGeneratorFrame.byteRadioButton.text")); // NOI18N
        numberTypePanel.add(byteRadioButton);

        numberTypeButtonGroup.add(shortRadioButton);
        shortRadioButton.setText(i18n.getString("RandomNumberGeneratorFrame.shortRadioButton.text")); // NOI18N
        numberTypePanel.add(shortRadioButton);

        numberTypeButtonGroup.add(intRadioButton);
        intRadioButton.setSelected(true);
        intRadioButton.setText(i18n.getString("RandomNumberGeneratorFrame.intRadioButton.text")); // NOI18N
        numberTypePanel.add(intRadioButton);

        numberTypeButtonGroup.add(longRadioButton);
        longRadioButton.setText(i18n.getString("RandomNumberGeneratorFrame.longRadioButton.text")); // NOI18N
        numberTypePanel.add(longRadioButton);

        numberTypeButtonGroup.add(floatRadioButton);
        floatRadioButton.setText(i18n.getString("RandomNumberGeneratorFrame.floatRadioButton.text")); // NOI18N
        numberTypePanel.add(floatRadioButton);

        numberTypeButtonGroup.add(doubleRadioButton);
        doubleRadioButton.setText(i18n.getString("RandomNumberGeneratorFrame.doubleRadioButton.text")); // NOI18N
        numberTypePanel.add(doubleRadioButton);

        numberTypeButtonGroup.add(gaussianRadioButton);
        gaussianRadioButton.setText(i18n.getString("RandomNumberGeneratorFrame.gaussianRadioButton.text")); // NOI18N
        numberTypePanel.add(gaussianRadioButton);

        securityPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(i18n.getString("RandomNumberGeneratorFrame.securityPanel.border.title"))); // NOI18N

        warmupItsLabel.setText(i18n.getString("RandomNumberGeneratorFrame.warmupItsLabel.text")); // NOI18N

        warmupItsSpinner.setModel(new SpinnerNumberModel(10000,0,Long.MAX_VALUE,1));

        javax.swing.GroupLayout securityPanelLayout = new javax.swing.GroupLayout(securityPanel);
        securityPanel.setLayout(securityPanelLayout);
        securityPanelLayout.setHorizontalGroup(
            securityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(securityPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(warmupItsLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(warmupItsSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        securityPanelLayout.setVerticalGroup(
            securityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(securityPanelLayout.createSequentialGroup()
                .addGroup(securityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(warmupItsLabel)
                    .addComponent(warmupItsSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        boundsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(i18n.getString("RandomNumberGeneratorFrame.boundsPanel.border.title"))); // NOI18N

        boundsEnabledCheckBox.setText(i18n.getString("RandomNumberGeneratorFrame.boundsEnabledCheckBox.text")); // NOI18N
        boundsEnabledCheckBox.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                boundsEnabledCheckBoxStateChanged(evt);
            }
        });

        lowerBoundLabel.setText(i18n.getString("RandomNumberGeneratorFrame.lowerBoundLabel.text")); // NOI18N

        upperBoundLabel.setText(i18n.getString("RandomNumberGeneratorFrame.upperBoundLabel.text")); // NOI18N

        lowerBoundField.setEditable(false);
        lowerBoundField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        lowerBoundField.setText("0"); // NOI18N

        upperBoundField.setEditable(false);
        upperBoundField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        upperBoundField.setText("1000"); // NOI18N

        javax.swing.GroupLayout boundsPanelLayout = new javax.swing.GroupLayout(boundsPanel);
        boundsPanel.setLayout(boundsPanelLayout);
        boundsPanelLayout.setHorizontalGroup(
            boundsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(boundsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(boundsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(boundsEnabledCheckBox)
                    .addGroup(boundsPanelLayout.createSequentialGroup()
                        .addGroup(boundsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(upperBoundLabel)
                            .addComponent(lowerBoundLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(boundsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lowerBoundField, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(upperBoundField))))
                .addContainerGap())
        );
        boundsPanelLayout.setVerticalGroup(
            boundsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(boundsPanelLayout.createSequentialGroup()
                .addComponent(boundsEnabledCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(boundsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lowerBoundLabel)
                    .addComponent(lowerBoundField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(boundsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(upperBoundLabel)
                    .addComponent(upperBoundField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        okButton.setText(i18n.getString("RandomNumberGeneratorFrame.okButton.text")); // NOI18N
        okButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                okButtonMouseClicked(evt);
            }
        });

        outputPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(i18n.getString("RandomNumberGeneratorFrame.outputPanel.border.title"))); // NOI18N

        outputTypeButtonGroup.add(fileRadioButton);
        fileRadioButton.setText(i18n.getString("RandomNumberGeneratorFrame.fileRadioButton.text")); // NOI18N

        outputTypeButtonGroup.add(showRadioButton);
        showRadioButton.setSelected(true);
        showRadioButton.setText(i18n.getString("RandomNumberGeneratorFrame.showRadioButton.text")); // NOI18N

        filenameRadioButton.setText(i18n.getString("RandomNumberGeneratorFrame.filenameRadioButton.text")); // NOI18N

        filenameField.setText(i18n.getString("RandomNumberGeneratorFrame.filenameField.text")); // NOI18N

        selectPubFileButton.setText(i18n.getString("RandomNumberGeneratorFrame.selectPubFileButton.text")); // NOI18N
        selectPubFileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectPubFileButtonActionPerformed(evt);
            }
        });

        iterationsLabel.setText(i18n.getString("RandomNumberGeneratorFrame.iterationsLabel.text")); // NOI18N

        iterationsSpinner.setModel(new SpinnerNumberModel(1000,0,Long.MAX_VALUE,1));

        javax.swing.GroupLayout outputPanelLayout = new javax.swing.GroupLayout(outputPanel);
        outputPanel.setLayout(outputPanelLayout);
        outputPanelLayout.setHorizontalGroup(
            outputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(outputPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(outputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(filenameRadioButton)
                    .addGroup(outputPanelLayout.createSequentialGroup()
                        .addGroup(outputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fileRadioButton)
                            .addComponent(iterationsLabel))
                        .addGap(4, 4, 4)
                        .addGroup(outputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(iterationsSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, outputPanelLayout.createSequentialGroup()
                                .addComponent(filenameField)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(selectPubFileButton, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(showRadioButton))))
                .addContainerGap())
        );
        outputPanelLayout.setVerticalGroup(
            outputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(outputPanelLayout.createSequentialGroup()
                .addGroup(outputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fileRadioButton)
                    .addComponent(showRadioButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(outputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(iterationsLabel)
                    .addComponent(iterationsSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(outputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(filenameRadioButton)
                    .addComponent(filenameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(selectPubFileButton))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(numberTypePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(okButton, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(securityPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(boundsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(outputPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(securityPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(boundsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(okButton))
                    .addComponent(numberTypePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(outputPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void selectPubFileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectPubFileButtonActionPerformed
    fileChooser.setSelectedFile(new File(filenameField.getText()));
    fileChooser.showSaveDialog(this);
    filenameField.setText(fileChooser.getSelectedFile().getAbsolutePath());
}//GEN-LAST:event_selectPubFileButtonActionPerformed

private void okButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_okButtonMouseClicked
    OutputStream out = null;
    OutputStreamWriter ow = null;
    try
    {
        //Get an instance of the right output stream
        if (fileRadioButton.isSelected())
        {
            out = new BufferedOutputStream(new FileOutputStream(filenameField.getText()));
        }
        else //implies showRadioButton.isSelected()
        {
            out = new ResultsFrameOutputStream(new ResultsFrame());
        }
        //Initialize the output stream writer
        ow = new OutputStreamWriter(out);
        //Perform the initial "empty" runs
        long i = getWarmupIts(); //Directly used as iteration variable
        for (; i > 0; i--)
        {
            mt.nextLong();
        }
        //Perform the real runs
        i = getIterations();
        if (longRadioButton.isSelected())
        {
        //<editor-fold>
            if (boundsEnabledCheckBox.isSelected())
            {
                //Get the bounds. upper bound is not needed as the modulus does all we want
                long lowerBound = Long.valueOf(lowerBoundField.getText());
                long modulus =
                        Long.valueOf(upperBoundField.getText()) - lowerBound;

                for (; i > 0; i--)
                {
                    ow.write(Long.toString((mt.nextLong() % modulus) + lowerBound) + "\n"); //NOI18N
                }
            }
            else
            {
                for (; i > 0; i--)
                {
                    ow.write(Long.toString(mt.nextLong()) + "\n"); //NOI18N
                }
            }
        }
        //</editor-fold>
        else if (byteRadioButton.isSelected())
        {
        //<editor-fold>
            if (boundsEnabledCheckBox.isSelected())
            {
                //Get the bounds. upper bound is not needed as the modulus does all we want
                byte lowerBound = Byte.valueOf(lowerBoundField.getText());
                byte modulus =
                        (byte) (Byte.valueOf(upperBoundField.getText()) - lowerBound);

                for (; i > 0; i--)
                {
                    ow.write(Byte.toString((byte) ((mt.nextByte() % modulus) + lowerBound)) + "\n"); //NOI18N
                }
            }
            else
            {
                for (; i > 0; i--)
                {
                    ow.write(Byte.toString(mt.nextByte()) + "\n"); //NOI18N
                }
            }
        }
        //</editor-fold>
        else if (floatRadioButton.isSelected())
        {
        //<editor-fold>
            if (boundsEnabledCheckBox.isSelected())
            {
                //Get the bounds. upper bound is not needed as the modulus does all we want
                float lowerBound = Float.valueOf(lowerBoundField.getText());
                float modulus =
                        Float.valueOf(upperBoundField.getText()) - lowerBound;

                for (; i > 0; i--)
                {
                    //Ensure positive signum
                    float num = mt.nextFloat();
                    if(num < 0) {num *= -1;}
                    ow.write(Float.toString((num % modulus) + lowerBound) + "\n"); //NOI18N
                }
            }
            else
            {
                for (; i > 0; i--)
                {
                    ow.write(Float.toString(mt.nextFloat()) + "\n"); //NOI18N
                }
            }
        }
        //</editor-fold>
        else if (doubleRadioButton.isSelected())
        {
        //<editor-fold>
            if (boundsEnabledCheckBox.isSelected())
            {
                //Get the bounds. upper bound is not needed as the modulus does all we want
                double lowerBound = Double.valueOf(lowerBoundField.getText());
                double modulus =
                        Double.valueOf(upperBoundField.getText()) - lowerBound;

                for (; i > 0; i--)
                {
                    //Ensure positive signum
                    double num = mt.nextDouble();
                    if(num < 0) {num *= -1;}
                    ow.write(Double.toString((num % modulus) + lowerBound) + "\n"); //NOI18N
                }
            }
            else
            {
                for (; i > 0; i--)
                {
                    ow.write(Double.toString(mt.nextDouble()) + "\n"); //NOI18N
                }
            }
        }
        //</editor-fold>
        else if (gaussianRadioButton.isSelected())
        {
            //<editor-fold>
            if (boundsEnabledCheckBox.isSelected())
            {
                //Get the bounds. upper bound is not needed as the modulus does all we want
                Double lowerBound = Double.valueOf(lowerBoundField.getText());
                Double modulus =
                        Double.valueOf(upperBoundField.getText()) - lowerBound;

                for (; i > 0; i--)
                {
                    double num = mt.nextGaussian();
                    if(num < 0) {num *= -1;}
                    ow.write(Double.toString((num % modulus) + lowerBound) + "\n"); //NOI18N
                }
            }
            else
            {
                for (; i > 0; i--)
                {
                    ow.write(Double.toString(mt.nextGaussian()) + "\n"); //NOI18N
                }
            }
        }
        //</editor-fold>
        else if (intRadioButton.isSelected())
        {
            //<editor-fold>
            if (boundsEnabledCheckBox.isSelected())
            {
                //Get the bounds. upper bound is not needed as the modulus does all we want
                int lowerBound = Integer.valueOf(lowerBoundField.getText());
                int modulus =
                        Integer.valueOf(upperBoundField.getText()) - lowerBound;

                for (; i > 0; i--)
                {
                    int num = mt.nextInt();
                    if(num < 0) {num *= -1;}
                    ow.write(Integer.toString((num % modulus) + lowerBound) + "\n"); //NOI18N
                }
            }
            else
            {
                for (; i > 0; i--)
                {
                    ow.write(Integer.toString(mt.nextInt()) + "\n"); //NOI18N
                }
            }
        }
        //</editor-fold>
        else if (shortRadioButton.isSelected())
        {
            //<editor-fold>
            if (boundsEnabledCheckBox.isSelected())
            {
                //Get the bounds. upper bound is not needed as the modulus does all we want
                short lowerBound = Short.valueOf(lowerBoundField.getText());
                short modulus =
                        (short) (Short.valueOf(upperBoundField.getText()) - lowerBound);

                for (; i > 0; i--)
                {
                    //Ensure positive signum
                    short num = mt.nextShort();
                    if(num < 0) {num *= -1;}
                    ow.write(Short.toString((short) ((num % modulus) + lowerBound)) + "\n"); //NOI18N
                }
            }
            else
            {
                for (; i > 0; i--)
                {
                    ow.write(Short.toString(mt.nextShort()) + "\n"); //NOI18N
                }
            }
        }
        //</editor-fold>
        else if (booleanRadioButton.isSelected())
        {
            //<editor-fold>
            //Boolean bounds are senseless and therefore ignored
            for (; i > 0; i--)
            {
                ow.write(Boolean.toString(mt.nextBoolean()) + "\n"); //NOI18N
            }
        }
    //</editor-fold>

    }
    catch (IOException ex)
    {
        JOptionPane.showMessageDialog(this, ex, i18n.getString("IO_Error"), JOptionPane.ERROR_MESSAGE);
    }
    catch(NumberFormatException ex) //Invalid bounds input
    {
        JOptionPane.showMessageDialog(this, ex, i18n.getString("Number_format_error"), JOptionPane.ERROR_MESSAGE);
    }
    finally
    {
        try
        {
            ow.close();
        }
        catch (IOException ex)
        {
            Logger.getLogger(RandomNumberGeneratorFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (NullPointerException ex)
        {
        }
    }
}//GEN-LAST:event_okButtonMouseClicked

private void boundsEnabledCheckBoxStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_boundsEnabledCheckBoxStateChanged
    //Enable the lower and upper bound fields if enabled, otherwise disable
    EventQueue.invokeLater(new Runnable()
    {
        @Override
        public void run()
        {
            boolean enable = boundsEnabledCheckBox.isSelected();
            lowerBoundField.setEditable(enable);
            upperBoundField.setEditable(enable);
        }
    });
}//GEN-LAST:event_boundsEnabledCheckBoxStateChanged

    private long getWarmupIts()
    {
        SpinnerNumberModel model =
                (SpinnerNumberModel) warmupItsSpinner.getModel();
        return model.getNumber().longValue();
    }

    private long getIterations()
    {
        SpinnerNumberModel model =
                (SpinnerNumberModel) iterationsSpinner.getModel();
        return model.getNumber().longValue();
    }
    private MersenneTwisterFast mt =
            new MersenneTwisterFast(Utils.MTSeed(JCrypterFrame.rand));
    private ResourceBundle i18n =
            ResourceBundle.getBundle("jcrypter/rand/Bundle");
    private JFileChooser fileChooser = JCrypterFrame.mainFrame.fileChooser;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton booleanRadioButton;
    private javax.swing.JCheckBox boundsEnabledCheckBox;
    private javax.swing.JPanel boundsPanel;
    private javax.swing.JRadioButton byteRadioButton;
    private javax.swing.JRadioButton doubleRadioButton;
    private javax.swing.JRadioButton fileRadioButton;
    private javax.swing.JTextField filenameField;
    private javax.swing.JLabel filenameRadioButton;
    private javax.swing.JRadioButton floatRadioButton;
    private javax.swing.JRadioButton gaussianRadioButton;
    private javax.swing.JRadioButton intRadioButton;
    private javax.swing.JLabel iterationsLabel;
    private javax.swing.JSpinner iterationsSpinner;
    private javax.swing.JRadioButton longRadioButton;
    private javax.swing.JTextField lowerBoundField;
    private javax.swing.JLabel lowerBoundLabel;
    private javax.swing.ButtonGroup numberTypeButtonGroup;
    private javax.swing.JPanel numberTypePanel;
    private javax.swing.JButton okButton;
    private javax.swing.JPanel outputPanel;
    private javax.swing.ButtonGroup outputTypeButtonGroup;
    private javax.swing.JPanel securityPanel;
    private javax.swing.JButton selectPubFileButton;
    private javax.swing.JRadioButton shortRadioButton;
    private javax.swing.JRadioButton showRadioButton;
    private javax.swing.JTextField upperBoundField;
    private javax.swing.JLabel upperBoundLabel;
    private javax.swing.JLabel warmupItsLabel;
    private javax.swing.JSpinner warmupItsSpinner;
    // End of variables declaration//GEN-END:variables
}
