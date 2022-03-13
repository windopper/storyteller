package com.kamilereon.storyteller.core;

import com.kamilereon.storyteller.annotations.CallStageAfterTick;
import com.kamilereon.storyteller.main.StoryTellerMain;
import com.kamilereon.storyteller.quest.StoryTellerQuest;
import com.kamilereon.storyteller.schedulers.Schedule;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class CoreData {

    private final Player player;
    private Set<? extends StoryTellerQuest> storyTellerQuests = new HashSet<>();
    private final HashMap<CallStageAfterTick, Schedule> scheduleHashMap = new HashMap<>();

    public CoreData(Player player) {
        this.player = player;
    }

    public static CoreData getOrCreateCoreData(Player player) {
        if(Memory.coreDatas.containsKey(player)) return Memory.coreDatas.get(player);
        CoreData coreData = new CoreData(player);
        Memory.coreDatas.put(player, coreData);
        return coreData;
    }

    public void remove() { Memory.coreDatas.remove(player); }

    public Set<? extends StoryTellerQuest> getQuests() { return storyTellerQuests; }

    public void setQuests(Set<? extends StoryTellerQuest> storyTellerQuests) { this.storyTellerQuests = storyTellerQuests; }

    public Schedule addSchedule(CallStageAfterTick key) {
        Schedule schedule = Schedule.createSchedule();
        Schedule oldSchedule = scheduleHashMap.put(key, schedule);
        assert oldSchedule != null;
        oldSchedule.destroy();
        return schedule;
    }

    public void removeSchedule(CallStageAfterTick key) {
        Schedule oldSchedule = scheduleHashMap.remove(key);
        oldSchedule.destroy();
    }

    public Schedule getSchedule(CallStageAfterTick key) {
        return scheduleHashMap.get(key);
    }

}
