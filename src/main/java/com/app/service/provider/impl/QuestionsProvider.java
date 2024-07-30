package com.app.service.provider.impl;

import com.app.enums.DifficultLevel;
import com.app.enums.QuestionCategory;
import com.app.file_operator.impl.QuestionsFileOperator;
import com.app.model.question.Question;
import com.app.model.question.QuestionMapper;
import com.app.service.provider.Provider;

import java.util.Map;
import java.util.stream.Collectors;

public class QuestionsProvider implements Provider {
    private final QuestionsFileOperator fileOperator;
    private final Map<String, Question> questions;

    public QuestionsProvider(String filename) {
        fileOperator = new QuestionsFileOperator(filename);
        questions = fileOperator.readFile();
    }

    public void add(String value) {
        var questionToAdd = Question.parse(value);
        if (!questions.containsValue(questionToAdd)) {
            questions.put(QuestionMapper.toQuestionText.apply(questionToAdd), questionToAdd);
        }
    }

    public boolean dbHasQuestion(String question) {
        return questions.containsKey(question);
    }

    @Override
    public void saveToDb() {
        fileOperator.save(from());
    }

    public String from() {
        return questions
                .values()
                .stream()
                .map(Question::from)
                .collect(Collectors.joining("\n"));
    }

    public String getKeyWithCategoryFilter(QuestionCategory questionCategory, int index) {
        var filtered = questions.values()
                .stream()
                .filter(q -> QuestionMapper.toCattegory.apply(q).equals(questionCategory))
                .collect(Collectors.toMap(QuestionMapper.toQuestionText,
                        q -> q));
        return filtered.keySet()
                .toArray()[index-1]
                .toString();
    }

    public String getKeyByIndex(int index) {
        return questions.keySet().toArray()[index].toString();
    }

    public String getKeyByIndexWithDifficultLevel(int index, DifficultLevel difficultLevel) {
        return questions.values()
                .stream()
                .filter(q -> QuestionMapper.toDifficultLevel.apply(q).equals(difficultLevel))
                .collect(Collectors.toMap(QuestionMapper.toQuestionText,
                        q -> q))
                .keySet()
                .toArray()[index]
                .toString();

    }

    public int getSize() {
        return questions.size();
    }

    public int getSizeWithDifficultLevel(DifficultLevel difficultLevel) {
        return questions.values()
                .stream()
                .filter(q -> QuestionMapper.toDifficultLevel.apply(q).equals(difficultLevel))
                .collect(Collectors.toMap(QuestionMapper.toQuestionText,
                        q -> q))
                .size();
    }

    public int getSizeWithCattegoryFilter(QuestionCategory questionCategory) {
        return questions.values()
                .stream()
                .filter(q -> QuestionMapper.toCattegory.apply(q).equals(questionCategory))
                .collect(Collectors.toMap(QuestionMapper.toQuestionText,
                        q -> q))
                .size();
    }

    public Question getValue(String key) {
        return questions.get(key);
    }
}
