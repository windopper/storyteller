package com.kamilereon.storyteller.controller;

import com.kamilereon.storyteller.configuration.FilterMode;
import com.kamilereon.storyteller.configuration.Filters;
import com.kamilereon.storyteller.configuration.QuestFilter;
import com.kamilereon.storyteller.core.CoreData;
import com.kamilereon.storyteller.core.Memory;
import com.kamilereon.storyteller.main.StoryTeller;
import com.kamilereon.storyteller.core.StoryTellerQuest;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.lang.reflect.Method;
import java.util.Set;

public class PlayerWatcher {
    public static void tracePlayer() {
        for(Player player : Bukkit.getOnlinePlayers()) {
            CoreData coreData = Memory.getCoreData(player);

            Set<? extends StoryTellerQuest> validQuests = StoryTeller.getQuestsFromPlayer(player, new QuestFilter()
                    .setFilters(Filters.CAN_START, Filters.NOT_FINISHED)
                    .setMode(FilterMode.AND));

            // 퀘스트 진행을 하기 위한 조건 탐색
            for(StoryTellerQuest storyTellerQuest : validQuests) {

                int stage = storyTellerQuest.stage;
                int detailProgress = storyTellerQuest.progress;

                Method[] methods = storyTellerQuest.getClass().getMethods();

                QuestCaller.callFinalSequence(storyTellerQuest);
                for(Method method : methods) {
                    method.setAccessible(true);
                    if(!StageHelper.isSequenceCorrect(stage, method)) continue;
                    StageCaller.callStageWhenEnterAreaRadius(player, storyTellerQuest, method);
                    StageCaller.callStageWhenEnterAreaRectangle(player, storyTellerQuest, method);
                    StageCaller.callStageWhenConditionSatisfied(player, storyTellerQuest, method);
                    StageCaller.callStageAfterTick(player, storyTellerQuest, method);
                    method.setAccessible(false);
                }
            }
        }
    }
}
