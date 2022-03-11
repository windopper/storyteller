package com.kamilereon.storyteller.quest;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.bukkit.entity.Player;

import java.io.Serializable;

public abstract class StoryTellerQuest implements Serializable {

    /**
        <p>stage = 1 || not started</p>
        <p>stage = -1 || com.kamilereon.storyteller.quest finished</p>
        <p>detailProgress == 1 || not started</p>
     */
    public int stage = 1;
    public int detailProgress = 1;
    @JsonIgnore
    transient public final Player player;

    public StoryTellerQuest(Player player) {
        this.player = player;
    }

    public boolean isFinished() { return stage == -1; }

    public boolean isProgressing() { return stage >= 1; }

    public boolean isNotStarted() { return stage == 0; }
}
