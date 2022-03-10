package Core;

import Quest.Quest;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;

public class CoreData {

    private final Player player;
    private final Set<Quest> quests = new HashSet<>();

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

    public Set<Quest> getQuests() { return quests; }

}
