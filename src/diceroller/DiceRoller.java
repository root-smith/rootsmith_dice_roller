/*
 */
package diceroller;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

//import java.util.ArrayList;
//import java.util.List;
//import java.util.Arrays;

/**
 * @author Kate Rutersmith
 */
class DicePool
{
    int NumDice, DieFaces, Constant;
    int[] Results;
    
    DicePool(int NumDice0, int DieFaces0, int Constant0)
    {
        NumDice = NumDice0;
        DieFaces = DieFaces0;
        Constant = Constant0;
        //Results = RollDice(NumDice0,DieFaces0);
    }
    
    public static int[] RollDice(int NumDice, int DieFaces)
    {
        int DieValues[] = new int[NumDice];
        int i = 0;
        
        for (i = 0; i < NumDice; i++)
        {
            DieValues[i] = (int) (Math.random() * DieFaces) + 1;
        }
        
        return DieValues;
        
        //int first_die_value = (int) (Math.random() * num_sides) + 1;
    }
}

public class DiceRoller
{
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        // TODO code application logic here
    
        int NumDice = 1; //default value for number of dice to roll
        int DieFaces = 6; //default value for number of faces per die
        int Constant = 0; //default value for number to add/subtract to the result 
    
        if (args.length == 0)
        {
            System.out.println("No arguments given. Using default values.");
            //num_sides = Integer.parseInt(args[0]);
        }
    
        checkString("25d6+1");
        //checkString("0d0-0");
    }
    
    //Regex patterns to split the user input into three substrings (which eventually become integers).
    
    //Match a numeric value with any integer of digits (\d+), then d, then another integer (\d+)
    //ex: 12d20
    private static Pattern twopart = Pattern.compile("(\\d+)d(\\d+)");
    
    //Match numeric value with any integer of digits (\d+), then d, then another number, then a plus or minus sign, then another integer
    //ex: 8d6+12
    private static Pattern fourpart = Pattern.compile("(\\d+)d(\\d+)([+-])(\\d+)");
    
    public static void checkString(String s)
    {
        //remove whitespace from string and convert to lowercase
        String toMatch = s.toLowerCase().replaceAll("\\s", "");
        
        Matcher m = fourpart.matcher(toMatch);
        if (m.matches()) 
        {
            System.out.println(s + " matches; NumDice = " + m.group(1) +
                               "; DieFaces = " + m.group(2) +
                               "; Operator = " + m.group(3) +
                               "; Constant = " + m.group(4) +
                               ".");
        } else
        {
            Matcher m2 = twopart.matcher(toMatch);
                    if (m2.matches()) 
                    {
                        System.out.println(s + " matches; NumDice = " + m2.group(1) +
                               "; DieFaces = " + m2.group(2) +
                               ".");
                    } else
                    {
                    System.out.println(s + " does not match.");
                    }
        }
    }

 }
