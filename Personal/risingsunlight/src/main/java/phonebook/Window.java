package phonebook;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.plaf.ColorUIResource;

public class Window extends JFrame {
    public Window() {
        // JFrame = a GUI window to add components to

        this.setTitle("Phonebook");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // by default, it's HIDE_ON_CLOSE
        this.setResizable(false);
        this.setSize(420, 420);
        this.setVisible(true);

        ImageIcon icon = new ImageIcon("Personal\\risingsunlight\\phonebook_icon.png");
        this.setIconImage(icon.getImage());
        this.getContentPane().setBackground(new ColorUIResource(122, 122, 122));
    }
}
