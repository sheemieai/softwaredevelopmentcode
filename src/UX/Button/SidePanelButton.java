package UX.Button;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class SidePanelButton extends JButton {
    public SidePanelButton(String label, int height) {
        super(label);
        setMaximumSize(new Dimension(Integer.MAX_VALUE, height));
        setPreferredSize(new Dimension(Integer.MAX_VALUE, height));
        setAlignmentX(Component.CENTER_ALIGNMENT);

        setBorder(new MatteBorder(1, 0, 1, 0, Color.BLACK));

        setMargin(new Insets(10, 10, 10, 10));
    }
}
