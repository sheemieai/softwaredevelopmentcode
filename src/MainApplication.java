import javax.swing.*;
import java.awt.*;

import UX.Panel.SidePanel;
import UX.Panel.MainPanel;
public class MainApplication extends JFrame {
    public MainApplication() {
        setTitle("Main App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create panels
        MainPanel mainPanel = new MainPanel(1, 1);
        SidePanel sidePanel = new SidePanel(mainPanel);

        // Add panels
        add(sidePanel, BorderLayout.WEST);
        add(mainPanel, BorderLayout.CENTER);

        // Set panel sizes
        sidePanel.setPreferredSize(new Dimension(200, getHeight()));
        mainPanel.setPreferredSize(new Dimension(getWidth() - 200, getHeight()));

        // Frame
        setSize(1350, 670);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        // GUI
        SwingUtilities.invokeLater(() -> {
            new MainApplication().setVisible(true);
        });
    }
}
