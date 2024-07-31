package com.app.service.quiz_service.impl;

import com.app.enums.DifficultLevel;
import com.app.enums.QuestionCategory;
import com.app.service.data_input.impl.DataInputService;
import com.app.service.provider.UserInputProvider;
import com.app.service.provider.impl.QuestionsProvider;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.lang.reflect.Field;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class QuizServiceTest {
    @Spy
    QuestionsProvider questionsProvider = new QuestionsProvider("src/test/resources/questions.csv");

    @Mock
    private UserInputProvider userInputProvider;

    @Spy
    @InjectMocks
    private QuizService quizService;

    @BeforeEach
    public void setUp() throws Exception {
        quizService = new QuizService(questionsProvider);
        doNothing().when(questionsProvider).saveToDb();
        replaceUserInputProvider();
    }

    private void replaceUserInputProvider() throws Exception {
        Field field = QuizService.class.getDeclaredField("userInputProvider");
        field.setAccessible(true);
        field.set(quizService, userInputProvider);
    }

    @Test
    @DisplayName("When playing with category filter")
    void test1() {
        when(userInputProvider.getUserInt()).thenReturn(1);
        assertThat(quizService
                .playGameWithCategoryUserInput(1, QuestionCategory.FILM)).isEqualTo(1);
    }

    @Test
    @DisplayName("When playing with difficult level")
    void test2() {
        when(userInputProvider.getUserInt()).thenReturn(1);
        assertThat(quizService.playGame(2, DifficultLevel.HARD)).isEqualTo(6);
    }
}
