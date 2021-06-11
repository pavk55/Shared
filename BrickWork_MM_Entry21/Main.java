package BrickWork;


import BrickWork.core.BuildingBrigade;

/** Console application scanning parameter via class ScanResource and creating first layer
 * matrix - a base for laying down top layer (secondLayer matrix) that is structure in
 * way that no element (Brick) could lay on the exactly same brick from the bottom layer.
 *
 * <p> validations for dimension compliance and verification of brick is applied.
 *
 * Than printing the second layer from a String
 */
public class Main {

    /** Main method Calls mediator class BuildingBrigade and perform all operations
     *  via that class
     * @param args
     */
    public static void main(String[] args) {

        BuildingBrigade buildingBrigade = new BuildingBrigade();
        buildingBrigade.validatingDimensions();
        buildingBrigade.validatingLayer();
        buildingBrigade.layDownSecondLayer();
        System.out.print(buildingBrigade.printSecondMatrix());

    }
}