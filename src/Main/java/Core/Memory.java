package Core;

import org.bukkit.entity.Player;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Memory {

    static final ConcurrentHashMap<Player, CoreData> coreDatas = new ConcurrentHashMap<>();
    static final HashMap<String, Class<?>> quests = new HashMap<>();

    public static void registerQuest(String name, Class<?> quest) { quests.put(name, quest); }

}
