package Scheduler;

import Controller.PlayerWatcher;
import Main.StoryTeller;
import org.bukkit.Bukkit;

public class CoreScheduler {
    public void initialize() {
        Bukkit.getScheduler().scheduleSyncDelayedTask(StoryTeller.getPlugin(StoryTeller.class), () -> {
            PlayerWatcher.tracePlayer();
        }, 1);
    }
}
