package io.bgama.enums;

public enum Category {
    INITIALIZATION(0L, "Initialization"),
    VIAGENS(1L, "Viagens"),
    SUPERMERCADO(2L, "Supermercado"),
    GASOLINA(3L, "Gasolina"),
    CASA(4L, "Casa"),
    ELETRICIDADE(5L, "Eletricidade"),
    AGUA(6L, "Água"),
    GAS(7L, "Gás"),
    ELETRONICA(8L, "Eletronica"),
    RESTAURACAO(9L, "Restauração"),
    CULTURA(10L, "Cultura"),
    CINEMA(11L, "Cinema"),
    TEATRO(12L, "Teatro"),
    LIVROS(13L, "Livros"),
    GINASIO(14L, "Ginásio"),
    ROUPA(15L, "Roupa"),
    ALCOOL(16L, "Alcool"),
    COSMETICO(17L, "Cosmético"),
    BARBEIRO(18L, "Barbeiro"),
    SAUDE(19L, "Saude"),
    MEDICO(20L, "Médico"),
    SEGUROS(21L, "Seguros"),
    IMPOSTOS(22L, "Impostos"),
    ORDENADO(23L, "Ordenado"),
    PRENDAS(24L, "Prendas");

    private final long value;
    private final String label;

    Category(long value, String label) {
        this.value = value;
        this.label = label;
    }

    public long getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }

    public static String getLabelById(long id) {
        for (Category category : values()) {
            if (category.getValue() == id) {
                return category.getLabel();
            }
        }
        return null;
    }
}

