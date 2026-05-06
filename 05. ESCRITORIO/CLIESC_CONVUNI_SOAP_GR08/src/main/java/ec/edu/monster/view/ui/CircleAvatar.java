package ec.edu.monster.view.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class CircleAvatar extends JComponent {
    private Image image;
    private int size = 110;

    public CircleAvatar(Image image) {
        this.image = image;
        setOpaque(false);
        setPreferredSize(new Dimension(size, size + 10));
    }

    public void setImage(Image image) {
        this.image = image; repaint();
    }

    @Override protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int s = size;
        int x = (getWidth() - s) / 2;
        int y = 6;

        // Sombra
        g2.setColor(new Color(0, 0, 0, 45));
        g2.fillOval(x, y + 4, s, s);

        // Aro exterior
        g2.setColor(new Color(255, 255, 255, 220));
        g2.setStroke(new BasicStroke(6f));
        g2.drawOval(x + 3, y + 3, s - 6, s - 6);

        // Clip circular y dibuja imagen
        Shape clip = new Ellipse2D.Float(x + 6, y + 6, s - 12, s - 12);
        g2.setClip(clip);
        if (image != null) {
            g2.drawImage(image, x + 6, y + 6, s - 12, s - 12, null);
        }
        g2.dispose();
    }
}
