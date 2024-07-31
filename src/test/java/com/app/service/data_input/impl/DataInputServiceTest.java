package com.app.service.data_input.impl;

import com.app.service.provider.UserInputProvider;
import com.app.service.provider.impl.QuestionsProvider;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.lang.reflect.Field;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class DataInputServiceTest {
    @Mock
    private UserInputProvider userInputProvider;

    @Spy
    private QuestionsProvider questionsProvider = new QuestionsProvider("src/test/resources/questions.csv");

    @InjectMocks
    private DataInputService dataInputService;

    @BeforeEach
    public void setUp() throws Exception {
        dataInputService = new DataInputService(questionsProvider);
        Mockito.doNothing().when(questionsProvider).saveToDb();
        replaceUserInputProvider();
    }

    private void replaceUserInputProvider() throws Exception {
        Field field = DataInputService.class.getDeclaredField("userInputProvider");
        field.setAccessible(true);
        field.set(dataInputService, userInputProvider);
    }

    @Test
    @DisplayName("When getting question from user2")
    void test1() {
        Mockito.when(userInputProvider.getUserText())
                .thenReturn("Nazwa najwyzszego szczytu w Europie?=Mont Blanc-Tak;Alpy-Nie;Everest-Nie;HARD;GEOGRAFIA");
        Assertions.assertThatCode(() -> dataInputService.getQuestionsFromUser())
                .doesNotThrowAnyException();
    }
}