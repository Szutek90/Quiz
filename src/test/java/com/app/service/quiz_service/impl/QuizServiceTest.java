package com.app.service.quiz_service.impl;

import com.app.service.data_input.impl.DataInputService;
import com.app.service.provider.UserInputProvider;
import com.app.service.provider.impl.QuestionsProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.lang.reflect.Field;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class QuizServiceTest {
    QuestionsProvider questionsProvider = new QuestionsProvider("src/test/resources/questions.csv");

    @Mock
    private UserInputProvider userInputProvider;

    @Spy
    QuizService quizService = new QuizService(questionsProvider);

    @BeforeEach
    public void setUp() throws Exception {
        Mockito.doNothing().when(questionsProvider).saveToDb();
        replaceUserInputProvider();
    }

    private void replaceUserInputProvider() throws Exception {
        Field field = DataInputService.class.getDeclaredField("userInputProvider");
        field.setAccessible(true);
        field.set(quizService, userInputProvider);
    }

    @Test
    @DisplayName("When playing with category filter")
    void test1(){

    }
}
