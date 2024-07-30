package com.app.model.question;

import com.app.enums.DifficultLevel;
import com.app.enums.QuestionCategory;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Arrays;

@EqualsAndHashCode
@ToString
public class Question {
    final String questionText;
    private final String answerYes;
    private final String answerNo1;
    private final String answerNo2;
    final DifficultLevel difficultLevel;
    private static int idCounter = 0;
    private final int id;
    final QuestionCategory questionCategory;

    public Question(String questionText, String answerYes, String answerNo1, String answerNo2,
                    DifficultLevel difficultLevel, QuestionCategory questionCategory) {
        this.questionText = questionText;
        this.answerYes = answerYes;
        this.answerNo1 = answerNo1;
        this.answerNo2 = answerNo2;
        this.difficultLevel = difficultLevel;
        id = idCounter++;
        this.questionCategory = questionCategory;
    }

    public static Question parse(String text) {
        var splittedFromQuestion = text.split("=");
        var splitted = Arrays.stream(splittedFromQuestion[1].split(";"))
                .map(t->t.replaceAll("-Tak|-Nie$", ""))
                .toArray(String[]::new);
        return new Question(splittedFromQuestion[0], splitted[0], splitted[1],
                splitted[2], DifficultLevel.valueOf(splitted[3]),
                QuestionCategory.valueOf(splitted[4]));
    }

    public String from() {
        return questionText + "=" + answerYes + ";" + answerNo1 + ";" + answerNo2 + ";" + difficultLevel + ";" +
                questionCategory;
    }

    public int sumPoints(int points) {
        return points + difficultLevel.getValue();
    }
}
