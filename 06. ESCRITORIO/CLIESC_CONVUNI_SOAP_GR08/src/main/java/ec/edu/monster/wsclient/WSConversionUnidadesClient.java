package ec.edu.monster.wsclient;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

/**
 * Interfaz del puerto (cliente) sin clases generadas.
 * MUY IMPORTANTE: targetNamespace y operationName deben coincidir con el WSDL.
 *
 * Con tu paquete de servidor "ec.edu.monster.ws", el targetNamespace por defecto es:
 *   http://ws.monster.edu.ec/
 * Si al abrir el WSDL ves otro, cámbialo aquí.
 */
@WebService(name = "WSConversionUnidades", targetNamespace = "http://ws.monster.edu.ec/")
public interface WSConversionUnidadesClient {

    // ===== Longitud =====
    @WebMethod(operationName = "centimetrosAPulgadas")
    float centimetrosAPulgadas(@WebParam(name = "centimetros") float centimetros);

    @WebMethod(operationName = "pulgadasACentimetros")
    float pulgadasACentimetros(@WebParam(name = "pulgadas") float pulgadas);

    // ===== Temperatura =====
    @WebMethod(operationName = "celsiusAFahrenheit")
    float celsiusAFahrenheit(@WebParam(name = "celsius") float celsius);

    @WebMethod(operationName = "fahrenheitACelsius")
    float fahrenheitACelsius(@WebParam(name = "fahrenheit") float fahrenheit);

    // ===== Masa =====
    @WebMethod(operationName = "kilogramosALibras")
    float kilogramosALibras(@WebParam(name = "kilogramos") float kilogramos);

    @WebMethod(operationName = "librasAKilogramos")
    float librasAKilogramos(@WebParam(name = "libras") float libras);
}
