package ec.edu.monster.view.ui;

import java.awt.*;
import javax.swing.*;

public class RoundedPanel extends JPanel {
    private int arc = 30;

    public RoundedPanel() {
        setOpaque(false);
    }

    public void setArc(int arc) {
        this.arc = arc; repaint();
    }

    @Override protected void paintComponent(Graphics g) {
        int w = getWidth(), h = getHeight();
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Sombra suave
        g2.setColor(new Color(0, 0, 0, 40));
        g2.fillRoundRect(6, 10, w - 12, h - 12, arc + 10, arc + 10);

        // Tarjeta tipo "glass"
        GradientPaint gp = new GradientPaint(0, 0,
                new Color(255, 255, 255, 220),
                0, h,
                new Color(255, 255, 255, 190));
        g2.setPaint(gp);
        g2.fillRoundRect(0, 0, w - 12, h - 12, arc, arc);

        // Borde y brillo superior
        g2.setColor(new Color(255, 255, 255, 180));
        g2.setStroke(new BasicStroke(1.6f));
        g2.drawRoundRect(0, 0, w - 12, h - 12, arc, arc);

        g2.dispose();
        super.paintComponent(g);
    }
}
