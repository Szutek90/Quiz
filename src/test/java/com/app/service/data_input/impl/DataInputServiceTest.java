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

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class DataInputServiceTest {
    @Spy
    private QuestionsProvider questionsProvider = new QuestionsProvider("src/test/resources/questions.csv");

    @Mock
    private UserInputProvider userInputProvider;

    @InjectMocks
    private DataInputService dataInputService;

    @BeforeEach
    public void setUp() {
        dataInputService = new DataInputService(questionsProvider);
        Mockito.doNothing().when(questionsProvider).saveToDb();
    }

    @Test
    @DisplayName("When getting question from user")
    void test() {
        try (MockedStatic<UserInputProvider> mockedStatic = Mockito.mockStatic(UserInputProvider.class)) {
            mockedStatic
                    .when(UserInputProvider::getUserText)
                    .thenReturn("Nazwa najwyzszego szczytu w Europie?=Mont Blanc-Tak;Alpy-Nie;Everest-Nie;HARD;GEOGRAFIA");
            Assertions.assertThatCode(() -> dataInputService.getQuestionsFromUser())
                    .doesNotThrowAnyException();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
}
