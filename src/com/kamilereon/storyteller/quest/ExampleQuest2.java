package com.kamilereon.storyteller.quest;

import com.kamilereon.storyteller.annotations.QuestProgressCondition;
import com.kamilereon.storyteller.core.StoryTellerQuest;
import org.bukkit.entity.Player;

public class ExampleQuest2 extends StoryTellerQuest {
    public ExampleQuest2(Player player) {
        super(player);
    }

    @QuestProgressCondition
    public boolean condition() { return true; }

}
