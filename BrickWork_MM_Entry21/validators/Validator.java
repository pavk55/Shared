package BrickWork.validators;

import BrickWork.matrices.MatrixFirstLayer;
import BrickWork.models.Brick;

import java.util.Map;

/**
 * Class validating input matrix. Default is first layer matrix.
 */
public class Validator {

    private MatrixFirstLayer matrixFirstLayer;

    /**
     *
     * @param matrixFirstLayer or other matrix derivable class.
     * default usage is to check first layer matrix
     *
     */
    public Validator(MatrixFirstLayer matrixFirstLayer) {
        this.matrixFirstLayer = matrixFirstLayer;
    }

    /**
     * Method cycles through every position on the matrix layer two and determine whether a correct brick
     * is formed with the number in the current position. Excluding from check already registered position
     * in the BrickHolder Class. If a number of brick piece is already registered but not correctly settled in the Brick class
     * SideA or SideB - than faulty piece on the board - exits with -1 and message.
     * to confirm that a Method brickChecker is applied.
     */
    public void valid2partsBricksLayer() {
        int[][] firstLayer = this.matrixFirstLayer.getFirstLayer();
        Map<Integer, Brick> brickCounter = BrickHolder.getBrickCounter();
        for (int rows = 0; rows < firstLayer.length; rows++) {
            for (int cols = 0; cols < firstLayer[rows].length; cols++) {
                int currentBrickNum = this.matrixFirstLayer.getFirstLayer()[rows][cols];
                //exit if existing num but on undetected location (possible 3 piece of same brick)
                if (brickCounter.containsKey(currentBrickNum)) {
                    if (brickCounter.get(currentBrickNum).getSideA()[0] == rows
                            && brickCounter.get(currentBrickNum).getSideA()[1] == cols
                            || brickCounter.get(currentBrickNum).getSideB()[0] == rows
                            && brickCounter.get(currentBrickNum).getSideB()[1] == cols) {
                        continue;
                    } else {
                        System.out.println("False matrix - incorrect piece of brick found.");
                        System.exit(-1);
                    }
                }
                brickChecker(currentBrickNum,rows, cols);
            }
        }
    }

    /**
     *
     * @param currentBrickNum - number to check with derived adjacent nums
     * @param rows - coordinates for rows
     * @param cols - coordinates for columns
     */
    private void brickChecker(int currentBrickNum, int rows, int cols) {

        /**
         * Finds the first piece of a Brick and register an Id - than save piece location to brick slot
         */
        //Add new brick increase counter + 1
        BrickHolder.addBrick(currentBrickNum);
        //save current location to one of the two slots of the brick
        saveLocationToBrick(currentBrickNum, rows, cols);


        /**
         * Sets coordinates to adjacent +1 +2 brick pieces in all directions (no diagonals).
         */
        //Coordinates
        // y axis
        int[] oneUp = {rows - 1, cols};
        int[] twoUp = {rows - 2, cols};
        int[] oneDown = {rows + 1, cols};
        int[] twoDown = {rows + 2, cols};
        // x axis
        int[] oneLeft = {rows, cols - 1};
        int[] twoLeft = {rows, cols - 2};
        int[] oneRight = {rows, cols + 1};
        int[] twoRight = {rows, cols + 2};

        /**
         * Check and register found on the given coordinates via methods calls
         * for +1 radius
         * for +2 radius
         */
        scanFieldWithRadiusOne(currentBrickNum, oneUp);
        scanFieldWithRadiusTwo(currentBrickNum, twoUp, oneUp);
        scanFieldWithRadiusOne(currentBrickNum, oneDown);
        scanFieldWithRadiusTwo(currentBrickNum, twoDown, oneDown);
        scanFieldWithRadiusOne(currentBrickNum, oneLeft);
        scanFieldWithRadiusTwo(currentBrickNum, twoLeft, oneLeft);
        scanFieldWithRadiusOne(currentBrickNum, oneRight);
        scanFieldWithRadiusTwo(currentBrickNum, twoRight, oneRight);

        /**
         * before method completion an assurance of brick completeness is run
         * a compliant brick is two pieces brick - any other result in faultiness.
         */
        int counter = BrickHolder.getBrickCounter().get(currentBrickNum).getCounter();
        if (counter != 2) {
            System.out.println("False matrix - incorrect piece of brick found.");
            System.exit(-1);
        }
    }
    /**
     * Method is looking if coordinates are valid and perform an equality check with the given number
     * and the number sitting on the given coordinates.
     * than register the piece in the BrickCounter - used to check for brick uniqueness
     * and completeness.
     *
     * performs an additional check if the numbers current, previous are forming a line of same
     * pieces(eventually if two pieces - a brick) or there is a scattered one piece of no matter
     * brick Id (result in Faulty matrix)
     *
     * @param currentBrickNum number(brick piece) to check for equality
     * @param coordinates - referencing coordinates to derive a value to compare with the "brick piece"
     */
    private void scanFieldWithRadiusTwo(int currentBrickNum, int[] coordinates, int[]prevCoordinates) {
        if (isValid(coordinates)) {
            int previousPositionNum = this.matrixFirstLayer.getFirstLayer()[prevCoordinates[0]][prevCoordinates[1]];
            int nextPositionNum = this.matrixFirstLayer.getFirstLayer()[coordinates[0]][coordinates[1]];
            //write equals
            if (currentBrickNum == nextPositionNum
                    && nextPositionNum == previousPositionNum) {
                //Add new brick increase counter + 1
                BrickHolder.addBrick(nextPositionNum);
                //save current location to one of the two slots of the brick
                saveLocationToBrick(nextPositionNum, coordinates[0], coordinates[1]);
            }
            //if not equals - exits program
            if (currentBrickNum == nextPositionNum
                    && nextPositionNum != previousPositionNum) {
                System.out.println("False matrix - scattered brick parts or other.");
                System.exit(-1);
            }
        }
    }

