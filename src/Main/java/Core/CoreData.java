package Core;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CoreData {

    private final Player player;
    private List<Class<?>> questList = new ArrayList<>();

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

}
