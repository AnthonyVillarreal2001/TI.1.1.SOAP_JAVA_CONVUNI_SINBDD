/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.monster.ws;

/**
 *
 * @author danie
 */
import ec.edu.monster.servicio.ConversionUnidadesServicio;
import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;

@WebService(serviceName = "WSConversionUnidades")
public class WSConversionUnidades {

    // ===== Longitud =====
    @WebMethod(operationName = "centimetrosAPulgadas")
    public float centimetrosAPulgadas(@WebParam(name = "centimetros") float centimetros) {
        ConversionUnidadesServicio conversionUnidades = new ConversionUnidadesServicio();
        return conversionUnidades.centimetrosAPulgadas(centimetros);
    }

    @WebMethod(operationName = "pulgadasACentimetros")
    public float pulgadasACentimetros(@WebParam(name = "pulgadas") float pulgadas) {
        ConversionUnidadesServicio conversionUnidades = new ConversionUnidadesServicio();
        return conversionUnidades.pulgadasACentimetros(pulgadas);
    }

    // ===== Temperatura =====
    @WebMethod(operationName = "celsiusAFahrenheit")
    public float celsiusAFahrenheit(@WebParam(name = "celsius") float celsius) {
        ConversionUnidadesServicio conversionUnidades = new ConversionUnidadesServicio();
        return conversionUnidades.celsiusAFahrenheit(celsius);
    }

    @WebMethod(operationName = "fahrenheitACelsius")
    public float fahrenheitACelsius(@WebParam(name = "fahrenheit") float fahrenheit) {
        ConversionUnidadesServicio conversionUnidades = new ConversionUnidadesServicio();
        return conversionUnidades.fahrenheitACelsius(fahrenheit);
    }

    // ===== Masa =====
    @WebMethod(operationName = "kilogramosALibras")
    public float kilogramosALibras(@WebParam(name = "kilogramos") float kilogramos) {
        ConversionUnidadesServicio conversionUnidades = new ConversionUnidadesServicio();
        return conversionUnidades.kilogramosALibras(kilogramos);
    }

    @WebMethod(operationName = "librasAKilogramos")
    public float librasAKilogramos(@WebParam(name = "libras") float libras) {
        ConversionUnidadesServicio conversionUnidades = new ConversionUnidadesServicio();
        return conversionUnidades.librasAKilogramos(libras);
    }
}
