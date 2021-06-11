package BrickWork.core;

import BrickWork.matrices.MatrixFirstLayer;
import BrickWork.matrices.MatrixSecondLayer;
import BrickWork.validators.Validator;

/**
 * Class serves as main coordination between other functional classes in the program
 * It gathers first layer, second layer of the read matrices and calls for a validation
 * operations.
 * <p>
 * printing functionality should be called after creation and validation of
 * the second layer.
 */
public class BuildingBrigade {
    /**
     * Number counter used to draw a unique number on every Brick (adjacent two spaces, horizontally
     * or vertically oriented)
     */
    private static int BRICK_NUM = 1;

    private MatrixFirstLayer matrixFirstLayer;
    private int[][] secondLayer;
    private Validator validator;

    /**
     * Class constructor - bringing in the class - first layer matrix and Validator class
     * additionally initializing empty second layer matrix
     */
    public BuildingBrigade() {
        this.matrixFirstLayer = new MatrixFirstLayer();
        MatrixSecondLayer matrixSecondLayer = new MatrixSecondLayer(this.matrixFirstLayer);
        this.validator = new Validator(this.matrixFirstLayer);
        this.secondLayer = matrixSecondLayer.getSecondLayer();
    }

    /**
     * Calling for a validator method
     */
    public void validatingLayer() {
        this.validator.valid2partsBricksLayer();
    }

    /**
     * Calling for a validator method.
     */
    public void validatingDimensions() {
        this.validator.validFirstLayerDimensions();
    }

    /**
     * method to process first layer(matrix) by
     * iterating every position of the first layer it uses a method quadrant resolver to
     * apply the structural algorithm to the second layer and place a block
     *
     * @return {int[][]} second layer - matrix
     */
    public int[][] layDownSecondLayer() {
        int[][] firstLayer = this.matrixFirstLayer.getFirstLayer();
        for (int rows1 = 0; rows1 < firstLayer.length; rows1 += 2) {
            for (int cols1 = 0; cols1 < firstLayer[rows1].length; cols1 += 2) {
                quadrantResolver(firstLayer, rows1, cols1);
            }
        }
        return this.secondLayer;
    }

    /**
     * quadrantResolver() selects narrow 2x2 space from the input (matrix) and determine how to place a
     * brick in such way to satisfy the requirement.
     * Having a 2x2 space, the possible space to use as referral is on position q2
     * quadrants map
     *          *          2 | 1
     *          *          3 | 4
     * the referral (q2) is check to all adjacent, non-diagonal spaces is {a == b}.
     * if {true} lay a brick on second matrix in such a manner that on brick half is laying on {a || b} and half on
     * non equal space.
     * however if not present - checks q4 quadrant (diagonally) and if no equality there place the Brick as by default
     * In order to place the brick coordinates on the second layer(matrix) - secondLayerSetter() is used.
     *
     * @param firstLayer - derived firstLayer of the matrix or any other validated matrix as an input
     * @param rows1      - coordinate of current row (q2 - default location)
     * @param cols1      - coordinate of current column (q2 - default location)
     */
    private void quadrantResolver(int[][] firstLayer, int rows1, int cols1) {
        /**
         * Coordinates
         * derived coordinates forming a coordinate system in a 2x2 space used later in secondLayerSetter method
         *
         */
        int[] q1 = {rows1, cols1 + 1};
        int[] q2 = {rows1, cols1};
        int[] q3 = {rows1 + 1, cols1};
        int[] q4 = {rows1 + 1, cols1 + 1};
        /**
         * derived numbers from the first layer matrix - used to make equality checks
         * if {true} the secondLayerSetter methods are called to accommodate new coordinates to layer two.
         *          quadrants map
         *          2 | 1
         *          3 | 4
         */
        int numQ1 = brickNum(firstLayer, q1);
        int numQ2 = brickNum(firstLayer, q2);
        int numQ3 = brickNum(firstLayer, q3);
        int numQ4 = brickNum(firstLayer, q4);

        if (isEqual(numQ2, numQ1)) {
            secondLayerSetter(q2, q3);
            secondLayerSetter(q1, q4);
        } else if (isEqual(numQ2, numQ3)) {
            secondLayerSetter(q2, q1);
            secondLayerSetter(q3, q4);
        } else if (isEqual(numQ4, numQ1)) {
            secondLayerSetter(q2, q1);
            secondLayerSetter(q3, q4);
        } else if (isEqual(numQ4, numQ3)) {
            secondLayerSetter(q2, q3);
            secondLayerSetter(q1, q4);
        } else {
            secondLayerSetter(q1, q2);
            secondLayerSetter(q3, q4);
        }
    }

