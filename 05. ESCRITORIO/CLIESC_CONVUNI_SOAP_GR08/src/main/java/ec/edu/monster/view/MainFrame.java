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

    private static final int HEADER_H = CardHeader.HEADER_HEIGHT; // 64
    private static final int GAP_UNDER_HEADER = 12;

    public MainFrame(String usuario) {
        this.usuario = usuario;

        setTitle("Conversor de Unidades");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1180, 680);
        setLocationRelativeTo(null);

        // ===== fondo
        GradientPanel bg = new GradientPanel();
        bg.setLayout(new BorderLayout(18, 18));
        bg.setBorder(new EmptyBorder(12, 16, 16, 16));
        setContentPane(bg);

        // ===== top bar
        JPanel top = new JPanel(new BorderLayout());
        top.setOpaque(false);

        JLabel titulo = new JLabel("Conversiones de Unidades");
        titulo.setFont(titulo.getFont().deriveFont(Font.BOLD, 24f));
        titulo.setBorder(new EmptyBorder(10, 8, 0, 8));
        titulo.setForeground(new Color(35, 35, 60));
        top.add(titulo, BorderLayout.WEST);

        JPanel right = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 6));
        right.setOpaque(false);
        JLabel pillUser = new JLabel("Conectado como  " + usuario.toUpperCase());
        pillUser.setOpaque(true);
        pillUser.setBackground(new Color(255, 255, 255, 210));
        pillUser.setForeground(new Color(90, 92, 110));
        pillUser.setBorder(new EmptyBorder(8, 12, 8, 12));
        PillButton btnLogout = new PillButton("CERRAR SESIÓN",
                new Color(242, 90, 90), new Color(247, 117, 96));
        btnLogout.setPreferredSize(new Dimension(150, 34));
        right.add(pillUser);
        right.add(btnLogout);
        top.add(right, BorderLayout.EAST);
        bg.add(top, BorderLayout.NORTH);

        btnLogout.addActionListener(e -> { dispose(); new LoginFrame().setVisible(true); });

        // ===== centro
        JPanel center = new JPanel(new GridBagLayout());
        center.setOpaque(false);
        bg.add(center, BorderLayout.CENTER);

        GridBagConstraints gc = new GridBagConstraints();
        gc.insets = new Insets(8, 8, 8, 8);
        gc.fill   = GridBagConstraints.BOTH;
        gc.anchor = GridBagConstraints.NORTH;
        gc.gridy  = 0;

        // Card 1: Categoría
        RoundedPanel card1 = buildCard(
                new CardHeader("Categoría de Conversión",
                        new Color(71, 97, 255), new Color(88, 60, 229), new Color(85, 108, 255)),
                body -> {
                    cbCategoria.setPreferredSize(new Dimension(360, 44));
                    cbCategoria.setMaximumSize  (new Dimension(360, 44));
                    cbCategoria.setAlignmentX(Component.CENTER_ALIGNMENT);
                    body.add(cbCategoria);
                });

        // Card 2: Tipo
        RoundedPanel card2 = buildCard(
                new CardHeader("Tipo de Conversión",
                        new Color(255, 166, 41), new Color(235, 87, 87), new Color(255, 184, 76)),
                body -> {
                    cbTipo.setPreferredSize(new Dimension(360, 44));
                    cbTipo.setMaximumSize  (new Dimension(360, 44));
                    cbTipo.setAlignmentX(Component.CENTER_ALIGNMENT);
                    body.add(cbTipo);
                });

        // Card 3: Valor
        RoundedPanel card3 = buildCard(
                new CardHeader("Ingrese el Valor",
                        new Color(44, 187, 99), new Color(16, 163, 127), new Color(63, 196, 120)),
                body -> {
                    txtValor.setPreferredSize(new Dimension(360, 44));
                    txtValor.setMaximumSize  (new Dimension(360, 44));
                    txtValor.setHorizontalAlignment(JTextField.CENTER);
                    txtValor.setAlignmentX(Component.CENTER_ALIGNMENT);

                    GradientButton btnConvertir = new GradientButton("CONVERTIR");
                    btnConvertir.setPreferredSize(new Dimension(220, 44));
                    btnConvertir.setMaximumSize  (new Dimension(220, 44));
                    btnConvertir.setAlignmentX(Component.CENTER_ALIGNMENT);

                    body.add(txtValor);
                    body.add(Box.createVerticalStrut(16));
                    body.add(btnConvertir);

                    btnConvertir.addActionListener(e -> convertir());
                });

        gc.gridx = 0; center.add(card1, gc);
        gc.gridx = 1; center.add(card2, gc);
        gc.gridx = 2; center.add(card3, gc);

        // Resultado
        RoundedPanel cardRes = new RoundedPanel();
        cardRes.setLayout(new BorderLayout());
        cardRes.setPreferredSize(new Dimension(420, 120));
        JPanel resHeader = new JPanel(new FlowLayout(FlowLayout.LEFT, 18, 16));
        resHeader.setOpaque(false);
        JLabel lres = new JLabel("Resultado");
        lres.setFont(lres.getFont().deriveFont(Font.BOLD, 16f));
        resHeader.add(lres);
        cardRes.add(resHeader, BorderLayout.NORTH);

        JPanel resBody = new JPanel(new GridBagLayout());
        resBody.setOpaque(false);
        lblResultado.setFont(lblResultado.getFont().deriveFont(Font.PLAIN, 13f));
        resBody.add(lblResultado);
        cardRes.add(resBody, BorderLayout.CENTER);

        gc.gridy = 1; gc.gridx = 0; gc.gridwidth = 3;
        center.add(cardRes, gc);

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

        // modelo
        cargarModelo();
        cbCategoria.addActionListener(e -> cargarTipos());
    }

    /* --------------------------------------------------------------
       Crea una card con:
         - Header contenido en un wrapper de ALTURA FIJA (HEADER_H)
         - Espaciador FIJO (GAP_UNDER_HEADER) debajo del header
         - Body con padding uniforme
       Así las 3 cabeceras quedan alineadas al pixel.
       -------------------------------------------------------------- */
    private RoundedPanel buildCard(CardHeader header, java.util.function.Consumer<JPanel> bodyBuilder) {
        RoundedPanel card = new RoundedPanel();
        card.setPreferredSize(new Dimension(360, 230));
        card.setLayout(null); // Layout absoluto para control total
        
        // Header en posición fija
        header.setBounds(0, 0, 360, HEADER_H);
        card.add(header);

        // Body debajo del header + gap
        JPanel body = new JPanel();
        body.setOpaque(false);
        body.setLayout(new BoxLayout(body, BoxLayout.Y_AXIS));
        body.setBorder(new EmptyBorder(16, 24, 24, 24));
        body.setBounds(0, HEADER_H + GAP_UNDER_HEADER, 360, 230 - (HEADER_H + GAP_UNDER_HEADER));
        card.add(body);

        bodyBuilder.accept(body);
        return card;
    }

    // Espaciador vertical con min=pref=max = h
    private static Component fixedVSpace(int h) {
        Dimension d = new Dimension(0, h);
        return new Box.Filler(d, d, d);
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