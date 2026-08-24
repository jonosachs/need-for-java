import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Random;

/**
 * Main class which intialises the game conditions and runs the gameplay
 *
 * @author Jonathan Sachs
 * @version 1.0
 */
public class Game {
    private static final Random RANDOM = new Random();
    private Player player;
    private Highway highway;
    private double fuelHandicap;
    private Message message;

    /**
     * Default constructor creating an object of the Game class
     */
    public Game() {
        player = new Player();
        highway = new Highway();
        fuelHandicap = 0;
        message = new Message();
    }

    /**
     * Moves player forward by the boost amount and updates player stats
     */
    public void boost() {
        int boost = player.getVehicle().getBoostSpeed();
        int y = highway.getPlayerPosition().getY();

        for (int i = 0; i < boost; i++) // run loop to absorb all obstacles encountered
        {
            double oldFuel = player.getVehicle().getCurrentFuel();
            player.getVehicle().setCurrentFuel(oldFuel - 3);
            int x = highway.getPlayerPosition().getX() + 1;
            checkCellForObstacles(x, y);
            highway.setPlayerPosition(x, y);
            player.getVehicle().setDistance(player.getVehicle().getDistance() + 1);
        }
    }

    /**
     * Checks the landing cell for obstacles and updates player stats
     *
     * @param x Accepts player x coordinate (cell) as an integer
     * @param y Accepts the player y coordinate (lane) as an integer
     */
    public void checkCellForObstacles(int x, int y) {

        String obstacle = highway.getSpecificLane(y).getSpecificLaneIndex(x).trim();

        switch (obstacle) {
            case "F": // Fuel
                if ((player.getVehicle().getCurrentFuel() + 10) > player.getVehicle().getMaxFuel()) // check if fuel
                                                                                                    // will be > max
                    player.getVehicle().setCurrentFuel(player.getVehicle().getMaxFuel()); // if yes set to max fuel
                                                                                          // instead
                else
                    player.getVehicle().setCurrentFuel(player.getVehicle().getCurrentFuel() + 10);
                System.out.println(message.obstacleGood());
                break;
            case "B": // Roadblock
                player.getVehicle().setCurrentDamage(player.getVehicle().getCurrentDamage() - 20);
                if (player.getVehicle().getCurrentDamage() > 0)
                    System.out.println(message.obstacleBad());
                break;
            case "S": // Spikes
                player.getVehicle().setCurrentDamage(player.getVehicle().getCurrentDamage() - 45);
                if (player.getVehicle().getCurrentDamage() > 0)
                    System.out.println(message.obstacleBad());
                break;
            case "O": // Manhole
                player.getVehicle().setCurrentDamage(player.getVehicle().getCurrentDamage() - 45);
                if (player.getVehicle().getCurrentDamage() > 0)
                    System.out.println(message.obstacleBad());
                break;
        }
    }

    /**
     * Accepts player difficulty selection and sets game conditions
     */
    public void chooseDifficulty() {
        UserInput user = new UserInput();
        int difficulty = user.userIntInput("\nPlease choose a difficulty level: " +
                "\n1) Easy \n2) Moderate \n3) Hard\n>", 1, 3);
        if (difficulty == 1) {
            highway.setLaneLength(RANDOM.nextInt(10, 15));
            fuelHandicap = 1;
            highway.setMaxObstacles(12);
        }
        if (difficulty == 2) {
            highway.setLaneLength(RANDOM.nextInt(15, 30));
            fuelHandicap = 0.8;
            highway.setMaxObstacles(24);
        }
        if (difficulty == 3) {
            highway.setLaneLength(RANDOM.nextInt(30, 50));
            fuelHandicap = 0.5;
            highway.setMaxObstacles(45);
        }
        System.out.println(message.theDeal());
    }

