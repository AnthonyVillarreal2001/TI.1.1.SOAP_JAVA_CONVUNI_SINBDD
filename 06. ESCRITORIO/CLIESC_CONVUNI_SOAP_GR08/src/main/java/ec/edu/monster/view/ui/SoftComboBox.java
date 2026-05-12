package ec.edu.monster.view.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;

public class SoftComboBox<E> extends JComboBox<E> {

    public SoftComboBox() {
        setOpaque(false);
        setFocusable(true);
        setBorder(new EmptyBorder(6, 12, 6, 10));
        setMaximumRowCount(8);
        setRenderer(new DefaultListCellRenderer() {
            @Override public Component getListCellRendererComponent(
                    JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel l = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                l.setBorder(new EmptyBorder(6, 12, 6, 10));
                l.setFont(l.getFont().deriveFont(Font.PLAIN, 13f));
                if (isSelected) {
                    l.setBackground(new Color(55, 65, 81));
                    l.setForeground(new Color(248, 250, 252));
                } else {
                    l.setBackground(new Color(17, 24, 39));
                    l.setForeground(new Color(226, 232, 240));
                }
                return l;
            }
        });

        setUI(new BasicComboBoxUI() {
            @Override protected JButton createArrowButton() {
                JButton b = new JButton("▾");
                b.setBorder(null);
                b.setFocusable(false);
                b.setContentAreaFilled(false);
                b.setForeground(new Color(226, 232, 240));
                b.setFont(b.getFont().deriveFont(Font.BOLD, 12f));
                return b;
            }
            @Override public void paintCurrentValueBackground(Graphics g, Rectangle bounds, boolean hasFocus) {
                // no-op
            }
        });
    }

    @Override protected void paintComponent(Graphics g) {
        int w = getWidth(), h = getHeight();
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // fondo rounded translúcido
        g2.setPaint(new GradientPaint(0, 0,
            new Color(2, 8, 18, 176),
                0, h,
            new Color(2, 8, 18, 208)));
        g2.fillRoundRect(0, 0, w - 1, h - 1, 14, 14);

        // borde suave
        g2.setColor(new Color(255, 255, 255, 45));
        g2.drawRoundRect(0, 0, w - 1, h - 1, 14, 14);

        g2.dispose();
        super.paintComponent(g);
    }
}
