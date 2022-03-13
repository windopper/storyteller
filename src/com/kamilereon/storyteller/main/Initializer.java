package com.kamilereon.storyteller.main;

import com.kamilereon.storyteller.quest.StoryTellerQuest;
import com.kamilereon.storyteller.core.CoreData;
import com.kamilereon.storyteller.core.Memory;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class Initializer {
    public void registerCoreData() {
        for(Player player : Bukkit.getOnlinePlayers()) {
            CoreData.getOrCreateCoreData(player);
        }
    }

//    @Deprecated
//    public void loadQuest() {
////        Bukkit.broadcastMessage("loadQuest...");
//        System.out.println("loadQuest...");
//
//        // Quest 클래스를 상속한 모든 클래스를 메모리에 저장
//        FilterBuilder filterBuilder = new FilterBuilder();
//
//        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
//        for(StackTraceElement s : stackTraceElements) {
//            System.out.println(s.getClassName());
//        }
//
//        Reflections ref = new Reflections(new ConfigurationBuilder()
//                .setUrls(ClasspathHelper.forPackage(""))
//                .filterInputsBy(new FilterBuilder().excludePackage("org.bukkit")
//                        .excludePackage("net.minecraft")
//                        .excludePackage("com.kamilereon.storyteller"))
//                .setScanners(Scanners.SubTypes));
//
//        Set<Class<? extends StoryTellerQuest>> classes = ref.getSubTypesOf(StoryTellerQuest.class);
//        System.out.println(classes.size()+" found");
//        for(Class<? extends StoryTellerQuest> clazz : classes) {
//            String questName = clazz.getName();
//            Memory.registerQuest(clazz);
////            Bukkit.broadcastMessage(questName+" is registered in memory");
//            System.out.println(questName+" is registered in memory");
//        }
//    }

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
