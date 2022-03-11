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
        return levelReq <= player.getLevel();
    }

    @CallStageWhenRightClickEntity(progressToCall = {1, 2, 3, 4, 5}, entityName = "HELLO")
    @StageSequence(stage = 1, finalProgress = 5)
    public void stage1() {
        switch(detailProgress) {
            case 1 -> {
                // 1
            }
            case 2 -> {
                // 2
            }
            case 3 -> {
                // 3
            }
            case 4 -> {
                // 4
            }
            case 5 -> {
                // 5
            }
        }
    }

    @CallStageWhenRightClickEntity(progressToCall = {2, 4, 5, 6}, entityName = "KKK")
    @CallStageWhenEnterAreaRadius(progressToCall = 1, radius = 3, worldName = "world", x = 1, y = 1, z = 1)
    @CallStageWhenEnterAreaRadius(progressToCall = 3, radius = 3, worldName = "world", x = 1, y = 1, z = 1)
    @CallStageWhenEnterAreaRectangle(progressToCall = 9, worldName = "world", x = 1, y = 2, z = 3, dx = 1, dy = 3, dz = 2)
    @StageSequence(stage = 2, finalProgress = 9)
    public void stage2() {

    }

    @FinalSequence
    public void finalSequence() {}

}
