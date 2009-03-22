/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jbmicalc;

import java.text.DecimalFormat;
import javax.swing.JOptionPane;

/**
 *
 * @author uli
 */
public class BMIAlgo
{
    // instance variables - replace the example below with your own

    private static final int hypo = 19;
    private static final int norm = 24;
    private static final int hyper = 30;
    private static final int adipositas = 40;
    private static final int death = 1000;

    public static void calcBMI(double mass, double in_meter)
    {
        double bmi = mass / (in_meter * in_meter);
        int int_bmi = (int) Math.round(bmi);

        DecimalFormat bmiFormat = new DecimalFormat("####0.0");

        if (int_bmi <= hypo)
        {
            JOptionPane.showMessageDialog(null, "Mit einem BMI von " + bmiFormat.format(bmi) + " hast du Untergewicht.");
        }
        else if (int_bmi <= norm)
        {
            JOptionPane.showMessageDialog(null, "<html><p color=\"#008739\">Mit einem BMI von " + bmiFormat.format(bmi) + " hast du Normalgewicht.</p>");
        }
        else if (int_bmi <= hyper)
        {
            JOptionPane.showMessageDialog(null, "<html><p color=\"#de9424\">Mit einem BMI von " + bmiFormat.format(bmi) + " hast du Übergewicht.");
        }
        else if (int_bmi <= adipositas)
        {
            JOptionPane.showMessageDialog(null, "<html><p color=\"#008739\">Mit einem BMI von " + bmiFormat.format(bmi) + " hast du Adipositas.");
        }
        else if (int_bmi < death)
        {
            JOptionPane.showMessageDialog(null, "Mit einem BMI von " + bmiFormat.format(bmi) + " hast du schwere Adipositas.");
        }
        else if (int_bmi >= death)
        {
            JOptionPane.showMessageDialog(null, "<html><p color=\"#FF0000\">Mit einem BMI von " + bmiFormat.format(bmi) + " bist du tot. Tooooooooot!!!!! :)</p>");
        }
        else
        {
            JOptionPane.showMessageDialog(null, "BMI: " + int_bmi);
        }
    }
}