    /**
     * it draws a number from the static field in the class - use it as an unique id;
     * than increase the static field (BRICK_NUM) by one - to ensure uniqueness
     *
     * @param qA - receives two coordinate points for the first part of the brick
     * @param qB - receives two coordinate points for the second part of the brick
     */
    private void secondLayerSetter(int[] qA, int[] qB) {
        this.secondLayer[qA[0]][qA[1]] = BRICK_NUM;
        this.secondLayer[qB[0]][qB[1]] = BRICK_NUM;
        BRICK_NUM++;
    }

    /**
     * Check for equality between inputs. {int}
     *
     * @param numA - a number matching two coordinates(from a matrix. presumably - second layer)
     * @param numB - a number matching two coordinates(from a matrix. presumably - second layer)
     * @return boolean state - determining both inputs are equal.
     */
    private boolean isEqual(int numA, int numB) {
        return numA == numB;
    }

    /**
     * @param firstLayer       - A matrix to derive values from (default - first layer matrix)
     * @param quadrantLocation - int[] holding rows/cols coordinates on position {0, 1}
     * @return int - with the number laying on the given coordinates set from the matrix - input
     */
    private int brickNum(int[][] firstLayer, int[] quadrantLocation) {
        return firstLayer[quadrantLocation[0]][quadrantLocation[1]];
    }

