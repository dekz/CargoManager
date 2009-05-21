/**
 * 
 */
package solution.gui;

import javax.swing.JTextArea;

import solution.CargoInventory;

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
        private final CargoInventory inventory;
        private final JTextArea      display;

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
