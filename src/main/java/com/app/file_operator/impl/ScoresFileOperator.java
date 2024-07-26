package com.app.file_operator.impl;

import com.app.model.player.Player;
import com.app.model.player.PlayerComparator;
import com.app.model.player.PlayerMapper;
import com.app.validator.FileExistsValidator;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ScoresFileOperator extends AbstractFileOperator<Integer, List<Player>> {

    public ScoresFileOperator(String filename) {
        super(filename);
    }

    public Map<Integer, List<Player>> readFile() {
        FileExistsValidator.fileExists(filename);
        try (var lines = Files.lines(Paths.get(filename))) {

            return lines
                    .map(Player::parse)
                    .collect(Collectors.groupingBy(PlayerMapper.toScore,
                            Collectors.collectingAndThen(
                                    Collectors.mapping(player -> player, Collectors.toList()),
                                    players -> players.stream().sorted(PlayerComparator.byDate).toList()
                            )));

        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

}
