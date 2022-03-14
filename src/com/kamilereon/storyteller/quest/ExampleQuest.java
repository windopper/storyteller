package com.kamilereon.storyteller.quest;

import com.kamilereon.storyteller.annotations.*;
import com.kamilereon.storyteller.core.StoryTellerQuest;
import com.kamilereon.storyteller.main.StoryTeller;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

// you must extend StoryTellerQuest.class to use storyteller library
public class ExampleQuest extends StoryTellerQuest {

    public ExampleQuest(Player player) {
        super(player);
    }

    final int levelReq = 5;

    // The only players who passed condition can call stage
    @QuestProgressCondition
    public boolean condition() {
        return true;
    }

    // call method if player enter given area
    @CallStageWhenEnterAreaRadius(progressToCall = {1, 2, 3}, worldName = "world", x = 136, y = 74, z = 19, radius = 3)
    @CallStageAfterTick(progressToCall = 4, tick = 60)
    // notify finalProgress is 6 to update stage when detailProgress reaches 6
    @StartSequence(finalProgress = 4)
    public void preStage() {
        switch (progress) {
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
        }
        Bukkit.broadcastMessage("stage"+progress);
    }

//    @CallStageWhenEnterAreaRadius(worldName = "world", x = 136, y = 74, z = 19, radius = 3)

    @CallStageWhenConditionSatisfied(targetMethodName = "condition2")
    @StageSequence(stage = 1, finalProgress = 10)
    public void stage1() {
        Bukkit.broadcastMessage("stage"+progress);
        // separate detailProgress to case by case
    }

    public boolean condition2() {
        return player.isSneaking();
    }

    // call when
    @FinalSequence
    public void finalSequence() {
        player.sendTitle("YOU RECEIVED A REWARD!", " ", 0, 40, 10);
        // Some Rewards to Player
    }

}
