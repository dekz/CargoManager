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
        type = TYPE.SIDE;
    }

    public enum TYPE {
        SIDE, TOP;
    }

    public void draw() {
        if (type == TYPE.SIDE) {
            sideView.draw();
        } else if (type == TYPE.TOP) {
            topView.draw();
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

        /**
         * 
         */
        public void draw() {
            ArrayList<ArrayList<String>> drawArray = new ArrayList<ArrayList<String>>();
            int kind = 0;
            ContainerLabel[] localStack;

            // An array list which will write the containerlabel(top one) to the
            // first index, and a string which will represent an int to be the
            // count

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
                // catches when we run out of stacks
                drawTopViewHelper(drawArray);
            } catch (Exception e) {
                // message("Drawing error: " + e);
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
