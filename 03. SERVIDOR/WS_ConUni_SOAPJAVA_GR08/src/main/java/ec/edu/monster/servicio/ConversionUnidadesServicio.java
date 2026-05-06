package ec.edu.monster.servicio;

/**
 *
 * @author danie
 */
public class ConversionUnidadesServicio {

    // Método auxiliar para redondear a 2 decimales
    private float redondear2Decimales(float valor) {
        return Math.round(valor * 100f) / 100f;
    }

    // Conversión de longitud
    public float centimetrosAPulgadas(float centimetros) {
        return redondear2Decimales(centimetros / 2.54f);
    }

    public float pulgadasACentimetros(float pulgadas) {
        return redondear2Decimales(pulgadas * 2.54f);
    }

    // Conversión de temperatura
    public float celsiusAFahrenheit(float celsius) {
        return redondear2Decimales((celsius * 9 / 5) + 32);
    }

    public float fahrenheitACelsius(float fahrenheit) {
        return redondear2Decimales((fahrenheit - 32) * 5 / 9);
    }

    // Conversión de masa
    public float kilogramosALibras(float kilogramos) {
        return redondear2Decimales(kilogramos * 2.20462f);
    }

    public float librasAKilogramos(float libras) {
        return redondear2Decimales(libras / 2.20462f);
    }
}
