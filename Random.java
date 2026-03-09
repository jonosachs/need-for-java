/**
*Class which generates a random number within a selected range
*/
public class Random
{
    /**
    *Default constructor for Class
    */
    public Random()
    {
        
    }
    
    
    /**
    *Generates a random number within a selected range
    *
    *@param min         Accepts a minimum value as an integer
    *@param max         Accepts a maximum value as an integer
    *@return            Returns the random value as an integer
    */
    public int genRandomNum(int min, int max)
    {
        int randomNum = (int)(Math.random() * max + min);
        return randomNum;
    }
    
}
