package com.app.service.provider.impl;

import com.app.model.player.Player;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

class ScoresProviderTest {
    static ScoresProvider provider;
    static Player p1;
    static Player p2;
    static Player p3;

    @BeforeAll
    static void beforeAll() {
        provider = new ScoresProvider("src/test/resources/scores.csv");
        p1 = new Player(0, "Pawel", LocalDate.of(2024, 4, 29), 50);
        p2 = new Player(1, "Simba", LocalDate.of(2024, 4, 25), 50);
        p3 = new Player(2, "Lili", LocalDate.of(2024, 4, 24), 30);
    }

    @Test
    @DisplayName("When getting 2 top players")
    void test1() {
        var expected = Map.of(50, List.of(p2, p1));
        var readed = provider.getTopPlayerScores(1);
        assertThat(readed).containsExactlyInAnyOrderEntriesOf(expected);
    }

    @Test
    @DisplayName("When saving to db")
    void test2() {
        assertThatCode(() -> provider.saveToDb()).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("When getting all players as string")
    void test3() {
        var expected = "1:Simba:2024-04-25:50\n" +
                "0:Pawel:2024-04-29:50\n" +
                "2:Lili:2024-04-24:30";
        var readed = provider.from();
        assertThat(readed).isEqualTo(expected);
    }

    @Test
    @DisplayName("When getting all players scores")
    void test4() {
        var expected = Map.of(50, List.of(p2, p1),30, List.of(p3));
        var readed = provider.getAllPlayerScores();
        assertThat(readed).containsExactlyInAnyOrderEntriesOf(expected);
    }

    @Test
    @DisplayName("When adding new score")
    void test5() {
        var p4 = new Player("Jan Kowalski", LocalDate.now(), 28);
        assertThatCode(() -> provider.addScore(28, p4)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("When getting top players in desired data")
    void test6() {
        var expected = Map.of(30, List.of(p3));
        var readed = provider.getTopPlayersInDesiredDate(LocalDate.of(2023,1,10),
                LocalDate.of(2024,4,24));
        assertThat(readed).containsExactlyInAnyOrderEntriesOf(expected);
    }
}
