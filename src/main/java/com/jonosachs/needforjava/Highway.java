package com.jonosachs.needforjava;

import java.util.Random;

/**
 * Class to create the highway
 */
public class Highway {
    private Lane[] lanes;
    private Lane lane;
    private PlayerPosition playerPosition;
    private Random random;

    /**
     * Default constructor creating an object of the Highway class
     */
    public Highway(Random random) {
        this.random = random;
        lanes = new Lane[3];
        lane = new Lane(this.random);
        playerPosition = new PlayerPosition();
    }

    /**
     * Creates the highway and places obstacles
     */
    public void createHighway() {
        int length = getLaneLength();
        int obstacles = getMaxObstacles();
        setLanesSize(3);

        for (int i = 0; i < lanes.length; i++) {
            setSpecificLane(i, length, obstacles);
            getSpecificLane(i).setLaneArray();
            getSpecificLane(i).placeObtacles();
        }
    }

    /**
     * Display method to show the highway display frame
     *
     * @param min Accepts minimum cell for the frame as an integer
     * @param max Accepts the maximum cell for the frame as an integer
     */
    public void displayHighway(int min, int max) {
        if (max > getLaneLength()) {
            max = getLaneLength();
        }

        min *= 3; // mutiply by 3 as each cell consists of space-dash-space (3x characters)
        max *= 3;

        for (int i = 2; i >= 0; i--) {
            System.out.println(getSpecificLane(i).displayPartLane(min, max));
        }
    }

    /**
     * Display method to show lane object
     *
     * @return Returns lane object as String
     */
    public String displayLane() {
        return lane + "";
    }

    /**
     * Accessor method to get the lane length
     *
     * @return Returns the lane length as an integer
     */
    public int getLaneLength() {
        return lane.getLaneLength();
    }

    /**
     * Accessor method to get the array containing all lanes
     *
     * @return Returns the lane object array
     */
    public Lane[] getLanes() {
        return lanes;
    }

    /**
     * Accessor method to get the maximum number of obstalces
     *
     * @return Returns the maximum number of obstacles as an integer
     */
    public int getMaxObstacles() {
        return lane.getMaxObstacles();
    }

    /**
     * Accessor method to get player position
     *
     * @return Returns the player position object
     */
    public PlayerPosition getPlayerPosition() {
        return playerPosition;
    }

    /**
     * Accessor method to get specific lane from Array
     *
     * @param index Accepts lane index as an integer
     * @return Returns the lane object at the index specified
     */
    public Lane getSpecificLane(int index) {
        return lanes[index];
    }

    /**
     * Mutator method to set the lane length
     *
     * @param laneLength Accepts lane length as an integer
     */
    public void setLaneLength(int laneLength) {
        lane.setLaneLength(laneLength);
    }

    /**
     * Mutator method to set the lanes array
     *
     * @param lanes Accepts the lanes object array
     */
    public void setLanes(Lane[] lanes) {
        this.lanes = lanes;
    }

    /**
     * Mutator method to set the lane length
     *
     * @param size Accepts length as an integer
     */
    public void setLanesSize(int size) {
        lanes = new Lane[size];
    }

    /**
     * Mutator method to set the max number of obtacles in the Lane Class
     *
     * @param maxObstacles Accepts obstacle number as an integer
     */
    public void setMaxObstacles(int maxObstacles) {
        lane.setMaxObstacles(maxObstacles);
    }

    /**
     * Mutator method to set the player position
     *
     * @param x Accepts x coordinate as an integer
     * @param y Accepts y coordinate (lane) as a integer
     */
    public void setPlayerPosition(int x, int y) {
        int oldX = playerPosition.getX();
        int oldY = playerPosition.getY();

        if (oldY == 0) {
            getSpecificLane(0).setSpecificLaneIndex(oldX, " - ");
        }
        if (oldY == 1) {
            getSpecificLane(1).setSpecificLaneIndex(oldX, " - ");
        }
        if (oldY == 2) {
            getSpecificLane(2).setSpecificLaneIndex(oldX, " - ");
        }

        if (y == 0) {
            getSpecificLane(0).setSpecificLaneIndex(x, " @ ");
        }
        if (y == 1) {
            getSpecificLane(1).setSpecificLaneIndex(x, " @ ");
        }
        if (y == 2) {
            getSpecificLane(2).setSpecificLaneIndex(x, " @ ");
        }
        playerPosition.setX(x);
        playerPosition.setY(y);
    }

    /**
     * Mutator method to set specific lane
     *
     * @param index        Accepts the array index as an integer
     * @param laneLength   Accepts the lane length as an integer
     * @param maxObstacles Accepts the maximum number of obstalces as an integer
     */
    public void setSpecificLane(int index, int laneLength, int maxObstacles) {
        lanes[index] = new Lane(laneLength, maxObstacles, random);
    }

}
