package br.com.dev.jsongenerator.enums;

public enum FormatterEnum {

    CPF("CPF"),
    CNPJ("CNPJ"),
    FUTURE_DATE("FUTURE_DATE"),
    PAST_DATE("PAST_DATE"),
    LOREM_TEXT("LOREM_TEXT"),
    NAME_PT_BR("NAME_PT_BR"),
    RANDOM_DATE("RANDOM_DATE");

    private String description;

    FormatterEnum(String description) {
        this.description = description;
    }
}
