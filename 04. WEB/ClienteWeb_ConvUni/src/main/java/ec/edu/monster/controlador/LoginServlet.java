package ec.edu.monster.controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    private static final String USER = "MONSTER";
    private static final String PASS = "MONSTER9";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String usuario = request.getParameter("usuario");
        String password = request.getParameter("password");
        HttpSession sesion = request.getSession();

        if (USER.equals(usuario) && PASS.equals(password)) {
            // Guardar sesión y limpiar cualquier error previo
            sesion.setAttribute("usuario", usuario);
            sesion.removeAttribute("loginError");

            // Ir al panel principal
            response.sendRedirect("conversion.jsp");
        } else {
            // Flash message: se leerá y borrará en index.jsp
            sesion.setAttribute("loginError", "Usuario o contraseña incorrectos.");
            response.sendRedirect("index.jsp");
        }
    }

    // (Opcional) si alguien hace GET a /login, redirigimos al formulario
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.sendRedirect("index.jsp");
    }
}
