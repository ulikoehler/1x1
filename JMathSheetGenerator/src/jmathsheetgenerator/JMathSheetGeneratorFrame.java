/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 *
 * NOTE: 43 lines per page without title, 33 with title ==> PageCount - 1
 */

/*
 * JMathSheetGeneratorFrame.java
 *
 * Created on 03.12.2008, 16:28:46
 */
package jmathsheetgenerator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Properties;
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
        //Read the config file
        readConfigFile();
    }

    private void writeConfigFile()
    {
        try
        {
            //Make sure the config file exists
            if (!configFile.exists())
            {
                configFile.createNewFile();
            }

            Properties props = new Properties();
            //Numerical properties
            props.setProperty("linesPerCol", Integer.toString(getLinesPerCol()));
            props.setProperty("pageCount", Integer.toString(getPageCount()));
            props.setProperty("numberFrom", Integer.toString(getNumberFrom()));
            props.setProperty("numberTo", Integer.toString(getNumberTo()));
            props.setProperty("resultFrom", Integer.toString(getResultFrom()));
            props.setProperty("resultTo", Integer.toString(getResultTo()));
            //Operator checkboxes
            props.setProperty("plusEnabled", Boolean.toString(plusCheckbox.isSelected()));
            props.setProperty("minusEnabled", Boolean.toString(minusCheckbox.isSelected()));
            props.setProperty("multEnabled", Boolean.toString(multCheckbox.isSelected()));
            props.setProperty("divEnabled", Boolean.toString(divCheckbox.isSelected()));
            //Options checkboxes
            props.setProperty("realResults", Boolean.toString(divCheckbox.isSelected()));
            props.setProperty("alignNumbers", Boolean.toString(divCheckbox.isSelected()));
            props.setProperty("schoolOperators", Boolean.toString(divCheckbox.isSelected()));
            //Text fields
            props.setProperty("title", titleField.getText());
            props.setProperty("lineLength", lineLengthField.getText());
            //Other
            File fcSelectedFile = fc.getSelectedFile();
            if (fcSelectedFile != null)
            {
                props.setProperty("fileChooserSelected", fcSelectedFile.getAbsolutePath());
            }
            //Write the properties to the config file
            props.store(new FileWriter(configFile), "JMathSheetGenerator options");
        }
        catch (IOException ex)
        {
            Logger.getLogger(JMathSheetGeneratorFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void readConfigFile()
    {
        /**
         * Try to load settings from the config file
         */
        if (configFile.exists())
        {
            try
            {
                Properties props = new Properties();
                props.load(new FileReader(configFile));
                //Exercises per line
                setLinesPerCol(new Integer(props.getProperty("linesPerCol")));
                setPageCount(new Integer(props.getProperty("pageCount")));
                setNumberFrom(new Integer(props.getProperty("numberFrom")));
                setNumberTo(new Integer(props.getProperty("numberTo")));
                setResultFrom(new Integer(props.getProperty("resultFrom")));
                setResultTo(new Integer(props.getProperty("resultTo")));
                //Operator checkboxes
                plusCheckbox.setSelected(props.getProperty("plusEnabled").equals("true"));
                minusCheckbox.setSelected(props.getProperty("minusEnabled").equals("true"));
                multCheckbox.setSelected(props.getProperty("multEnabled").equals("true"));
                divCheckbox.setSelected(props.getProperty("divEnabled").equals("true"));
                //Options checkboxes
                realResultsCheckbox.setSelected(props.getProperty("realResults").equals("true"));
                alignNumbersCheckbox.setSelected(props.getProperty("alignNumbers").equals("true"));
                schoolOperatorsCheckbox.setSelected(props.getProperty("schoolOperators").equals("true"));
                //Text fields
                titleField.setText(props.getProperty("title"));
                lineLengthField.setText(props.getProperty("lineLength"));
                //Other
                fc.setSelectedFile(new File(props.getProperty("fileChooserSelected")));
            }
            catch (NumberFormatException ex)
            {
            }
            catch (IOException ex)
            {
                Logger.getLogger(JMathSheetGeneratorFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * This method is called from within the constructor to
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
        realResultsCheckbox = new javax.swing.JCheckBox();
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
        operandLimitsPanel = new javax.swing.JPanel();
        numbersFromLabel1 = new javax.swing.JLabel();
        numberFromSpinner = new javax.swing.JSpinner();
        numbersToLabel1 = new javax.swing.JLabel();
        numberToSpinner = new javax.swing.JSpinner();
        lineLengthField = new javax.swing.JTextField();
        schoolOperatorsCheckbox = new javax.swing.JCheckBox();
        pageCountLabel = new javax.swing.JLabel();
        pageCountSpinner = new javax.swing.JSpinner();
        titleLabel = new javax.swing.JLabel();
        titleField = new javax.swing.JTextField();
        pageNumberingCheckbox = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle(i18n.getString("JMathSheetGeneratorFrame.title")); // NOI18N
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

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

        realResultsCheckbox.setText(i18n.getString("JMathSheetGeneratorFrame.realResultsCheckbox.text")); // NOI18N
        realResultsCheckbox.setToolTipText(i18n.getString("JMathSheetGeneratorFrame.realResultsCheckbox.toolTipText")); // NOI18N

        lineLengthLabel.setText(i18n.getString("JMathSheetGeneratorFrame.lineLengthLabel.text")); // NOI18N

        plusCheckbox.setSelected(true);
        plusCheckbox.setText("+"); // NOI18N
        plusCheckbox.setToolTipText( i18n.getString("JMathSheetGeneratorFrame.plusCheckbox.toolTipText")); // NOI18N

        minusCheckbox.setSelected(true);
        minusCheckbox.setText("-"); // NOI18N
        minusCheckbox.setToolTipText( i18n.getString("JMathSheetGeneratorFrame.minusCheckbox.toolTipText")); // NOI18N

        multCheckbox.setText("<html>&middot"); // NOI18N
        multCheckbox.setToolTipText( i18n.getString("JMathSheetGeneratorFrame.multCheckbox.toolTipText")); // NOI18N

        divCheckbox.setText(":"); // NOI18N
        divCheckbox.setToolTipText( i18n.getString("JMathSheetGeneratorFrame.divCheckbox.toolTipText")); // NOI18N

        resultLimitsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(i18n.getString("JMathSheetGeneratorFrame.resultLimitsPanel.border.title"))); // NOI18N

        numbersFromLabel.setText(i18n.getString("JMathSheetGeneratorFrame.numbersFromLabel.text")); // NOI18N

        resultFromSpinner.setModel(new javax.swing.SpinnerNumberModel());

        numbersToLabel.setText(i18n.getString("JMathSheetGeneratorFrame.numbersToLabel.text")); // NOI18N

        resultToSpinner.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(100), null, null, Integer.valueOf(1)));

        javax.swing.GroupLayout resultLimitsPanelLayout = new javax.swing.GroupLayout(resultLimitsPanel);
        resultLimitsPanel.setLayout(resultLimitsPanelLayout);
        resultLimitsPanelLayout.setHorizontalGroup(
            resultLimitsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, resultLimitsPanelLayout.createSequentialGroup()
                .addContainerGap(69, Short.MAX_VALUE)
                .addComponent(numbersFromLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(resultFromSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(numbersToLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(resultToSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        resultLimitsPanelLayout.setVerticalGroup(
            resultLimitsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(resultLimitsPanelLayout.createSequentialGroup()
                .addGroup(resultLimitsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(resultToSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(numbersToLabel)
                    .addComponent(resultFromSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(numbersFromLabel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        operandLimitsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(i18n.getString("JMathSheetGeneratorFrame.operandLimitsPanel.border.title"))); // NOI18N

        numbersFromLabel1.setText(i18n.getString("JMathSheetGeneratorFrame.numbersFromLabel1.text")); // NOI18N

        numberFromSpinner.setModel(new javax.swing.SpinnerNumberModel());

        numbersToLabel1.setText(i18n.getString("JMathSheetGeneratorFrame.numbersToLabel1.text")); // NOI18N

        numberToSpinner.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(100), null, null, Integer.valueOf(1)));

        javax.swing.GroupLayout operandLimitsPanelLayout = new javax.swing.GroupLayout(operandLimitsPanel);
        operandLimitsPanel.setLayout(operandLimitsPanelLayout);
        operandLimitsPanelLayout.setHorizontalGroup(
            operandLimitsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, operandLimitsPanelLayout.createSequentialGroup()
                .addContainerGap(69, Short.MAX_VALUE)
                .addComponent(numbersFromLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(numberFromSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(numbersToLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(numberToSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        operandLimitsPanelLayout.setVerticalGroup(
            operandLimitsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(operandLimitsPanelLayout.createSequentialGroup()
                .addGroup(operandLimitsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(numberToSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(numbersToLabel1)
                    .addComponent(numberFromSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(numbersFromLabel1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lineLengthField.setText( i18n.getString("JMathSheetGeneratorFrame.lineLengthField.text")); // NOI18N
        lineLengthField.setToolTipText( i18n.getString("JMathSheetGeneratorFrame.lineLengthField.toolTipText")); // NOI18N

        schoolOperatorsCheckbox.setSelected(true);
        schoolOperatorsCheckbox.setText( i18n.getString("JMathSheetGeneratorFrame.schoolOperatorsCheckbox.text")); // NOI18N
        schoolOperatorsCheckbox.setToolTipText( i18n.getString("JMathSheetGeneratorFrame.schoolOperatorsCheckbox.toolTipText")); // NOI18N

        pageCountLabel.setText( i18n.getString("JMathSheetGeneratorFrame.pageCountLabel.text")); // NOI18N

        pageCountSpinner.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));

        titleLabel.setText( i18n.getString("JMathSheetGeneratorFrame.titleLabel.text")); // NOI18N

        titleField.setText( i18n.getString("JMathSheetGeneratorFrame.titleField.text")); // NOI18N

        pageNumberingCheckbox.setText( i18n.getString("JMathSheetGeneratorFrame.pageNumberingCheckbox.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(operandLimitsPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(operatorLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 89, Short.MAX_VALUE)
                        .addComponent(plusCheckbox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(minusCheckbox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(multCheckbox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(divCheckbox)
                        .addGap(26, 26, 26))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lineLengthLabel)
                            .addComponent(linesPerColumnLabel)
                            .addComponent(pageCountLabel)
                            .addComponent(titleLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(titleField, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                            .addComponent(linesPerColSpinner, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                            .addComponent(pageCountSpinner, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                            .addComponent(lineLengthField, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)))
                    .addComponent(resultLimitsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(okButton, javax.swing.GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE)
                    .addComponent(schoolOperatorsCheckbox)
                    .addComponent(realResultsCheckbox)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(alignNumbersCheckbox)
                        .addGap(18, 18, 18)
                        .addComponent(pageNumberingCheckbox)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(titleLabel)
                    .addComponent(titleField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(linesPerColSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(linesPerColumnLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pageCountLabel)
                    .addComponent(pageCountSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(plusCheckbox)
                    .addComponent(operatorLabel)
                    .addComponent(minusCheckbox)
                    .addComponent(multCheckbox)
                    .addComponent(divCheckbox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lineLengthLabel)
                    .addComponent(lineLengthField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(operandLimitsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(resultLimitsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(realResultsCheckbox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(alignNumbersCheckbox)
                    .addComponent(pageNumberingCheckbox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(schoolOperatorsCheckbox)
                .addGap(6, 6, 6)
                .addComponent(okButton)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private int getLinesPerCol()
    {
        SpinnerNumberModel sm = (SpinnerNumberModel) linesPerColSpinner.getModel();
        return sm.getNumber().intValue();
    }

    private void setLinesPerCol(int val)
    {
        SpinnerNumberModel sm = (SpinnerNumberModel) linesPerColSpinner.getModel();
        sm.setValue(val);
    }

    private int getPageCount()
    {
        SpinnerNumberModel sm = (SpinnerNumberModel) pageCountSpinner.getModel();
        return sm.getNumber().intValue();
    }

    private void setPageCount(int val)
    {
        SpinnerNumberModel sm = (SpinnerNumberModel) pageCountSpinner.getModel();
        sm.setValue(val);
    }

    private int getNumberFrom()
    {
        SpinnerNumberModel sm = (SpinnerNumberModel) numberFromSpinner.getModel();
        return sm.getNumber().intValue();
    }

    private void setNumberFrom(int val)
    {
        SpinnerNumberModel sm = (SpinnerNumberModel) numberFromSpinner.getModel();
        sm.setValue(val);
    }

    private int getNumberTo()
    {
        SpinnerNumberModel sm = (SpinnerNumberModel) numberToSpinner.getModel();
        return sm.getNumber().intValue();
    }

    private void setNumberTo(int val)
    {
        SpinnerNumberModel sm = (SpinnerNumberModel) numberToSpinner.getModel();
        sm.setValue(val);
    }

    private int getResultFrom()
    {
        SpinnerNumberModel sm = (SpinnerNumberModel) resultFromSpinner.getModel();
        return sm.getNumber().intValue();
    }

    private void setResultFrom(int val)
    {
        SpinnerNumberModel sm = (SpinnerNumberModel) resultFromSpinner.getModel();
        sm.setValue(val);
    }

    private int getResultTo()
    {
        SpinnerNumberModel sm = (SpinnerNumberModel) resultToSpinner.getModel();
        return sm.getNumber().intValue();
    }

    private void setResultTo(int val)
    {
        SpinnerNumberModel sm = (SpinnerNumberModel) resultToSpinner.getModel();
        sm.setValue(val);
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

    private void writeCol(int exercises)
    {
        try
        {
            fw.write("\\begin{tabular}{" + tabularColFormatString + "}\n"); //NOI18N
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
            char op; //Used only for switching
            String opString = "";
            //Declare a reference to the operators array
            String opArray[] = stdOperators;
            if (schoolOperatorsCheckbox.isSelected())
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
                            opString = opArray[0];
                            break;
                        }
                        case '-':
                        {
                            result = nums[0] - nums[1];
                            opString = opArray[1];
                            break;
                        }
                        case '*':
                        {
                            result = nums[0] * nums[1];
                            opString = opArray[2];
                            break;
                        }
                        case '/':
                        {
                            if (nums[1] == 0)
                            {
                                continue;
                            }
                            result = nums[0] / nums[1];
                            opString = opArray[3];
                            break;
                        }
                        default:
                            result = 0;
                    }
                }
                while (!(inRange(result, resultLowerLimit, resultUpperLimit)));
                //Write the exercise line
                fw.write("" + Integer.toString(nums[0]) + " & " + opString + " & " +
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
            fc.showSaveDialog(this);
            File selectedFile = fc.getSelectedFile();
            if (selectedFile == null) //User aborted the save dialog
            {
                return;
            }
            fw = new FileWriter(selectedFile);
            //Write the header
            fw.write("\\documentclass[a4paper,twocolumn]{scrartcl}\n"); //NOI18N
            fw.write("\\usepackage[utf8]{inputenc}\n"); //NOI18N
            fw.write("\\usepackage[T1]{fontenc}\n"); //NOI18N
            fw.write("\\usepackage[ngerman]{babel}\n"); //NOI18N
            fw.write("\\parindent 0pt \n"); //NOI18N
            //Write the title if the title is not empty
            String title = titleField.getText();
            if (!title.isEmpty())
            {
                fw.write("\\title{" + title + "}\n"); //NOI18N
                fw.write("\\date{}\n"); //NOI18N
                fw.write("\\author{}\n"); //NOI18N
            }
            if (!pageNumberingCheckbox.isSelected())
            {
                fw.write("\\pagestyle{empty}\n\n");
            } //NOI18N

            fw.write("\\begin{document}\n"); //NOI18N
            //Write the title page if the title is not empty
            if (!title.isEmpty())
            {
                fw.write("\\maketitle\n");
                if (!pageNumberingCheckbox.isSelected())
                {
                    fw.write("\\thispagestyle{empty}\n");
                }
            }
            /**
             * Change the tabular column format string
             * if number aligning is not activated
             */
            if (!alignNumbersCheckbox.isSelected())
            {
                tabularColFormatString = "ccccc"; //NOI18N
            }
            //Main page writing loop
            int pageCount = getPageCount();
            int linesPerCol = getLinesPerCol();
            for (int i = getPageCount(); i > 0; i--)
            {
                //Write the two columns
                //Title page: 10 lines less
                if (i == pageCount && !title.isEmpty())
                {
                    writeCol(linesPerCol - 10);
                    writeCol(linesPerCol - 10);
                }
                else
                {
                    writeCol(linesPerCol);
                    writeCol(linesPerCol);
                }
                if (i > 1)
                {
                    fw.write("\\newpage\n");
                }
            }
            //Write the main footer
            fw.write("\\end{document}\n");
        }
        catch (NullPointerException ex) //Occurs if the user aborted the file selection dialog
        {
            Logger.getLogger(JMathSheetGeneratorFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IOException ex)
        {
            Logger.getLogger(JMathSheetGeneratorFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try
            {
                if (fw != null)
                {
                    fw.close();
                }
            }
            catch (NullPointerException ex)
            {
                Logger.getLogger(JMathSheetGeneratorFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            catch (IOException ex)
            {
                Logger.getLogger(JMathSheetGeneratorFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_okButtonMouseClicked

    private void formWindowClosing(java.awt.event.WindowEvent evt)//GEN-FIRST:event_formWindowClosing
    {//GEN-HEADEREND:event_formWindowClosing
        //Write the config file
        writeConfigFile();
    }//GEN-LAST:event_formWindowClosing

    private byte[] getUrandMTSeed()
    {
        try
        {
            File urandom = new File("/dev/urandom"); //NOI18N
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
        return generateMTSeed(new SecureRandom());
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
    private JFileChooser fc = new JFileChooser();
    private MersenneTwisterFast mt;
    private FileWriter fw;
    private ResourceBundle i18n = ResourceBundle.getBundle("jmathsheetgenerator/Bundle"); //NOI18N
    private String tabularColFormatString = "rcrcc"; //LaTeX table format string //NOI18N
    private final File configFile = new File(".jmsgen"); //NOI18N
    private static final String[] stdOperators =
    {
        "+", "-", "*", "/"
    };
    private static final String[] schoolOperators =
    {
        "+", "-", "$\\cdot$", ":"
    };
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox alignNumbersCheckbox;
    private javax.swing.JCheckBox divCheckbox;
    private javax.swing.JTextField lineLengthField;
    private javax.swing.JLabel lineLengthLabel;
    private javax.swing.JSpinner linesPerColSpinner;
    private javax.swing.JLabel linesPerColumnLabel;
    private javax.swing.JCheckBox minusCheckbox;
    private javax.swing.JCheckBox multCheckbox;
    private javax.swing.JSpinner numberFromSpinner;
    private javax.swing.JSpinner numberToSpinner;
    private javax.swing.JLabel numbersFromLabel;
    private javax.swing.JLabel numbersFromLabel1;
    private javax.swing.JLabel numbersToLabel;
    private javax.swing.JLabel numbersToLabel1;
    private javax.swing.JButton okButton;
    private javax.swing.JPanel operandLimitsPanel;
    private javax.swing.JLabel operatorLabel;
    private javax.swing.JLabel pageCountLabel;
    private javax.swing.JSpinner pageCountSpinner;
    private javax.swing.JCheckBox pageNumberingCheckbox;
    private javax.swing.JCheckBox plusCheckbox;
    private javax.swing.JCheckBox realResultsCheckbox;
    private javax.swing.JSpinner resultFromSpinner;
    private javax.swing.JPanel resultLimitsPanel;
    private javax.swing.JSpinner resultToSpinner;
    private javax.swing.JCheckBox schoolOperatorsCheckbox;
    private javax.swing.JTextField titleField;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration//GEN-END:variables
}