    /**
     * Accepts player vehicle selection and sets the fuel handicap
     */
    public void chooseVehicle() {
        UserInput user = new UserInput();
        System.out.println("\nOk time to choose your getaway vehicle, what's it gonna be?");
        int vehicleType = user.userIntInput(player.vehiclesToString() + ">", 1, 3);
        if (vehicleType == 1) {
            player.setVehicle(player.getSpecificVehicle(0));
            player.setCurrentFuel(player.getVehicle().getMaxFuel() * fuelHandicap);
            System.out.println("My personal favourite!");
        }
        if (vehicleType == 2) {
            player.setVehicle(player.getSpecificVehicle(1));
            player.setCurrentFuel(player.getVehicle().getMaxFuel() * fuelHandicap);
            System.out.println("Solid choice!");
        }
        if (vehicleType == 3) {
            player.setVehicle(player.getSpecificVehicle(2));
            player.setCurrentFuel(player.getVehicle().getMaxFuel() * fuelHandicap);
            System.out.println("Looking to do some damage!");
        }
        player.getSpecificVehicle(vehicleType - 1).setDistance(0);

        System.out.println("\nHere's the intel we have on your route:");
        System.out.println(">It's " + highway.getLaneLength() + " km to the escape point");
        if (fuelHandicap < 1)
            System.out.println(">Your starting fuel is " + (fuelHandicap * 100) + "% so make the most of it!");
        else
            System.out.println(">Your starting fuel is " + (fuelHandicap * 100) + "%, i think you're gonna need it!");

        System.out.println("\nOk it's time to go kid, the cops are on our tail!");
    }

    /**
     * Creates the highway via the Highway class and sets a random player starting
     * lane
     */
    public void createHighway() {
        highway.createHighway();

        int num = RANDOM.nextInt(0, 2);
        highway.setPlayerPosition(0, num);
    }

    /**
     * Formats and shows player stats and result at the end of the game
     *
     * @param result Accepts the game result as a String
     */
    public void endGameStats(String result) {
        String stats = "\nPlayer Stats\nName: " + player.getName() + " Vehicle: "
                + player.getVehicle().getVehicleType() + " " + player.getVehicle().getCurrentStats() +
                "\nResult: " + result;
        System.out.println(stats);
        writeFile("output.txt", stats);
    }

    /**
     * Displays a failed message to the player
     */
    public void endMsgFailure() {
        System.out.println(message.failureMessage());
        endGameStats("FAILED\n");
    }

    /**
     * Displays a success message to the player
     */
    public void endMsgSuccess() {
        System.out.println(message.successMessage());
        player.getVehicle().setDistance(highway.getLaneLength()); // distance out of bounds
        endGameStats("ESCAPED\n");
    }

    /**
     * Reads a text file via the Player class
     *
     * @param filename Accepts the filename to read as a String
     * @return Returns vehicle details formatted as a StringConfiguration is still
     *         incorrect. Do you want to edit it again?
     */
    public String getVehiclesFromFile(String filename) {
        player.getVehiclesFromFile(filename);
        return player.vehiclesToString();
    }

    /**
     * Checks player damage and fuel via the Player class
     *
     * @return Returns false if player exceeds the allowed values
     */
    public boolean healthOk() {
        if (player.getVehicle().getCurrentFuel() <= 0) {
            return false;
        }
        if (player.getVehicle().getCurrentDamage() <= 0) {
            return false;
        }
        return true;
    }

    /**
     * Main method called on execution and defining the control flow of the program
     */
    public static void main(String[] args)

    {
        Message message = new Message();
        Game game = new Game();

        System.out.println(message.credits());
        game.getVehiclesFromFile("vehicles.txt");
        game.welcome();
        game.chooseDifficulty();
        game.chooseVehicle();
        game.createHighway();
        game.playGame();
    }

    /**
     * Moves the player forward and updates player stats
     */
    public void moveForward() {
        int x = highway.getPlayerPosition().getX() + 1;
        int y = highway.getPlayerPosition().getY();
        player.getVehicle().setCurrentFuel(player.getVehicle().getCurrentFuel() - 1);
        checkCellForObstacles(x, y);
        highway.setPlayerPosition(x, y);
        player.getVehicle().setDistance(player.getVehicle().getDistance() + 1);
    }

