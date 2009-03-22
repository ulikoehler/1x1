/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jbmicalc;

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

        if (int_bmi <= hypo)
        {
            JOptionPane.showMessageDialog(null, "Mit einem BMI von " + int_bmi + " hast du Untergewicht.");
        }
        else if (int_bmi <= norm)
        {
            JOptionPane.showMessageDialog(null, "Mit einem BMI von " + int_bmi + " hast du Normalgewicht.");
        }
        else if (int_bmi <= hyper)
        {
            JOptionPane.showMessageDialog(null, "Mit einem BMI von " + int_bmi + " hast du Uebergewicht.");
        }
        else if (int_bmi <= adipositas)
        {
            JOptionPane.showMessageDialog(null, "Mit einem BMI von " + int_bmi + " hast du Adipositas.");
        }
        else if (int_bmi < death)
        {
            JOptionPane.showMessageDialog(null, "Mit einem BMI von " + int_bmi + " hast du schwere Adipositas.");
        }
        else if (int_bmi >= death)
        {
            JOptionPane.showMessageDialog(null, "Mit einem BMI von " + int_bmi + " bist du tot. Tooooooooot!!!!! :)");
        }
        else
        {
            JOptionPane.showMessageDialog(null, "BMI: " + int_bmi);
        }
    }
}
