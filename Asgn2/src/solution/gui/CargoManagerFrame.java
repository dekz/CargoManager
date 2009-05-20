/**
 * 
 */
package solution.gui;

import java.awt.Dimension;

import javax.swing.JFrame;

/**
 * @author Bodaniel Jeanes and Jacob Evans
 */
public class CargoManagerFrame extends JFrame {
    // Display constants
    protected static final int       WIDTH    = 600;
    protected static final int       HEIGHT   = 500;
    protected static final Dimension PREFSIZE = new Dimension(WIDTH, HEIGHT);

    public CargoManagerFrame() {
        // Initialise the Frame and add the GamePanel
        setTitle("Cargo Manager");
        setSize(PREFSIZE);

        getContentPane().add(new CargoManagerPanel());
        repaint();
    }
}
