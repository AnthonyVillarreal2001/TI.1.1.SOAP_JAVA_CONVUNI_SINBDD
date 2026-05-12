package ec.edu.monster.view.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GradientButton extends JButton {
    private boolean hover = false;
    private boolean pressed = false;

    public GradientButton(String text) {
        super(text);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setForeground(Color.WHITE);
        setFont(getFont().deriveFont(Font.BOLD, 14f));
        setOpaque(false);

        addMouseListener(new MouseAdapter() {
            @Override public void mouseEntered(MouseEvent e) { hover = true; repaint(); }
            @Override public void mouseExited(MouseEvent e)  { hover = false; repaint(); }
            @Override public void mousePressed(MouseEvent e) { pressed = true; repaint(); }
            @Override public void mouseReleased(MouseEvent e){ pressed = false; repaint(); }
        });
    }

    @Override protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int w = getWidth(), h = getHeight();

        if (!isEnabled()) {
            g2.setColor(new Color(180, 180, 190));
            g2.fillRoundRect(0, 0, w, h, 22, 22);
        } else {
            // Hover/pressed alteran la intensidad
            int a1 = pressed ? 205 : (hover ? 230 : 220);
            int a2 = pressed ? 205 : (hover ? 230 : 220);
            GradientPaint gp = new GradientPaint(0, 0,
                    new Color(97, 100, 255, a1),
                    w, h,
                    new Color(143, 84, 233, a2));
            g2.setPaint(gp);
            g2.fillRoundRect(0, 0, w, h, 22, 22);
        }

        // Borde suave
        g2.setColor(new Color(255, 255, 255, 120));
        g2.setStroke(new BasicStroke(1.2f));
        g2.drawRoundRect(0, 0, w - 1, h - 1, 22, 22);

        g2.dispose();
        super.paintComponent(g);
    }
}
