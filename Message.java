/**
*Class storing in-game messages to be displayed to the user
*/ 
public class Message
{
    /**
    *Default constructor for Class
    */
    public Message()
    {

    }

    /**
    *Generates random messages for invalid user input
    *
    *@return            Returns the message as a String
    */ 
    public String badSelection()
    {
        return randomMsg("Pick a number kid!", 
        "Enter a number guy, how many times I gotta tell ya?!", 
        "You want me to choose a number for you?");
    }

    /**
    *Generates game credits
    *
    *@return            Returns the message as a String
    */ 
    public String credits()
    {
        return
        "\n**   **  *******  **" + 
        "\n***  **  *******  **" +
        "\n**** **  *******  **" +
        "\n** ****  **       **" +
        "\n**  ***  **  *******" +
        "\n**  ***  **  *******\n" + 
        "\nNEED FOR JAVA\nCoded by Jonathan Sachs\n";
    }

    /**
    *Generates random messages for successful game outcome
    *
    *@return            Returns the message as a String
    */ 
    public String successMessage()
    {
        String msg = "\nYOU ESCAPED!!!!\n";
        return msg += randomMsg("Amazing driving kid!", "How do ya like them apples?!", 
        "Rob Cohen called, he wants wants you in the next Fast & Furious movie!" );
    }

    /**
    *Generates random messages for unsuccessful game outcome
    *
    *@return            Returns the message as a String
    */ 
    public String failureMessage()
    {
        String msg = "\nYOU FAILED...\n";
        return msg += randomMsg("Better luck next time kid...", 
        "You win some you lose some...", "Go back to driving school pal...");
    }

    /**
    *Generates random messages for attempted out-of-bounds moves
    *
    *@return            Returns the message as a String
    */ 
    public String outOfLanesMessage()
    {
        return randomMsg("Watch where you're going!", "This isn't bumber cars kid!", 
        "Stay on the road!");
    }

    /**
    *Generates random messages for a negative obstacle encounter
    *
    *@return            Returns the message as a String
    */ 
    public String obstacleBad()
    {
        return randomMsg("There goes my insurance premium!", "Ouch!", 
        "Are you trying to get us killed!?");
    }

    /**
    *Generates random messages for a positive obstacle encounter
    *
    *@return            Returns the message as a String
    */ 
    public String obstacleGood()
    {
        return randomMsg("Phew we needed that!", "Just what the doctor ordered!", 
        "That aught to help..");
    }

    /**
    *Provides the mechanism for generating random messages
    *
    *@param msg1        Accepts a message as a String
    *@param msg2        Accepts a message as a String
    *@param msg3        Accepts a message as a String
    *@return            Returns the randomly chosen message as a String
    */ 
    public String randomMsg (String msg1, String msg2, String msg3)
    {
        Random random = new Random ();
        int num = random.genRandomNum(1, 3);
        if(num == 1) 
            return msg1;
        if(num == 2) 
            return msg2;
        else
            return msg3;
    }
    
    /**
    *Generates the game plot message
    *
    *@return            Returns the message as a String
    */ 
    public String theDeal()
    {   
        return "\nTHE DEAL WENT BAD.."
        + "\nOk kid here's the story, that contact I had at the bank.."
        + "\nwell he turned out to be an undercover cop.. I know..my bad." 
        + "\nAnyway long story short we've been foiled and we gotta get"
        + "\nyou outer there A-SAP unless you wanna spend the next 10"
        + "\nyears in the slammer. I have a safehouse a few clicks up the"
        + "\nhighway. If you can make it you'll be able to hide there"
        + "\nuntil this all blows over. Oh, one more thing, the cops are"
        + "\none step ahead and have set traps along the highway, I'll do"
        + "\nmy best to guide you but honestly it's sink or swim here kid!";
    }
}

