/**
 * 
 */
package solution.gui;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * @author Bodaniel Jeanes and Jacob Evans
 */
public class CargoManagerFrame extends JFrame {
    // Display constants
    protected static final int       WIDTH    = 800;
    protected static final int       HEIGHT   = 600;
    protected static final Dimension PREFSIZE = new Dimension(WIDTH, HEIGHT);

    /**
     * @param args
     */
    public static void main(String[] args) {
        // Set up the HiringProblem and launch the frame

        Integer numStacks;
        Integer maxHeight;
        Integer maxContainers;
        CargoManagerFrame frame;

        try {
            numStacks = Integer.parseInt(args[0]);
            maxHeight = Integer.parseInt(args[1]);
            maxContainers = Integer.parseInt(args[2]);

            frame = new CargoManagerFrame(numStacks, maxHeight, maxContainers);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Argument error");
            System.err.println("Starting with no default inventory");
            frame = new CargoManagerFrame();
        }

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

    public CargoManagerFrame() {
        // Initialise the Frame and add the GamePanel
        setTitle("Cargo Manager");
        setSize(PREFSIZE);
        try {
            getContentPane().add(new CargoManagerPanel());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Could not start application: "
                    + e.getMessage());
            System.exit(1); // ugly but effective for now
        }
        repaint();
    }

    /**
     * @param numStacks
     * @param maxHeight
     * @param maxContainers
     */
    public CargoManagerFrame(Integer numStacks, Integer maxHeight,
            Integer maxContainers) {
        // Initialise the Frame and add the GamePanel
        setTitle("Cargo Manager");
        setSize(PREFSIZE);
        try {
            getContentPane().add(
                    new CargoManagerPanel(numStacks, maxHeight, maxContainers));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Could not start application: "
                    + e.getMessage());
            System.exit(1); // ugly but effective for now
        }
        repaint();
    }
}
