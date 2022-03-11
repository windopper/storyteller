package com.kamilereon.storyteller.main;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Main {
    public static void main(String[] args) {
        System.out.println("hi");
        Player player = Bukkit.getPlayer("hi");
        Initializer initializer = new Initializer();
        initializer.loadQuest();
        initializer.initAllQuest(player);
    }
}
