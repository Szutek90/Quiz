package com.app.enums;

public enum QuestionCategory {
    FILM("FILM"),
    KSIAZKA("KSIAZKA"),
    GEOGRAFIA("GEOGRAFIA"),
    ZWIERZETA("ZWIERZETA");

    private final String value;

    QuestionCategory(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}
