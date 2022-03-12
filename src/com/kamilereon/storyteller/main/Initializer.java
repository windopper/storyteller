package com.kamilereon.storyteller.main;

import com.kamilereon.storyteller.quest.StoryTellerQuest;
import com.kamilereon.storyteller.core.CoreData;
import com.kamilereon.storyteller.core.Memory;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class Initializer {
    void registerCoreData() {
        for(Player player : Bukkit.getOnlinePlayers()) {
            CoreData.getOrCreateCoreData(player);
        }
    }

    void loadQuest() {
//        Bukkit.broadcastMessage("loadQuest...");
        System.out.println("loadQuest...");

        // Quest 클래스를 상속한 모든 클래스를 메모리에 저장
        FilterBuilder filterBuilder = new FilterBuilder();

        Reflections ref = new Reflections(new ConfigurationBuilder()
                .forPackage("")
                .filterInputsBy(new FilterBuilder().excludePackage("org.reflections").excludePackage("org.slf4j"))
                .setScanners(Scanners.SubTypes));

        Set<Class<? extends StoryTellerQuest>> classes = ref.getSubTypesOf(StoryTellerQuest.class);
        for(Class<? extends StoryTellerQuest> clazz : classes) {
            String questName = clazz.getName();
            Memory.registerQuest(clazz);
//            Bukkit.broadcastMessage(questName+" is registered in memory");
            System.out.println(questName+" is registered in memory");
        }
    }

    public void initAllQuest(Player player) {
        Set<? extends StoryTellerQuest> registeredQuests = Memory.getQuests().stream()
                        .map(q -> {
                            try {
                                return q.getConstructor(Player.class).newInstance(player);
                            }
                            catch(Exception e) {
                                e.printStackTrace();
                                return null;
                            }
                        })
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        Memory.getCoreData(player).setQuests(registeredQuests);
    }
}
