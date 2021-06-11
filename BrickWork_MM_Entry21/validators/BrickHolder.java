package BrickWork.validators;

import BrickWork.models.Brick;

import java.util.HashMap;
import java.util.Map;

/**
 * A static class used mainly in Validator to ensure uniqueness of the validated Bricks.
 * and hold some coordinate points
 */
public class BrickHolder {

    /**
     * map holding a unique int identity of a Brick
     */
    private static Map<Integer, Brick> brickCounter = new HashMap<>();

    /**
     * Method adds a Brick identity to consider and verifies a brick pieces not exceeding allowed
     * Check ability is activated when increase brick counter is called. If exceed allowed - Brick brakes.
     * @param brickNum - is a brick identifier of a piece of brick
     */
    public static void addBrick(int brickNum) {
        if (brickCounter.containsKey(brickNum)) {
            brickCounter.get(brickNum).increaseCounter();
        } else {
            brickCounter.put(brickNum, new Brick());
        }
    }

    /**
     *
     * @return field - the map counter. Used in brick validations.
     */
    public static Map<Integer, Brick> getBrickCounter() {
        return brickCounter;
    }
}
