package ec.edu.monster.view.ui;

import javax.swing.*;
import java.awt.*;

public class SoftPasswordField extends JPasswordField {
    private final String placeholder;

    public SoftPasswordField(String placeholder) {
        this.placeholder = placeholder;
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(10, 14, 10, 14));
        setForeground(new Color(40, 45, 60));
        setCaretColor(new Color(80, 80, 100));
    }

    @Override protected void paintComponent(Graphics g) {
        // Fondo redondeado translúcido
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int w = getWidth(), h = getHeight();
        g2.setPaint(new GradientPaint(0, 0, new Color(255, 255, 255, 210),
                                      0, h, new Color(255, 255, 255, 175)));
        g2.fillRoundRect(0, 0, w - 1, h - 1, 18, 18);
        g2.setColor(new Color(0, 0, 0, 35));
        g2.drawRoundRect(0, 0, w - 1, h - 1, 18, 18);
        g2.dispose();

        super.paintComponent(g);

        if (getPassword().length == 0 && !isFocusOwner()) {
            Graphics2D gph = (Graphics2D) g.create();
            gph.setColor(new Color(120, 120, 140, 140));
            gph.setFont(getFont().deriveFont(Font.PLAIN, getFont().getSize2D()));
            gph.drawString(placeholder, 14, getHeight()/2 + getFont().getSize()/2 - 2);
            gph.dispose();
        }
    }
}
