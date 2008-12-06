/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 *
 * NOTE: 43 lines per page
 */

/*
 * JMathSheetGeneratorFrame.java
 *
 * Created on 03.12.2008, 16:28:46
 */
package jmathsheetgenerator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author uli
 */
public class JMathSheetGeneratorFrame extends javax.swing.JFrame
{

    /** Creates new form JMathSheetGeneratorFrame */
    public JMathSheetGeneratorFrame()
    {
        initComponents();
        //Initialize the Mersenne Twister
        mt = new MersenneTwisterFast(generateMTSeed());
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        okButton = new javax.swing.JButton();
        linesPerColumnLabel = new javax.swing.JLabel();
        operatorLabel = new javax.swing.JLabel();
        linesPerColSpinner = new javax.swing.JSpinner();
        alignNumbersCheckbox = new javax.swing.JCheckBox();
        floatingPointLabel = new javax.swing.JCheckBox();
        lineLengthLabel = new javax.swing.JLabel();
        plusCheckbox = new javax.swing.JCheckBox();
        minusCheckbox = new javax.swing.JCheckBox();
        multCheckbox = new javax.swing.JCheckBox();
        divCheckbox = new javax.swing.JCheckBox();
        resultLimitsPanel = new javax.swing.JPanel();
        numbersFromLabel = new javax.swing.JLabel();
        resultFromSpinner = new javax.swing.JSpinner();
        numbersToLabel = new javax.swing.JLabel();
        resultToSpinner = new javax.swing.JSpinner();
        numberLimitsPanel1 = new javax.swing.JPanel();
        numbersFromLabel1 = new javax.swing.JLabel();
        numberFromSpinner = new javax.swing.JSpinner();
        numbersToLabel1 = new javax.swing.JLabel();
        numberToSpinner = new javax.swing.JSpinner();
        lineLengthField = new javax.swing.JTextField();
        schoolOperatorsCheckbox = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle(i18n.getString("JMathSheetGeneratorFrame.title")); // NOI18N

        okButton.setText(i18n.getString("JMathSheetGeneratorFrame.okButton.text")); // NOI18N
        okButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                okButtonMouseClicked(evt);
            }
        });

        linesPerColumnLabel.setText(i18n.getString("JMathSheetGeneratorFrame.linesPerColumnLabel.text")); // NOI18N

        operatorLabel.setText(i18n.getString("JMathSheetGeneratorFrame.operatorLabel.text")); // NOI18N

        linesPerColSpinner.setModel(new javax.swing.SpinnerNumberModel(43, 0, 43, 1));
        linesPerColSpinner.setToolTipText(i18n.getString("JMathSheetGeneratorFrame.linesPerColSpinner.toolTipText")); // NOI18N

        alignNumbersCheckbox.setSelected(true);
        alignNumbersCheckbox.setText(i18n.getString("JMathSheetGeneratorFrame.alignNumbersCheckbox.text")); // NOI18N
        alignNumbersCheckbox.setToolTipText(i18n.getString("JMathSheetGeneratorFrame.alignNumbersCheckbox.toolTipText")); // NOI18N

        floatingPointLabel.setText(i18n.getString("JMathSheetGeneratorFrame.floatingPointLabel.text")); // NOI18N
        floatingPointLabel.setToolTipText(i18n.getString("JMathSheetGeneratorFrame.floatingPointLabel.toolTipText")); // NOI18N

        lineLengthLabel.setText(i18n.getString("JMathSheetGeneratorFrame.lineLengthLabel.text")); // NOI18N

        plusCheckbox.setSelected(true);
        plusCheckbox.setText(i18n.getString("JMathSheetGeneratorFrame.plusCheckbox.text")); // NOI18N

        minusCheckbox.setSelected(true);
        minusCheckbox.setText(i18n.getString("JMathSheetGeneratorFrame.minusCheckbox.text")); // NOI18N

        multCheckbox.setText(i18n.getString("JMathSheetGeneratorFrame.multCheckbox.text")); // NOI18N

        divCheckbox.setText(i18n.getString("JMathSheetGeneratorFrame.divCheckbox.text")); // NOI18N

        resultLimitsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(i18n.getString("JMathSheetGeneratorFrame.resultLimitsPanel.border.title"))); // NOI18N

        numbersFromLabel.setText(i18n.getString("JMathSheetGeneratorFrame.numbersFromLabel.text")); // NOI18N

        resultFromSpinner.setModel(new javax.swing.SpinnerNumberModel());

        numbersToLabel.setText(i18n.getString("JMathSheetGeneratorFrame.numbersToLabel.text")); // NOI18N

        resultToSpinner.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(100), null, null, Integer.valueOf(1)));

        javax.swing.GroupLayout resultLimitsPanelLayout = new javax.swing.GroupLayout(resultLimitsPanel);
        resultLimitsPanel.setLayout(resultLimitsPanelLayout);
        resultLimitsPanelLayout.setHorizontalGroup(
            resultLimitsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(resultLimitsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(numbersFromLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(resultFromSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(numbersToLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(resultToSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        resultLimitsPanelLayout.setVerticalGroup(
            resultLimitsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(resultLimitsPanelLayout.createSequentialGroup()
                .addGroup(resultLimitsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(numbersFromLabel)
                    .addComponent(resultFromSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(numbersToLabel)
                    .addComponent(resultToSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        numberLimitsPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(i18n.getString("JMathSheetGeneratorFrame.numberLimitsPanel1.border.title"))); // NOI18N

        numbersFromLabel1.setText(i18n.getString("JMathSheetGeneratorFrame.numbersFromLabel1.text")); // NOI18N

        numberFromSpinner.setModel(new javax.swing.SpinnerNumberModel());

        numbersToLabel1.setText(i18n.getString("JMathSheetGeneratorFrame.numbersToLabel1.text")); // NOI18N

        numberToSpinner.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(100), null, null, Integer.valueOf(1)));

        javax.swing.GroupLayout numberLimitsPanel1Layout = new javax.swing.GroupLayout(numberLimitsPanel1);
        numberLimitsPanel1.setLayout(numberLimitsPanel1Layout);
        numberLimitsPanel1Layout.setHorizontalGroup(
            numberLimitsPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, numberLimitsPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(numbersFromLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(numberFromSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(numbersToLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(numberToSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        numberLimitsPanel1Layout.setVerticalGroup(
            numberLimitsPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(numberLimitsPanel1Layout.createSequentialGroup()
                .addGroup(numberLimitsPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(numberToSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(numbersToLabel1)
                    .addComponent(numberFromSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(numbersFromLabel1))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        lineLengthField.setText( i18n.getString("JMathSheetGeneratorFrame.lineLengthField.text")); // NOI18N
        lineLengthField.setToolTipText( i18n.getString("JMathSheetGeneratorFrame.lineLengthField.toolTipText")); // NOI18N

        schoolOperatorsCheckbox.setSelected(true);
        schoolOperatorsCheckbox.setText( i18n.getString("JMathSheetGeneratorFrame.schoolOperatorsCheckbox.text")); // NOI18N
        schoolOperatorsCheckbox.setToolTipText( i18n.getString("JMathSheetGeneratorFrame.schoolOperatorsCheckbox.toolTipText")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(schoolOperatorsCheckbox)
                    .addComponent(resultLimitsPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(numberLimitsPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(operatorLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                        .addComponent(plusCheckbox)
                        .addGap(4, 4, 4)
                        .addComponent(minusCheckbox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(multCheckbox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(divCheckbox)
                        .addGap(14, 14, 14))
                    .addComponent(floatingPointLabel)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(linesPerColumnLabel)
                            .addComponent(lineLengthLabel))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lineLengthField, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                            .addComponent(linesPerColSpinner, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)))
                    .addComponent(alignNumbersCheckbox)
                    .addComponent(okButton, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(operatorLabel)
                        .addGap(9, 9, 9))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(linesPerColSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(linesPerColumnLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(plusCheckbox)
                            .addComponent(minusCheckbox)
                            .addComponent(multCheckbox)
                            .addComponent(divCheckbox))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lineLengthLabel)
                    .addComponent(lineLengthField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(numberLimitsPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(resultLimitsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(floatingPointLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(alignNumbersCheckbox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(schoolOperatorsCheckbox)
                .addGap(6, 6, 6)
                .addComponent(okButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private int getLinesPerCol()
    {
        SpinnerNumberModel sm = (SpinnerNumberModel) linesPerColSpinner.getModel();
        return sm.getNumber().intValue();
    }

    private int getExercisesPerCol()
    {
        SpinnerNumberModel sm = (SpinnerNumberModel) linesPerColSpinner.getModel();
        return sm.getNumber().intValue();
    }

    private int getNumberFrom()
    {
        SpinnerNumberModel sm = (SpinnerNumberModel) numberFromSpinner.getModel();
        return sm.getNumber().intValue();
    }

    private int getNumberTo()
    {
        SpinnerNumberModel sm = (SpinnerNumberModel) numberToSpinner.getModel();
        return sm.getNumber().intValue();
    }

    private int getResultFrom()
    {
        SpinnerNumberModel sm = (SpinnerNumberModel) resultFromSpinner.getModel();
        return sm.getNumber().intValue();
    }

    private int getResultTo()
    {
        SpinnerNumberModel sm = (SpinnerNumberModel) resultToSpinner.getModel();
        return sm.getNumber().intValue();
    }

    private int[] getRandNums(int lowerLimit, int upperLimit)
    {
        int[] ret = new int[2];
        ret[0] = mt.nextInt(upperLimit - lowerLimit) + lowerLimit;
        ret[1] = mt.nextInt(upperLimit - lowerLimit) + lowerLimit;
        return ret;
    }

    private boolean inRange(int arg, int lower, int upper)
    {
        return (arg >= lower && arg < upper);
    }

    private void writeCol()
    {
        try
        {
            int exercises = getExercisesPerCol();
            fw.write("\\begin{tabular}{rcrcc}\n"); //NOI18N
            String placeholder = "\\underline{\\hspace{" + lineLengthField.getText() + "}}";
            //Escape characters in the placeholder string
            placeholder.replaceAll("_", "\\\\_");
            //Build a string containing all operators
            String operators = "";
            if (plusCheckbox.isSelected())
            {
                operators += "+";
            }
            if (minusCheckbox.isSelected())
            {
                operators += "-";
            }
            if (multCheckbox.isSelected())
            {
                operators += "*";
            }
            if (divCheckbox.isSelected())
            {
                operators += "/";
            }
            int opNum = operators.length(); //Number of operators
            int numberLowerLimit = getNumberFrom();
            int numberUpperLimit = getNumberTo();
            int resultLowerLimit = getResultFrom();
            int resultUpperLimit = getResultTo();
            int[] nums;
            int result = 0;
            char op;
            //Declare a reference to the operators array
            char opArray[] = stdOperators;
            if(schoolOperatorsCheckbox.isSelected())
            {
                opArray = schoolOperators;
            }
            for (int i = 0; i < exercises;
                    i++)
            {
                do
                {
                    nums = getRandNums(numberLowerLimit, numberUpperLimit);
                    op = operators.charAt(mt.nextInt(opNum));
                    switch (op)
                    {
                        case '+':
                        {
                            result = nums[0] + nums[1];
                            op = opArray[0];
                            break;
                        }
                        case '-':
                        {
                            result = nums[0] - nums[1];
                            op = opArray[1];
                            break;
                        }
                        case '*':
                        {
                            result = nums[0] * nums[1];
                            op = opArray[2];
                            break;
                        }
                        case '/':
                        {
                            result = nums[0] / nums[1];
                            op = opArray[3];
                            break;
                        }
                        default:
                            result = 0;
                    }
                }
                while (!(inRange(result, resultLowerLimit, resultUpperLimit)));
                //Write the exercise line
                fw.write("" + Integer.toString(nums[0]) + " & " + op + " & " +
                        Integer.toString(nums[1]) + " & = & " + placeholder + " \\\\\n");
            }
            //Write the footer
            fw.write("\\end{tabular}\n");
        }
        catch (IOException ex)
        {
            Logger.getLogger(JMathSheetGeneratorFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void okButtonMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_okButtonMouseClicked
    {//GEN-HEADEREND:event_okButtonMouseClicked
        try
        {
            JFileChooser fc = new JFileChooser(new File("."));
            fc.showSaveDialog(this);
            fw = new FileWriter(fc.getSelectedFile());
            //Write the header
            fw.write("\\documentclass[a4paper,twocolumn]{scrartcl}\n"); //NOI18N
            fw.write("\\usepackage[utf8]{inputenc}\n"); //NOI18N
            fw.write("\\usepackage[T1]{fontenc}\n"); //NOI18N
            fw.write("\\usepackage[ngerman]{babel}\n"); //NOI18N
            fw.write("\\parindent 0pt \n"); //NOI18N
            fw.write("\\pagestyle{empty}\n\n"); //NOI18N
            fw.write("\\begin{document}\n"); //NOI18N
            /**
             * Change the tabular column format string
             * if number aligning is not activated
             */
             if(!alignNumbersCheckbox.isSelected())
             {
                 tabularColFormatString = "ccccc";
             }
            /**
             * Write the main exercises
             */
            writeCol();
            writeCol();
            //Write the main footer
            fw.write("\\end{document}\n");
        }
        catch (IOException ex)
        {
            Logger.getLogger(JMathSheetGeneratorFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try
            {
                fw.close();
            }
            catch (IOException ex)
            {
                Logger.getLogger(JMathSheetGeneratorFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            catch (NullPointerException ex)
            {
                Logger.getLogger(JMathSheetGeneratorFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_okButtonMouseClicked

    private byte[] getUrandMTSeed()
    {
        try
        {
            File urandom = new File("/dev/urandom");
            if (!urandom.exists())
            {
                return null;
            }
            byte[] seed = new byte[2496];
            FileInputStream fin = new FileInputStream(urandom);
            fin.read(seed);
            fin.close();
            return seed;
        }
        catch (IOException ex)
        {
            Logger.getLogger(JMathSheetGeneratorFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null; //Never occurs
    }

    private int[] generateMTSeed()
    {
        //Seed; 624(*4) = max number of seed bytes
        final int mtSeedSize = 2496;
        byte[] seedBytes = null;
        seedBytes = getUrandMTSeed();
        if (seedBytes == null)
        {
            seedBytes = new byte[mtSeedSize];
            new SecureRandom().nextBytes(seedBytes);
        }
        return byteToInt(seedBytes);
    }

    private int[] generateMTSeed(SecureRandom randomSource)
    {
        //Seed; 624(*4) = max number of seed bytes
        final int mtSeedSize = 2496;
        byte[] seedBytes = null;
        seedBytes = getUrandMTSeed();
        if (seedBytes == null)
        {
            seedBytes = new byte[mtSeedSize];
            randomSource.nextBytes(seedBytes);
        }
        return byteToInt(seedBytes);
    }

    private static int[] byteToInt(byte[] input)
    {
        int[] array = new int[input.length / 4]; //sizeof(int) = 4 bytes
        for (int i = 0; i < array.length; i++)
        {
            array[i] = (int) input[4 * i];
            array[i] += (int) input[4 * i + 1] << 8;
            array[i] += (int) input[4 * i + 2] << 16;
            array[i] += (int) input[4 * i + 3] << 24;
        }
        return array;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        java.awt.EventQueue.invokeLater(new Runnable()
        {

            public void run()
            {
                new JMathSheetGeneratorFrame().setVisible(true);
            }
        });
    }
    private MersenneTwisterFast mt;
    private FileWriter fw;
    private ResourceBundle i18n = ResourceBundle.getBundle("jmathsheetgenerator/Bundle");
    private String tabularColFormatString = "rcrcc"; //LaTeX table format string
    private static final char[] stdOperators = {'+','-','*','/'};
    private static final char[] schoolOperators = {'+','-','\u06d4',':'};
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox alignNumbersCheckbox;
    private javax.swing.JCheckBox divCheckbox;
    private javax.swing.JCheckBox floatingPointLabel;
    private javax.swing.JTextField lineLengthField;
    private javax.swing.JLabel lineLengthLabel;
    private javax.swing.JSpinner linesPerColSpinner;
    private javax.swing.JLabel linesPerColumnLabel;
    private javax.swing.JCheckBox minusCheckbox;
    private javax.swing.JCheckBox multCheckbox;
    private javax.swing.JSpinner numberFromSpinner;
    private javax.swing.JPanel numberLimitsPanel1;
    private javax.swing.JSpinner numberToSpinner;
    private javax.swing.JLabel numbersFromLabel;
    private javax.swing.JLabel numbersFromLabel1;
    private javax.swing.JLabel numbersToLabel;
    private javax.swing.JLabel numbersToLabel1;
    private javax.swing.JButton okButton;
    private javax.swing.JLabel operatorLabel;
    private javax.swing.JCheckBox plusCheckbox;
    private javax.swing.JSpinner resultFromSpinner;
    private javax.swing.JPanel resultLimitsPanel;
    private javax.swing.JSpinner resultToSpinner;
    private javax.swing.JCheckBox schoolOperatorsCheckbox;
    // End of variables declaration//GEN-END:variables
}
