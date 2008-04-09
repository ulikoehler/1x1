
import java.util.Vector;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

//Enums

/**
 *
 * @author User
 */
public class mtSettingsInterface
{
public Vector<Integer> rows = new Vector<Integer>();
public Vector<mtOperator> operators = new Vector<mtOperator>();
public boolean[] options = new boolean[1];

public mtSettingsInterface()
    {
        ///Set default options to avoid Nullpointer exceptions
        //Init options array
        options[0] = true;
        
        //Init rows vector
        rows.add(1);
        rows.add(2);
        rows.add(3);
        rows.add(4);
        rows.add(5);
        rows.add(6);
        rows.add(7);
        rows.add(8);
        rows.add(9);
        rows.add(10);
        
        //Init operators vector
        operators.add(mtOperator.MULT);
    }
}