    /**
     * Method see if coordinates are valid and perform an equality check with the given number
     * and the number sitting on the given coordinates.
     * than register the piece in the BrickCounter - used to check for brick uniqueness
     * and completeness.
     *
     * @param currentBrickNum number(brick piece) to check for equality
     * @param coordinates - referencing coordinates to derive a value to compare with the "brick piece"
     */
    private void scanFieldWithRadiusOne(int currentBrickNum, int[] coordinates) {
        if (isValid(coordinates)) {
            int nextPositionNum = this.matrixFirstLayer.getFirstLayer()[coordinates[0]][coordinates[1]];
            if (currentBrickNum == nextPositionNum) {
                //Add new brick increase counter + 1
                BrickHolder.addBrick(nextPositionNum);
                //save current location to one of the two slots of the brick
                saveLocationToBrick(nextPositionNum, coordinates[0], coordinates[1]);
            }
        }
    }

    /**
     * Method checks for a free slot and place results there.
     * A situation with with no free slot on the brick is ensured with "Break ability" of the brick
     * See Brick class
     * @param currentBrickNum - used to search for uniqueness in the BrickCounter class
     * @param rows - coordinates to input in the free A or B slot of the Brick
     * @param cols - coordinates to input in the free A or B slot of the Brick
     */
    public void saveLocationToBrick(int currentBrickNum, int rows, int cols) {
        if (BrickHolder.getBrickCounter().get(currentBrickNum).isEmptyA()) {
            BrickHolder.getBrickCounter().get(currentBrickNum).getSideA()[0] = rows;
            BrickHolder.getBrickCounter().get(currentBrickNum).getSideA()[1] = cols;
        } else {
            BrickHolder.getBrickCounter().get(currentBrickNum).getSideB()[0] = rows;
            BrickHolder.getBrickCounter().get(currentBrickNum).getSideB()[1] = cols;
        }
    }

    /**
     *
     * @param coordinate receives int[] with Rows int[0] and cols int[1] are within the first layer matrix
     * dimensions
     * @return boolean value for inside or outside the given dimensions
     */
    public boolean isValid(int[] coordinate) {
        int[] dimensions = this.matrixFirstLayer.getDimensions();
        int modifiedRows = coordinate[0];
        int modifiedCols = coordinate[1];
        return modifiedRows >= 0
                && modifiedRows < dimensions[0]
                && modifiedCols >= 0
                && modifiedCols < dimensions[1];
    }

    /**
     * Validating whether the received scanned int[] which are used in MatrixFirstLayer class
     * complies with the dimensions which MatrixCreator uses.
     */
    public void validFirstLayerDimensions() {
        int[][] firstLayer = this.matrixFirstLayer.getFirstLayer();
        int[] dimensions = this.matrixFirstLayer.getDimensions();
        if (firstLayer.length == dimensions[0]) {
            for (int rows = 0; rows < firstLayer.length; rows++) {
                if (firstLayer[rows].length != dimensions[1]) {
                    System.out.println("False matrix - dimensions outside allowed.");
                    System.exit(-1);
                }
            }
        }
    }

}
