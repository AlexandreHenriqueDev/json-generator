package br.com.dev.jsongenerator.enums;

public enum FormatterEnum {

    CPF("CPF"),
    CNPJ("CPF");

    private String description;

    FormatterEnum(String description) {
        this.description = description;
    }
}
