package com.kamilereon.storyteller.controller;

import com.kamilereon.storyteller.annotations.StageSequence;
import com.kamilereon.storyteller.annotations.StartSequence;
import com.kamilereon.storyteller.quest.StoryTellerQuest;
import com.kamilereon.storyteller.utils.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.Optional;

class StageHelper {
    public static Optional<StageSequence> getEndStageSequence(StoryTellerQuest storyTellerQuest) {
        // 최종 스테이지 단계와 스테이지의 최종 진행 단계를 확인
        Method[] methods = storyTellerQuest.getClass().getDeclaredMethods();

        return ReflectionUtils.getMethodsWhichAnnotationHas(StageSequence.class, methods)
                .stream()
                .map(m -> m.getAnnotation(StageSequence.class))
                .reduce((s1, s2) -> {
                    int s1_stage = s1.stage();
                    int s2_stage = s2.stage();
                    return Math.max(s1_stage, s2_stage) == s1_stage ? s1 : s2;
                });
    }

    public static boolean isCorrectSequence(int currentStage, int currentDetailProgress, Method method) {
        StageSequence stageSequence = method.getAnnotation(StageSequence.class);
        StartSequence startSequence = method.getAnnotation(StartSequence.class);
        if(stageSequence == null && startSequence == null) return false;

        if(currentStage == 0) {
            // 아직 퀘스트가 시작되지 않았을 때 메서드에 @StartSequence 어노테이션이 존재한다면 true
            return startSequence != null;
        }
        else if(currentStage >= 1) {
            if(stageSequence != null) {
                // 퀘스트가 시작 되었을때 @StageSequence 어노테이션의 stage 가 현재 스테이지와 같다면 true
                return stageSequence.stage() == currentStage;
            }
        }
        return false;
    }

    public static int getFinalProgress(Method method) {
        StageSequence stageSequence = method.getAnnotation(StageSequence.class);
        StartSequence startSequence = method.getAnnotation(StartSequence.class);
        if(startSequence != null) {
            return startSequence.finalProgress();
        }
        else if(stageSequence != null) return stageSequence.finalProgress();
        return 0;
    }
}
