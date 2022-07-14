package phonebook;

import java.awt.Color;
import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.FontUIResource;

public class Window extends JFrame {
    /**
     * Tutorial video link : https://www.youtube.com/watch?v=Kmgo00avvEw
     */
    public Window() {
        createWindow();
        // JPanel panelHello = createPanel(0, 0, 250, 250);
        JPanel panelHello = createPanel(0, 0, 250, 250);
        createLabel("Hello", 20, panelHello);
        createButton(panelHello);
        this.add(panelHello);
        this.setVisible(true);
    }

    private void createWindow() {
        // JFrame = a GUI window to add components to

        this.setTitle("Phonebook");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // by default, it's HIDE_ON_CLOSE
        this.setResizable(false);
        this.setLayout(null);
        this.setSize(420, 420);

        ImageIcon icon = new ImageIcon("Personal\\risingsunlight\\phonebook_icon.png");
        this.setIconImage(icon.getImage());
        this.getContentPane().setBackground(new ColorUIResource(255, 255, 255));
    }

    private JPanel createPanel(int positionX, int positionY, int width, int height) {
        // JPanel = a component which contain other components

        JPanel panel = new JPanel();
        panel.setBounds(positionX, positionY, width, height);
        panel.setBackground(Color.CYAN);
        panel.setLayout(new BorderLayout());
        return panel;
    }

    private void createLabel(String labelText, int fontSize, JPanel panelToAdd) {
        // JLabel = a GUI display area for a string of text, an image, or both

        JLabel label = new JLabel(labelText);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setFont(new FontUIResource("Times New Roman", FontUIResource.PLAIN, fontSize));
        panelToAdd.add(label);
    }

    private void createButton(JPanel panelToAdd) {
        // JButton = a button that performs an action when clicked on

        JButton button = new JButton();
        panelToAdd.add(button);
    }
}
