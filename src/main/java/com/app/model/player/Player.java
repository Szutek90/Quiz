package com.app.model.player;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDate;

@EqualsAndHashCode
@ToString
public class Player {
    private static int idCounter = 0;
    private final String name;
    private final int id;
    final LocalDate dateOfScore;
    final int score;

    public Player(String name, LocalDate dateOfScore, int score) {
        this.name = name;
        this.id = idCounter++;
        this.dateOfScore = dateOfScore;
        this.score = score;
    }

    public Player(int id, String name, LocalDate dateOfScore, int score) {
        this.name = name;
        this.id = id;
        this.dateOfScore = dateOfScore;
        this.score = score;
        idCounter = id + 1;
    }

    public boolean givenDataIsAfterOrEqual(LocalDate date) {
        return date.isAfter(dateOfScore) || date.isEqual(dateOfScore);
    }

    public boolean giveDataIsBeforeOrEqual(LocalDate date) {
        return date.isBefore(dateOfScore) || date.isEqual(dateOfScore);
    }

    public static Player parse(String text) {
        var playerData = text.split(":");
        return new Player(Integer.parseInt(playerData[0]), playerData[1],
                LocalDate.parse(playerData[2]), Integer.parseInt(playerData[3]));
    }
}
