package Main;

import Annotation.Quest;
import Core.CoreData;
import Core.Memory;
import com.google.common.reflect.Reflection;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.reflections.Reflections;
import org.reflections.util.ConfigurationBuilder;

import java.util.Set;

import static org.reflections.scanners.Scanners.TypesAnnotated;

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
                .setScanners(TypesAnnotated));

        Set<Class<?>> classes = ref.getTypesAnnotatedWith(Quest.class);
        for(Class<?> clazz : classes) {
            String questName = clazz.getAnnotation(Quest.class).questName();
            Memory.registerQuest(questName, clazz);
            System.out.println(questName+" is registered in memory");
        }
    }
}
