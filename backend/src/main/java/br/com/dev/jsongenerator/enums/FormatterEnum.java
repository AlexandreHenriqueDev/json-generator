package br.com.dev.jsongenerator.enums;

public enum FormatterEnum {

    CPF("CPF"),
    CNPJ("CPF"),
    FUTURE_DATE("FUTURE_DATE"),
    PAST_DATE("PAST_DATE"),
    RANDOM_DATE("RANDOM_DATE");

    private String description;

    FormatterEnum(String description) {
        this.description = description;
    }
}
