/*
 */
package diceroller;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Arrays;

/**
 * @author Kate Rutersmith
 */
class DicePool
{
    int NumDice, DieFaces, Constant;
    int[] Results;
    String Operator;
    
    DicePool(int NumDice0, int DieFaces0, int Constant0)
    {
        NumDice = NumDice0;
        DieFaces = DieFaces0;
        Constant = Constant0;
        Results = RollDice(NumDice0,DieFaces0);
        Operator = (Constant0 == 0) ? "" : (Constant0 > 0) ? "+" : "-";
    }
    
    public int[] RollDice(int NumDice, int DieFaces)
    {
        int DieValues[] = new int[NumDice];
        
        for (int i = 0; i < NumDice; i++)
        {
            DieValues[i] = (int) (Math.random() * DieFaces) + 1;
        }
        
        return DieValues;
    }
    
    int getNumDice()
    {
        return this.NumDice;
    }
    
    int getDieFaces()
    {
        return this.DieFaces;
    }
    
    int getConstant()
    {
        return this.Constant;
    }
    
    int[] getResultArray()
    {
        return this.Results;
    }
    
    int getResultSum()
    {
        int sum = 0;
        for (int i = 0; i < this.Results.length; i++)
            sum += this.Results[i];
        sum += this.Constant;
        return sum;
    }
    
    void Reroll()
    {
        this.Results = RollDice(this.NumDice,this.DieFaces);
    }

    int[] getReroll()
    {
        this.Results = RollDice(this.NumDice,this.DieFaces);
        return this.Results;
    }
    
    public void printLongResults()
    {
        System.out.println("Number of Dice: " + this.NumDice);
        System.out.println("Die Faces: " + this.DieFaces);
        System.out.println("Constant: " + this.Constant);
        System.out.println(Arrays.toString(this.Results));
        System.out.println(this.getResultSum());
    }
    
    public void printShortResults()
    {
        System.out.print("Rolled " + this.NumDice + "d" + this.DieFaces);
        
        if (this.Constant != 0)
        {
            System.out.print(this.Operator + this.Constant);
        }
                
        System.out.println(" for a total of " + this.getResultSum());
        System.out.println("Rolls: " + Arrays.toString(this.Results));
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
        
        DicePool output_dicepool = new DicePool(NumDice,DieFaces,Constant);
        
        if (args.length == 0)
        {
            System.out.println("No arguments given. Using default values.");
        } else
        {
          checkString(args[0]);
          output_dicepool = createDicePool(args[0]);
        }
        
        output_dicepool.printLongResults();
        output_dicepool.printShortResults();
        
        //DicePool test = new DicePool(2,6,2);
        //test.printLongResults();
        
    }
    
    public static void checkString(String s)
    {
        //remove whitespace from string and convert to lowercase
        String toMatch = s.toLowerCase().replaceAll("\\s", "");
        
        Matcher m = RegexFourArgs.matcher(toMatch);
        if (m.matches()) 
        {
            System.out.println(s + " matches; NumDice = " + m.group(1) +
                               "; DieFaces = " + m.group(2) +
                               "; Operator = " + m.group(3) +
                               "; Constant = " + m.group(4) +
                               ".");
        } else
        {
            Matcher m2 = RegexTwoArgs.matcher(toMatch);
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
    
    public static DicePool createDicePool(String args)
    {
        String toMatch = args.toLowerCase().replaceAll("\\s", "");
        int NumDice = 0; //default value
        int DieFaces = 6; //default value
        int Constant = 0; //default value
        int Multiplier = 100; //default value
        
        Matcher m = RegexFourArgs.matcher(toMatch);
        if (m.matches()) 
        {
            NumDice = Integer.parseInt(m.group(1));
            DieFaces = Integer.parseInt(m.group(2));
                if (m.group(3).equals("+"))
                    Multiplier = 1;
                else if (m.group(3).equals("-"))
                    Multiplier = -1;
            Constant = Multiplier * Integer.parseInt(m.group(4));
        } else
        {
            Matcher m2 = RegexTwoArgs.matcher(toMatch);
            if (m2.matches()) 
            {
                NumDice = Integer.parseInt(m.group(1));
                DieFaces = Integer.parseInt(m.group(2));
            } else
            {
                System.out.println(args + " does not match.");
            }
        }
        
        DicePool temp = new DicePool(NumDice, DieFaces, Constant);
        return temp;
    }
    
    //Regex patterns to split the user input into three substrings (which eventually become integers).
    
    //Match a numeric value with any integer of digits (\d+), then d, then another integer (\d+)
    //ex: 12d20
    private static Pattern RegexTwoArgs = Pattern.compile("(\\d+)d(\\d+)");
    
    //Match numeric value with any integer of digits (\d+), then d, then another number, then a plus or minus sign, then another integer
    //ex: 8d6+12
    private static Pattern RegexFourArgs = Pattern.compile("(\\d+)d(\\d+)([+-])(\\d+)");
    
}
