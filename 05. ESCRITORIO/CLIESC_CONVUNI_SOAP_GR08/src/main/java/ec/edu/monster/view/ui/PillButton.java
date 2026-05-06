package ec.edu.monster.view.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PillButton extends JButton {
    private final Color c1, c2;
    private boolean hover, pressed;

    public PillButton(String text, Color c1, Color c2) {
        super(text);
        this.c1 = c1;
        this.c2 = c2;
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setForeground(Color.WHITE);
        setFont(getFont().deriveFont(Font.BOLD, 12f));
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

        Color a = c1, b = c2;
        if (hover) { a = a.brighter(); b = b.brighter(); }
        if (pressed){ a = a.darker();  b = b.darker();  }

        g2.setPaint(new GradientPaint(0, 0, a, w, h, b));
        int r = h; // súper redondo (“pill”)
        g2.fillRoundRect(0, 0, w, h, r, r);

        g2.setColor(new Color(255,255,255,120));
        g2.drawRoundRect(0, 0, w-1, h-1, r, r);

        g2.dispose();
        super.paintComponent(g);
    }
}
