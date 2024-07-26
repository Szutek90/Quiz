package com.app.service.quiz_service;


import com.app.enums.DifficultLevel;

public interface GameService {
    int playGame(int quantity, DifficultLevel difficultLevel);
}
