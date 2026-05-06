package ec.edu.monster.view;

import ec.edu.monster.controller.AuthController;
import ec.edu.monster.view.ui.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class LoginFrame extends JFrame {

    private final AuthController auth = new AuthController();

    public LoginFrame() {
        setTitle("Conversor de Unidades - Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1120, 700);
        setLocationRelativeTo(null);

        GradientPanel bg = new GradientPanel();
        bg.setBackgroundImageResource("/img/sullivan.jpg");
        bg.setLayout(new GridBagLayout());
        setContentPane(bg);

        JPanel shell = new JPanel(new GridBagLayout());
        shell.setOpaque(false);
        shell.setPreferredSize(new Dimension(980, 560));

        GridBagConstraints sc = new GridBagConstraints();
        sc.gridy = 0;
        sc.insets = new Insets(0, 12, 0, 12);
        sc.fill = GridBagConstraints.BOTH;
        sc.weighty = 1;

        RoundedPanel intro = new RoundedPanel();
        intro.setArc(36);
        intro.setPreferredSize(new Dimension(580, 520));
        intro.setLayout(new BoxLayout(intro, BoxLayout.Y_AXIS));
        intro.setBorder(new EmptyBorder(42, 42, 42, 42));

        JLabel eyebrow = new JLabel("Cliente Web SOAP");
        eyebrow.setFont(eyebrow.getFont().deriveFont(Font.PLAIN, 18f));
        eyebrow.setForeground(new Color(226, 232, 240));
        eyebrow.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel title = new JLabel("Conversor de Unidades");
        title.setFont(title.getFont().deriveFont(Font.BOLD, 52f));
        title.setForeground(new Color(248, 250, 252));
        title.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel copy = new JLabel("Aplicación cliente para consumir el servicio SOAP de conversión de unidades.");
        copy.setFont(copy.getFont().deriveFont(Font.PLAIN, 18f));
        copy.setForeground(new Color(203, 213, 225));
        copy.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel chips = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        chips.setOpaque(false);
        chips.setAlignmentX(Component.LEFT_ALIGNMENT);
        chips.add(makeChip("SOAP"));
        chips.add(makeChip("Java 21"));
        chips.add(makeChip("Interfaz renovada"));

        intro.add(eyebrow);
        intro.add(Box.createVerticalStrut(14));
        intro.add(title);
        intro.add(Box.createVerticalStrut(18));
        intro.add(copy);
        intro.add(Box.createVerticalStrut(28));
        intro.add(chips);

        RoundedPanel panel = new RoundedPanel();
        panel.setArc(30);
        panel.setPreferredSize(new Dimension(380, 560));
        panel.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(12, 32, 12, 32);
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1.0;

        JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT, 14, 0));
        header.setOpaque(false);
        JLabel mark = new JLabel("MC", SwingConstants.CENTER);
        mark.setOpaque(true);
        mark.setBackground(new Color(245, 158, 11));
        mark.setForeground(new Color(8, 16, 29));
        mark.setBorder(new EmptyBorder(10, 14, 10, 14));
        mark.setFont(mark.getFont().deriveFont(Font.BOLD, 14f));

        JPanel ht = new JPanel();
        ht.setOpaque(false);
        ht.setLayout(new BoxLayout(ht, BoxLayout.Y_AXIS));
        JLabel t1 = new JLabel("Acceso seguro");
        t1.setForeground(new Color(203, 213, 225));
        JLabel t2 = new JLabel("Iniciar sesión");
        t2.setForeground(new Color(248, 250, 252));
        t2.setFont(t2.getFont().deriveFont(Font.BOLD, 30f));
        ht.add(t1);
        ht.add(Box.createVerticalStrut(4));
        ht.add(t2);
        header.add(mark);
        header.add(ht);
        c.gridy++; panel.add(header, c);

        PlaceholderTextField txtUser = new PlaceholderTextField("Usuario");
        SoftPasswordField txtPass    = new SoftPasswordField("Contraseña");
        txtUser.setPreferredSize(new Dimension(300, 44));
        txtPass.setPreferredSize(new Dimension(300, 44));
        c.insets = new Insets(28, 32, 10, 32);
        c.gridy++; panel.add(txtUser, c);
        c.gridy++; panel.add(txtPass, c);

        GradientButton btnLogin = new GradientButton("INICIAR SESIÓN");
        btnLogin.setPreferredSize(new Dimension(300, 46));
        c.insets = new Insets(16, 32, 16, 32);
        c.gridy++; panel.add(btnLogin, c);

        JLabel hint = new JLabel("Por favor, ingrese sus credenciales", SwingConstants.CENTER);
        hint.setForeground(new Color(203, 213, 225));
        c.insets = new Insets(10, 32, 12, 32);
        c.gridy++; panel.add(hint, c);

        sc.gridx = 0;
        sc.weightx = 0.62;
        shell.add(intro, sc);

        sc.gridx = 1;
        sc.weightx = 0.38;
        shell.add(panel, sc);

        bg.add(shell, new GridBagConstraints());

        btnLogin.addActionListener(ev -> {
            String u = txtUser.getText();
            String p = new String(txtPass.getPassword());
            if (auth.login(u, p)) {
                SwingUtilities.invokeLater(() -> {
                    dispose();
                    new MainFrame(auth.getUsuario()).setVisible(true);
                });
            } else {
                JOptionPane.showMessageDialog(this,
                        "Usuario o contraseña incorrectos.",
                        "Acceso denegado",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private JLabel makeChip(String text) {
        JLabel chip = new JLabel(text);
        chip.setOpaque(true);
        chip.setBackground(new Color(255, 255, 255, 26));
        chip.setForeground(new Color(248, 250, 252));
        chip.setBorder(new EmptyBorder(8, 12, 8, 12));
        return chip;
    }
}
