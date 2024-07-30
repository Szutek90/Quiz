package com.app.service.provider.impl;

import com.app.enums.DifficultLevel;
import com.app.enums.QuestionCategory;
import com.app.model.question.Question;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class QuestionsProviderTest {
    static QuestionsProvider provider;

    @BeforeAll
    static void beforeAll() {
        provider = new QuestionsProvider("src/test/resources/questions.csv");
    }

    @Test
    @DisplayName("When adding new question")
    void test1() {
        assertThatCode(() ->
                provider.add("Główna rzeka w Polsce?=Wisła-Tak;Odra-Nie;Warta-Nie;EASY;FILM"))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("When db has not given question")
    void test2() {
        assertThat(provider.dbHasQuestion("Kolor BMW?")).isFalse();
    }

    @Test
    @DisplayName("When saving to db")
    void test3() {
        assertThatCode(() -> provider.saveToDb()).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("When getting whole questions")
    void test4() {
        var expected = "Autor powieści \"Władca Pierścieni\"?=J.R.R. Tolkien;George Orwell;C.S. Lewis;HARD;KSIAZKA\n" +
                "Jakiego koloru jest Simba?=Rudy;Bialy;Rozowy;HARD;ZWIERZETA\n" +
                "Stolica Niemiec?=Berlin;Wiedeń;Paryż;MEDIUM;GEOGRAFIA\n" +
                "Główna rzeka w Polsce?=Wisła;Odra;Warta;EASY;FILM";
        var readed = provider.from();

        assertThat(provider.from()).hasSameSizeAs(expected);
    }

    @Test
    @DisplayName("When getting key with category filter")
    void test5() {
        var category = QuestionCategory.KSIAZKA;
        assertThat(provider.getKeyWithCategoryFilter(category, 1))
                .isEqualTo("Autor powieści \"Władca Pierścieni\"?");
    }

    @Test
    @DisplayName("When getting key by index")
    void test6() {
        assertThat(provider.getKeyByIndex(2)).isEqualTo("Stolica Niemiec?");
    }

    @Test
    @DisplayName("When getting key by index and difficult level")
    void test7() {
        assertThat(provider.getKeyByIndexWithDifficultLevel(1, DifficultLevel.HARD))
                .isEqualTo("Jakiego koloru jest Simba?");
    }

    @Test
    @DisplayName("When getting size with difficult level")
    void test8() {
        assertThat(provider.getSizeWithDifficultLevel(DifficultLevel.HARD)).isEqualTo(2);
    }

    @Test
    @DisplayName("When value from given key")
    void test9() {
        var expectedQuestion = new Question("Główna rzeka w Polsce?", "Wisła", "Odra",
                "Warta", DifficultLevel.EASY, QuestionCategory.FILM).from();
        var readedQuestion = provider.getValue("Główna rzeka w Polsce?").from();
        assertThat(readedQuestion).isEqualTo(expectedQuestion);
    }

    @Test
    @DisplayName("When getting size with category filter")
    void test10() {
        assertThat(provider.getSizeWithCattegoryFilter(QuestionCategory.GEOGRAFIA))
                .isEqualTo(1);
    }

    @Test
    @DisplayName("When getting size")
    void test11() {
        assertThat(provider.getSize()).isEqualTo(4);
    }
}
