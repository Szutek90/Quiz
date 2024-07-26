package com.app.enums;

public enum DifficultLevel {
    EASY(1),
    MEDIUM(2),
    HARD(3);

    private final int value;

    DifficultLevel(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
