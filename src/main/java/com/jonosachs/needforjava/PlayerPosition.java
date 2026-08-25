package com.jonosachs.needforjava;

/**
 * Class which stores the player position data
 */
public class PlayerPosition {
    private int x;
    private int y;

    /**
     * Default constructor creating an object of the class
     */
    public PlayerPosition() {
        x = -1;
        y = -1;
    }

    /**
     * Non-default constructor creating an object of the class with user defined
     * attributes
     *
     * @param x Accepts the player x coordinate (cell) as an integer
     * @param y Accepts the player y coordinate (lane) as an integer
     */
    public PlayerPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Accessor method to get the player x coordinate (cell)
     *
     * @return Returns the player x coordinate (cell) as an integer
     */
    public int getX() {
        return x;
    }

    /**
     * Accessor method to get the player y coordinate (lane)
     *
     * @return Returns the player y coordinate (lane) as an integer
     */
    public int getY() {
        return y;
    }

    /**
     * Mutator method to set the player x coordinate (cell)
     *
     * @param x Accepts the player x coordinate (cell) as a String
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Mutator method to set the player y coordinate (lane)
     *
     * @param y Accepts the player y coordinate (lane) as a String
     */
    public void setY(int y) {
        this.y = y;
    }
}
