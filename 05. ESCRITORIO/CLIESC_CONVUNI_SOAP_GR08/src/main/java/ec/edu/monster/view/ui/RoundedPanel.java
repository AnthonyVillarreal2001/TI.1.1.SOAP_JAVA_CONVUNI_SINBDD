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
        int pw = Math.max(0, w - 12);
        int ph = Math.max(0, h - 12);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Sombra más profunda para efecto glass sobre imagen.
        g2.setColor(new Color(0, 0, 0, 95));
        g2.fillRoundRect(8, 12, pw, ph, arc + 10, arc + 10);

        // Superficie tipo "dark glass".
        GradientPaint gp = new GradientPaint(0, 0,
            new Color(7, 15, 28, 198),
                0, h,
            new Color(6, 12, 22, 222));
        g2.setPaint(gp);
        g2.fillRoundRect(0, 0, pw, ph, arc, arc);

        // Borde de contraste.
        g2.setColor(new Color(255, 255, 255, 58));
        g2.setStroke(new BasicStroke(1.6f));
        g2.drawRoundRect(0, 0, pw, ph, arc, arc);

        // Brillo superior sutil.
        g2.setPaint(new GradientPaint(0, 0,
            new Color(255, 255, 255, 36),
            0, ph / 2f,
            new Color(255, 255, 255, 0)));
        g2.fillRoundRect(1, 1, Math.max(0, pw - 2), Math.max(0, ph / 2), arc - 4, arc - 4);

        g2.dispose();
        super.paintComponent(g);
    }
}
