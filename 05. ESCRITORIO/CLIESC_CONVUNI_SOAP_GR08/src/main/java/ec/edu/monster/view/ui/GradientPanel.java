package ec.edu.monster.view.ui;

import java.awt.*;
import javax.swing.JPanel;

public class GradientPanel extends JPanel {
    private final Color c1 = new Color(126, 64, 244);
    private final Color c2 = new Color(164, 93, 255);
    private final Color c3 = new Color(247, 136, 245);

    public GradientPanel() { setOpaque(false); }

    @Override protected void paintComponent(Graphics g) {
        int w = getWidth(), h = getHeight();
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        float[] dist = {0f, 0.5f, 1f};
        Color[] cols = {c1, c2, c3};
        LinearGradientPaint gp = new LinearGradientPaint(0, 0, w, h, dist, cols);
        g2.setPaint(gp);
        g2.fillRect(0, 0, w, h);
        g2.dispose();
        super.paintComponent(g);
    }
}
