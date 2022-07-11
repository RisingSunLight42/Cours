package phonebook;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.plaf.ColorUIResource;

public class Window extends JFrame {
    /**
     * Tutorial video link : https://www.youtube.com/watch?v=Kmgo00avvEw
     */
    public Window() {
        createWindow();
        createLabel("Hello");
        this.setVisible(true);
    }

    private void createWindow() {
        // JFrame = a GUI window to add components to

        this.setTitle("Phonebook");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // by default, it's HIDE_ON_CLOSE
        this.setResizable(false);
        this.setSize(420, 420);

        ImageIcon icon = new ImageIcon("Personal\\risingsunlight\\phonebook_icon.png");
        this.setIconImage(icon.getImage());
        this.getContentPane().setBackground(new ColorUIResource(255, 255, 255));
    }

    private void createLabel(String labelText) {
        // JLabel = a GUI display area for a string of text, an image, or both

        JLabel label = new JLabel(labelText);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        this.add(label);
    }
}
