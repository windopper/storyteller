package com.kamilereon.storyteller.controller;

import com.kamilereon.storyteller.annotations.CallStageWhenRightClickEntity;
import com.kamilereon.storyteller.configuration.FilterMode;
import com.kamilereon.storyteller.configuration.Filters;
import com.kamilereon.storyteller.configuration.QuestFilter;
import com.kamilereon.storyteller.core.CoreData;
import com.kamilereon.storyteller.main.Initializer;
import com.kamilereon.storyteller.main.StoryTeller;
import com.kamilereon.storyteller.core.StoryTellerQuest;
import com.kamilereon.storyteller.utils.ReflectionUtils;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.lang.reflect.Method;
import java.util.Set;

public class PlayerListener implements org.bukkit.event.Listener {

    @EventHandler
    public void PlayerQuitEvent(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        CoreData.getOrCreateCoreData(player).remove();
    }

    @EventHandler
    public void PlayerJoinEvent(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        CoreData.getOrCreateCoreData(player);
        Initializer initializer = new Initializer();
        initializer.initAllQuest(player);
    }

    @EventHandler
    public void PlayerInteractAtEntityEvent(PlayerInteractAtEntityEvent event) {
        Entity entity = event.getRightClicked();
        Player player = event.getPlayer();
        String name = entity.getCustomName();
        if(name == null) return;

        for(StoryTellerQuest quest : StoryTeller.getQuestsFromPlayer(player, new QuestFilter().setFilters(Filters.CAN_START, Filters.NOT_FINISHED).setMode(FilterMode.AND))) {
            Method[] methods = quest.getClass().getDeclaredMethods();
            Set<Method> s = ReflectionUtils.getMethodsWhichAnnotationHas(CallStageWhenRightClickEntity.class, methods);
            for(Method m : s) {
                StageCaller.callStageWhenRightClickEntity(player, quest, m, name);
            }
        }
    }
}
