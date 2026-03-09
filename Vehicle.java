/**
*Class storing vehicle information
*/ 
public class Vehicle
{
    private String vehicleType;
    private int distance;
    private int boostSpeed;
    private double maxFuel;
    private int maxDamage;
    private double currentFuel;
    private int currentDamage;

    /**
    *Default constructor creating an object of the Vehicle class
    */ 
    public Vehicle()
    {
        vehicleType = "none";
        distance = -1;
        boostSpeed = -1;
        maxFuel = -1;
        maxDamage = -1;
        currentFuel = -1;
        currentDamage = -1;
    }

    /**
    *Non-default constructor to assign specific values to the Vehicle objects 
    *
    *@param vehicleType         Accepts vehicle type as String
    *@param boostSpeed          Accepts boost speed as int
    *@param maxFuel             Accepts the maximum fuel as int
    *@param maxDamage           Accepts the maximum damage as int
    */ 
    public Vehicle(String vehicleType, int boostSpeed, double maxFuel, int maxDamage)
    {
        this.vehicleType = vehicleType;
        this.boostSpeed = boostSpeed;
        this.maxFuel = maxFuel;
        this.maxDamage = maxDamage;
        this.currentDamage = maxDamage;
    }

    /**
    *Display method to format limited vehicle object attributes
    *
    *@return                    Returns the vehicle object attributes as a String
    */ 
    public String display()
    {
        return "Vehicle Type = " + vehicleType + ", Boost Speed = " 
        + boostSpeed + ", Max Fuel = " + maxFuel + ", Max Damage = " + maxDamage;
    }

    /**
    *Accesor method to get the boost speed
    *
    *@return                    Returns the boost speed as an Integer
    */ 
    public int getBoostSpeed()
    {
        return boostSpeed;
    }

    /**
    *Accesor method to get the current fuel
    *
    *@return                    Returns the current fuel as an Integer
    */ 
    public double getCurrentFuel()
    {
        return currentFuel;
    }

    /**
    *Accesor method to get the current damage
    *
    *@return                    Returns the current damage as an Integer
    */ 
    public int getCurrentDamage()
    {
        return currentDamage;
    }

    /**
    *Formats the vehicle object current state
    *
    *@return                    Returns the vehicle object state as a String
    */ 
    public String getCurrentStats()
    {
        return "Distance: " + distance + "km" + " Fuel: " + (int)getCurrentFuel() + "/" + (int)getMaxFuel() + " " +
        "Damage: " + getCurrentDamage() + "/" + getMaxDamage();
    }

    /**
    *Accesor method to get the distance
    *
    *@return                    Returns the distance as an integer
    */ 
    public int getDistance()
    {
        return this.distance;
    }

    /**
    *Accesor method to get the maximum damage
    *
    *@return                    Returns the maximum damage as an Integer
    */ 
    public int getMaxDamage()
    {
        return maxDamage;
    }

    /**
    *Accesor method to get the maximum fuel
    *
    *@return                    Returns the maximum fuel as an Integer
    */ 
    public double getMaxFuel()
    {
        return maxFuel;
    }

    /**
    *Accesor method to get the vehicle type
    *
    *@return                    Returns the vehicle type as a String
    */ 
    public String getVehicleType()
    {
        return vehicleType;
    }

    /**
    *Mutator method to set the boost speed
    *
    *@param boostSpeed          Accepts the boost speed as an integer
    */ 
    public void setBoostSpeed(int boostSpeed)
    {
        this.boostSpeed = boostSpeed;
    }

    /**
    *Mutator method to set the current damage
    *
    *@param currentDamage       Accepts the current damage as an integer
    */ 
    public void setCurrentDamage(int currentDamage)
    {
        this.currentDamage = currentDamage;
    }
    
    /**
    *Mutator method to set the curretn fuel
    *
    *@param currentFuel         Accepts the current fuel as an integer
    */ 
    public void setCurrentFuel(double currentFuel)
    {
        this.currentFuel = currentFuel;
    }

    /**
    *Mutator method to set the distance
    *
    *@param distance         Accepts the distance as an integer
    */ 
    public void setDistance(int distance)
    {
        this.distance = distance; 
    }

    /**
    *Mutator method to set the maximum fuel
    *
    *@param maxFuel             Accepts the maximum fuel as an integer
    */ 
    public void setMaxFuel(double maxFuel)
    {
        this.maxFuel = maxFuel;
    }

    /**
    *Mutator method to set the maxmium damage
    *
    *@param maxDamage        Accepts the maximum damage as an integer
    */ 
    public void setMaxDamage(int maxDamage)
    {
        this.maxDamage = maxDamage;
    }

    /**
    *Mutator method to set the vehicle type
    *
    *@param vehicleType         Accepts the vehicle type as a String
    */
    public void setVehicleType(String vehicleType)
    {
        this.vehicleType = vehicleType;
    }
}
