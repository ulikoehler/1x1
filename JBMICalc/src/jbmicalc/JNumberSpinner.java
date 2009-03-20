package jbmicalc;

/*
* JNumberSpinner.java
* A spinner with easier number access
* Created on 28.02.2009, 19:22:18
*/

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

/**
*
* @author uli
*/
public class JNumberSpinner extends JSpinner
{

    /** Creates new form BeanForm */
    public JNumberSpinner()
    {
        
    }

    public int getIntValue()
    {
        return ((SpinnerNumberModel) getModel()).getNumber().intValue();
    }

    public long getLongValue()
    {
        return ((SpinnerNumberModel) getModel()).getNumber().longValue();
    }

    public float getFloatValue()
    {
        return ((SpinnerNumberModel) getModel()).getNumber().floatValue();
    }

    public double getDoubleValue()
    {
        return ((SpinnerNumberModel) getModel()).getNumber().doubleValue();
    }

}