/**
 * 
 */
package solution.gui;

import java.io.PrintStream;
import java.util.ArrayList;

import solution.CargoException;
import solution.CargoInventory;
import solution.ContainerLabel;

/**
 * @author bjeanes
 */
public class TempCode {
    private CargoInventory inventory;
    private PrintStream    display;

    ArrayList<String> drawContainers(ContainerLabel[] containerLabels, int kind) {

        ArrayList<String> stack = new ArrayList<String>();

        if (containerLabels.length > 0) {
            stack.add("----------");

            for (ContainerLabel label : containerLabels) {
                if (label != null) {
                    stack.add(" " + label.toString() + " ");
                    stack.add("----------");
                }
            }
        } else {
            stack.add("          ");
        }

        return stack;

    }

    void reDraw() {
        ContainerLabel[] localContainers;
        int kind = 0;
        int previousStackCount = 0;
        int maxLineCount = 0;
        ArrayList<String> viewLines = new ArrayList<String>();
        viewLines.add("");
        while (true) {
            try {
                localContainers = inventory.toArray(kind);
                ArrayList<String> stackStrings = drawContainers(
                        localContainers, kind);

                if ((localContainers.length > 0) && (previousStackCount > 0)) {
                    ArrayList<String> divider = new ArrayList<String>();
                    divider.add("-");
                    for (int i = 0; i < Math.max(previousStackCount,
                            localContainers.length); i++) {
                        divider.add("|");
                        divider.add("-");
                    }
                    mergeArrayLists(viewLines, divider);
                } else {
                    ArrayList<String> emptyDivider = new ArrayList<String>();
                    emptyDivider.add(" ");
                    mergeArrayLists(viewLines, emptyDivider);
                }

                if (localContainers.length > 0) {
                    mergeArrayLists(viewLines, stackStrings);
                } else {
                    ArrayList<String> emptyLine = new ArrayList<String>();
                    emptyLine.add("          ");
                    mergeArrayLists(viewLines, emptyLine);
                }

                previousStackCount = localContainers.length;

                if (numberOfRows(localContainers.length) > maxLineCount) {
                    maxLineCount = numberOfRows(localContainers.length);
                }

                kind++;
                System.out.println("found a container");
            } catch (CargoException e) {
                // we reached the end of the stacks

                if (previousStackCount > 0) {
                    ArrayList<String> divider = new ArrayList<String>();
                    divider.add("-\n");
                    for (int i = 0; i < previousStackCount; i++) {
                        divider.add("|\n");
                        divider.add("-\n");
                    }
                    mergeArrayLists(viewLines, divider);
                } else {
                    ArrayList<String> emptyDivider = new ArrayList<String>();
                    emptyDivider.add(" \n");
                    mergeArrayLists(viewLines, emptyDivider);
                }

                for (String line : viewLines) {
                    display.append(line);
                }

                System.out.println("ran out of containers bailing");
                return;
            }

        }
        // "---------

    }

    int numberOfRows(int containers) {
        if (containers != 0) {
            return 0;
        } else {
            return 2 + containers * 3;
        }
    }

    ArrayList<String> mergeArrayLists(ArrayList<String> originalList,
            ArrayList<String> newList) {
        ArrayList<String> returnArrayList = new ArrayList<String>();
        int height = Math.max(originalList.size(), newList.size());
        int originalListWidth = originalList.get(0).length();
        int newListWidth = newList.get(0).length();

        for (int i = 0; i < height; i++) {
            String originalString;
            String newString;

            if (originalList.size() > (height - 1)) {
                char[] spaces = new char[originalListWidth];

                for (int j = 0; j < originalListWidth; j++) {
                    spaces[j] = ' ';
                }

                originalString = new String(spaces);
            } else {
                originalString = originalList.get(i);
            }

            if (newList.size() > height - 1) {
                char[] spaces = new char[newListWidth];

                for (int j = 0; j < newListWidth; j++) {
                    spaces[j] = ' ';
                }

                newString = new String(spaces);
            } else {
                newString = originalList.get(i);
            }

            returnArrayList.add(originalString + newString);
        }

        return returnArrayList;
    }

}
