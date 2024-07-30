package com.app.service.data_input.impl;

import com.app.service.data_input.DataInput;
import com.app.service.provider.impl.QuestionsProvider;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

@Slf4j
public class DataInputService implements DataInput {
    private final QuestionsProvider questionsProvider;
    private final Scanner sc;


    public DataInputService(QuestionsProvider questionsProvider) {
        this.questionsProvider = questionsProvider;
        sc = new Scanner(System.in);
    }

@Override
    public void getQuestionsFromUser() {
        var text = "";
        do {
            log.info("Please input question and answers");
            text = sc.nextLine();
            if (!text.isEmpty() && expressionIsCorrect(text)) {
                questionsProvider.add(text);
            }
        } while (!text.isEmpty());
        questionsProvider.saveToDb();
    }


    private boolean expressionIsCorrect(String expression) {
        var parts = expression.split("=");
        if (parts.length != 2) {
            return false;
        }
        var answers = parts[1].split(";");
        if (questionsProvider.dbHasQuestion(parts[0])) {
            return false;
        }
        var yesAnswer = 0;
        var noAnswer = 0;
        if (answers.length != 5) {
            return false;
        }
        for (int i = 0; i < 3; i++) {
            yesAnswer += answers[i].toUpperCase().endsWith("-TAK") ? 1 : 0;
            noAnswer += answers[i].toUpperCase().endsWith("-NIE") ? 1 : 0;
        }
        return parts[0].matches("(\\w+\\s*)*[?$]") && yesAnswer == 1 && noAnswer == 2;
    }
}
