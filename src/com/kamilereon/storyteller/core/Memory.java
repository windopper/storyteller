package com.kamilereon.storyteller.core;

import com.kamilereon.storyteller.quest.StoryTellerQuest;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class Memory {

    static final ConcurrentHashMap<Player, CoreData> coreDatas = new ConcurrentHashMap<>();
    static final Set<Class<? extends StoryTellerQuest>> quests = new HashSet<>();

    public static CoreData getCoreData(Player player) { return coreDatas.get(player); }

    @SafeVarargs
    public static void registerQuest(Class<? extends StoryTellerQuest> ...quest) { quests.addAll(Arrays.stream(quest).collect(Collectors.toSet())); }

    public static Set<Class<? extends StoryTellerQuest>> getQuests() { return quests; }
}
