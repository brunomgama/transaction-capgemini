package io.bgama.enums;

public enum Types {
    INITIALIZATION(0L, "Initialization"),
    CASH(1L, "Cash"),
    CARD(2L, "Card"),
    TRANSFER(3L, "Transfer");

    private final Long value;
    private final String label;

    Types(Long value, String label) {
        this.value = value;
        this.label = label;
    }

    public Long getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }

    public static String getLabelById(long id) {
        for (Types type : values()) {
            if (type.getValue() == id) {
                return type.getLabel();
            }
        }
        return null; // or throw an exception if you prefer
    }
}
