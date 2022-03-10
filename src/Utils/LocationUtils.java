package Utils;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.Vector;

public class LocationUtils {
    public static boolean isPlayerInArea(Player player, Location location, double radius) {
        return player.getLocation().distance(location) <= radius;
    }
    public static boolean isPlayerInArea(Player player, Location location1, Vector delta) {
        BoundingBox box = BoundingBox.of(location1, location1.clone().add(delta));
        return box.contains(player.getBoundingBox());
    }
}
