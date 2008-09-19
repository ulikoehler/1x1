/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package j24;

import java.io.FileWriter;
import java.io.IOException;
import net.sourceforge.jeval.EvaluationException;
import net.sourceforge.jeval.Evaluator;

/**
 *
 * @author uli
 */
public class Main {
    
    //Constants determinating the calculated range
    static final int upperLimit = 9;
    static final int lowerLimit = 1;
    static final int numCount = 4; //Do NOT change before implemented extended bracket algorithm
    static final int desiredResult = 24;
    
    //Switches
    static final boolean enableOneModulus = false;
    static final boolean enableTwoModuli = false;
    static final boolean strictModulus = true; //Filter modulus results where the modulus is greater than the main number
    static final boolean enableStd = true;

    static final char[] ops = {'+','-','*','/'}; //All operators available

    //Expression string builder
    static StringBuilder expBuilder = new StringBuilder();
    //Evaluator
    static Evaluator ev = new Evaluator();
    //Numbers iterated through
    static int[] nums = new int[numCount];
    //Operators iterated through
    static int[] opIds = new int[numCount - 1]; //Saves the ops indices of the operators currently used in the loop
    
    static double res;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        {
            //ArrayIndexOutOfBoundsException if inside loop
            //Init file output
            FileWriter fw = null;
            try
            {
                for (int i = 0; i < nums.length - 1; i++)
                {
                    nums[i] = lowerLimit;
                    opIds[i] = 0;
                }
                
                nums[nums.length - 1] = 0;
                fw = new FileWriter("/dev/shm/24.txt");
                
                //Init statistics variables
                int stdCorrectResults = 0;
                int stdAllResults = 0;
                
                int mod1CorrectResults = 0;
                int mod1AllResults = 0;
                
                int mod2CorrectResults = 0;
                int mod2AllResults = 0;

                //Operator loop
                for (int opI = 0; opI <= 4*opIds.length - 1; opI++)
                {
                    //Number loop
                    // <editor-fold>
                    for (int numPerms = 0; numPerms < (int) Math.pow(upperLimit - lowerLimit,numCount); numPerms++)
                    {
                        ///////////////
                        //Test the standard arithmetics
                        //////////////
                        //<editor-fold>
                        if(enableStd)
                        {
                        //(x x) (x x)
                        if(
                            !(opIds[0] == 0 && opIds[1] == 0 && opIds[2] == 0) // + + +
                            && !(opIds[0] == 2 && opIds[1] == 2 && opIds[2] == 2) // * * *
                            )
                        {
                            expBuilder = new StringBuilder();
                            expBuilder.append('(');
                            expBuilder.append(nums[0]);
                            expBuilder.append(ops[opIds[0]]);
                            expBuilder.append(nums[1]);
                            expBuilder.append(')');
                            expBuilder.append(ops[opIds[1]]);
                            expBuilder.append('(');
                            expBuilder.append(nums[2]);
                            expBuilder.append(ops[opIds[2]]);
                            expBuilder.append(nums[3]);
                            expBuilder.append(')');
                            res = ev.getNumberResult(expBuilder.toString());
                            if (res == desiredResult)
                            {
                                fw.write(expBuilder.toString() + "=" + (int)res + "\n");
                                stdCorrectResults++;
                            }
                            stdAllResults++;
                        }
                        //x ([x x] x)
                        if(
                                !(opIds[0] == 0 && opIds[1] == 0 && opIds[2] == 0) // + + +
                                && !(opIds[0] == 2 && opIds[1] == 2 && opIds[2] == 2) // * * *
                                )
                        {
                            expBuilder = new StringBuilder();
                            expBuilder.append(nums[0]);
                            expBuilder.append(ops[opIds[0]]);
                            expBuilder.append("((");
                            expBuilder.append(nums[1]);
                            expBuilder.append(ops[opIds[1]]);
                            expBuilder.append(nums[2]);
                            expBuilder.append(')');
                            expBuilder.append(ops[opIds[2]]);
                            expBuilder.append(nums[3]);
                            expBuilder.append(')');
                            res = ev.getNumberResult(expBuilder.toString());
                            if (res == desiredResult)
                            {
                                fw.write(expBuilder.toString() + "=" + (int)res + "\n");
                                stdCorrectResults++;
                            }
                            stdAllResults++;
                        }
                        //x (x [x x])
                        if(
                                !(opIds[0] == 0 && opIds[1] == 0 && opIds[2] == 0) // + + +
                                && !(opIds[0] == 2 && opIds[1] == 2 && opIds[2] == 2) // * * *
                                )
                        {
                            expBuilder = new StringBuilder();
                            expBuilder.append(nums[0]);
                            expBuilder.append(ops[opIds[0]]);
                            expBuilder.append('(');
                            expBuilder.append(nums[1]);
                            expBuilder.append(ops[opIds[1]]);
                            expBuilder.append("(");
                            expBuilder.append(nums[2]);
                            expBuilder.append(ops[opIds[2]]);
                            expBuilder.append(nums[3]);
                            expBuilder.append("))");
                            res = ev.getNumberResult(expBuilder.toString());
                            if (res == desiredResult)
                            {
                                fw.write(expBuilder.toString() + "=" + (int)res + "\n");
                                stdCorrectResults++;
                            }
                            stdAllResults++;
                        }
                        //([x x] x) x
                        if(
                                !(opIds[0] == 0 && opIds[1] == 0 && opIds[2] == 0) // + + +
                                && !(opIds[0] == 2 && opIds[1] == 2 && opIds[2] == 2) // * * *
                                )
                        {
                            expBuilder = new StringBuilder();   
                            expBuilder.append("((");
                            expBuilder.append(nums[0]);
                            expBuilder.append(ops[opIds[0]]);
                            expBuilder.append(nums[1]);
                            expBuilder.append(')');
                            expBuilder.append(ops[opIds[1]]);
                            expBuilder.append(nums[2]);
                            expBuilder.append(')');
                            expBuilder.append(ops[opIds[2]]);
                            expBuilder.append(nums[3]);
                            res = ev.getNumberResult(expBuilder.toString());
                            if (res == desiredResult)
                            {
                                fw.write(expBuilder.toString() + "=" + (int)res + "\n");
                                stdCorrectResults++;
                            }
                            stdAllResults++;
                        }
                        //(x [x x]) x
                        if(
                                !(opIds[0] == 0 && opIds[1] == 0 && opIds[2] == 0) // + + +
                                && !(opIds[0] == 2 && opIds[1] == 2 && opIds[2] == 2) // * * *
                                )
                        {
                            expBuilder = new StringBuilder();
                            expBuilder.append('(');
                            expBuilder.append(nums[0]);
                            expBuilder.append(ops[opIds[0]]);
                            expBuilder.append('(');
                            expBuilder.append(nums[1]);
                            expBuilder.append(ops[opIds[1]]);
                            expBuilder.append(nums[2]);
                            expBuilder.append("))");
                            expBuilder.append(ops[opIds[2]]);
                            expBuilder.append(nums[3]);
                            res = ev.getNumberResult(expBuilder.toString());
                            if (res == desiredResult)
                            {
                                fw.write(expBuilder.toString() + "=" + (int)res + "\n");
                                stdCorrectResults++;
                            }
                            stdAllResults++;
                        }
                        //x (x x x)
                        if(
                                !(opIds[0] == 0 && opIds[1] == 0 && opIds[2] == 0) // + + +
                                && !(opIds[0] == 2 && opIds[1] == 2 && opIds[2] == 2) // * * *
                                )
                        {
                            expBuilder = new StringBuilder();
                            expBuilder.append(nums[0]);
                            expBuilder.append(ops[opIds[0]]);
                            expBuilder.append('(');
                            expBuilder.append(nums[1]);
                            expBuilder.append(ops[opIds[1]]);
                            expBuilder.append(nums[2]);
                            expBuilder.append(ops[opIds[2]]);
                            expBuilder.append(nums[3]);
                            expBuilder.append(')');
                            res = ev.getNumberResult(expBuilder.toString());
                            if (res == desiredResult)
                            {
                                fw.write(expBuilder.toString() + "=" + (int)res + "\n");
                                stdCorrectResults++;
                            }
                            stdAllResults++;
                        }
                        //(x x x) x
                        if(
                                !(opIds[0] == 0 && opIds[1] == 0 && opIds[2] == 0) // + + +
                                && !(opIds[0] == 2 && opIds[1] == 2 && opIds[2] == 2) // * * *
                                )
                        {
                            expBuilder = new StringBuilder();
                            expBuilder.append('(');
                            expBuilder.append(nums[0]);
                            expBuilder.append(ops[opIds[0]]);
                            expBuilder.append(nums[1]);
                            expBuilder.append(ops[opIds[1]]);
                            expBuilder.append(nums[2]);
                            expBuilder.append(')');
                            expBuilder.append(ops[opIds[2]]);
                            expBuilder.append(nums[3]);
                            res = ev.getNumberResult(expBuilder.toString());
                            if (res == desiredResult)
                            {
                                fw.write(expBuilder.toString() + "=" + (int)res + "\n");
                                stdCorrectResults++;
                            }
                            stdAllResults++;
                        }
                        //x x (x x)
                        if(
                                !(opIds[0] == 0 && opIds[1] == 0 && opIds[2] == 0) // + + +
                                && !(opIds[0] == 2 && opIds[1] == 2 && opIds[2] == 2) // * * *
                                )
                        {
                            expBuilder = new StringBuilder();
                            expBuilder.append(nums[0]);
                            expBuilder.append(ops[opIds[0]]);
                            expBuilder.append(nums[1]);
                            expBuilder.append(ops[opIds[1]]);
                            expBuilder.append('(');
                            expBuilder.append(nums[2]);
                            expBuilder.append(ops[opIds[2]]);
                            expBuilder.append(nums[3]);
                            expBuilder.append(')');
                            res = ev.getNumberResult(expBuilder.toString());
                            if (res == desiredResult)
                            {
                                fw.write(expBuilder.toString() + "=" + (int)res + "\n");
                                stdCorrectResults++;
                            }
                            stdAllResults++;
                        }
                        //x (x x) x
                        if(
                                !(opIds[0] == 0 && opIds[1] == 0 && opIds[2] == 0) // + + +
                                && !(opIds[0] == 2 && opIds[1] == 2 && opIds[2] == 2) // * * *
                                )
                        {
                            expBuilder = new StringBuilder();
                            expBuilder.append(nums[0]);
                            expBuilder.append(ops[opIds[0]]);
                            expBuilder.append('(');
                            expBuilder.append(nums[1]);
                            expBuilder.append(ops[opIds[1]]);
                            expBuilder.append(nums[2]);
                            expBuilder.append(')');
                            expBuilder.append(ops[opIds[2]]);
                            expBuilder.append(nums[3]);
                            res = ev.getNumberResult(expBuilder.toString());
                            if (res == desiredResult)
                            {
                                fw.write(expBuilder.toString() + "=" + (int)res + "\n");
                                stdCorrectResults++;
                            }
                            stdAllResults++;
                        }
                        //(x x) x x
                        if(
                                !(opIds[0] == 0 && opIds[1] == 0 && opIds[2] == 0) // + + +
                                && !(opIds[0] == 2 && opIds[1] == 2 && opIds[2] == 2) // * * *
                                )
                        {
                            expBuilder = new StringBuilder();
                            expBuilder.append('(');
                            expBuilder.append(nums[0]);
                            expBuilder.append(ops[opIds[0]]);
                            expBuilder.append(nums[1]);
                            expBuilder.append(')');
                            expBuilder.append(ops[opIds[1]]);
                            expBuilder.append(nums[2]);
                            expBuilder.append(ops[opIds[2]]);
                            expBuilder.append(nums[3]);
                            res = ev.getNumberResult(expBuilder.toString());
                            if (res == desiredResult)
                            {
                                fw.write(expBuilder.toString() + "=" + (int)res + "\n");
                                stdCorrectResults++;
                            }
                            stdAllResults++;
                        }
                        //x x x x
                        expBuilder = new StringBuilder();
                        expBuilder.append(nums[0]);
                        expBuilder.append(ops[opIds[0]]);
                        expBuilder.append(nums[1]);
                        expBuilder.append(ops[opIds[1]]);
                        expBuilder.append(nums[2]);
                        expBuilder.append(ops[opIds[2]]);
                        expBuilder.append(nums[3]);
                        res = ev.getNumberResult(expBuilder.toString());
                        if (res == desiredResult)
                        {
                            fw.write(expBuilder.toString() + "=" + (int)res + "\n");
                            stdCorrectResults++;
                        }
                        stdAllResults++;
                        //</editor-fold>
                        }

                    
                        //////////////
                        //Test modular algebras with one modulus variable
                        //////////////
                        //<editor-fold>
                        if(enableOneModulus)//&& opI % 4 == 0)
                        {
                            int res;
                            for(int i = 0; i < numCount;i++) //All numbers are tested as modulus
                            {
                                //x x x
                                expBuilder = new StringBuilder();
                                expBuilder.append(nums[(i+1)%numCount]);
                                expBuilder.append(ops[opIds[0]]);
                                expBuilder.append(nums[(i+2)%numCount]);
                                expBuilder.append(ops[opIds[1]]);
                                expBuilder.append(nums[(i+3)%numCount]);
                                res = (int) ev.getNumberResult(expBuilder.toString()) % nums[i];
                                if(res == desiredResult)
                                {
                                    fw.write(expBuilder.toString() + "=" + res + "\n");
                                    mod1CorrectResults++;
                                }
                                mod1AllResults++;
                                //(x x) x
                                if(opIds[1] != 0 && opIds[1] != 2) //Dont calculate if the brace term is added or multiplied
                                {
                                    expBuilder = new StringBuilder();
                                    expBuilder.append('(');
                                    expBuilder.append(nums[(i+1)%numCount]);
                                    expBuilder.append(ops[opIds[0]]);
                                    expBuilder.append(nums[(i+2)%numCount]);
                                    expBuilder.append(')');                                    
                                    expBuilder.append(ops[opIds[1]]);
                                    expBuilder.append(nums[(i+3)%numCount]);
                                    res = (int) ev.getNumberResult(expBuilder.toString()) % nums[i];
                                    if(res == desiredResult)
                                    {
                                        fw.write(expBuilder.toString() + "=" + res + "\n");
                                        mod1CorrectResults++;
                                    }
                                    mod1AllResults++;                                    
                                }
                                //x (x x)
                                if(opIds[0] != 0 && opIds[0] != 2) //Dont calculate if the brace term is added or multiplied
                                {
                                    expBuilder = new StringBuilder();
                                    expBuilder.append(nums[(i+1)%numCount]);
                                    expBuilder.append(ops[opIds[0]]);
                                    expBuilder.append('(');
                                    expBuilder.append(nums[(i+2)%numCount]);                                    
                                    expBuilder.append(ops[opIds[1]]);
                                    expBuilder.append(nums[(i+3)%numCount]);
                                    expBuilder.append(')');
                                    res = (int) ev.getNumberResult(expBuilder.toString()) % nums[i];
                                    if(res == desiredResult)
                                    {
                                        fw.write(expBuilder.toString() + "\u2263" + res +  " (mod " + nums[3] + ")\n");
                                        mod1CorrectResults++;
                                    }
                                    mod1AllResults++;                                    
                                }
                            }
                        }
                        //</editor-fold>
                        
                        //////////////
                        //Test modular algebras with two modulus variables
                        //////////////
                        //<editor-fold>
                        if(enableTwoModuli)
                        {
                            int res;
                            double modDouble;
                            int modulus;
                            StringBuilder modExpBuilder;
                            for(int i = 0; i < numCount;i++) //All numbers are tested as modulus
                            {                                
                                expBuilder = new StringBuilder();
                                expBuilder.append(nums[i]);
                                expBuilder.append(ops[opIds[0]]);
                                expBuilder.append(nums[(i+1)%numCount]);
                                res = (int) ev.getNumberResult(expBuilder.toString());
                                //Continue if the left side result is 0
                                if(res == 0) {continue;}
                                
                                modExpBuilder = new StringBuilder();
                                modExpBuilder.append(nums[(i+2)%numCount]);
                                modExpBuilder.append(ops[opIds[1]]);
                                modExpBuilder.append(nums[(i+3)%numCount]);
                                modDouble = ev.getNumberResult(modExpBuilder.toString());
                                if(Double.isInfinite(modDouble)) {continue;} //x/0 --> Inf.
                                    
                                modulus = (int) modDouble;
                                
                                //Filter invalid results
                                if(strictModulus && modulus > res) {continue;}
                                //Continue if the modulus is 0
                                if(modDouble == 0) {continue;}
                                //Calculate final result : res % modulus
                                res %= modulus;
                                
                                if(res == desiredResult)
                                {
                                    fw.write(expBuilder.toString() + "\u2263" + res + " (mod " + modExpBuilder.toString() + ")\n");
                                    mod2CorrectResults++;
                                }
                                mod2AllResults++;
                            }
                        } //Number loop
                        //</editor-fold>
                        
                        ////End tests
                    
                        //Increment first number with overflow handling
                        for (int i = 0; i < numCount; i++)
                        {
                            nums[i] = ((nums[i] + 1) % upperLimit) + lowerLimit;
                            if (nums[i] != lowerLimit)
                            {
                                break;
                            }
                        }
                    }
                    // </editor-fold>
                    
                    //Increment last operator index with overflow handling
                    for (int i = 0; i < numCount - 2; i++)
                    {
                        opIds[i] = (opIds[i] + 1) % 4;
                        if (opIds[i] != 0)
                        {
                            break;
                        }
                    }
                }
                
                //Print out statistics
                System.out.println("Statistics:");
                System.out.println("  Standard arithmetics:");
                System.out.println("    Number of results:" + stdAllResults);
                System.out.println("    Correct results:" + stdCorrectResults);
                System.out.println("    Wrong results:" + (stdAllResults-stdCorrectResults) );
                System.out.println("  One Modulus:");
                System.out.println("    Number of results:" + mod1AllResults);
                System.out.println("    Correct results:" + mod1CorrectResults);
                System.out.println("    Wrong results:" + (mod1AllResults-mod1CorrectResults) );
                System.out.println("  Two Moduli:");
                System.out.println("    Number of results:" + mod2AllResults);
                System.out.println("    Correct results:" + mod2CorrectResults);
                System.out.println("    Wrong results:" + (mod2AllResults-mod2CorrectResults) );
                //Close the file writer
                fw.close();
            }
            catch (IOException ex)
            {
                ex.printStackTrace();
            }
            catch (EvaluationException ex)
            {
                ex.printStackTrace();
            }
            finally
            {
                try
                {
                    fw.close();
                }
                catch (IOException ex)
                {
                    ex.printStackTrace();
                }
            }
        }
    }

}
