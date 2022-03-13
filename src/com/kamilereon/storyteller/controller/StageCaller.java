package com.kamilereon.storyteller.controller;

import com.kamilereon.storyteller.annotations.CallStageWhenEnterAreaRadius;
import com.kamilereon.storyteller.annotations.CallStageWhenEnterAreaRectangle;
import com.kamilereon.storyteller.annotations.StageSequence;
import com.kamilereon.storyteller.quest.StoryTellerQuest;
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

        if(!StageHelper.isCorrectSequence(stage, detailProgress, method)) return;

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
                for(int i : targetDetailProgress) {
                    // detailProgress 가 어노테이션이 가리키는 targetProgress 와 다르고 targetProgress 가 0이면 continue
                    // targetProgress 가 0이라는 뜻은 어떤 detailProgress 에서도 실행된다는 뜻
                    if(detailProgress != i && i != 0) continue;
                    try {
                        // 메서드 실행
                        method.invoke(storyTellerQuest);
                        // finalProgress 가 detailProgress 보다 작거나 같으면
                        if(finalProgress <= detailProgress) {
                            // 다음 stage 로 업데이트 후 detailProgress 를 초기화
                            storyTellerQuest.detailProgress = 1;
                            storyTellerQuest.stage++;
                        }
                        // 아직 마지막 스테이지에 도달하지 않았다면 detailProgress 를 업데이트
                        else storyTellerQuest.detailProgress ++;
                        return;
                    }
                    catch(Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void callStageWhenEnterAreaRectangle(Player player, StoryTellerQuest storyTellerQuest, Method method) {

        int stage = storyTellerQuest.stage;
        int detailProgress = storyTellerQuest.detailProgress;

        if(!StageHelper.isCorrectSequence(stage, detailProgress, method)) return;

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
                for(int i : targetDetailProgress) {
                    // detailProgress 가 어노테이션이 가리키는 targetProgress 와 다르고 targetProgress 가 0이면 continue
                    // targetProgress 가 0이라는 뜻은 어떤 detailProgress 에서도 실행된다는 뜻
                    if(detailProgress != i && i != 0) continue;
                    try {
                        // 메서드 실행
                        method.invoke(storyTellerQuest);
                        // finalProgress 가 detailProgress 보다 작거나 같으면
                        if(finalProgress <= detailProgress) {
                            // 다음 stage 로 업데이트 후 detailProgress 를 초기화
                            storyTellerQuest.detailProgress = 1;
                            storyTellerQuest.stage++;
                        }
                        // 아직 마지막 스테이지에 도달하지 않았다면 detailProgress 를 업데이트
                        else storyTellerQuest.detailProgress ++;
                        return;
                    }
                    catch(Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
