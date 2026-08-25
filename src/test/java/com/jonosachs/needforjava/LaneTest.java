package com.jonosachs.needforjava;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import java.util.Random;
import java.util.Arrays;

class LaneTest {

    @Test
    void randomObstacleNeverReturnsManhole() {
        Random random = new Random(42L);
        Lane lane = new Lane(random);
        // 'O' surrounded by whitespace is the manhole
        String unexpected = " O ";

        String assertMsg = "randomObstacle calls random.nextInt(1,10) which is "
                + "max. exclusive meaning 10 is never returned, the only "
                + "value associated with the manhole";

        for (int i = 0; i < 100; i++) {
            String actual = lane.randomObstacle();
            assertNotEquals(unexpected, actual, assertMsg);
        }

    }

    @Test
    void placeObstaclesSkipsFirstThreeCells() {
        int laneLength = 10;
        int maxObstacles = 10;
        Random random = new Random(42L);

        Lane lane = new Lane(laneLength, maxObstacles, random);
        lane.placeObtacles();

        String[] laneArray = lane.getLaneArray();
        for (int cell = 0; cell < 3; cell++) {
            String actual = laneArray[cell];
            String assertMsg = "Found an obstacle at cell %d, should be null".formatted(cell);

            // setLaneArray is usually called first which writes the road as " - " cells.
            // Here placeObstacles is called in isolation so road is null unless overwritten
            // by placeObtacles
            assertNull(actual, assertMsg);
        }

    }

    @Test
    void placeObstaclesStopsAtMaxObstacles() {
        int laneLength = 20;
        int maxObstacles = 2;
        Random random = new Random(42L);
        Lane lane = new Lane(laneLength, maxObstacles, random);
        lane.placeObtacles();
        String[] laneArray = lane.getLaneArray();
        // Non obstacle cells are null since setLaneArray has not been called to write
        // the road
        int numObstacles = (int) Arrays.stream(laneArray)
                .filter(cell -> cell != null && !cell.equals(" - "))
                .count();
        String assertMsg = "Should be %d, not %d".formatted(maxObstacles, numObstacles);
        assertEquals(maxObstacles, numObstacles, assertMsg);
    }

    @Test
    void tryObstacleReturnsObstacleHalfTheTime() {
        int laneLength = 10;
        int maxObstacles = 10;
        Random random = new Random(42L);
        Lane lane = new Lane(laneLength, maxObstacles, random);

        int count = 0;
        String road = " - ";
        String payload;
        for (int i = 0; i < 100; i++) {
            payload = lane.tryObstacle();
            if (!road.equals(payload)) {
                count++;
            }
        }

        // Docstring says tryObstacle produces an obstacle 1/3 of the time but
        // reality is 1/2, as it uses random.nextInt(1,3) with max exclusive [1,3).
        // Note random seed 42 gives exactly 41 obstacles.
        String assertMsg = "seed 42 should return 41 obstacles exactly, not %d".formatted(count);
        assertEquals(41, count, assertMsg);
    }

}
