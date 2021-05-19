package br.com.dev.jsongenerator.enums;

public enum TypeEnum {

    STRING("STRING"),
    INTEGER("INTEGER"),
    DOUBLE("DOUBLE"),
    LONG("LONG"),
    BOOLEAN("BOOLEAN"),
    ARRAY("ARRAY"),
    OBJECT("OBJECT");

    private String description;

    TypeEnum(String description) {
        this.description = description;
    }
}
