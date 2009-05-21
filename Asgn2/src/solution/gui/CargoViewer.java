/**
 * 
 */
package solution.gui;

import java.util.ArrayList;

import javax.swing.JTextArea;

import solution.CargoException;
import solution.CargoInventory;
import solution.ContainerLabel;

/**
 * @author Bodaniel Jeanes, Jacob Evans
 */
public class CargoViewer {
    private final SideViewer sideView;
    private final TopViewer  topView;
    private TYPE             type;

    public CargoViewer(CargoInventory inventory, JTextArea display) {
        sideView = new SideViewer(inventory, display);
        topView = new TopViewer(inventory, display);
        type = TYPE.TOP;
    }

    public enum TYPE {
        SIDE, TOP;
    }

    public void draw() {
        if (type == TYPE.SIDE) {
            System.out.println("Displaying Side View");
            sideView.draw();
        } else if (type == TYPE.TOP) {
            System.out.println("Displaying Top View");
            topView.draw();
        } else {
            System.out.println("Unknown view type");
        }
    }

    public TYPE getType() {
        return type;
    }

    public void setType(TYPE type) {
        this.type = type;
    }

    protected class Viewer {
        protected final CargoInventory inventory;
        protected final JTextArea      display;

        protected Viewer(CargoInventory inventory, JTextArea display) {
            this.inventory = inventory;
            this.display = display;
        }
    }

    protected class TopViewer extends Viewer {
        public TopViewer(CargoInventory inventory, JTextArea display) {
            super(inventory, display);
        }

        public void draw() {

            ArrayList<ArrayList<String>> drawArray = getData();

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
             * for (ArrayList<String> arrayList : drawArray) {
             * display.append("| "); display.append(" " + arrayList.get(1) + "
             * "); }
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

        /**
         * 
         */
        @SuppressWarnings("finally")
        public ArrayList<ArrayList<String>> getData() {
            ArrayList<ArrayList<String>> drawArray = new ArrayList<ArrayList<String>>();
            int kind = 0;
            ContainerLabel[] stack;

            // An array list which will write the containerlabel(top one) to the
            // first index, and a string which will represent an int to be the
            // count

            try {
                while (true) {
                    stack = inventory.toArray(kind);
                    if (stack.length > 0) {
                        if (stack[0] != null) {
                            // need to catch empty stacks
                            ArrayList<String> localArrayListDump = new ArrayList<String>();
                            int objectCount = 0;
                            // find out how many objects we have so we don't hit
                            // some NUllPointers
                            while (stack[objectCount] != null) {
                                objectCount++;
                            }
                            // add our top container
                            localArrayListDump.add(stack[objectCount - 1]
                                    .toString());
                            // add our count of containers
                            localArrayListDump.add(Integer
                                    .toString(objectCount));

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
                // catches when we run out of stacks, then we wait for finally
                // to return the stack
            } finally {
                return drawArray;
            }
        }
    }

    protected class SideViewer extends Viewer {
        public SideViewer(CargoInventory inventory, JTextArea display) {
            super(inventory, display);
        }

        public void draw() {

        }
    }
}
