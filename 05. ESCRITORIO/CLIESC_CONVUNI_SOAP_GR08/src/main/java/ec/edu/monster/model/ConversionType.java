package ec.edu.monster.model;

public enum ConversionType {
    // Longitud
    CM_A_IN("Centímetros → Pulgadas", ConversionCategory.LONGITUD),
    IN_A_CM("Pulgadas → Centímetros", ConversionCategory.LONGITUD),

    // Temperatura
    C_A_F("Celsius (°C) → Fahrenheit (°F)", ConversionCategory.TEMPERATURA),
    F_A_C("Fahrenheit (°F) → Celsius (°C)", ConversionCategory.TEMPERATURA),

    // Masa
    KG_A_LB("Kilogramos → Libras", ConversionCategory.MASA),
    LB_A_KG("Libras → Kilogramos", ConversionCategory.MASA);

    private final String label;
    private final ConversionCategory category;

    ConversionType(String label, ConversionCategory category) {
        this.label = label; this.category = category;
    }
    public String label() { return label; }
    public ConversionCategory category() { return category; }
    @Override public String toString() { return label; }
}
