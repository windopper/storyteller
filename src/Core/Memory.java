package Core;

import Quest.Quest;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Memory {

    static final ConcurrentHashMap<Player, CoreData> coreDatas = new ConcurrentHashMap<>();
    static final Set<Class<? extends Quest>> quests = new HashSet<>();

    public static CoreData getCoreData(Player player) { return coreDatas.get(player); }

    public static void registerQuest(Class<? extends Quest> quest) { quests.add(quest); }

    public static Set<Class<? extends Quest>> getQuests() { return quests; }
}
