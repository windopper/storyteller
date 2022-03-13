package com.kamilereon.storyteller.main;

import com.kamilereon.storyteller.commands.MainCommands;
import com.kamilereon.storyteller.controller.PlayerListener;
import com.kamilereon.storyteller.events.InteractListener;
import com.kamilereon.storyteller.quest.ExampleQuest;
import com.kamilereon.storyteller.quest.ExampleQuest2;
import com.kamilereon.storyteller.schedulers.CoreScheduler;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class StoryTellerMain extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerListener(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new InteractListener(), this);

        init();
    }

    @Override
    public void onDisable() {

        fin();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        String commandName = command.getName();

        MainCommands.Listener(player, commandName, args);

        return true;
    }

    public void init() {

        Initializer initializer = new Initializer();
        initializer.registerCoreData();

        StoryTeller.registerQuest(ExampleQuest.class, ExampleQuest2.class);

//        initializer.loadQuest();

        for(Player player : Bukkit.getOnlinePlayers()) {
            initializer.initAllQuest(player);
        }

        CoreScheduler coreScheduler = new CoreScheduler();
        coreScheduler.initialize();
    }

    public void fin() {

    }
}
