package ec.edu.monster.controlador;

import ec.edu.monster.cliente.ws.WSConversionUnidades;
import ec.edu.monster.cliente.ws.WSConversionUnidades_Service;

public class WS_CONVUNI_CONTROLLER {

    private final WSConversionUnidades_Service service;
    private final WSConversionUnidades port;

    public WS_CONVUNI_CONTROLLER() {
        service = new WSConversionUnidades_Service();
        port = service.getWSConversionUnidadesPort();
    }

    public float convertirCentimetrosAPulgadas(float cm) {
        return port.centimetrosAPulgadas(cm);
    }

    public float convertirPulgadasACentimetros(float in) {
        return port.pulgadasACentimetros(in);
    }

    public float convertirCelsiusAFahrenheit(float c) {
        return port.celsiusAFahrenheit(c);
    }

    public float convertirFahrenheitACelsius(float f) {
        return port.fahrenheitACelsius(f);
    }

    public float convertirKilogramosALibras(float kg) {
        return port.kilogramosALibras(kg);
    }

    public float convertirLibrasAKilogramos(float lb) {
        return port.librasAKilogramos(lb);
    }
}