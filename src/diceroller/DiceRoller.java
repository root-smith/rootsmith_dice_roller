/*
 */
package diceroller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

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
        Results = RollDice(NumDice0,DieFaces0);
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

public class DiceRoller {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    if (args.length == 0)
    {
        System.out.println("No arguments given. Using default values.");
        //num_sides = Integer.parseInt(args[0]);
    }
    
    //System.out.println("Test Println.");
    
    String ToParse = "15d20+11";
    String[] SplitArray = ToParse.toLowerCase().split("d");
    String NumDice = SplitArray[0];
    String Remainder = SplitArray[1];
    
    
    if (Remainder.contains("+"))
            String[] SplitByOperator = Remainder.split("+");
    else if (Remainder.contains("-"))
            String[] SplitByOperator = Remainder.split("+");
    
    System.out.println(part1 + " " + part2);
    }
    
        //Regex pattern to translate the user input into three variables
    
    //Match a numeric value with any number of digits (\d+) that comes before any number of spaces and the letter d
    private static Pattern getNumDice = Pattern.compile("\\d+(?=\\s*d)");
    
    //Match
    private static Pattern getDieFaces = Pattern.compile("((?<=d)\\d+|(?<=d\\s)\\d+)");
    
    //
    private static Pattern getConstant = Pattern.compile("(?<=[+-])\\d+|(?<=[+-]\\s)\\d+");
    
}
