package ec.edu.monster.vista;

import ec.edu.monster.controlador.WS_CONVUNI_CONTROLLER;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.Set;

@WebServlet(name = "WS_CONVUNI_VISTA", urlPatterns = {"/convertir"})
public class WS_CONVUNI_VISTA extends HttpServlet {

    private static final Set<String> PERMITE_NEGATIVOS = Set.of("cToF", "fToC");
    private static final DecimalFormat RESULT_FORMAT = new DecimalFormat("#,##0.00");

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String valorStr = request.getParameter("valor");
        String tipo = request.getParameter("tipo");

        if (valorStr == null || valorStr.isBlank() || tipo == null || tipo.isBlank()) {
            redirigirConError(request, response, "Faltan datos por completar.");
            return;
        }

        float valor;
        try {
            valor = Float.parseFloat(valorStr);
        } catch (NumberFormatException e) {
            redirigirConError(request, response, "El valor ingresado no es numérico.");
            return;
        }

        if (valor < 0 && !PERMITE_NEGATIVOS.contains(tipo)) {
            redirigirConError(request, response, "No se permiten valores negativos para esta conversión.");
            return;
        }

        WS_CONVUNI_CONTROLLER controller = new WS_CONVUNI_CONTROLLER();
        float resultado;

        switch (tipo) {
            case "cmToIn" -> resultado = controller.convertirCentimetrosAPulgadas(valor);
            case "inToCm" -> resultado = controller.convertirPulgadasACentimetros(valor);
            case "cToF" -> resultado = controller.convertirCelsiusAFahrenheit(valor);
            case "fToC" -> resultado = controller.convertirFahrenheitACelsius(valor);
            case "kgToLb" -> resultado = controller.convertirKilogramosALibras(valor);
            case "lbToKg" -> resultado = controller.convertirLibrasAKilogramos(valor);
            default -> {
                redirigirConError(request, response, "La operación solicitada no es válida.");
                return;
            }
        }

        String resultadoFormateado = RESULT_FORMAT.format(resultado);
        String redirectUrl = request.getContextPath()
                + "/conversion.jsp?resultado=" + urlEncode(resultadoFormateado)
                + "&valor=" + urlEncode(valorStr)
                + "&tipo=" + urlEncode(tipo);
        response.sendRedirect(redirectUrl);
    }

    private void redirigirConError(HttpServletRequest request, HttpServletResponse response, String mensaje)
            throws IOException {
        String url = request.getContextPath() + "/conversion.jsp?error=" + urlEncode(mensaje);
        response.sendRedirect(url);
    }

    private String urlEncode(String value) {
        return URLEncoder.encode(value, StandardCharsets.UTF_8);
    }
}