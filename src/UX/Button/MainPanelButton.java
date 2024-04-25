package UX.Button;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class MainPanelButton extends JButton {
    private static final int ARC_WIDTH = 15; // Set the horizontal diameter of the arc
    private static final int ARC_HEIGHT = 15; // Set the vertical diameter of the arc

    public MainPanelButton(String label) {
        super(label);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorder(new EmptyBorder(5, 10, 5, 10));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());

        // Make a rounded rectangular button
        g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), ARC_WIDTH, ARC_HEIGHT));

        g2.dispose();

        // Paint the label text and other features
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setStroke(new BasicStroke(1));
        g2.setColor(getForeground());
        // Add a rounded border
        g2.draw(new RoundRectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1, ARC_WIDTH, ARC_HEIGHT));
        g2.dispose();
    }

    @Override
    public Dimension getPreferredSize() {
        Dimension size = super.getPreferredSize();
        size.width += getInsets().left + getInsets().right;
        size.height += getInsets().top + getInsets().bottom;
        return size;
    }
}
