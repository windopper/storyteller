package com.kamilereon.storyteller.commands;

import com.kamilereon.storyteller.core.Memory;
import com.kamilereon.storyteller.utils.StoryTellerUtils;
import org.bukkit.entity.Player;

import java.util.Optional;

public class MainCommands {
    public static void Listener(Player sender, String commandName, String[] args) {
        if(commandName.equals("story")) {
            if(args.length == 1) {
                if(args[0].equals("save")) {
                    Optional<String> json = StoryTellerUtils.getQuestDatasByJson(sender);
                    sender.sendMessage(json.orElse("NULL 발생"));
                }
                else if(args[0].equals("questlist")) {
                    Memory.getQuests().forEach(q -> {
                        sender.sendMessage(q.getName());
                    });
                }
            }
        }
    }
}
