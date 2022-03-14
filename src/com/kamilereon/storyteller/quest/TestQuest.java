package com.kamilereon.storyteller.quest;

import com.kamilereon.storyteller.annotations.CallStageAfterTick;
import com.kamilereon.storyteller.annotations.StartSequence;
import com.kamilereon.storyteller.core.StoryTellerQuest;
import org.bukkit.entity.Player;

public class TestQuest extends StoryTellerQuest {
    public TestQuest(Player player) {
        super(player);
    }

    @CallStageAfterTick(progressToCall = {1, 2, 3, 4, 5}, tick = 60)
    @StartSequence(finalProgress = 5)
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
            case 5 -> {
                // 5
            }
        }
    }


}
