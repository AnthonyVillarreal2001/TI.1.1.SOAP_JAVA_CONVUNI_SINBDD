package ec.edu.monster.view.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FabButton extends JButton {
    private boolean hover, pressed;

    public FabButton() {
        setToolTipText("Limpiar");
        setFocusable(false);
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setPreferredSize(new Dimension(56, 56));

        addMouseListener(new MouseAdapter() {
            @Override public void mouseEntered(MouseEvent e) { hover = true; repaint(); }
            @Override public void mouseExited (MouseEvent e) { hover = false; repaint(); }
            @Override public void mousePressed(MouseEvent e) { pressed = true; repaint(); }
            @Override public void mouseReleased(MouseEvent e){ pressed = false; repaint(); }
        });
    }

    @Override protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int w = getWidth(), h = getHeight();

        Color a = new Color(255, 143, 0);
        Color b = new Color(237, 76, 92);
        if (hover) { a = a.brighter(); b = b.brighter(); }
        if (pressed) { a = a.darker(); b = b.darker(); }

        // Círculo con gradiente
        g2.setPaint(new GradientPaint(0, 0, a, w, h, b));
        g2.fill(new Ellipse2D.Double(0, 0, w - 1, h - 1));

        // Borde suave
        g2.setColor(new Color(255, 255, 255, 160));
        g2.draw(new Ellipse2D.Double(0.5, 0.5, w - 2, h - 2));

        // Ícono de tacho (vector)
        g2.setStroke(new BasicStroke(2.2f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2.setColor(new Color(255, 255, 255, 230));
        int cx = w / 2, cy = h / 2;
        // tapa
        g2.drawLine(cx - 10, cy - 10, cx + 10, cy - 10);
        g2.drawLine(cx - 6, cy - 12, cx + 6, cy - 12);
        // cuerpo
        g2.drawRoundRect(cx - 9, cy - 9, 18, 20, 6, 6);
        // líneas
        g2.drawLine(cx - 3, cy - 4, cx - 3, cy + 7);
        g2.drawLine(cx,     cy - 4, cx,     cy + 7);
        g2.drawLine(cx + 3, cy - 4, cx + 3, cy + 7);

        g2.dispose();
        super.paintComponent(g);
    }
}
