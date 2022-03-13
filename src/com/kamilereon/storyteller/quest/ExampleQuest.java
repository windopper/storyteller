package com.kamilereon.storyteller.quest;

import com.kamilereon.storyteller.annotations.*;
import org.bukkit.entity.Player;

public class ExampleQuest extends StoryTellerQuest {

    final int levelReq = 5;

    public ExampleQuest(Player player) {
        super(player);
    }

    @QuestProgressCondition
    public boolean condition() {
        return true;
    }

    @CallStageWhenEnterAreaRadius(worldName = "world", x = 136, y = 74, z = 19, radius = 3)
    @StartSequence(finalProgress = 5)
    public void stage1() {
        player.sendMessage(detailProgress+"first"+stage);
    }

    @CallStageWhenEnterAreaRadius(worldName = "world", x = 136, y = 74, z = 19, radius = 3)
    @StageSequence(stage = 1, finalProgress = 10)
    public void stage2() {
        player.sendMessage(detailProgress+"second"+stage);
    }

    @FinalSequence
    public void finalSequence() {
        player.sendTitle("NICE NICE NICE", " ", 0, 40, 10);
    }

}
