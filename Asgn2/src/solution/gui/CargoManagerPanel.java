/**
 * 
 */
package solution.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

/**
 * @author Bodaniel Jeanes and Jacob Evans
 */
public class CargoManagerPanel extends JPanel implements ActionListener {

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        initialiseComponents();
    }

    /*
     * This is where we will actually draw all our GUI elements
     */
    private void initialiseComponents() {

    }
}
