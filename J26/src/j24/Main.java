/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package j24;

import java.util.logging.Level;
import java.util.logging.Logger;
import net.sourceforge.jeval.EvaluationException;
import net.sourceforge.jeval.Evaluator;

/**
 *
 * @author uli
 */
public class Main {
    
    //Constants determinating the calculated range
    static final int limits = 9;
    static final int numCount = 4; //Do NOT change before implemented extended bracket algorithm
    static final int desiredResult = 24;

    static final char[] ops = {'+','-','*','/'}; //All operators available

    //Expression string builder
    static StringBuilder expBuilder = null;
    //Evaluator
    static Evaluator ev = new Evaluator();
    //Numbers iterated through
    static int[] nums = new int[numCount];
    //Operators iterated through
    static int[] opIdcs = new int[numCount - 1]; //Saves the ops indices of the operators currently used in the loop

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        try
            {

                for(int i = 0; i < nums.length - 1;i++) //Fill nums and opIdcs with 0s
                {
                    nums[i] = 0;
                    opIdcs[i] = 0;
                }
                nums[nums.length - 1] = 0; //ArrayIndexOutOfBoundsException if inside loop

                //Operator loop
                for(int opI = opIdcs.length - 1; opI >= 0; opI--)
                    {
                        //Number loop
                        for(int numPerms = (int) Math.pow(limits,numCount);numPerms > 0;numPerms--)
                            {
                                //Bracket loop (unrolled)
                                    //(x x) (x x)
                                        expBuilder = new StringBuilder();
                                        expBuilder.append('(');
                                        expBuilder.append(nums[0]);
                                        expBuilder.append(ops[opIdcs[0]]);
                                        expBuilder.append(nums[1]);
                                        expBuilder.append(')');
                                        expBuilder.append(ops[opIdcs[1]]);
                                        expBuilder.append('(');
                                        expBuilder.append(nums[2]);
                                        expBuilder.append(ops[opIdcs[2]]);
                                        expBuilder.append(nums[3]);
                                        expBuilder.append(')');
                                        double res = ev.getNumberResult(expBuilder.toString());
                                        if(res == 24.0) {System.out.println(expBuilder.toString() + "=" + res);}
                                    //x ([x x] x)
                                        expBuilder = new StringBuilder();
                                        expBuilder.append(nums[0]);
                                        expBuilder.append(ops[opIdcs[0]]);
                                        expBuilder.append("((");
                                        expBuilder.append(nums[1]);
                                        expBuilder.append(ops[opIdcs[1]]);
                                        expBuilder.append(nums[2]);
                                        expBuilder.append(')');
                                        expBuilder.append(ops[opIdcs[2]]);
                                        expBuilder.append(nums[3]);
                                        expBuilder.append(')');
                                        res = ev.getNumberResult(expBuilder.toString());
                                        if(res == 24.0) {System.out.println(expBuilder.toString() + "=" + res);}
                                    //x (x [x x])
                                        expBuilder = new StringBuilder();
                                        expBuilder.append(nums[0]);
                                        expBuilder.append(ops[opIdcs[0]]);
                                        expBuilder.append('(');
                                        expBuilder.append(nums[1]);
                                        expBuilder.append(ops[opIdcs[1]]);
                                        expBuilder.append("(");
                                        expBuilder.append(nums[2]);
                                        expBuilder.append(ops[opIdcs[2]]);
                                        expBuilder.append(nums[3]);
                                        expBuilder.append("))");
                                        res = ev.getNumberResult(expBuilder.toString());
                                        if(res == 24.0) {System.out.println(expBuilder.toString() + "=" + res);}
                                    //([x x] x) x
                                        expBuilder = new StringBuilder();
                                        expBuilder.append("((");
                                        expBuilder.append(nums[0]);
                                        expBuilder.append(ops[opIdcs[0]]);
                                        expBuilder.append(nums[1]);
                                        expBuilder.append(')');
                                        expBuilder.append(ops[opIdcs[1]]);
                                        expBuilder.append(nums[2]);
                                        expBuilder.append(')');
                                        expBuilder.append(ops[opIdcs[2]]);
                                        expBuilder.append(nums[3]);
                                        res = ev.getNumberResult(expBuilder.toString());
                                        if(res == 24.0) {System.out.println(expBuilder.toString() + "=" + res);}
                                    //(x [x x]) x
                                        expBuilder = new StringBuilder();
                                        expBuilder.append('(');
                                        expBuilder.append(nums[0]);
                                        expBuilder.append(ops[opIdcs[0]]);
                                        expBuilder.append('(');
                                        expBuilder.append(nums[1]);
                                        expBuilder.append(ops[opIdcs[1]]);
                                        expBuilder.append(nums[2]);
                                        expBuilder.append("))");
                                        expBuilder.append(ops[opIdcs[2]]);
                                        expBuilder.append(nums[3]);
                                        res = ev.getNumberResult(expBuilder.toString());
                                        if(res == 24.0) {System.out.println(expBuilder.toString() + "=" + res);}
                                    //x (x x x)
                                        expBuilder = new StringBuilder();
                                        expBuilder.append(nums[0]);
                                        expBuilder.append(ops[opIdcs[0]]);
                                        expBuilder.append('(');
                                        expBuilder.append(nums[1]);
                                        expBuilder.append(ops[opIdcs[1]]);
                                        expBuilder.append(nums[2]);
                                        expBuilder.append(ops[opIdcs[2]]);
                                        expBuilder.append(nums[3]);
                                        expBuilder.append(')');
                                        res = ev.getNumberResult(expBuilder.toString());
                                        if(res == 24.0) {System.out.println(expBuilder.toString() + "=" + res);}
                                    //(x x x) x
                                        expBuilder = new StringBuilder();
                                        expBuilder.append('(');
                                        expBuilder.append(nums[0]);
                                        expBuilder.append(ops[opIdcs[0]]);
                                        expBuilder.append(nums[1]);
                                        expBuilder.append(ops[opIdcs[1]]);
                                        expBuilder.append(nums[2]);
                                        expBuilder.append(')');
                                        expBuilder.append(ops[opIdcs[2]]);
                                        expBuilder.append(nums[3]);
                                        res = ev.getNumberResult(expBuilder.toString());
                                        if(res == 24.0) {System.out.println(expBuilder.toString() + "=" + res);}
                                    //x x (x x)
                                        expBuilder = new StringBuilder();
                                        expBuilder.append(nums[0]);
                                        expBuilder.append(ops[opIdcs[0]]);
                                        expBuilder.append(nums[1]);
                                        expBuilder.append(ops[opIdcs[1]]);
                                        expBuilder.append('(');
                                        expBuilder.append(nums[2]);
                                        expBuilder.append(ops[opIdcs[2]]);
                                        expBuilder.append(nums[3]);
                                        expBuilder.append(')');
                                        res = ev.getNumberResult(expBuilder.toString());
                                        if(res == 24.0) {System.out.println(expBuilder.toString() + "=" + res);}
                                    //x (x x) x
                                        expBuilder = new StringBuilder();
                                        expBuilder.append(nums[0]);
                                        expBuilder.append(ops[opIdcs[0]]);
                                        expBuilder.append('(');
                                        expBuilder.append(nums[1]);
                                        expBuilder.append(ops[opIdcs[1]]);
                                        expBuilder.append(nums[2]);
                                        expBuilder.append(')');
                                        expBuilder.append(ops[opIdcs[2]]);
                                        expBuilder.append(nums[3]);
                                        res = ev.getNumberResult(expBuilder.toString());
                                        if(res == 24.0) {System.out.println(expBuilder.toString() + "=" + res);}
                                    //(x x) x x
                                        expBuilder = new StringBuilder();
                                        expBuilder.append('(');
                                        expBuilder.append(nums[0]);
                                        expBuilder.append(ops[opIdcs[0]]);
                                        expBuilder.append(nums[1]);
                                        expBuilder.append(')');
                                        expBuilder.append(ops[opIdcs[1]]);
                                        expBuilder.append(nums[2]);
                                        expBuilder.append(ops[opIdcs[2]]);
                                        expBuilder.append(nums[3]);
                                        res = ev.getNumberResult(expBuilder.toString());
                                        if(res == 24.0) {System.out.println(expBuilder.toString() + "=" + res);}
                                    //x x x x
                                        expBuilder = new StringBuilder();
                                        expBuilder.append(nums[0]);
                                        expBuilder.append(ops[opIdcs[0]]);
                                        expBuilder.append(nums[1]);
                                        expBuilder.append(ops[opIdcs[1]]);
                                        expBuilder.append(nums[2]);
                                        expBuilder.append(ops[opIdcs[2]]);
                                        expBuilder.append(nums[3]);
                                        res = ev.getNumberResult(expBuilder.toString());
                                        if(res == 24.0) {System.out.println(expBuilder.toString() + "=" + res);}

                        //Increment first number with overflow handling
                            for(int i = 0;i < numCount-1;i++)
                            {
                                nums[i] = (nums[i] + 1) % limits;
                                if(nums[i] != 0) {break;}
                            }
                        }

                        //Increment last operator index with overflow handling
                        for(int i = 0;i < numCount-2;i++)
                        {
                            opIdcs[i] = (opIdcs[i] + 1) % 4;
                            if(opIdcs[i] != 0) {break;}
                        }
                    }

                        
            }
        catch (EvaluationException ex)
            {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

}
