package com.kamilereon.storyteller.main;

import com.kamilereon.storyteller.commands.MainCommands;
import com.kamilereon.storyteller.events.InteractListener;
import com.kamilereon.storyteller.schedulers.CoreScheduler;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class StoryTeller extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        Bukkit.getServer().getPluginManager().registerEvents(new com.kamilereon.storyteller.events.Listener(), this);
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

        initializer.loadQuest();

        for(Player player : Bukkit.getOnlinePlayers()) {
            initializer.initAllQuest(player);
        }

        CoreScheduler coreScheduler = new CoreScheduler();
        coreScheduler.initialize();
    }

    public void fin() {
        Finalizer finalize = new Finalizer();
    }
}
