package Core;

import Quest.StoryTellerQuest;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Memory {

    static final ConcurrentHashMap<Player, CoreData> coreDatas = new ConcurrentHashMap<>();
    static final Set<Class<? extends StoryTellerQuest>> quests = new HashSet<>();

    public static CoreData getCoreData(Player player) { return coreDatas.get(player); }

    public static void registerQuest(Class<? extends StoryTellerQuest> quest) { quests.add(quest); }

    public static Set<Class<? extends StoryTellerQuest>> getQuests() { return quests; }
}
