package ec.edu.monster.view;

import ec.edu.monster.controller.AuthController;
import ec.edu.monster.view.ui.*;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class LoginFrame extends JFrame {

    private static final String AVATAR_PATH = "/img/sullivan.jpg";
    private final AuthController auth = new AuthController();

    public LoginFrame() {
        setTitle("Conversor de Unidades - Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(880, 540);
        setLocationRelativeTo(null);

        GradientPanel bg = new GradientPanel();
        bg.setLayout(new GridBagLayout());
        setContentPane(bg);

        RoundedPanel card = new RoundedPanel();
        card.setPreferredSize(new Dimension(430, 470));
        card.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(8, 22, 8, 22);
        c.gridx = 0; c.gridy = 0;
        c.fill = GridBagConstraints.HORIZONTAL;

        // ===== Avatar circular con aro
        JComponent avatarComp;
        try {
            URL url = getClass().getResource(AVATAR_PATH);
            Image img = (url != null)
                    ? new ImageIcon(url).getImage().getScaledInstance(140, 140, Image.SCALE_SMOOTH)
                    : null;
            avatarComp = new CircleAvatar(img);
        } catch (Exception e) {
            JLabel fallback = new JLabel("M", SwingConstants.CENTER);
            fallback.setFont(fallback.getFont().deriveFont(Font.BOLD, 64f));
            fallback.setPreferredSize(new Dimension(110, 110));
            avatarComp = fallback;
        }
        c.gridy++; card.add(avatarComp, c);

        // ===== Títulos
        JLabel titulo = new JLabel("¡Bienvenido!", SwingConstants.CENTER);
        titulo.setFont(titulo.getFont().deriveFont(Font.BOLD, 28f));
        c.gridy++; card.add(titulo, c);

        JLabel subt = new JLabel("Conversor de Unidades", SwingConstants.CENTER);
        subt.setFont(subt.getFont().deriveFont(Font.PLAIN, 14f));
        c.gridy++; card.add(subt, c);

        // ===== Inputs estilo "glass"
        PlaceholderTextField txtUser = new PlaceholderTextField("Usuario");
        SoftPasswordField txtPass    = new SoftPasswordField("Contraseña");
        txtUser.setPreferredSize(new Dimension(360, 44));
        txtPass.setPreferredSize(new Dimension(360, 44));
        c.gridy++; card.add(txtUser, c);
        c.gridy++; card.add(txtPass, c);

        // ===== Botón con gradiente y hover
        GradientButton btnLogin = new GradientButton("INICIAR SESIÓN");
        btnLogin.setPreferredSize(new Dimension(360, 46));
        c.gridy++; card.add(btnLogin, c);

        // ===== Hint
        JLabel hint = new JLabel("Por favor, ingrese sus credenciales", SwingConstants.CENTER);
        hint.setForeground(new Color(110, 110, 130));
        c.gridy++; card.add(hint, c);

        bg.add(card, new GridBagConstraints());

        // Acción del botón
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
}
