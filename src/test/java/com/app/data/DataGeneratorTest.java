package com.app.data;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DataGeneratorTest {
    @Test
    @DisplayName("When getting valu from range")
    void test() {
        Assertions.assertThat(DataGenerator.generateRandomInteger(-50, 20)).isBetween(-50, 20);
    }
}
