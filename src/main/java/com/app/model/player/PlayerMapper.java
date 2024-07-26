package com.app.model.player;

import java.time.LocalDate;
import java.util.function.Function;

public interface PlayerMapper {
    Function<Player, Integer> toScore = player -> player.score;
    Function<Player, LocalDate> toDateOfScore = player -> player.dateOfScore;
}
