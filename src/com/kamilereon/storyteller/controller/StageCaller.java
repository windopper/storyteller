package com.kamilereon.storyteller.controller;

import com.kamilereon.storyteller.annotations.*;
import com.kamilereon.storyteller.core.CoreData;
import com.kamilereon.storyteller.core.Memory;
import com.kamilereon.storyteller.quest.StoryTellerQuest;
import com.kamilereon.storyteller.schedulers.Schedule;
import com.kamilereon.storyteller.utils.LocationUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.lang.reflect.Method;

public class StageCaller {
    public static void callStageWhenEnterAreaRadius(Player player, StoryTellerQuest storyTellerQuest, Method method) {

        int stage = storyTellerQuest.stage;
        int detailProgress = storyTellerQuest.detailProgress;

        int finalProgress = StageHelper.getFinalProgress(method);

        // CallStageWhenEnterAreaRadius 가 메서드에 선언되었다면 가져오기
        CallStageWhenEnterAreaRadius[] l_cs = method.getAnnotationsByType(CallStageWhenEnterAreaRadius.class);
        // 메서드에서 가져온 어노테이션 순환
        for(CallStageWhenEnterAreaRadius l_c : l_cs) {

            int[] targetDetailProgress = l_c.progressToCall();
            String world = l_c.worldName();
            double radius = l_c.radius();
            double x = l_c.x();
            double y = l_c.y();
            double z = l_c.z();
            Location location = new Location(Bukkit.getWorld(world), x, y, z);

            if(LocationUtils.isPlayerInArea(player, location, radius)) {
                StageHelper.invokeMethodIfProgressValid(method, storyTellerQuest, targetDetailProgress, detailProgress);
            }
        }
    }

    public static void callStageWhenEnterAreaRectangle(Player player, StoryTellerQuest storyTellerQuest, Method method) {

        int stage = storyTellerQuest.stage;
        int detailProgress = storyTellerQuest.detailProgress;

        int finalProgress = StageHelper.getFinalProgress(method);

        CallStageWhenEnterAreaRectangle[] l_cs = method.getAnnotationsByType(CallStageWhenEnterAreaRectangle.class);

        for(CallStageWhenEnterAreaRectangle l_c : l_cs) {

            int[] targetDetailProgress = l_c.progressToCall();
            String world = l_c.worldName();
            double x = l_c.x();
            double y = l_c.y();
            double z = l_c.z();
            double dx = l_c.dx();
            double dy = l_c.dy();
            double dz = l_c.dz();

            Location location = new Location(Bukkit.getWorld(world), x, y, z);
            Vector vector = new Vector(dx, dy, dz);

            if(LocationUtils.isPlayerInArea(player, location, vector)) {
                StageHelper.invokeMethodIfProgressValid(method, storyTellerQuest, targetDetailProgress, detailProgress);
            }
        }
    }

    //TODO NEED TEST
    public static void callStageWhenConditionSatisfied(Player player, StoryTellerQuest storyTellerQuest, Method method) {
        int stage = storyTellerQuest.stage;
        int detailProgress = storyTellerQuest.detailProgress;

        int finalProgress = StageHelper.getFinalProgress(method);

        CallStageWhenConditionSatisfied[] l_cs = method.getAnnotationsByType(CallStageWhenConditionSatisfied.class);

        for(CallStageWhenConditionSatisfied l_c : l_cs) {
            int[] targetDetailProgress = l_c.progressToCall();
            String name = l_c.targetMethodName();
            try {
                Method m = storyTellerQuest.getClass().getMethod(name);
                boolean value = (boolean) m.invoke(storyTellerQuest);
                if(value) {
                    StageHelper.invokeMethodIfProgressValid(method, storyTellerQuest, targetDetailProgress, detailProgress);
                }
            }
            catch(Exception e) {

            }
        }
    }

    //TODO NEED TEST
    public static void callStageWhenRightClickEntity(Player player, StoryTellerQuest storyTellerQuest, Method method, String entityName) {

        int stage = storyTellerQuest.stage;
        int detailProgress = storyTellerQuest.detailProgress;

        int finalProgress = StageHelper.getFinalProgress(method);

        CallStageWhenRightClickEntity[] l_cs = method.getAnnotationsByType(CallStageWhenRightClickEntity.class);

        for(CallStageWhenRightClickEntity l_c : l_cs) {

            int[] targetDetailProgress = l_c.progressToCall();
            String name = l_c.entityName();
            if(!name.equals(entityName)) continue;

            StageHelper.invokeMethodIfProgressValid(method, storyTellerQuest, targetDetailProgress, detailProgress);
        }
    }

    //TODO NEED TEST
    public static void callStageAfterTick(Player player, StoryTellerQuest storyTellerQuest, Method method) {
        int stage = storyTellerQuest.stage;
        int detailProgress = storyTellerQuest.detailProgress;
        CoreData coreData = Memory.getCoreData(player);

        int finalProgress = StageHelper.getFinalProgress(method);

        CallStageAfterTick[] l_cs = method.getAnnotationsByType(CallStageAfterTick.class);
        for(CallStageAfterTick l_c : l_cs) {
            int[] targetDetailProgress = l_c.progressToCall();
            int tickWait = l_c.tick();

            for(int i : targetDetailProgress) {

                if(detailProgress != i && i != 0) continue;

                Schedule schedule = coreData.getSchedule(l_c);
                if(schedule == null) {
                    // create schedule from coreData
                    schedule = coreData.addSchedule(l_c);
                }

                int currentTick = schedule.getTick();
                if(currentTick > tickWait) {
                    try {
                        // 메서드 실행
                        method.invoke(storyTellerQuest);

                        if(finalProgress <= detailProgress) {
                            storyTellerQuest.detailProgress = 1;
                            storyTellerQuest.stage++;
                        }
                        else storyTellerQuest.detailProgress ++;
                        return;
                    }
                    catch(Exception e) {
                        e.printStackTrace();
                    }
                    finally {
                        coreData.removeSchedule(l_c);
                    }
                }
            }
        }
    }
}
