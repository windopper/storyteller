package com.kamilereon.storyteller.main;

import com.kamilereon.storyteller.core.Memory;
import com.kamilereon.storyteller.quest.StoryTellerQuest;
import com.kamilereon.storyteller.configuration.QuestFilter;
import org.bukkit.entity.Player;

import java.util.Set;
import java.util.stream.Collectors;

public class StoryTeller {
    @SafeVarargs
    public static void registerQuest(Class<? extends StoryTellerQuest> ...storyTellerQuests) {
        Memory.registerQuest(storyTellerQuests);
    }

    public static Set<Class<? extends StoryTellerQuest>> getQuestList() {
        return Memory.getQuests();
    }

    public static Set<? extends StoryTellerQuest> getQuestsFromPlayer(Player player) {
        return Memory.getCoreData(player).getQuests();
    }

    public static Set<? extends StoryTellerQuest> getQuestsFromPlayer(Player player, QuestFilter questFilter) {
        Set<? extends StoryTellerQuest> quests = Memory.getCoreData(player).getQuests();
        return quests.stream().filter(questFilter::test).collect(Collectors.toSet());
    }
}
