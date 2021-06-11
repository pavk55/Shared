package BrickWork.matrices;

/**
 * Simple matrix creation class
 * Uses pre validated dimensions from first layer class - derived fields from MatrixfirstLayer
 */
public class MatrixSecondLayer {

    private int[][] secondLayer;

    /**
     * Creating second layer from given dimensions.
     * @param matrixFirstLayer - from which dimensions are used.
     */
    public MatrixSecondLayer(MatrixFirstLayer matrixFirstLayer) {
        int[] dimensions = matrixFirstLayer.getDimensions();
        this.secondLayer = new int[dimensions[0]][dimensions[1]];
    }

    /**
     *
     * @return - field - secondLayer(used later in BuildingBrigade class)
     */
    public int[][] getSecondLayer() {
        return secondLayer;
    }
}
