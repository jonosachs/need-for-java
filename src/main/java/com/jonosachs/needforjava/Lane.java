package com.jonosachs.needforjava;

import java.util.Random;

/**
 * Class to create the lanes and obstacles of the highway
 */
public class Lane {
    private Random random;
    private int laneLength;
    private int maxObstacles;
    private String[] laneArray;

    /**
     * Creates a single lane with random obstacles
     *
     * @param laneLength   number of cells in the lane
     * @param maxObstacles upper bound for number of obstacles
     * @param random       injected random source for obstacle placement
     */
    public Lane(int laneLength, int maxObstacles, Random random) {
        this.random = random;
        this.laneLength = laneLength;
        this.maxObstacles = maxObstacles;
        this.laneArray = new String[laneLength];
    }

    /**
     * Default lane configuration with placeholder values
     *
     * @param random injected random source for obstacle placement
     *
     */
    public Lane(Random random) {
        this.random = random;
        this.laneLength = -1;
        this.maxObstacles = -1;
        this.laneArray = new String[1];
    }

    /**
     * Formats the lane array for printing
     *
     * @return Returns the lane array as a String
     */
    public String displayLaneArray() {
        String ArrayToString = "";
        for (int i = 0; i < laneArray.length; i++) {
            ArrayToString += laneArray[i] + "";
        }
        return ArrayToString;
    }

    /**
     * Display method to show part of the lane
     *
     * @param start Accepts the starting frame as an integer
     * @param stop  Accepts the closing frame as an integer
     * @return Returns part of the lane to be displayed as String
     */
    public String displayPartLane(int start, int stop) {
        return displayLaneArray().substring(start, stop);
    }

    /**
     * Accessor method to get lane array
     *
     * @return Returns the lane array
     */
    public String[] getLaneArray() {
        return laneArray;
    }

    /**
     * Accessor method to get lane length
     *
     * @return Returns the lane length as an integer
     */
    public int getLaneLength() {
        return laneLength;
    }

    /**
     * Accessor method to get max obstacles
     *
     * @return Returns max obstalces as an integer
     */
    public int getMaxObstacles() {
        return maxObstacles;
    }

    /**
     * Accessor method to call a specific lane in the array
     *
     * @param index Accepts array index as an integer
     * @return Returns the lane from the array as a String
     */
    public String getSpecificLaneIndex(int index) {
        return laneArray[index];
    }

    /**
     * Places obstacles on a lane
     */
    public void placeObtacles() {
        int index = 3; // no obstacles to first 3 cells per design criteria
        int numObstalces = 0;

        while (numObstalces < maxObstacles && index < getLaneLength()) {
            String event = tryObstacle();
            laneArray[index] = event;
            if (event != " - ") {
                numObstalces++;
            }
            index++;
        }
    }

    /**
     * Generates random obstacles with weighted probabilites
     *
     * @return Returns the random obstacle as a String
     */
    public String randomObstacle() {
        int randomNum = random.nextInt(1, 10);
        String obstacle = "";

        switch (randomNum) {
            case 1:
            case 2:
            case 3: // 3 out of 10 = 30% chance of F
                obstacle = " F ";
                break;
            case 4:
            case 5:
            case 6:
            case 7:
                obstacle = " B ";
                break;
            case 8:
            case 9:
                obstacle = " S ";
                break;
            case 10:
                obstacle = " O ";
                break;
        }
        return obstacle;
    }

    /**
     * Produces a generic lane array without obstacles
     */
    public void setLaneArray() {
        for (int i = 0; i < getLaneLength(); i++) {
            laneArray[i] = " - ";
        }
    }

    /**
     * Accessor method to set lane length
     *
     * @param laneLength Accepts the lane length as an integer
     */
    public void setLaneLength(int laneLength) {
        this.laneLength = laneLength;
    }

    /**
     * Mutator method to set the maximum number of obstacles
     *
     * @param maxObstacles Accepts maximum obstalces as an integer
     */
    public void setMaxObstacles(int maxObstacles) {
        this.maxObstacles = maxObstacles;
    }

    /**
     * Mutator method setting lane text at specific array index
     *
     * @param index Accepts the index as an integer
     * @param text  Accepts text to write to array as String
     */
    public void setSpecificLaneIndex(int index, String text) {
        laneArray[index] = text;
    }

    /**
     * Method producing an obstacle 1/3 of the time
     *
     * @return Returns obstalce or otherwise as a String
     */
    public String tryObstacle() {
        int randomNum = random.nextInt(1, 3);
        String event = "";

        switch (randomNum) {
            case 1:
                event = randomObstacle();
                break;
            case 2:
            case 3:
                event = " - ";
                break;
        }
        return event;
    }

}
