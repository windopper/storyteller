package com.kamilereon.storyteller.file;

import com.kamilereon.storyteller.main.StoryTellerMain;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class ConfigFile {

    private static final ConfigFile instance = new ConfigFile();
    StoryTellerMain main = StoryTellerMain.getPlugin(StoryTellerMain.class);
    private final java.io.File file = new java.io.File(main.getDataFolder(), "config.yml");
    FileConfiguration config = main.getConfig();

    private ConfigFile() {}

    public synchronized static ConfigFile getInstance() { return instance; }

    public void init() {
        if(file.exists()) return;

//        config.addDefault("loopDelay", 5);
        config.set("loopDelay", 5);

        main.saveConfig();

        try {
            config.save(file);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public int getLoopDelay() {
        return config.getInt("loopDelay");
    }
}
