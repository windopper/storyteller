package Quest;

import org.bukkit.entity.Player;

public abstract class Quest {

    /*
    progress = 0 || NOT STARTED
    progress = -1 || QUEST FINISHED

    detailProgress == 0 || NOT STARTED
     */
    public int progress = 0;
    public int detailProgress = 0;
    public Player player;

    public Quest(Player player) {
        this.player = player;
    }

}