    /**
     * Shows the playing frame and executes player commands
     */
    public void playGame() {
        Scanner console = new Scanner(System.in);
        PlayerPosition position = new PlayerPosition();

        boolean flag = false;
        int frame = 10;
        String result = "";

        while (flag == false) {
            System.out.println("\n(@ = Player, F = Fuel, B = Road Block, S = Tyre Spike," +
                    " O = Open Manhole)");
            System.out.println(player.getVehicle().getCurrentStats());
            if (frame >= highway.getLaneLength()) {
                frame = highway.getLaneLength();
            }
            highway.displayHighway(frame - 10, frame);
            System.out.print("Select 1 to Move Forward\nSelect 2 to Swerve Up" +
                    "\nSelect 3 to Swerve Down\nSelect 4 to Boost\n>");
            try {
                int action = Integer.parseInt(console.nextLine());
                switch (action) {
                    case 1:
                        moveForward();
                        frame++;
                        break;
                    case 2:
                        if (swerveUp()) // runs swerve method and return false if outside lanes
                            frame++;
                        break;
                    case 3:
                        if (swerveDown())
                            frame++;
                        break;
                    case 4:
                        boost();
                        frame += player.getVehicle().getBoostSpeed();
                        break;
                }
            } catch (Index­Out­Of­Bounds­Exception e) // player wins if position > lane length
            {
                flag = true;
                result = "success";
            } catch (Exception e) {
                System.out.println(message.badSelection());
            }
            if (!healthOk()) {
                flag = true;
                result = "failure";
            }
        }
        if (result == "success")
            endMsgSuccess();
        if (result == "failure")
            endMsgFailure();
    }

    /**
     * Moves the player down and updates player stats
     *
     * @return Returns false if player is attempting to move out of bounds
     */
    public boolean swerveDown() {
        if (withinLanes(highway.getPlayerPosition().getY() - 1)) {
            int x = highway.getPlayerPosition().getX() + 1;
            int y = highway.getPlayerPosition().getY() - 1;
            player.getVehicle().setCurrentFuel(player.getVehicle().getCurrentFuel() - 2);
            checkCellForObstacles(x, y);
            highway.setPlayerPosition(x, y);
            player.getVehicle().setDistance(player.getVehicle().getDistance() + 1);
            return true;
        }
        return false;
    }

    /**
     * Moves the player up and updates player stats
     *
     * @return Returns false if player is attempting to move out of bounds
     */
    public boolean swerveUp() {
        if (withinLanes(highway.getPlayerPosition().getY() + 1)) {
            int x = highway.getPlayerPosition().getX() + 1;
            int y = highway.getPlayerPosition().getY() + 1;
            player.getVehicle().setCurrentFuel(player.getVehicle().getCurrentFuel() - 2);
            checkCellForObstacles(x, y);
            highway.setPlayerPosition(x, y);
            player.getVehicle().setDistance(player.getVehicle().getDistance() + 1);
            return true;
        }
        return false;
    }

    /**
     * Displays the welcome message and accepts a player name
     */
    public void welcome() {
        UserInput user = new UserInput();
        System.out.println("Welcome to Need for Java!");
        String name = user.userStringInput("Please enter your name: ", 3, 12);
        player.setName(name);
        System.out.println("Welcome " + player.getName() + "!");
    }

    /**
     * Checks if proposed move is within the lane boundary
     *
     * @param y Accepts player y coordinate (corresponding to a lane) as an integer
     * @return Returns true if value is within range
     */
    public boolean withinLanes(int y) {
        if (y <= 2 && y >= 0) {
            return true;
        }
        System.out.println(message.outOfLanesMessage());
        return false;
    }

    /**
     * Writes the game result to file
     *
     * @param fileName Accepts a filename as a String
     * @param text     Accepts the text to write to file as a String
     */
    public void writeFile(String fileName, String text) {
        try {
            PrintWriter write = new PrintWriter(fileName);
            write.println(text);
            write.close();
        } catch (Exception e) {
            System.out.println("Could not write file");
        }
    }

}
