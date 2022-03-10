package Main;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class StoryTeller extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        Bukkit.getServer().getPluginManager().registerEvents(new Events.Listener(), this);
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
        return super.onCommand(sender, command, label, args);
    }

    public void init() {
        Initialize initialize = new Initialize();
        initialize.registerCoreData();
    }

    public void fin() {
        Finalize finalize = new Finalize();
    }
}
