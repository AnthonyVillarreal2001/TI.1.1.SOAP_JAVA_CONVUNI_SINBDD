package ec.edu.monster.model;

public enum ConversionCategory {
    LONGITUD, TEMPERATURA, MASA;

    @Override public String toString() {
        switch (this) {
            case LONGITUD: return "Longitud";
            case TEMPERATURA: return "Temperatura";
            case MASA: return "Masa";
            default: return name();
        }
    }
}
