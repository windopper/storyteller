package com.kamilereon.storyteller.schedulers;

import com.kamilereon.storyteller.controller.PlayerWatcher;
import com.kamilereon.storyteller.main.StoryTeller;
import org.bukkit.Bukkit;

public class CoreScheduler {
    public void initialize() {
        Bukkit.getScheduler().scheduleSyncDelayedTask(StoryTeller.getPlugin(StoryTeller.class), () -> {
            PlayerWatcher.tracePlayer();
        }, 1);
    }
}
