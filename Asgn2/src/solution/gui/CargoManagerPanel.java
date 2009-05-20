/**
 * 
 */
package solution.gui;

import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import solution.CargoException;
import solution.CargoInventory;
import solution.ContainerLabel;

/**
 * @author Bodaniel Jeanes and Jacob Evans
 */
public class CargoManagerPanel extends JPanel implements ActionListener {
    private JButton        loadBtn;
    private JButton        unloadBtn;
    private JTextArea      display;
    private JTextField     input;
    private CargoInventory inventory;
    private ContainerLabel currentContainer;

    public CargoManagerPanel() {
        initialiseComponents();
        try {
            inventory = new CargoInventory(5, 5, 20);
        } catch (Exception e) {

        }
        reDraw();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */

    // @Override
    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();

        if ((currentContainer = getContainerFromInput()) != null) {
            try {
                System.out.println("loading container "
                        + currentContainer.toString());
                if (source == loadBtn) {
                    inventory.loadContainer(currentContainer);
                } else if (source == unloadBtn) {
                    inventory.unloadContainer(currentContainer);
                }
            } catch (CargoException e) {
                message(e.getMessage());
            }
        } else {
            input.setText("");
            message("Invalid shipping container label");
        }
    }

    private void message(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }

    private ContainerLabel getContainerFromInput() {
        Pattern pattern = Pattern.compile("\\d{8}");
        Matcher matcher = pattern.matcher(input.getText());
        if (matcher.matches()) {
            try {
                Integer kind = Integer.parseInt(input.getText(0, 3));
                Integer identifier = Integer.parseInt(input.getText(3, 5));
                Integer kindLength = kind.toString().length();
                Integer identifierLength = identifier.toString().length();
                return new ContainerLabel(kind, kindLength, identifier,
                        identifierLength);
            } catch (Exception e) {
                return null;
            } finally {
                input.setText("");
            }
        }
        return null;
    }

    /*
     * This is where we will actually draw all our GUI elements
     */
    private void initialiseComponents() {
        GridBagLayout layout = new GridBagLayout();
        setLayout(layout);

        initialiseTextDisplay();
        initialiseButtons();
    }

    /**
     * 
     */
    private void initialiseButtons() {
        GridBagConstraints constraints = new GridBagConstraints();

        loadBtn = new JButton("Load");
        loadBtn.addActionListener(this);
        loadBtn.setVisible(true);
        addToPanel(loadBtn, constraints, 0, 3, 1, 1);

        unloadBtn = new JButton("Unload");
        unloadBtn.addActionListener(this);
        unloadBtn.setVisible(true);
        addToPanel(unloadBtn, constraints, 1, 3, 1, 1);

        repaint();
    }

    /**
     * 
     */
    private void initialiseTextDisplay() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.weightx = 100;
        constraints.weighty = 100;
        constraints.fill = GridBagConstraints.BOTH;

        // Text Area and Scroll Pane
        display = new JTextArea(" ", 50, 50);
        display.setEditable(false);
        display.setLineWrap(true);
        display.setFont(new Font("Courier New", Font.PLAIN, 12));
        display.setBorder(BorderFactory.createEtchedBorder());
        addToPanel(display, constraints, 0, 0, 4, 1);

        // input field
        input = new JTextField();
        input.addActionListener(this);
        constraints.weighty = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.SOUTH;
        addToPanel(input, constraints, 0, 1, 4, 1);

        repaint();
    }

    /**
     * A convenience method to add a component to given grid bag layout
     * locations. Code due to Cay Horstmann
     * 
     * @author Cay Horstmann (via INB370)
     * @param c
     *            the component to add
     * @param constraints
     *            the grid bag constraints to use
     * @param x
     *            the x grid position
     * @param y
     *            the y grid position
     * @param w
     *            the grid width
     * @param h
     *            the grid height
     */
    private void addToPanel(Component c, GridBagConstraints constraints, int x,
            int y, int w, int h) {
        constraints.gridx = x;
        constraints.gridy = y;
        constraints.gridwidth = w;
        constraints.gridheight = h;
        add(c, constraints);
    }

    void drawContainers(ContainerLabel[] labels) {
    // ------------ //12 Dashes
    // | 01200432 | //
    // ------------
    // i.toString()
    // display.getColumns()
    // display.getRows()
    // display.insert(string, int pos)
    // display.setColumns(int)
    // display.append(string)
    // display.setText(str)
    // display.setText("");
    // for (ContainerLabel containerLabel : labels) {
    // display.append("-----------\n| ");
    // display.append(containerLabel.toString());
    // display.append("\n-----------");
    // }
    }

    void reDraw() {
        ContainerLabel[] localContainers;
        int kind = 0;
        while (true) {
            try {
                localContainers = inventory.toArray(kind);
                drawContainers(localContainers);
                kind++;
                System.out.println("found a container");
            } catch (CargoException e) {
                // we reached the end of the stacks
                return;
            }

        }
    }
}
