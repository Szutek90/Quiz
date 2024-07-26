package com.app.file_operator.impl;

import com.app.model.question.Question;
import com.app.model.question.QuestionMapper;
import com.app.validator.FileExistsValidator;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class QuestionsFileOperator extends AbstractFileOperator<String, Question>{
    private final Class<Question> questionClass;


    public QuestionsFileOperator(String filename) {
        super(filename);
        questionClass = Question.class;
    }

    public Map<String, Question> readFile() {
        FileExistsValidator.fileExists(filename);
        try (var lines = Files.lines(Paths.get(filename))) {
            return lines
                    .map(Question::parse)
                    .collect(Collectors.toMap(
                            QuestionMapper.toQuestionText,
                            q -> q,
                            (v1, v2) -> v1,
                            HashMap::new
                    ));
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
}
