/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.monster.wsclient;
import ec.edu.monster.ws.WSConversionUnidades;
import ec.edu.monster.ws.WSConversionUnidades_Service;
/**
 *
 * @author
 */

import javax.xml.ws.BindingProvider;
import java.util.Map;
import java.util.Scanner;

public class Main {

    // === Configuración ===
    private static final String ENDPOINT =
            "http://DESKTOP-PAO029P:8080/WS_ConUni_SOAPJAVA_GR08/WSConversionUnidades";

    // Credenciales de login
    private static final String USERNAME = "MONSTER";
    private static final String PASSWORD = "MONSTER9";

    // Timeouts opcionales (ms)
    private static final int CONNECT_TIMEOUT_MS = 5000;
    private static final int REQUEST_TIMEOUT_MS = 10000;

    public static void main(String[] args) {
        // Forzar impresión UTF-8 (para °, →, etc.)
        try {
            System.setOut(new java.io.PrintStream(System.out, true, "UTF-8"));
        } catch (Exception ignored) {}

        Scanner sc = new Scanner(System.in);

        System.out.println("========================================");
        System.out.println("   Cliente Consola - WS Conversiones");
        System.out.println("========================================\n");

        // ===== LOGIN =====
        if (!login(sc)) {
            System.out.println("Demasiados intentos fallidos. Saliendo...");
            return;
        }

        // Instanciar el servicio y el port
        ec.edu.monster.ws.WSConversionUnidades_Service service =
                new ec.edu.monster.ws.WSConversionUnidades_Service();
        ec.edu.monster.ws.WSConversionUnidades port =
                service.getWSConversionUnidadesPort();

        // Configurar endpoint y credenciales para el WS (Basic Auth si está activado en Payara)
        Map<String, Object> ctx = ((BindingProvider) port).getRequestContext();
        ctx.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, ENDPOINT);
        ctx.put(BindingProvider.USERNAME_PROPERTY, USERNAME);
        ctx.put(BindingProvider.PASSWORD_PROPERTY, PASSWORD);
        ctx.put("com.sun.xml.ws.connect.timeout", CONNECT_TIMEOUT_MS);
        ctx.put("com.sun.xml.ws.request.timeout", REQUEST_TIMEOUT_MS);

        // ===== MENÚ INTERACTIVO =====
        boolean salir = false;
        while (!salir) {
            mostrarMenu();
            int opcion = leerEntero(sc, "Elige una opción: ");

            try {
                switch (opcion) {
                    case 1: { // cm -> in
                        float cm = leerFloat(sc, "Ingresa centímetros: ");
                        float in = port.centimetrosAPulgadas(cm);
                        System.out.printf("%.6f cm → %.6f in%n%n", cm, in);
                        break;
                    }
                    case 2: { // in -> cm
                        float in = leerFloat(sc, "Ingresa pulgadas: ");
                        float cm = port.pulgadasACentimetros(in);
                        System.out.printf("%.6f in → %.6f cm%n%n", in, cm);
                        break;
                    }
                    case 3: { // °C -> °F
                        float c = leerFloat(sc, "Ingresa °C: ");
                        float f = port.celsiusAFahrenheit(c);
                        System.out.printf("%.6f °C → %.6f °F%n%n", c, f);
                        break;
                    }
                    case 4: { // °F -> °C
                        float f = leerFloat(sc, "Ingresa °F: ");
                        float c = port.fahrenheitACelsius(f);
                        System.out.printf("%.6f °F → %.6f °C%n%n", f, c);
                        break;
                    }
                    case 5: { // kg -> lb
                        float kg = leerFloat(sc, "Ingresa kilogramos: ");
                        float lb = port.kilogramosALibras(kg);
                        System.out.printf("%.6f kg → %.6f lb%n%n", kg, lb);
                        break;
                    }
                    case 6: { // lb -> kg
                        float lb = leerFloat(sc, "Ingresa libras: ");
                        float kg = port.librasAKilogramos(lb);
                        System.out.printf("%.6f lb → %.6f kg%n%n", lb, kg);
                        break;
                    }
                    case 0:
                        salir = true;
                        System.out.println("¡Hasta luego!");
                        break;
                    default:
                        System.out.println("Opción no válida. Intenta de nuevo.\n");
                }
            } catch (Exception ex) {
                System.out.println("Error al invocar el servicio: " + ex.getMessage() + "\n");
            }
        }
    }

    // ======= Utilidades =======

    private static boolean login(Scanner sc) {
        final int MAX_INTENTOS = 3;
        for (int i = 1; i <= MAX_INTENTOS; i++) {
            System.out.print("Usuario: ");
            String u = sc.nextLine().trim();

            System.out.print("Contraseña: ");
            String p = sc.nextLine().trim();

            if (USERNAME.equals(u) && PASSWORD.equals(p)) {
                System.out.println("\nLogin exitoso.\n");
                return true;
            } else {
                System.out.printf("Credenciales inválidas (intento %d/%d)%n%n", i, MAX_INTENTOS);
            }
        }
        return false;
    }

    private static void mostrarMenu() {
        System.out.println("---------- MENÚ ----------");
        System.out.println("1) Centímetros → Pulgadas");
        System.out.println("2) Pulgadas → Centímetros");
        System.out.println("3) °C → °F");
        System.out.println("4) °F → °C");
        System.out.println("5) Kilogramos → Libras");
        System.out.println("6) Libras → Kilogramos");
        System.out.println("0) Salir");
        System.out.println("--------------------------");
    }

    private static int leerEntero(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String linea = sc.nextLine().trim();
            try {
                return Integer.parseInt(linea);
            } catch (NumberFormatException e) {
                System.out.println("Debes ingresar un número entero.\n");
            }
        }
    }

    private static float leerFloat(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String linea = sc.nextLine().trim();
            try {
                return Float.parseFloat(linea);
            } catch (NumberFormatException e) {
                System.out.println("Debes ingresar un número (usa punto decimal).");
            }
        }
    }
}