package com.app.model.player;

import java.util.Comparator;

public interface PlayerComparator {
    Comparator<Player> byDate = Comparator.comparing(PlayerMapper.toDateOfScore);
}
