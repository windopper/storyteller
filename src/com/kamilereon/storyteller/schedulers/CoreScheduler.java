package com.kamilereon.storyteller.schedulers;

import com.kamilereon.storyteller.controller.PlayerWatcher;
import com.kamilereon.storyteller.file.ConfigFile;
import com.kamilereon.storyteller.main.StoryTellerMain;
import org.bukkit.Bukkit;

public class CoreScheduler {
    public void initialize() {
        Bukkit.getScheduler().runTaskTimer(StoryTellerMain.getPlugin(StoryTellerMain.class), PlayerWatcher::tracePlayer
        , 0, 5);
    }
}
