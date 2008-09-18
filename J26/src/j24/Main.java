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
    static final int limits = 9;
    static final int numCount = 4; //Do NOT change before implemented extended bracket algorithm
    static final int desiredResult = 24;
    static final boolean enableModulus = true;

    static final char[] ops = {'+','-','*','/'}; //All operators available

    //Expression string builder
    static StringBuilder expBuilder = new StringBuilder();
    //Evaluator
    static Evaluator ev = new Evaluator();
    //Numbers iterated through
    static int[] nums = new int[numCount];
    //Operators iterated through
    static int[] opIdcs = new int[numCount - 1]; //Saves the ops indices of the operators currently used in the loop
    
    //private void 

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
                    nums[i] = 0;
                    opIdcs[i] = 0;
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
                for (int opI = 0; opI <= 5*opIdcs.length - 1; opI++)
                {
                    //Number loop
                    // <editor-fold>
                    for (int numPerms = 0; numPerms < (int) Math.pow(limits,numCount); numPerms++)
                    {
                        ///////////////
                        //Test the standard arithmetics
                        //////////////
                        //<editor-fold>
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
                        if (res == desiredResult)
                        {
                            fw.write(expBuilder.toString() + "=" + (int)res + "\n");
                            stdCorrectResults++;
                        }
                        stdAllResults++;
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
                        if (res == desiredResult)
                        {
                            fw.write(expBuilder.toString() + "=" + (int)res + "\n");
                            stdCorrectResults++;
                        }
                        stdAllResults++;
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
                        if (res == desiredResult)
                        {
                            fw.write(expBuilder.toString() + "=" + (int)res + "\n");
                            stdCorrectResults++;
                        }
                        stdAllResults++;
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
                        if (res == desiredResult)
                        {
                            fw.write(expBuilder.toString() + "=" + (int)res + "\n");
                            stdCorrectResults++;
                        }
                        stdAllResults++;
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
                        if (res == desiredResult)
                        {
                            fw.write(expBuilder.toString() + "=" + (int)res + "\n");
                            stdCorrectResults++;
                        }
                        stdAllResults++;
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
                        if (res == desiredResult)
                        {
                            fw.write(expBuilder.toString() + "=" + (int)res + "\n");
                            stdCorrectResults++;
                        }
                        stdAllResults++;
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
                        if (res == desiredResult)
                        {
                            fw.write(expBuilder.toString() + "=" + (int)res + "\n");
                            stdCorrectResults++;
                        }
                        stdAllResults++;
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
                        if (res == desiredResult)
                        {
                            fw.write(expBuilder.toString() + "=" + (int)res + "\n");
                            stdCorrectResults++;
                        }
                        stdAllResults++;
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
                        if (res == desiredResult)
                        {
                            fw.write(expBuilder.toString() + "=" + (int)res + "\n");
                            stdCorrectResults++;
                        }
                        stdAllResults++;
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
                        if (res == desiredResult)
                        {
                            fw.write(expBuilder.toString() + "=" + (int)res + "\n");
                            stdCorrectResults++;
                        }
                        stdAllResults++;
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
                        if (res == desiredResult)
                        {
                            fw.write(expBuilder.toString() + "=" + (int)res + "\n");
                            stdCorrectResults++;
                        }
                        stdAllResults++;
                        //</editor-fold>

                    
                        //////////////
                        //Test modular algebras with one modulus variable
                        //////////////
                        //<editor-fold>
                        if(enableModulus )//&& opI % 4 == 0)
                        {
                            for(int i = 0; i < numCount;i++) //All numbers are tested as modulus
                            {
                                //x x x
                                expBuilder = new StringBuilder();
                                expBuilder.append(nums[(i+1)%numCount]);
                                expBuilder.append(ops[opIdcs[0]]);
                                expBuilder.append(nums[(i+2)%numCount]);
                                expBuilder.append(ops[opIdcs[1]]);
                                expBuilder.append(nums[(i+3)%numCount]);
                                res = ev.getNumberResult(expBuilder.toString()) % nums[i];
                                if(res == desiredResult)
                                {
                                    fw.write(expBuilder.toString() + "=" + (int)res + "\n");
                                    mod1CorrectResults++;
                                }
                                mod1AllResults++;
                                //(x x) x
                                if(opIdcs[1] != 0 && opIdcs[1] != 2) //Dont calculate if the brace term is added or multiplied
                                {
                                    expBuilder = new StringBuilder();
                                    expBuilder.append('(');
                                    expBuilder.append(nums[(i+1)%numCount]);
                                    expBuilder.append(ops[opIdcs[0]]);
                                    expBuilder.append(nums[(i+2)%numCount]);
                                    expBuilder.append(')');                                    
                                    expBuilder.append(ops[opIdcs[1]]);
                                    expBuilder.append(nums[(i+3)%numCount]);
                                    res = ev.getNumberResult(expBuilder.toString()) % nums[i];
                                    if(res == desiredResult)
                                    {
                                        fw.write(expBuilder.toString() + "=" + (int)res + "\n");
                                        mod1CorrectResults++;
                                    }
                                    mod1AllResults++;                                    
                                }
                                //x (x x)
                                if(opIdcs[0] != 0 && opIdcs[0] != 2) //Dont calculate if the brace term is added or multiplied
                                {
                                    expBuilder = new StringBuilder();
                                    expBuilder.append(nums[(i+1)%numCount]);
                                    expBuilder.append(ops[opIdcs[0]]);
                                    expBuilder.append('(');
                                    expBuilder.append(nums[(i+2)%numCount]);                                    
                                    expBuilder.append(ops[opIdcs[1]]);
                                    expBuilder.append(nums[(i+3)%numCount]);
                                    expBuilder.append(')');
                                    res = ev.getNumberResult(expBuilder.toString()) % nums[i];
                                    if(res == desiredResult)
                                    {
                                        fw.write(expBuilder.toString() + "\u2263" + (int)res +  " (mod " + nums[3] + ")\n");
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
                        if(enableModulus)//&& opI % 4 == 0)
                        {
                            int modulus;
                            StringBuilder modExpBuilder;
                            for(int i = 0; i < numCount;i++) //All numbers are tested as modulus
                            {                                
                                expBuilder = new StringBuilder();
                                expBuilder.append(nums[i]);
                                expBuilder.append(ops[opIdcs[0]]);
                                expBuilder.append(nums[(i+1)%numCount]);
                                res = (int) ev.getNumberResult(expBuilder.toString());
                                //Continue if the left side result is 0
                                if(res == 0) {continue;}
                                
                                modExpBuilder = new StringBuilder();
                                modExpBuilder.append(nums[(i+2)%numCount]);
                                modExpBuilder.append(ops[opIdcs[1]]);
                                modExpBuilder.append(nums[(i+3)%numCount]);
                                modulus = (int) ev.getNumberResult(modExpBuilder.toString());
                                //Continue if the modulus is 0
                                if(modulus == 0) {continue;}
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
                        for (int i = 0; i < numCount - 1; i++)
                        {
                            nums[i] = (nums[i] + 1) % limits;
                            if (nums[i] != 0)
                            {
                                break;
                            }
                        }
                    }
                    // </editor-fold>
                    
                    //Increment last operator index with overflow handling
                    for (int i = 0; i < numCount - 2; i++)
                    {
                        opIdcs[i] = (opIdcs[i] + 1) % 4;
                        if (opIdcs[i] != 0)
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
