package Main;

import Quest.Quest;
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

        Reflections ref = new Reflections(new ConfigurationBuilder()
                .forPackage("")
                .setScanners(Scanners.SubTypes));

//        Set<Class<?>> classes = ref.getTypesAnnotatedWith(Quest.class);
        Set<Class<? extends Quest>> classes = ref.getSubTypesOf(Quest.class);
        for(Class<? extends Quest> clazz : classes) {
//            String questName = clazz.getAnnotation(Quest.class).questName();
            String questName = clazz.getName();
            Memory.registerQuest(clazz);
            System.out.println(questName+" is registered in memory");
        }
    }
}
