package com.app.service.quiz_service.impl;

import com.app.data.DataGenerator;
import com.app.enums.DifficultLevel;
import com.app.enums.QuestionCategory;
import com.app.service.provider.impl.QuestionsProvider;
import com.app.service.quiz_service.GameService;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;
import java.util.regex.Pattern;

@Slf4j
public class QuizService implements GameService {
    private final QuestionsProvider questionsProvider;
    private final Scanner scanner = new Scanner(System.in);

    public QuizService(QuestionsProvider questionsProvider) {
        this.questionsProvider = questionsProvider;
    }

    public int playGameWithCategoryUserInput(int quantity, QuestionCategory questionCategory) {
        var score = 0;
        var rndValues = DataGenerator.generateUniqueRandomIntegers(questionsProvider
                .getSizeWithCattegoryFilter(questionCategory), quantity);
        for (var value : rndValues) {
            var key = questionsProvider.getKeyWithCategoryFilter(questionCategory, value);
            score = getScore(score, key);
        }

        return score;
    }

    @Override
    public int playGame(int quantity, DifficultLevel difficultLevel) {
        var score = 0;
        var rndValues = difficultLevel != null ?
                DataGenerator.generateUniqueRandomIntegers(questionsProvider.getSizeWithDifficultLevel(difficultLevel),
                        quantity) : DataGenerator.generateUniqueRandomIntegers(questionsProvider.getSize(), quantity);

        for (var value : rndValues) {
            var key = difficultLevel != null ? questionsProvider.getKeyByIndexWithDifficultLevel(value,
                    difficultLevel) : questionsProvider.getKeyByIndex(value);
            score = getScore(score, key);
        }
        return score;
    }

    private int getScore(int score, String key) {
        var question = questionsProvider.getValue(key);
        var answers = question.from().split("=")[1].split(";");
        askQuestion(key, answers);
        var answer = getUserAnswer();
        if (answers[answer - 1].toUpperCase().endsWith("-TAK")) {
            log.info("To byla poprawna odpowiedz!");
            score = question.sumPoints(score);
        } else {
            log.info("To byla bledna odpowiedz");
        }
        return score;
    }

    private QuestionCategory getCategoryFromUser() {
        var validAnswer = "";
        while (true) {
            log.info("Enter catregory\n");
            if (scanner.hasNext()) {
                validAnswer = scanner.next().toUpperCase();
                try {
                    return QuestionCategory.valueOf(validAnswer);
                } catch (IllegalArgumentException e) {
                    log.info("Please enter a correct category\n");
                }
            } else {
                log.info("Please enter a valid string.");
                scanner.next();
            }
        }
    }

    private int getUserAnswer() {
        var answer = 0;
        var validAnswer = false;
        do {
            if (scanner.hasNextInt()) {
                answer = scanner.nextInt();
                if (answer == 1 || answer == 2 || answer == 3) {
                    validAnswer = true;
                } else {
                    log.info("Please type 1, 2 or 3");
                }
            } else {
                log.info("Please enter an integer");
                scanner.next();
            }
        } while (!validAnswer);
        return answer;
    }

    private void askQuestion(String key, String[] answers) {
        var pattern = Pattern.compile("-tak|-nie", Pattern.CASE_INSENSITIVE);
        log.info("Please answer question below, 1, 2 or 3?");
        log.info(key);
        log.info("1. " + pattern.matcher(answers[0]).replaceAll("") +
                "\n2. " + pattern.matcher(answers[1]).replaceAll("") +
                "\n3. " + pattern.matcher(answers[2]).replaceAll(""));
    }
}