    /**
     * Method forms a final String calling other String methods returning values from processing second layer
     * matrix
     *
     * @return String from cooked Strings
     */
    public String printSecondMatrix() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(upperNBottomLines()).append(System.lineSeparator());
        stringBuilder.append(innerMatrixElements());
        stringBuilder.append(upperNBottomLines()).append(System.lineSeparator());
        return stringBuilder.toString();
    }

    /**
     * innerMatrixElements method adds additional rows to cycle through in between regular second layer rows
     * - totalling (n + (n - 1)) and runs on every element from the second layer matrix and the newly formed structure
     *
     * @return String used as a component to the final String to print.
     */
    private String innerMatrixElements() {
        /**
         * dimensions are used for the iteration process.
         * For rows - number of rows (i) = n + (n - 1) where (n - 1) are the rows filling in between the rows of
         * a normal second layer matrix (class MatrixSecondLayer); n - rows ot the second layer matrix
         * Columns use a regular columns size of the matrix (default)
         */
        int[] dimensions = this.matrixFirstLayer.getDimensions();
        int rowsRun = dimensions[0] + (dimensions[0] - 1);
        int colsRun = dimensions[1];

        /**
         * matrixCounter is used to iterate through (second layer) matrix, smaller sized
         * than the intended iteration to gather the final String components.
         */
        int matrixRowCounter = 0;

        /**
         *
         */
        StringBuilder stringBuilder = new StringBuilder();

        for (int rows1 = 0; rows1 < rowsRun; rows1++) {
            if (rows1 % 2 != 0) {
                matrixRowCounter--;
            }
            for (int cols1 = 0; cols1 < colsRun; cols1++) {

                /**
                 * currentNum and previousNum are used to find equal adjacent spaces
                 * its values redefined later by current coordinates when a second layer matrix is iterated
                 * to gather a String
                 */
                int currentNum = 0;
                int previousNum = 0;

                if (rows1 % 2 == 0) {
                    if (cols1 == 0) {
                        if (this.secondLayer[matrixRowCounter][cols1] >= 10) {
                            stringBuilder.append("* " + this.secondLayer[matrixRowCounter][cols1] + " ");
                        } else {
                            stringBuilder.append("*  " + this.secondLayer[matrixRowCounter][cols1] + " ");
                        }
                    }
                    else if (cols1 == this.secondLayer[matrixRowCounter].length - 1) {
                        currentNum = this.secondLayer[matrixRowCounter][cols1];
                        previousNum = this.secondLayer[matrixRowCounter][cols1 - 1];
                        if (currentNum == previousNum) {
                            if (this.secondLayer[matrixRowCounter][cols1] >= 10) {
                                stringBuilder.append("  " + this.secondLayer[matrixRowCounter][cols1] + " *").append(System.lineSeparator());
                            } else {
                                stringBuilder.append("   " + this.secondLayer[matrixRowCounter][cols1] + " *").append(System.lineSeparator());
                            }
                        } else {
                            if (this.secondLayer[matrixRowCounter][cols1] >= 10) {
                                stringBuilder.append("* " + this.secondLayer[matrixRowCounter][cols1] + " *").append(System.lineSeparator());
                            } else {
                                stringBuilder.append("*  " + this.secondLayer[matrixRowCounter][cols1] + " *").append(System.lineSeparator());
                            }
                        }
                    }
                    else {
                        currentNum = this.secondLayer[matrixRowCounter][cols1];
                        previousNum = this.secondLayer[matrixRowCounter][cols1 - 1];
                        if (currentNum == previousNum) {
                            if (this.secondLayer[matrixRowCounter][cols1] >= 10) {
                                stringBuilder.append("  " + this.secondLayer[matrixRowCounter][cols1] + " ");
                            } else {
                                stringBuilder.append("   " + this.secondLayer[matrixRowCounter][cols1] + " ");
                            }
                        } else {
                            if (this.secondLayer[matrixRowCounter][cols1] >= 10) {
                                stringBuilder.append("* " + this.secondLayer[matrixRowCounter][cols1] + " ");
                            } else {
                                stringBuilder.append("*  " + this.secondLayer[matrixRowCounter][cols1] + " ");
                            }
                        }
                    }
                }
                /**
                 * if Odd Rows
                 */
                else {
                    /**
                     * if Last elements
                     */
                    if (cols1 == this.secondLayer[matrixRowCounter].length - 1) {
                        if (rows1 == 1) {
                            currentNum = this.secondLayer[matrixRowCounter][cols1];
                            previousNum = this.secondLayer[matrixRowCounter - 1][cols1];
                            if (currentNum == previousNum) {
                                stringBuilder.append("*    *").append(System.lineSeparator());
                            } else {
                                stringBuilder.append("* ** *").append(System.lineSeparator());
                            }
                        } else {
                            currentNum = this.secondLayer[matrixRowCounter][cols1];
                            previousNum = this.secondLayer[matrixRowCounter - 1][cols1];
                            if (currentNum == previousNum) {
                                stringBuilder.append("*    *").append(System.lineSeparator());
                            } else {
                                stringBuilder.append("* ** *").append(System.lineSeparator());
                            }
                        }
                    }
                    /**
                     * For all other (not last elements)
                     */
                    else {
                        if (rows1 == 1) {
                            currentNum = this.secondLayer[matrixRowCounter][cols1];
                            previousNum = this.secondLayer[matrixRowCounter - 1][cols1];
                            if (currentNum == previousNum) {
                                stringBuilder.append("*    ");
                            } else {
                                stringBuilder.append("* ** ");
                            }

                        } else {
                            currentNum = this.secondLayer[matrixRowCounter][cols1];
                            previousNum = this.secondLayer[matrixRowCounter - 1][cols1];
                            if (currentNum == previousNum) {
                                stringBuilder.append("*    ");
                            } else {
                                stringBuilder.append("* ** ");
                            }
                        }
                    }
                }
            }
            /**
             * increase The matrix parallel counter when on a non even row by 2
             */
            if (rows1 % 2 == 0) {
                matrixRowCounter += 2;
            }
        }
        return stringBuilder.toString();
    }

    /**
     * @return String to use as upper or bottom line when creating a final String to print.
     * Upper and bottom lines depend on the Matrix (toPrint) dimensions.
     * As the second layer matrix - is exactly the
     * same as dimensions to validated first layer one - first layer dimensions
     * are used to create the lines.
     */
    private String upperNBottomLines() {
        int n = this.matrixFirstLayer.getDimensions()[1];
        StringBuilder stringBuilder = new StringBuilder();
        String preRepeat = "*";
        String repeat = " ** *".repeat(n);
        stringBuilder.append(preRepeat.concat(repeat));
        return stringBuilder.toString();
    }
}
