import java.util.*;

/**
*Class which accepts and validates various user inputs
*/
public class UserInput
{
    /**
    *Default constructor for Class
    */
    public UserInput()
    {

    }
    
    /**
    *Accepts a String user input of a certain length
    *
    *@param text                Accepts the text to prompt the user as a String
    *@param lowerLimit          Accepts the lower limit for the input length as a String
    *@param upperLimit          Accepts the upper limit for the input length as a String
    *@return                    Returns the user input as a String
    */
    public String userStringInput(String text, int lowerLimit, int upperLimit)
    {
        Scanner console = new Scanner(System.in);
        String input = "";
        boolean flag = true;
        
        while(flag)
        {
            try
            {
                System.out.print(text);
                input = console.nextLine();
                if (input.length() - 1 >= lowerLimit && input.length() - 1 <= upperLimit && !input.isBlank()) 
                {  
                    flag = false;
                    break;
                }
                else
                {
                    System.out.println("Input must be between " + lowerLimit + " and " + upperLimit);
                }
            }
            catch(Exception e)
            {
                System.out.println("Invalid input, please try again");
            }
        }
        return input;
    }

    /**
    *Accepts an integer user input of a certain length
    *
    *@param text                Accepts the text to prompt the user as a String
    *@param lowerLimit          Accepts the lower limit for the input length as a String
    *@param upperLimit          Accepts the upper limit for the input length as a String
    *@return                    Returns the user input as an integer
    */
    public int userIntInput(String text, int lowerLimit, int upperLimit)
    {
        Scanner console = new Scanner(System.in);
        int input = 0;
        boolean flag = true;
        
        while(flag)
        {
            try
            {
                System.out.print(text);
                input = Integer.parseInt(console.nextLine());
                if (input >= lowerLimit && input <= upperLimit) 
                {  
                    flag = false;
                    break;
                }
                else
                {
                    System.out.println("Please enter a number between " + lowerLimit + " and " + upperLimit);
                }
            }
            catch(Exception e)
            {
                System.out.println("Please enter a number between " + lowerLimit + " and " + upperLimit);
            }
        }
        return input;
    }
   
    /**
    *Accepts a char user input
    *
    *@param text                Accepts the text to prompt the user as a String
    *@return                    Returns the user input as a char
    */
    public char userCharInput(String text)
    {
        Scanner console = new Scanner(System.in);
        char input = 'a';
        boolean flag = true;
        
        while(flag)
        {
            try
            {
                System.out.print(text);
                input = console.nextLine().charAt(0);
                flag = false;
            }
            catch(Exception e)
            {
                System.out.print("Invalid input, please try again");
            }
        }
        return input;
    }

}
