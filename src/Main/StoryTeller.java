package Main;

import Events.InteractListener;
import Scheduler.CoreScheduler;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class StoryTeller extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        Bukkit.getServer().getPluginManager().registerEvents(new Events.Listener(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new InteractListener(), this);
        super.onEnable();
        init();
    }

    @Override
    public void onDisable() {
        super.onDisable();
        fin();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        String commandName = command.getName();

        return super.onCommand(sender, command, label, args);
    }

    public void init() {

        Initialize initialize = new Initialize();
        initialize.registerCoreData();

        initialize.loadQuest();

        CoreScheduler coreScheduler = new CoreScheduler();
        coreScheduler.initialize();
    }

    public void fin() {
        Finalize finalize = new Finalize();
    }
}
