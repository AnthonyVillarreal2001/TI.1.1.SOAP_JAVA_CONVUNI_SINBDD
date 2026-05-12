package ec.edu.monster.view.ui;

import java.awt.*;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class GradientPanel extends JPanel {
    private final Color c1 = new Color(11, 20, 36);
    private final Color c2 = new Color(23, 37, 63);
    private final Color c3 = new Color(46, 69, 108);
    private Image bgImage;

    public GradientPanel() {
        setOpaque(false);
        setBackgroundImageResource("/img/sullivan.jpg");
    }

    public final void setBackgroundImageResource(String resourcePath) {
        try {
            URL url = getClass().getResource(resourcePath);
            bgImage = (url != null) ? new ImageIcon(url).getImage() : null;
        } catch (Exception ex) {
            bgImage = null;
        }
        repaint();
    }

    @Override protected void paintComponent(Graphics g) {
        int w = getWidth(), h = getHeight();
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        if (bgImage != null) {
            int iw = bgImage.getWidth(this);
            int ih = bgImage.getHeight(this);
            if (iw > 0 && ih > 0) {
                double scale = Math.max((double) w / iw, (double) h / ih);
                int dw = (int) Math.ceil(iw * scale);
                int dh = (int) Math.ceil(ih * scale);
                int dx = (w - dw) / 2;
                int dy = (h - dh) / 2;
                g2.drawImage(bgImage, dx, dy, dw, dh, this);
            }
        } else {
            float[] dist = {0f, 0.5f, 1f};
            Color[] cols = {c1, c2, c3};
            LinearGradientPaint gp = new LinearGradientPaint(0, 0, w, h, dist, cols);
            g2.setPaint(gp);
            g2.fillRect(0, 0, w, h);
        }

        // Overlay oscuro para legibilidad, similar al cliente web.
        g2.setPaint(new GradientPaint(0, 0,
                new Color(5, 12, 22, 70),
                0, h,
                new Color(5, 12, 22, 185)));
        g2.fillRect(0, 0, w, h);

        RadialGradientPaint rg1 = new RadialGradientPaint(
                new Point((int) (w * 0.15), (int) (h * 0.18)),
                Math.max(120, (int) (w * 0.28f)),
                new float[]{0f, 1f},
                new Color[]{new Color(96, 165, 250, 55), new Color(96, 165, 250, 0)});
        g2.setPaint(rg1);
        g2.fillRect(0, 0, w, h);

        RadialGradientPaint rg2 = new RadialGradientPaint(
                new Point((int) (w * 0.88), (int) (h * 0.82)),
                Math.max(120, (int) (w * 0.30f)),
                new float[]{0f, 1f},
                new Color[]{new Color(245, 158, 11, 45), new Color(245, 158, 11, 0)});
        g2.setPaint(rg2);
        g2.fillRect(0, 0, w, h);

        g2.dispose();
        super.paintComponent(g);
    }
}
