package BrickWork.models;

/**
 * Class is used mainly in Validations of first matrix. Where Validator class searches for correct
 * placement of brick parts (A "correct" brick is one with two SideA and SideB coordinates)
 *
 */
public class Brick {
    private int[] sideA;
    private int[] sideB;
    private int counter;

    /**
     * Constructor initializes all values of the held fields
     */
    public Brick() {
        this.sideA = new int[2];
        this.sideB = new int[2];
        this.counter = 1;
    }

    /**
     *
     * @return given fields for one of the sides of the brick
     */
    public int[] getSideA() {
        return sideA;
    }

    /**
     *
     * @return given fields for one of the sides of the brick
     */
    public int[] getSideB() {
        return sideB;
    }

    /**
     * public setter for the counter field - a counter should be 2 to be properly validated in the
     * Validator class. if for some reason (false inputs) the parts/counter of the brick exceeds 2
     * it breaks and falsify first layer - resulting in program exit with (-1) and message.
     */
    public void increaseCounter() {
        this.counter++;
        if (this.counter > 2) {
            System.out.println("False matrix - 3 spaces brick found.");
            System.exit(-1);
        }
    }

    /**
     *
     * @return field to check current counter in Validator class
     */
    public int getCounter() {
        return counter;
    }

    /**
     *
     * @return a check to be used in Validator class
     */
    public boolean isEmptyA() {
        return this.sideA[0] == 0
                && this.sideA[1] == 0;
    }

    /**
     *
     * @return a check to be used in Validator class
     */
    public boolean isEmptyB() {
        return this.sideA[0] == 0
                && this.sideA[1] == 0;
    }
}
