package UX.Panel.Logo;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class LogoPanel extends JPanel {
    public LogoPanel(String imagePath) {
        setLayout(new BorderLayout());
        setBackground(new Color(0xc6b2a0));
        setBorder(new MatteBorder(1, 0, 1, 0, Color.BLACK));

        ImageIcon originalIcon = new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(100, 50,  java.awt.Image.SCALE_SMOOTH));

        JLabel logoLabel = new JLabel(originalIcon);
        logoLabel.setHorizontalAlignment(JLabel.CENTER);
        logoLabel.setVerticalAlignment(JLabel.CENTER);
        add(logoLabel, BorderLayout.CENTER);

        setAlignmentX(Component.CENTER_ALIGNMENT);
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        setPreferredSize(new Dimension(Integer.MAX_VALUE, 100));
    }
}
