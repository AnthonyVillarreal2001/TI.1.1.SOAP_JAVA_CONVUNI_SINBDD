/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.monster.prueba;

import ec.edu.monster.servicio.ConversionUnidadesServicio;

/**
 *
 * @author danie
 */
public class PruebaConversionUnidades {
    private static final float EPS = 1e-3f;

    public static void main(String[] args) {

        // ===== DATOS =====
        float centimetros = 1.0f;
        float pulgadas = 1.0f;

        float celsius = 25.0f;         // 25°C → 77°F
        float fahrenheit = 77.0f;      // 77°F → 25°C aprox

        float kilogramos = 1.0f;       // 1 kg → 2.20462 lb
        float libras = 2.20462f;       // 2.20462 lb → 1 kg

        // ===== PROCESO =====
        ConversionUnidadesServicio svc = new ConversionUnidadesServicio();

        float pulgadasConvertidas     = svc.centimetrosAPulgadas(centimetros);
        float centimetrosConvertidos  = svc.pulgadasACentimetros(pulgadas);

        float fDeC = svc.celsiusAFahrenheit(celsius);
        float cDeF = svc.fahrenheitACelsius(fahrenheit);

        float lbDeKg = svc.kilogramosALibras(kilogramos);
        float kgDeLb = svc.librasAKilogramos(libras);

        // Round-trip simples para validar aproximación
        float cmRoundTrip = svc.pulgadasACentimetros(svc.centimetrosAPulgadas(centimetros));
        float inRoundTrip = svc.centimetrosAPulgadas(svc.pulgadasACentimetros(pulgadas));
        float cRoundTrip  = svc.fahrenheitACelsius(svc.celsiusAFahrenheit(celsius));
        float fRoundTrip  = svc.celsiusAFahrenheit(svc.fahrenheitACelsius(fahrenheit));
        float kgRoundTrip = svc.librasAKilogramos(svc.kilogramosALibras(kilogramos));
        float lbRoundTrip = svc.kilogramosALibras(svc.librasAKilogramos(libras));

        // ===== REPORTE =====
        System.out.println("=== PRUEBAS DE CONVERSION ===");
        System.out.printf("Entrada: %.5f cm -> %.5f in%n", centimetros, pulgadasConvertidas);
        System.out.printf("Entrada: %.5f in -> %.5f cm%n", pulgadas, centimetrosConvertidos);

        System.out.printf("Entrada: %.2f °C -> %.2f °F%n", celsius, fDeC);
        System.out.printf("Entrada: %.2f °F -> %.2f °C%n", fahrenheit, cDeF);

        System.out.printf("Entrada: %.5f kg -> %.5f lb%n", kilogramos, lbDeKg);
        System.out.printf("Entrada: %.5f lb -> %.5f kg%n", libras, kgDeLb);

        // Validaciones básicas con tolerancia
        checkApprox("cm -> in -> cm", centimetros, cmRoundTrip, EPS);
        checkApprox("in -> cm -> in", pulgadas, inRoundTrip, EPS);
        checkApprox("C -> F -> C", celsius, cRoundTrip, EPS);
        checkApprox("F -> C -> F", fahrenheit, fRoundTrip, EPS);
        checkApprox("kg -> lb -> kg", kilogramos, kgRoundTrip, EPS);
        checkApprox("lb -> kg -> lb", libras, lbRoundTrip, 1e-2f); // tolerancia un poco mayor por la constante
    }

    private static void checkApprox(String nombre, float esperado, float obtenido, float eps) {
        boolean ok = Math.abs(esperado - obtenido) <= eps;
        System.out.printf("Check %-16s | esperado=%.6f, obtenido=%.6f, eps=%.6f -> %s%n",
                nombre, esperado, obtenido, eps, ok ? "OK" : "FALLO");
    }
}
