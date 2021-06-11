package BrickWork.matrices;

import BrickWork.inputResources.ScanResource;

/**
 * Class to create and validate parameters for a matrix creation.
 * Initialize a matrix in the constructor that read a console values.
 */
public class MatrixCreator {
    private int[][] matrix;
    private int[] dimensions = new int[2];

    /**
     * Constructor that takes dimension values from a console and process it via setter
     * creating empty matrix from it.
     * if values not according to requirements - exits program with message and returning (-1);
     */
    public MatrixCreator() {
        int[] scannedArray = ScanResource.getScannedArray();
        if (setRows(scannedArray[0]) == -1 || setCols(scannedArray[1]) == -1) {
            System.out.println("False matrix - incorrect dimensions input");
            System.exit(-1);
        }
        this.matrix = new int
                [setRows(scannedArray[0])]
                [setCols(scannedArray[1])];
        setDimensions(setRows(scannedArray[0]), setCols(scannedArray[1]));
    }

    /**
     * Set the values to a dimensions field
     * @param setRows checked dimension values for rows
     * @param setCols checked dimension values for columns
     */
    private void setDimensions(int setRows, int setCols) {
        this.dimensions[0] = setRows;
        this.dimensions[1] = setCols;
    }

    /**
     *
     * @param i - is a value for columns
     * @return the validated value to be used as a dimension setter
     */
    private int setCols(int i) {
        if (i % 2 == 0
                && i < 100
                && i > 0 ) {
            return i;
        } else {
            return -1;
        }
    }

    /**
     *
     * @param i - is a value for rows
     * @return the validated value to be used as a dimension setter
     */
    private int setRows(int i) {
        if (i % 2 == 0
                && i < 100
                && i > 0 ) {
            return i;
        } else {
            return -1;
        }
    }

    /**
     *
     * @return the field matrix  - int[][]
     */
    public int[][] getMatrix() {
        return matrix;
    }

    /**
     *
     * @return return the field - dimensions {int []}
     */
    public int[] getDimensions() {
        return dimensions;
    }
}
