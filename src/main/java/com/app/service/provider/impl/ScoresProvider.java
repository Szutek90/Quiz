package com.app.service.provider.impl;

import com.app.file_operator.impl.ScoresFileOperator;
import com.app.model.player.Player;
import com.app.service.provider.Provider;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class ScoresProvider implements Provider {
    private final Map<Integer, List<Player>> playerScores;
    private final ScoresFileOperator fileManager;

    public ScoresProvider(String filename) {
        fileManager = new ScoresFileOperator(filename);
        playerScores = fileManager.readFile();
    }

    public Map<Integer, List<Player>> getTopPlayerScores(int quantity) {
        return playerScores.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))
                .limit(quantity)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (v1, v2) -> v1,
                        LinkedHashMap::new
                ));
    }

    @Override
    public void saveToDb(){
        fileManager.save(from());
    }

    public String from(){
        return playerScores.values().stream()
                .flatMap(List::stream) // spłaszczam listę list graczy do jednego strumienia graczy
                .map(Player::toString)
                .collect(Collectors.joining("\n"));
    }

    public Map<Integer, List<Player>> getAllPlayerScores() {
        return getTopPlayerScores(playerScores.size());
    }

    public void addScore(int score, Player player) {
        playerScores.computeIfAbsent(score, p -> new ArrayList<>()).add(player);
    }

    public Map<Integer, List<Player>> getTopPlayersInDesiredDate(LocalDate from, LocalDate to) {
        var topScores = new TreeMap<Integer, List<Player>>(Collections.reverseOrder());
        for (var score : playerScores.entrySet()) {
            var players = score.getValue().stream()
                    .filter(p -> p.givenDataIsAfterOrEqual(to) &&
                            p.giveDataIsBeforeOrEqual(from))
                    .toList();
            if(!players.isEmpty()) {
                topScores.put(score.getKey(), players);
            }
        }
        return topScores;
    }
}