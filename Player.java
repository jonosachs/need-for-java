import java.util.*;
import java.io.*;

/**
*Class which stores player name and array of vehicle objects
*/
public class Player
{
    private String name;
    private Vehicle vehicle;
    private Vehicle[] vehicles;

    /**
    *Default constructor creating an object of the Player class
    */
    public Player()
    {
        name = "None";
        vehicle = new Vehicle();
        vehicles = new Vehicle[0];
    }

    /**
    *Non-Default constructor to set specific object attribute values
    *
    *@param name            Accepts the player name as String
    *@param vehicles        Accepts the vehicle array as object array
    */
    public Player(String name, Vehicle[] vehicles)
    {
        this.name = name;
        this.vehicles = vehicles;
    }

    /**
    *Accesses the vehicle display method in the Highway class
    *
    *@return                Returns the vehicle as a String
    */
    public String displayVehicle() 
    {
        return vehicle.display();
    }

    /**
    *Accessor method to display the current fuel from the Vehicle Class`
    *
    *@return                Returns the fuel as a double
    */
    public double getCurrentFuel()
    {
        return vehicle.getCurrentFuel();
    }

    /**
    *Accessor method to get the player name 
    *
    *@return                Returns player name as a String
    */
    public String getName()
    {
        return name;
    }

    /**
    *Accessor method to get the specific player vehicle from array
    *
    *@param index           Accepts array index as an integer
    *@return                Returns the player vehicle object at the specified index
    */
    public Vehicle getSpecificVehicle(int index)
    {
        return vehicles[index];
    }
    
    /**
    *Accessor method to get the vehicle object 
    *
    *@return                Returns the player vehicle object
    */
    public Vehicle getVehicle()
    {
        return vehicle;
    }

    /**
    *Accessor method to get the vehicles array 
    *
    *@return                Returns the vehicles object array
    */
    public Vehicle[] getVehicles()
    {
        return vehicles;
    }

    /**
    *Creates vehicle objects from a text file
    *
    *@param filename        Accepts the filename as a String
    */
    public void getVehiclesFromFile(String filename)
    {
        try
        {
            String fileName = filename;
            FileReader reader = new FileReader(fileName);
            Scanner file = new Scanner(reader);
            vehicles = new Vehicle[3];
            int i = 0;
            try
            {
                while (file.hasNextLine()) //Motorcycle,4,100,30
                {
                    String[] lineContents = file.nextLine().split(",");
                    String vehicleType = lineContents[0];
                    int boostSpeed = 0;
                    boostSpeed = Integer.parseInt(lineContents[1]);
                    int maxFuel = 0;
                    maxFuel = Integer.parseInt(lineContents[2]);
                    int maxDamage = 0;
                    maxDamage = Integer.parseInt(lineContents[3]);
                    vehicles[i] = new Vehicle(vehicleType, boostSpeed, maxFuel, maxDamage);
                    i++;
                }
            }
            finally
            {
                reader.close();
            }    
        }
        catch(Exception e)
        {
            System.out.println("Could not read file");
        }
    }

    /**
    *Mutator method to set the current fuel in the Vehicle Class
    *
    *@param currentFuel     Accepts the current fuel value as a double
    */
    public void setCurrentFuel(double currentFuel)
    {
        vehicle.setCurrentFuel(currentFuel);
    }

    /**
    *Mutator method to set the player name
    *
    *@param name            Accepts player name as a String
    */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
    *Mutator method to set the player vehicle
    *
    *@param vehicle         Accepts a vehicle object
    */
    public void setVehicle(Vehicle vehicle)
    {
        this.vehicle = vehicle;
    }

    /**
    *Mutator method to set vehicles array
    *
    *@param vehicles        Accepts the vehicles array
    */
    public void setVehicles(Vehicle[] vehicles)
    {
        this.vehicles = vehicles;
    }

    /**
    *Mutator method to set the vehicles array size
    *
    *@param size            Accepts the array size as an integer 
    */
    public void setVehiclesSize(int size)
    {
        vehicles = new Vehicle[size];
    }

    /**
    *Formats the vehicles array
    *
    *@return                Returns all the vehicle objects in the array as a String
    */
    public String vehiclesToString()
    {
        String vehiclesToString = "";
        int x = 1;
        for(int i = 0; i < vehicles.length; i++)
        {
            vehiclesToString += x + ") " + vehicles[i].display() + "\n";
            x++;
        }
        return vehiclesToString;
    }

}

