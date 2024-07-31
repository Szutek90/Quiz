package com.app.model.player;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class PlayerTest {
    Player player = new Player(0, "Pawel", LocalDate.of(2024, 4, 29), 50);

    @Test
    @DisplayName("When given data is after")
    void test1(){
        Assertions.assertThat(player.givenDataIsAfterOrEqual(LocalDate.of(2024,5,20)))
                .isTrue();
    }

    @Test
    @DisplayName("When given data is equal")
    void test2(){
        Assertions.assertThat(player.givenDataIsAfterOrEqual(LocalDate.of(2024,4,29)))
                .isTrue();
    }

    @Test
    @DisplayName("When given data is equal")
    void test3(){
        Assertions.assertThat(player.giveDataIsBeforeOrEqual(LocalDate.of(2024,4,29)))
                .isTrue();
    }

    @Test
    @DisplayName("When given data is before")
    void test4(){
        Assertions.assertThat(player.giveDataIsBeforeOrEqual(LocalDate.of(2022,4,29)))
                .isTrue();
    }

    @Test
    @DisplayName("When given data is after")
    void test5(){
        Assertions.assertThat(player.giveDataIsBeforeOrEqual(LocalDate.of(2025,4,29)))
                .isFalse();
    }
}
