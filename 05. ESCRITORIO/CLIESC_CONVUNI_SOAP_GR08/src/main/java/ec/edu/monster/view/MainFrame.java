package ec.edu.monster.view;

import ec.edu.monster.controller.ConversionController;
import ec.edu.monster.model.ConversionCategory;
import ec.edu.monster.model.ConversionType;
import ec.edu.monster.view.ui.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class MainFrame extends JFrame {

    private final ConversionController conv = new ConversionController();
    private final String usuario;

    private final SoftComboBox<ConversionCategory> cbCategoria = new SoftComboBox<>();
    private final SoftComboBox<ConversionType> cbTipo = new SoftComboBox<>();
    private final PlaceholderTextField txtValor = new PlaceholderTextField("Ingrese el valor numérico");
    private final JLabel lblResultado = new JLabel("Ingresa un valor y presiona Convertir.");

    private final Map<ConversionCategory, List<ConversionType>> tiposPorCategoria = new EnumMap<>(ConversionCategory.class);

    public MainFrame(String usuario) {
        this.usuario = usuario;

        setTitle("Conversor de Unidades");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280, 760);
        setLocationRelativeTo(null);

        GradientPanel bg = new GradientPanel();
        bg.setBackgroundImageResource("/img/sullivan.jpg");
        bg.setLayout(new BorderLayout(18, 18));
        bg.setBorder(new EmptyBorder(20, 20, 20, 20));
        setContentPane(bg);

        RoundedPanel topCard = new RoundedPanel();
        topCard.setArc(28);
        topCard.setPreferredSize(new Dimension(10, 140));
        topCard.setLayout(new BorderLayout());

        JPanel top = new JPanel(new BorderLayout(12, 0));
        top.setOpaque(false);
        top.setBorder(new EmptyBorder(18, 24, 18, 24));

        JPanel brand = new JPanel();
        brand.setOpaque(false);
        brand.setLayout(new BoxLayout(brand, BoxLayout.Y_AXIS));
        JLabel eyebrow = new JLabel("Cliente SOAP");
        eyebrow.setForeground(new Color(226, 232, 240));
        eyebrow.setFont(eyebrow.getFont().deriveFont(Font.PLAIN, 15f));
        JLabel titulo = new JLabel("Conversiones de Unidades");
        titulo.setFont(titulo.getFont().deriveFont(Font.BOLD, 38f));
        titulo.setForeground(new Color(248, 250, 252));
        JLabel copy = new JLabel("Aplicación cliente para conversión de unidades vía SOAP.");
        copy.setForeground(new Color(203, 213, 225));
        copy.setFont(copy.getFont().deriveFont(Font.PLAIN, 16f));
        brand.add(eyebrow);
        brand.add(Box.createVerticalStrut(6));
        brand.add(titulo);
        brand.add(Box.createVerticalStrut(8));
        brand.add(copy);
        top.add(brand, BorderLayout.CENTER);

        JPanel right = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 6));
        right.setOpaque(false);
        JLabel pillUser = new JLabel("Conectado como  " + usuario.toUpperCase());
        pillUser.setOpaque(true);
        pillUser.setBackground(new Color(255, 255, 255, 25));
        pillUser.setForeground(new Color(226, 232, 240));
        pillUser.setBorder(new EmptyBorder(10, 14, 10, 14));
        PillButton btnLogout = new PillButton("CERRAR SESIÓN",
                new Color(242, 90, 90), new Color(247, 117, 96));
        btnLogout.setPreferredSize(new Dimension(170, 38));
        right.add(pillUser);
        right.add(btnLogout);
        top.add(right, BorderLayout.EAST);
        topCard.add(top, BorderLayout.CENTER);
        bg.add(topCard, BorderLayout.NORTH);

        btnLogout.addActionListener(e -> { dispose(); new LoginFrame().setVisible(true); });

        JPanel workspace = new JPanel(new GridBagLayout());
        workspace.setOpaque(false);
        bg.add(workspace, BorderLayout.CENTER);

        GridBagConstraints wc = new GridBagConstraints();
        wc.gridy = 0;
        wc.insets = new Insets(10, 10, 10, 10);
        wc.fill = GridBagConstraints.BOTH;
        wc.weighty = 1;

        RoundedPanel hero = new RoundedPanel();
        hero.setArc(30);
        hero.setPreferredSize(new Dimension(690, 500));
        hero.setLayout(new BoxLayout(hero, BoxLayout.Y_AXIS));
        hero.setBorder(new EmptyBorder(30, 34, 30, 34));

        JLabel heroEyebrow = new JLabel("Panel de trabajo");
        heroEyebrow.setForeground(new Color(226, 232, 240));
        heroEyebrow.setFont(heroEyebrow.getFont().deriveFont(Font.PLAIN, 16f));

        JLabel heroTitle = new JLabel("Una sola superficie, más abierta y menos cargada.");
        heroTitle.setForeground(new Color(248, 250, 252));
        heroTitle.setFont(heroTitle.getFont().deriveFont(Font.BOLD, 36f));

        JLabel heroText = new JLabel("Selecciona la categoría, define la operación y escribe el valor.");
        heroText.setForeground(new Color(203, 213, 225));
        heroText.setFont(heroText.getFont().deriveFont(Font.PLAIN, 17f));

        JPanel featureRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        featureRow.setOpaque(false);
        featureRow.add(makeChip("Longitud"));
        featureRow.add(makeChip("Temperatura"));
        featureRow.add(makeChip("Masa"));

        hero.add(heroEyebrow);
        hero.add(Box.createVerticalStrut(14));
        hero.add(heroTitle);
        hero.add(Box.createVerticalStrut(18));
        hero.add(heroText);
        hero.add(Box.createVerticalStrut(24));
        hero.add(featureRow);

        RoundedPanel form = new RoundedPanel();
        form.setArc(30);
        form.setPreferredSize(new Dimension(460, 500));
        form.setLayout(new GridBagLayout());
        form.setBorder(new EmptyBorder(12, 14, 12, 14));

        GridBagConstraints fc = new GridBagConstraints();
        fc.gridx = 0;
        fc.fill = GridBagConstraints.HORIZONTAL;
        fc.weightx = 1;
        fc.insets = new Insets(7, 12, 7, 12);

        JLabel lblCat = sectionLabel("Categoría de conversión");
        JLabel lblTipo = sectionLabel("Tipo de conversión");
        JLabel lblVal = sectionLabel("Valor");

        cbCategoria.setPreferredSize(new Dimension(380, 46));
        cbTipo.setPreferredSize(new Dimension(380, 46));
        txtValor.setPreferredSize(new Dimension(380, 46));
        txtValor.setHorizontalAlignment(JTextField.CENTER);

        GradientButton btnConvertir = new GradientButton("CONVERTIR");
        btnConvertir.setPreferredSize(new Dimension(190, 44));
        JButton btnLimpiar = new PillButton("LIMPIAR", new Color(71, 97, 255), new Color(88, 60, 229));
        btnLimpiar.setPreferredSize(new Dimension(150, 44));

        JPanel actionRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        actionRow.setOpaque(false);
        actionRow.add(btnConvertir);
        actionRow.add(btnLimpiar);

        RoundedPanel resultStrip = new RoundedPanel();
        resultStrip.setArc(24);
        resultStrip.setLayout(new BorderLayout());
        resultStrip.setPreferredSize(new Dimension(380, 110));
        resultStrip.setBorder(new EmptyBorder(14, 16, 14, 16));
        JLabel resTitle = new JLabel("Resultado");
        resTitle.setForeground(new Color(248, 250, 252));
        resTitle.setFont(resTitle.getFont().deriveFont(Font.BOLD, 15f));
        lblResultado.setForeground(new Color(226, 232, 240));
        lblResultado.setFont(lblResultado.getFont().deriveFont(Font.PLAIN, 13f));

        JPanel resultBody = new JPanel();
        resultBody.setOpaque(false);
        resultBody.setLayout(new BoxLayout(resultBody, BoxLayout.Y_AXIS));
        resultBody.add(resTitle);
        resultBody.add(Box.createVerticalStrut(6));
        resultBody.add(lblResultado);
        resultStrip.add(resultBody, BorderLayout.CENTER);

        int y = 0;
        fc.gridy = y++; form.add(lblCat, fc);
        fc.gridy = y++; form.add(cbCategoria, fc);
        fc.gridy = y++; form.add(lblTipo, fc);
        fc.gridy = y++; form.add(cbTipo, fc);
        fc.gridy = y++; form.add(lblVal, fc);
        fc.gridy = y++; form.add(txtValor, fc);
        fc.gridy = y++; form.add(actionRow, fc);
        fc.gridy = y++; form.add(resultStrip, fc);

        btnConvertir.addActionListener(e -> convertir());
        btnLimpiar.addActionListener(e -> limpiar());

        wc.gridx = 0;
        wc.weightx = 0.6;
        workspace.add(hero, wc);
        wc.gridx = 1;
        wc.weightx = 0.4;
        workspace.add(form, wc);

        // FAB limpiar
        FabButton btnClear = new FabButton();
        getLayeredPane().add(btnClear, JLayeredPane.PALETTE_LAYER);
        addComponentListener(new ComponentAdapter() {
            @Override public void componentResized(ComponentEvent e) {
                int d = 56, m = 24;
                int x = getWidth()  - d - m;
                int y = getHeight() - d - (m + 40);
                btnClear.setBounds(x, y, d, d);
            }
        });
        btnClear.addActionListener(e -> limpiar());

        cargarModelo();
        cbCategoria.addActionListener(e -> cargarTipos());
    }

    private JLabel sectionLabel(String text) {
        JLabel l = new JLabel(text);
        l.setForeground(new Color(226, 232, 240));
        l.setFont(l.getFont().deriveFont(Font.BOLD, 14f));
        return l;
    }

    private JLabel makeChip(String text) {
        JLabel chip = new JLabel(text);
        chip.setOpaque(true);
        chip.setBackground(new Color(255, 255, 255, 26));
        chip.setForeground(new Color(248, 250, 252));
        chip.setBorder(new EmptyBorder(8, 12, 8, 12));
        return chip;
    }

    private void cargarModelo() {
        cbCategoria.addItem(ConversionCategory.LONGITUD);
        cbCategoria.addItem(ConversionCategory.TEMPERATURA);
        cbCategoria.addItem(ConversionCategory.MASA);

        tiposPorCategoria.put(ConversionCategory.LONGITUD, List.of(
                ConversionType.CM_A_IN, ConversionType.IN_A_CM));
        tiposPorCategoria.put(ConversionCategory.TEMPERATURA, List.of(
                ConversionType.C_A_F, ConversionType.F_A_C));
        tiposPorCategoria.put(ConversionCategory.MASA, List.of(
                ConversionType.KG_A_LB, ConversionType.LB_A_KG));

        cargarTipos();
    }

    private void cargarTipos() {
        ConversionCategory cat = (ConversionCategory) cbCategoria.getSelectedItem();
        cbTipo.removeAllItems();
        if (cat == null) return;
        for (ConversionType t : tiposPorCategoria.get(cat)) cbTipo.addItem(t);
    }

    private void convertir() {
        try {
            float v = Float.parseFloat(txtValor.getText().trim());
            ConversionType t = (ConversionType) cbTipo.getSelectedItem();
            if (t == null) { lblResultado.setText("Seleccione el tipo de conversión."); return; }
            float r = conv.convertir(t, v);
            lblResultado.setText(formatea(t, v, r));
        } catch (NumberFormatException ex) {
            lblResultado.setText("Ingrese un número válido.");
        } catch (Exception ex) {
            lblResultado.setText("Error: " + ex.getMessage());
        }
    }

    private void limpiar() {
        cbCategoria.setSelectedIndex(-1);
        cbTipo.removeAllItems();
        txtValor.setText("");
        lblResultado.setText("Ingresa un valor y presiona Convertir.");
        txtValor.requestFocusInWindow();
    }

    private String formatea(ConversionType tipo, float v, float r) {
        switch (tipo) {
            case CM_A_IN: return String.format("%.4f cm = %.4f in", v, r);
            case IN_A_CM: return String.format("%.4f in = %.4f cm", v, r);
            case C_A_F:   return String.format("%.2f °C = %.2f °F", v, r);
            case F_A_C:   return String.format("%.2f °F = %.2f °C", v, r);
            case KG_A_LB: return String.format("%.4f kg = %.4f lb", v, r);
            case LB_A_KG: return String.format("%.4f lb = %.4f kg", v, r);
            default:      return String.valueOf(r);
        }
    }
}