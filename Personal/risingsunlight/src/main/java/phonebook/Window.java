package phonebook;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Window {
    public Window() {
        // JFrame = a GUI window to add components to

        JFrame frame = new JFrame();
        frame.setTitle("Phonebook");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // by default, it's HIDE_ON_CLOSE
        frame.setResizable(false);
        frame.setSize(420, 420);
        frame.setVisible(true);

        ImageIcon icon = new ImageIcon("Personal\\risingsunlight\\phonebook_icon.png");
        frame.setIconImage(icon.getImage());
    }
}
