package com.app.validator.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.*;

class StringValidatorTest {
    static StringValidator validator = new StringValidator();

    @Test
    @DisplayName("When String is not empty")
    void test1() {
        assertThatThrownBy(() -> StringValidator.isEmpty("xyz"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("When String is empty")
    void test2() {
        assertThatCode(() -> StringValidator.isEmpty(""))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("When string match predicate")
    void test3() {
        Predicate<String> predicate = str -> str.startsWith("A");
        assertThatCode(() -> validator.validate(predicate, "Ananas"))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("When string doesnt match predicate")
    void test4() {
        Predicate<String> predicate = str -> str.startsWith("A");
        assertThatThrownBy(() -> validator.validate(predicate, "Gruszka"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("When predicate is null")
    void test5() {
        assertThatThrownBy(() -> validator.validate(null,"Test"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
