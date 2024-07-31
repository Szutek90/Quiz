package com.app.enums;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class QuestionCategoryTest {

    @Test
    @DisplayName("When getting value")
    void test() {
        var q = QuestionCategory.FILM;
        Assertions.assertThat(q.getValue()).isEqualTo("FILM");
    }
}
