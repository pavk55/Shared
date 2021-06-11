package BrickWork.matrices;

import BrickWork.inputResources.ScanResource;

/**
 * Class used to return a first layer matrix of Bricks. Later used as main building
 * foundation for the second layer Matrix
 */
public class MatrixFirstLayer {

    private int[][] firstLayer;
    private int[] dimensions;

    /**
     * Constructor uses matrixCreator to return empty matrix - which later on is processed
     * via the setFirstLayer() - all the values are field in the int[][] by inputs from the console.
     * Dimensions are passed on from MatrixCreator class to the local dimension field.
     *
     */
    public MatrixFirstLayer() {
//        1.
        MatrixCreator matrixCreator = new MatrixCreator();
//        2.
        setFirstLayer(matrixCreator.getMatrix());
//        3.
        this.dimensions = matrixCreator.getDimensions();
    }

    /**
     * Method settles the firstLayer as cycle through the rows
     * and calls custom Scanner method to assign int[] to the certain row of the given matrix.
     * @param matrix - and empty matrix (default - validated first layer matrix)
     */
    private void setFirstLayer(int[][] matrix) {
        for (int rowsAr1 = 0; rowsAr1 < matrix.length; rowsAr1++) {
            matrix[rowsAr1] = ScanResource.getScannedArray();
        }
        this.firstLayer = matrix;
    }

    /**
     *
     * @return field resource - first layer
     */
    public int[][] getFirstLayer() {
        return firstLayer;
    }

    /**
     *
     * @return field resource - dimensions - used later for various validations and layer two
     * foundation
     */
    public int[] getDimensions() {
        return dimensions;
    }
}
