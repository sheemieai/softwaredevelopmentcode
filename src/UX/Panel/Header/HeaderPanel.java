package UX.Panel.Header;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class HeaderPanel extends JPanel {
    public HeaderPanel(String label1, String label2) {
        setLayout(new BorderLayout());
        setBackground(new Color(0xc6b2a0));
        setBorder(new MatteBorder(1, 0, 1, 0, Color.BLACK));

        JLabel textLabel1 = new JLabel("\t" + label1, SwingConstants.LEFT);
        JLabel textLabel2 = new JLabel(label2, SwingConstants.CENTER);

        textLabel1.setForeground(Color.BLACK);
        textLabel2.setForeground(Color.BLACK);

        setAlignmentX(Component.CENTER_ALIGNMENT);
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        setPreferredSize(new Dimension(Integer.MAX_VALUE, 100));

        add(textLabel1, BorderLayout.PAGE_START);
        add(textLabel2, BorderLayout.PAGE_END);
    }
}
