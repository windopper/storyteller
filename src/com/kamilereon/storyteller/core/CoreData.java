package com.kamilereon.storyteller.core;

import com.kamilereon.storyteller.annotations.CallStageAfterTick;
import com.kamilereon.storyteller.schedulers.Schedule;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class CoreData {

    private final Player player;
    private Set<StoryTellerQuest> storyTellerQuests = new HashSet<>();
    private final HashMap<String, Schedule> scheduleHashMap = new HashMap<>();

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

    public void setQuests(Set<StoryTellerQuest> storyTellerQuests) { this.storyTellerQuests = storyTellerQuests; }

    public <T extends StoryTellerQuest> void uploadQuest(T storyTellerQuest) {
        try {
            StoryTellerQuest old = storyTellerQuests.stream().filter(q -> q.getClass() == storyTellerQuest.getClass()).toList().get(0);
            storyTellerQuests.remove(old);
        }
        catch (Exception e) {

        }
        finally {
            storyTellerQuests.add(storyTellerQuest);
        }
    }

    public Schedule addSchedule(String key) {
        Schedule schedule = Schedule.createSchedule();
        Schedule oldSchedule = scheduleHashMap.put(key, schedule);
        if(oldSchedule != null) oldSchedule.destroy();
        return schedule;
    }

    public void removeSchedule(String key) {
        Schedule oldSchedule = scheduleHashMap.remove(key);
        oldSchedule.destroy();
    }

    public Schedule getSchedule(String key) {
        return scheduleHashMap.get(key);
    }

    public boolean hasSchedule(String key) {
        return scheduleHashMap.containsKey(key);
    }

}
