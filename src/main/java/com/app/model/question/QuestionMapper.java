package com.app.model.question;

import com.app.enums.DifficultLevel;
import com.app.enums.QuestionCategory;

import java.util.function.Function;

public interface QuestionMapper {
    Function<Question, String> toQuestionText = question -> question.questionText;
    Function<Question, DifficultLevel> toDifficultLevel = question -> question.difficultLevel;
    Function<Question, QuestionCategory> toCattegory = question -> question.questionCategory;
}
