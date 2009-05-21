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

    /**
     * 
     */
    void drawTopView() {
        ArrayList<ArrayList<String>> drawArray = new ArrayList<ArrayList<String>>();
        int kind = 0;
        ContainerLabel[] localStack;

        // An array list which will write the containerlabel(top one) to the
        // first index, and a string which will represent an int to be the count

        try {
            while (true) {
                localStack = inventory.toArray(kind);
                if (localStack.length > 0) {
                    if (localStack[0] != null) {
                        // need to catch empty stacks
                        ArrayList<String> localArrayListDump = new ArrayList<String>();
                        int objectCount = 0;
                        // find out how many objects we have so we don't hit
                        // some NUllPointers
                        while (localStack[objectCount] != null) {
                            objectCount++;
                        }
                        // add our top container
                        localArrayListDump.add(localStack[objectCount - 1]
                                .toString());
                        // add our count of containers
                        localArrayListDump.add(Integer.toString(objectCount));

                        drawArray.add(localArrayListDump);

                    } else {
                        // if the all the containers have been unloaded and
                        // there are spaces
                        ArrayList<String> localArrayListDump = new ArrayList<String>();
                        localArrayListDump.add("        ");
                        localArrayListDump.add("0");
                        drawArray.add(localArrayListDump);
                    }

                    kind++;
                }
            }
        } catch (CargoException e) {
            // catches when we run out of stacks
            drawTopViewHelper(drawArray);
        } catch (Exception e) {
            // message("Drawing error: " + e);
        }

    }

    /**
     * @param drawArray
     */
    void drawTopViewHelper(ArrayList<ArrayList<String>> drawArray) {

        // drawing of the top lines for how many stacks we have
        for (ArrayList<String> arrayList2 : drawArray) {
            display.append("-----------");
        }
        display.append("\n");

        // drawing the top most stack
        for (ArrayList<String> arrayList : drawArray) {
            display.append("| ");
            display.append("  " + arrayList.get(0) + "  ");
        }
        display.append("\n");

        // drawing the bottom lines of the topmost stack
        for (ArrayList<String> arrayList2 : drawArray) {
            display.append("-----------");
        }
        display.append("\n");
        /*
         * for (ArrayList<String> arrayList : drawArray) { display.append("|
         * "); display.append(" " + arrayList.get(1) + " "); }
         */

        // quick and dirty Hack
        // draw the totals of the stacks
        for (int i = 0; i < drawArray.size() - 1; i++) {
            display.append("| ");
            display.append("     " + drawArray.get(i).get(1) + "      ");

        }
        display.append("|");
        display.append("\n");
        // draw the bottom lines of the totalbox
        for (ArrayList<String> arrayList2 : drawArray) {
            display.append("-----------");
        }

    }
}
