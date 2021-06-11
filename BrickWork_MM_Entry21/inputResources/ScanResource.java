package BrickWork.inputResources;

import java.util.Arrays;
import java.util.Scanner;

/**
 *Class uses Scanner class to create custom return
 */
public class ScanResource {

    private static Scanner scanner = new Scanner(System.in);

    /**
     *
     * @return custom element from scanner class - a {int[]} array by iterating over string values
     * and parse them to Integer.
     */
    public static int[] getScannedArray() {
        return Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}
