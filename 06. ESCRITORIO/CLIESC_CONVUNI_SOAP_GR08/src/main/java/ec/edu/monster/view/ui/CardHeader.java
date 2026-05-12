package ec.edu.monster.view.ui;

import javax.swing.*;
import java.awt.*;

public class CardHeader extends JPanel {
    public static final int HEADER_HEIGHT = 64;  // <-- altura única para las 3 cards

    private final String title;
    private final Color c1, c2, badge;

    public CardHeader(String title, Color c1, Color c2, Color badge) {
        this.title = title;
        this.c1 = c1; this.c2 = c2; this.badge = badge;
        setOpaque(false);
        setPreferredSize(new Dimension(10, HEADER_HEIGHT));
        setMinimumSize(new Dimension(10, HEADER_HEIGHT));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, HEADER_HEIGHT));
    }

    @Override protected void paintComponent(Graphics g) {
        int w = getWidth(), h = getHeight();
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setPaint(new GradientPaint(0, 0, c1, w, h, c2));
        g2.fillRoundRect(0, 0, w, h, 18, 18);

        int d = 22, x = 16, y = (h - d) / 2;
        g2.setColor(badge);
        g2.fillOval(x, y, d, d);
        g2.setColor(Color.WHITE);
        g2.setFont(getFont().deriveFont(Font.BOLD, 14f));
        g2.drawString("i", x + 8, y + 15);

        g2.setFont(getFont().deriveFont(Font.BOLD, 16f));
        g2.drawString(title, x + d + 12, y + 16);

        g2.dispose();
        super.paintComponent(g);
    }
}
