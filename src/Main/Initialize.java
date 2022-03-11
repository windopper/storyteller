package Main;

import Quest.StoryTellerQuest;
import Core.CoreData;
import Core.Memory;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;
import org.reflections.util.ConfigurationBuilder;

import java.util.Set;

public class Initialize {
    void registerCoreData() {
        for(Player player : Bukkit.getOnlinePlayers()) {
            CoreData.getOrCreateCoreData(player);
        }
    }

    void loadQuest() {
        System.out.println("loadQuest...");

        // Quest 클래스를 상속한 모든 클래스를 메모리에 저장
        Reflections ref = new Reflections(new ConfigurationBuilder()
                .forPackage("")
                .setScanners(Scanners.SubTypes));

        Set<Class<? extends StoryTellerQuest>> classes = ref.getSubTypesOf(StoryTellerQuest.class);
        for(Class<? extends StoryTellerQuest> clazz : classes) {
            String questName = clazz.getName();
            Memory.registerQuest(clazz);
            System.out.println(questName+" is registered in memory");
        }
    }
}
