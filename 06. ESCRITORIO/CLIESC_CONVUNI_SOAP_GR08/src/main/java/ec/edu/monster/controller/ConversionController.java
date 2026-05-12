package ec.edu.monster.controller;

import ec.edu.monster.model.ConversionType;
import ec.edu.monster.wsclient.WSConversionUnidadesClient;

import jakarta.xml.ws.Service;
import javax.xml.namespace.QName;
import java.net.URL;

public class ConversionController {

    // URL del WSDL del servidor (otro proyecto)
    private static final String WSDL_URL =
        "http://localhost:8080/WS_ConUni_SOAPJAVA_GR08/WSConversionUnidades?wsdl";

    // Deben coincidir con el WSDL: targetNamespace, serviceName y portName
    private static final String NS = "http://ws.monster.edu.ec/";
    private static final QName SERVICE_QNAME = new QName(NS, "WSConversionUnidades");
    private static final QName PORT_QNAME    = new QName(NS, "WSConversionUnidadesPort");

    private final WSConversionUnidadesClient port;

    public ConversionController() {
        try {
            URL wsdl = new URL(WSDL_URL);
            Service svc = Service.create(wsdl, SERVICE_QNAME);
            // Crea el proxy del puerto usando tu interfaz anotada
            this.port = svc.getPort(PORT_QNAME, WSConversionUnidadesClient.class);
        } catch (Exception e) {
            throw new IllegalStateException(
                "No se pudo leer el WSDL en: " + WSDL_URL + "\n" + e.getMessage(), e);
        }
    }

    public float convertir(ConversionType tipo, float valor) {
        switch (tipo) {
            case CM_A_IN: return port.centimetrosAPulgadas(valor);
            case IN_A_CM: return port.pulgadasACentimetros(valor);
            case C_A_F:   return port.celsiusAFahrenheit(valor);
            case F_A_C:   return port.fahrenheitACelsius(valor);
            case KG_A_LB: return port.kilogramosALibras(valor);
            case LB_A_KG: return port.librasAKilogramos(valor);
            default: throw new IllegalArgumentException("Tipo no soportado");
        }
    }
}
